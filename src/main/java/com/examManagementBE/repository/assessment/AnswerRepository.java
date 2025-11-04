package com.examManagementBE.repository.assessment;

import com.examManagementBE.entity.assessment.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    public List<Answer> findAnswersByQuestion_QuestionId(Integer questionId);
}
