package com.examManagementBE.service.assessment;

import com.examManagementBE.entity.assessment.*;
import com.examManagementBE.entity.user.Student;
import com.examManagementBE.exception.AppException;
import com.examManagementBE.exception.ErrorCode;
import com.examManagementBE.mapper.AnswerMapper;
import com.examManagementBE.pojo.response.assessment.QuestionResult.AnswerResultResponse;
import com.examManagementBE.pojo.response.assessment.QuestionResult.QuestionResult;
import com.examManagementBE.pojo.response.assessment.TestAttemptHistoryDetailResponse;
import com.examManagementBE.pojo.response.assessment.TestAttemptHistoryResponse;
import com.examManagementBE.repository.StudentRepository;
import com.examManagementBE.repository.UserRepository;
import com.examManagementBE.repository.assessment.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class TestAttemptService {

    private final TestRepository testRepository;
    private final StudentTestAttemptRepository studentTestAttemptRepository;
    private final StudentAnswerLogRepository studentAnswerLogRepository;
    private final TestQuestionRepository testQuestionRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final StudentRepository studentRepository;
    private final AnswerMapper answerMapper;
    private static final Logger logger = LoggerFactory.getLogger(TestAttemptService.class);

    public List<TestAttemptHistoryResponse> getAllTestAttemptHistoryOverviewOfUser(int studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        List<StudentTestAttempt> attempts = studentTestAttemptRepository.findAllByStudent(student);
        if (ObjectUtils.isEmpty(attempts)) {
            return new ArrayList<>();
        }
        List<TestAttemptHistoryResponse> result = new ArrayList<>();

        attempts.forEach(attempt -> {
            TestAttemptHistoryDetailResponse testAttemptDetail = getDetailTestAttemptHistoryOfUser(attempt.getId());
            Test test = testRepository.findById(attempt.getId().getTestId()).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
            TestAttemptHistoryResponse temp = new TestAttemptHistoryResponse(student.getFullName(), studentId, attempt.getId(), attempt.getId().getTestId(), testAttemptDetail.getTestName(), testAttemptDetail.getTotalTimes(), testAttemptDetail.getTotalQuestions(), testAttemptDetail.getScore(), testAttemptDetail.getMaxScore(), test.getReleasedAnswer(), test.getReleasedScore());
            result.add(temp);
        });
        return result;
    }

    public TestAttemptHistoryDetailResponse getDetailTestAttemptHistoryOfUser(StudentTestAttemptId studentTestAttemptId) {
        Student student = studentRepository.findById(studentTestAttemptId.getStudentUserId()).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        Test test = testRepository.findById(studentTestAttemptId.getTestId()).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        StudentTestAttempt studentTestAttempt = studentTestAttemptRepository.findByStudentAndTest(student, test).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        List<Integer> listQuestionId = testQuestionRepository.findAllByTestId(studentTestAttemptId.getTestId()).stream().map(TestQuestion::getQuestionId).toList();

        Duration duration = Duration.between(studentTestAttempt.getStartTime(), studentTestAttempt.getSubmitTime());
        String totalTimes = "%d hours %d minutes %d seconds".formatted(
                duration.toHours(),
                duration.toMinutesPart(),
                duration.toSecondsPart()
        );
        TestAttemptHistoryDetailResponse response = new TestAttemptHistoryDetailResponse(test.getTitle(), studentTestAttempt.getStartTime(), totalTimes, listQuestionId.size(), 0, 0, 0, new ArrayList<>());

        AtomicInteger score = new AtomicInteger(0);
        AtomicInteger maxScore = new AtomicInteger(0);
        AtomicInteger numberOfCorrectAnswer = new AtomicInteger(0);
        List<QuestionResult> questionResultList = new ArrayList<>();

        listQuestionId.forEach(questionId -> {
            Question question = questionRepository.findById(questionId).get();
            List<AnswerResultResponse> questionAnswer = answerRepository.findAllByQuestion(question).stream().map(answerMapper::toAnswerResultResponse).toList();
            List<AnswerResultResponse> answerList = studentAnswerLogRepository.findByStudentAndQuestion(student, question).stream().map(StudentAnswerLog::getSelectedAnswer).map(answerMapper::toAnswerResultResponse).toList();

            int questionMaxPoint = question.getScore();
            maxScore.addAndGet(questionMaxPoint);
            int currentQuestionScore = 0;

            // Multiple Choice Value
            if (answerList.size() > 1) {
                AtomicInteger numberOfCorrectSelection = new AtomicInteger();

                answerList.forEach(studentAnswer -> {
                    if (ObjectUtils.isNotEmpty(studentAnswer) && studentAnswer.getIsCorrect()) {
                        numberOfCorrectSelection.getAndIncrement();
                        numberOfCorrectAnswer.getAndIncrement();
                    }
                });

                int questionScore = ((2 * numberOfCorrectSelection.get() - answerList.size()) / answerList.size()) * question.getScore();
                if (questionScore > 0) {
                    score.addAndGet(questionScore);
                    currentQuestionScore = questionScore;
                }

            } else {
                if (ObjectUtils.isNotEmpty(answerList) && ObjectUtils.isNotEmpty(answerList.getFirst())) {
                    if (answerList.getFirst().getIsCorrect()) {
                        int currentScore = question.getScore();
                        currentQuestionScore = currentScore;
                        score.addAndGet(currentScore);
                        numberOfCorrectAnswer.getAndIncrement();
                    }
                }
            }
            QuestionResult questionResult = new QuestionResult(questionId, question.getQuestionText(), currentQuestionScore, questionMaxPoint, questionAnswer, answerList);
            questionResultList.add(questionResult);
        });

        response.setScore(score.get());
        response.setMaxScore(maxScore.get());
        response.setTotalCorrectQuestions(numberOfCorrectAnswer.get());
        response.setQuestionResultDetails(questionResultList);

        return response;
    }

    public List<TestAttemptHistoryResponse> getAllTestAttemptHistoryOverviewOfTest(int testId) {
        Test mainTest = testRepository.findById(testId).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        List<StudentTestAttempt> attempts = studentTestAttemptRepository.findAllByTest(mainTest);
        if (ObjectUtils.isEmpty(attempts)) {
            return new ArrayList<>();
        }
        List<TestAttemptHistoryResponse> result = new ArrayList<>();

        attempts.forEach(attempt -> {
            TestAttemptHistoryDetailResponse testAttemptDetail = getDetailTestAttemptHistoryOfUser(attempt.getId());
            Student student = studentRepository.findById(attempt.getId().getStudentUserId()).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
            TestAttemptHistoryResponse temp = new TestAttemptHistoryResponse(student.getFullName(), student.getUser_ID(), attempt.getId(), attempt.getId().getTestId(), testAttemptDetail.getTestName(), testAttemptDetail.getTotalTimes(), testAttemptDetail.getTotalQuestions(), testAttemptDetail.getScore(), testAttemptDetail.getMaxScore(), mainTest.getReleasedAnswer(), mainTest.getReleasedScore());
            result.add(temp);
        });
        return result;
    }
}
