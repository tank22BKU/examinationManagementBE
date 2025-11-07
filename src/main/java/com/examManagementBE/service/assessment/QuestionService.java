package com.examManagementBE.service.assessment;

import com.examManagementBE.entity.assessment.Question;
import com.examManagementBE.repository.assessment.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public boolean SaveQuestionList(List<Question> questions) {
        for (Question question : questions) {
            questionRepository.save(question);
        }
        return true;
    }


}
