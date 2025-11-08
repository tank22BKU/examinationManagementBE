package com.examManagementBE.service.assessment;

import com.examManagementBE.entity.assessment.Test;
import com.examManagementBE.entity.assessment.Answer;
import com.examManagementBE.entity.assessment.Question;
import com.examManagementBE.entity.assessment.TestQuestion;
import com.examManagementBE.repository.assessment.AnswerRepository;
import com.examManagementBE.repository.assessment.QuestionRepository;
import com.examManagementBE.repository.assessment.TestQuestionRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;    
    private final AnswerRepository  answerRepository;
    private final TestQuestionRepository testQuestionRepository;

    public boolean SaveQuestionList(List<Question> questions) {
        for (Question question : questions) {
            questionRepository.save(question);
        }
        return true;
    }
    public List<Question> getAllQuestionsById(Test test) {
        List<TestQuestion> testQuestions = testQuestionRepository.findByTestId(test.getTestId());
        return testQuestions.stream()
                .map(tq -> questionRepository.findById(tq.getQuestionId()).orElse(null))
                .filter(q -> q != null)
                .toList();
    }
    public List<Answer> getAllAnswersByQuestion(Question question) {
        // Implementation to fetch answers by question
        return answerRepository.findAllByQuestion(question);
    }

}
