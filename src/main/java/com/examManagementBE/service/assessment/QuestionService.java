package com.examManagementBE.service.assessment;

import com.examManagementBE.entity.assessment.Test;
import com.examManagementBE.entity.assessment.Answer;
import com.examManagementBE.entity.assessment.Question;
import com.examManagementBE.entity.assessment.TestQuestion;
import com.examManagementBE.entity.user.Teacher;
import com.examManagementBE.pojo.request.assessment.QuestionAnswerRequest;
import com.examManagementBE.repository.assessment.AnswerRepository;
import com.examManagementBE.repository.assessment.QuestionRepository;
import com.examManagementBE.repository.assessment.TestQuestionRepository;
import com.examManagementBE.pojo.request.assessment.AnswerRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;    
    private final AnswerRepository  answerRepository;
    private final TestQuestionRepository testQuestionRepository;

    public boolean SaveQuestionList(List<QuestionAnswerRequest> questions, Teacher teacher, Test test) {
        for (QuestionAnswerRequest request : questions) {
            Question newQuestion = Question.builder().questionText(request.getQuestionText()).score(request.getScore()).composer(teacher).build();
            questionRepository.save(newQuestion);

            for (AnswerRequest answer : request.getAnswers()) {
                Answer newAnswer = Answer.builder().answerText(answer.getAnswerText()).correctAnswer(answer.getCorrectAnswer()).question(newQuestion).build();
                answerRepository.save(newAnswer);
            }

            TestQuestion newTestQuestion = TestQuestion.builder().questionId(newQuestion.getQuestionId()).testId(test.getTestId()).build();
            testQuestionRepository.save(newTestQuestion);
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
    public List<AnswerRequest> getAllAnswersByQuestion(Question question) {
        // Implementation to fetch answers by question
        List<Answer> answers = answerRepository.findAllByQuestion(question);
        List<AnswerRequest> ansReq = answers.stream().map(answer -> new AnswerRequest(answer.getAnswerId(), answer.getAnswerText(),answer.getCorrectAnswer())).toList();
        return ansReq;
    }

}
