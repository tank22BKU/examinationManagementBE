package com.examManagementBE.repository.assessment;

import com.examManagementBE.entity.assessment.Answer;
import com.examManagementBE.entity.assessment.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    List<Answer> findAllByQuestion(Question question);
}
