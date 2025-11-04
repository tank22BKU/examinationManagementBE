package com.examManagementBE.service.assessment;

import com.examManagementBE.entity.assessment.Question;
import com.examManagementBE.entity.assessment.Test;
import com.examManagementBE.entity.assessment.TestQuestion;
import com.examManagementBE.entity.assessment.Answer;
import com.examManagementBE.entity.student.StudentAnswerLog;
import com.examManagementBE.repository.assessment.QuestionRepository;
import com.examManagementBE.repository.assessment.TestQuestionRepository;
import com.examManagementBE.repository.assessment.AnswerRepository;
import com.examManagementBE.entity.assessment.TestQuestionId;
import com.examManagementBE.repository.student.StudentAnswerLogRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final TestQuestionRepository TestQuestionRepository;
    private final AnswerRepository answerRepository;
    private final StudentAnswerLogRepository studentAnswerLogRepository;

    public boolean SaveQuestionList(List<Question> questions) {
        for (Question question : questions) {
            questionRepository.save(question);
        }
        return true;
    }
    public List<Question> getAllQuestionsById(Test test) {
        List<TestQuestion> testQuestions = TestQuestionRepository.findByTestId(test.getTestId());
        return testQuestions.stream()
                .map(tq -> questionRepository.findById(tq.getQuestionId()).orElse(null))
                .filter(q -> q != null)
                .toList();
    }
    public boolean deleteQuestionById(Integer questionId, Integer testId) {
        if (questionRepository.existsById(questionId)) {
            // Xóa các dòng trong bảng test-question
            TestQuestionId tqId = new TestQuestionId();
            tqId.setQuestionId(questionId);
            tqId.setTestId(testId);
            if (TestQuestionRepository.existsById(tqId)) {
                TestQuestionRepository.deleteById(tqId);
            }
            // xóa tất cả answer của question đó
            List<Answer> answers = answerRepository.findAnswersByQuestion_QuestionId(questionId);
            answers.stream().forEach(ans -> {
                // Xóa tất cả answer của hs cho question đó
                List<StudentAnswerLog> salList = studentAnswerLogRepository.findAllBySelectedAnswerId(ans.getAnswerId());
                if (!salList.isEmpty()) {
                    studentAnswerLogRepository.deleteAll(salList);
                }
            });
            if (!answers.isEmpty()) {
                answerRepository.deleteAll(answers);
            }
            
            // Xóa question cuối cùng
            questionRepository.deleteById(questionId);
            return true;
        }
        return false;
    }
    public List<Answer> getAnswersByQuestionId(Integer questionId) {
        Question question = questionRepository.findById(questionId).orElse(null);
        if (question != null) {
            return answerRepository.findAnswersByQuestion_QuestionId(questionId);
        }
        return null;
    }
}
