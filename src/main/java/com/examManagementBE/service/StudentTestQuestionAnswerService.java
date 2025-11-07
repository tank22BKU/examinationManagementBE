package com.examManagementBE.service;

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

@Service
@RequiredArgsConstructor
public class StudentTestQuestionAnswerService {

    private final StudentTestQuestionAnswerRepository attemptRepository;
    @Transactional(readOnly = true)
    public List<StudentTestAttemptDTO> getAttemptsByStudentId(Integer studentId) {
        // Lấy danh sách attempt kèm test, testQuestions, question (EntityGraph)
        List<StudentTestAttempt> attempts = attemptRepository.findByStudentId(studentId);

        return attempts.stream().map(attempt -> {
            StudentTestAttemptDTO dto = new StudentTestAttemptDTO();
            dto.setStudentId(attempt.getStudent().getUser_ID());
         //   dto.setStartTime(attempt.getStartTime());
         //   dto.setSubmitTime(attempt.getSubmitTime());

            Test test = attempt.getTest();
            if (test != null) {
                TestDTO testDTO = new TestDTO();
                testDTO.setTestId(test.getTestId());
                testDTO.setTestName(test.getTitle());

                // Lấy danh sách câu hỏi qua testQuestions
                List<QuestionDTO> questions = test.getTestQuestions()
                        .stream()
                        .map(tq -> {
                            Question question = tq.getQuestion();
                            QuestionDTO qdto = new QuestionDTO();
                            qdto.setQuestionId(question.getQuestionId());
                            qdto.setQuestionText(question.getQuestionText());

                            // Map danh sách answers của question
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

                            // Nếu muốn lấy student answer: check từ attempt
                            attempt.getStudent().getStudentQuestions()
                                    .stream()
                                    .filter(sq -> sq.getQuestion().getQuestionId().equals(question.getQuestionId()))
                                    .findFirst()
                                    .ifPresent(sq -> qdto.setStudentAnswerText(sq.getStudentAnswerText()));

                            return qdto;
                        }).toList();

                testDTO.setQuestions(questions);
                dto.setTest(testDTO);
            }

            return dto;
        }).toList();
    }
}
