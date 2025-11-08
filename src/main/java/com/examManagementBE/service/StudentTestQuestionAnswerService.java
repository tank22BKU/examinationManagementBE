package com.examManagementBE.service;//package com.examManagementBE.service;

import com.examManagementBE.dto.AnswerDTO;
import com.examManagementBE.dto.QuestionDTO;
import com.examManagementBE.dto.StudentTestAttemptDTO;
import com.examManagementBE.dto.TestDTO;
import com.examManagementBE.entity.assessment.Question;
import com.examManagementBE.entity.assessment.StudentTestAttempt;
import com.examManagementBE.entity.assessment.Test;
import com.examManagementBE.repository.StudentTestQuestionAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class StudentTestQuestionAnswerService {

    private final StudentTestQuestionAnswerRepository attemptRepository;

    @Transactional(readOnly = true)
    public List<StudentTestAttemptDTO> getAttemptsByStudentId(Integer studentId) {
        List<StudentTestAttempt> attempts = attemptRepository.findByStudentId(studentId);

        return attempts.stream().map(attempt -> {
            StudentTestAttemptDTO dto = new StudentTestAttemptDTO();
            dto.setStudentId(attempt.getStudent().getUser_ID());

            Test test = attempt.getTest();
            if (test != null) {
                TestDTO testDTO = new TestDTO();
                testDTO.setTestId(test.getTestId());
                testDTO.setTestName(test.getTitle());

                AtomicReference<Double> totalScore = new AtomicReference<>(0.0);

                List<QuestionDTO> questions = test.getTestQuestions().stream().map(tq -> {
                    Question question = tq.getQuestion();
                    QuestionDTO qdto = new QuestionDTO();
                    qdto.setQuestionId(question.getQuestionId());
                    qdto.setQuestionText(question.getQuestionText());

                    // Map danh sách answers
                    if (question.getAnswers() != null) {
                        List<AnswerDTO> answers = question.getAnswers().stream().map(a -> {
                            AnswerDTO adto = new AnswerDTO();
                            adto.setAnswerId(a.getAnswerId());
                            adto.setAnswerText(a.getAnswerText());
                            adto.setCorrect(a.getCorrectAnswer());
                            return adto;
                        }).toList();
                        qdto.setAnswers(answers);
                    }

                    // Lấy câu trả lời của học sinh
                    attempt.getStudent().getStudentQuestions().stream()
                            .filter(sq -> sq.getQuestion().getQuestionId().equals(question.getQuestionId()))
                            .findFirst()
                            .ifPresent(sq -> {
                                qdto.setStudentAnswerText(sq.getStudentAnswerText());

                                // Kiểm tra đúng/sai
                                boolean isCorrect = question.getAnswers().stream()
                                        .anyMatch(a ->
                                                Boolean.TRUE.equals(a.getCorrectAnswer()) &&
                                                        a.getAnswerText().equalsIgnoreCase(sq.getStudentAnswerText())
                                        );

                                if (isCorrect) {
                                    double score = question.getScore() != null ? question.getScore() : 0.0;
                                    totalScore.updateAndGet(v -> v + score);
                                }
                            });

                    return qdto;
                }).toList();

                testDTO.setQuestions(questions);
                dto.setTest(testDTO);
                dto.setTotalScore(totalScore.get());
            }

            return dto;
        }).toList();
    }
}






