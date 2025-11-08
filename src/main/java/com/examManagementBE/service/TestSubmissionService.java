package com.examManagementBE.service;

import com.examManagementBE.entity.assessment.*;
import com.examManagementBE.entity.user.Student;
import com.examManagementBE.exception.AppException;
import com.examManagementBE.exception.ErrorCode;
import com.examManagementBE.pojo.request.submission.StudentAnswerRequest;
import com.examManagementBE.pojo.request.submission.SubmissionRequest;
import com.examManagementBE.pojo.response.assessment.TestAttemptHistoryDetailResponse;
import com.examManagementBE.repository.StudentRepository;
import com.examManagementBE.repository.assessment.*;
import com.examManagementBE.service.assessment.TestAttemptService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class TestSubmissionService {

    private final StudentRepository studentRepository;
    private final TestRepository testRepository;
    private final StudentTestAttemptRepository studentTestAttemptRepository;
    private final StudentAnswerLogRepository studentAnswerLogRepository;
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final TestQuestionRepository testQuestionRepository;
    private final TestAttemptService testAttemptService;

    public Boolean submitTest(SubmissionRequest request) {
        Student student = studentRepository.findById(request.getStudentId()).orElseThrow(() -> new RuntimeException("Student not found"));
        Test test = testRepository.findById(request.getTestId()).orElseThrow(() -> new RuntimeException("Test not found"));

        StudentTestAttempt studentTestAttempt = studentTestAttemptRepository.findByStudentAndTest(student, test);
        if(ObjectUtils.isNotEmpty(studentTestAttempt)){ // auto-save progress
            AtomicInteger score = new AtomicInteger();

            List<StudentAnswerRequest> answerList = request.getStudentAnswerRequests();
            answerList.forEach(userChoice -> {
                Question question = questionRepository.findById(userChoice.getQuestionId()).orElseThrow(() -> new RuntimeException("Question not found"));
                Answer answer = answerRepository.findByQuestionAndAnswerText(question, userChoice.getAnswer());
                if(ObjectUtils.isNotEmpty(answer) && answer.getCorrectAnswer()){
                    score.addAndGet(question.getScore());
                }
                StudentAnswerLog studentAnswerLog = studentAnswerLogRepository.findByStudentAndTestAndQuestion(student, test, question);
                if(ObjectUtils.isNotEmpty(studentAnswerLog)){ // user has choice another answer before and now change it
                    studentAnswerLog.setSelectedAnswer(answer);
                    studentAnswerLogRepository.save(studentAnswerLog);
                } else { // new choice
                    StudentAnswerLogId logId = new StudentAnswerLogId(student.getUser_ID(), question.getQuestionId(), test.getTestId());
                    StudentAnswerLog newStudentAnswerLog = StudentAnswerLog.builder().id(logId).student(student).test(test).question(question).selectedAnswer(answer).build();
                    studentAnswerLogRepository.save(newStudentAnswerLog);
                }
            });

            studentTestAttempt.setScore(score.get());
            studentTestAttempt.setSubmitTime(OffsetDateTime.now());
            studentTestAttempt.setActualTime(LocalTime.now());
            studentTestAttemptRepository.save(studentTestAttempt);
        } else{ // create new submission session
            AtomicInteger score = new AtomicInteger();

            List<StudentAnswerRequest> answerList = request.getStudentAnswerRequests();
            answerList.forEach(userChoice -> {
                Question question = questionRepository.findById(userChoice.getQuestionId()).orElseThrow(() -> new RuntimeException("Question not found"));
                Answer answer = answerRepository.findByQuestionAndAnswerText(question, userChoice.getAnswer());
                if(ObjectUtils.isNotEmpty(answer) && answer.getCorrectAnswer()){
                    score.addAndGet(question.getScore());
                }
                StudentAnswerLogId logId = new StudentAnswerLogId(student.getUser_ID(), question.getQuestionId(), test.getTestId());
                StudentAnswerLog newStudentAnswerLog = StudentAnswerLog.builder().id(logId).student(student).test(test).question(question).selectedAnswer(answer).build();
                studentAnswerLogRepository.save(newStudentAnswerLog);
            });

            StudentTestAttemptId key = new StudentTestAttemptId(student.getUser_ID(), test.getTestId());
            StudentTestAttempt newStudentTestAttempt = StudentTestAttempt.builder().id(key).student(student).test(test).score(score.get()).startTime(OffsetDateTime.now()).actualTime(LocalTime.now()).submitTime(OffsetDateTime.now()).build();
            studentTestAttemptRepository.save(newStudentTestAttempt);
            test.setSubmissions(test.getSubmissions() + 1);
            testRepository.save(test);
        }

        return true;
    }

    public TestAttemptHistoryDetailResponse getCurrentProgress(StudentTestAttemptId studentTestAttemptId) {
        TestAttemptHistoryDetailResponse response = testAttemptService.getDetailTestAttemptHistoryOfUser(studentTestAttemptId);
        response.setTotalCorrectQuestions(null);
        response.setScore(null);

        if (response.getQuestionResultDetails() != null) {
            response.getQuestionResultDetails().forEach(q -> {
                q.setScore(null);
                if (q.getAnswers() != null) {
                    q.getAnswers().forEach(a -> a.setIsCorrect(null));
                }
            });
        }

        return response;
    }
}
