package com.examManagementBE.repository.assessment;

import com.examManagementBE.entity.assessment.Answer;
import com.examManagementBE.entity.assessment.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    List<Answer> findAllByQuestion(Question question);
    @Query("SELECT a FROM Answer a WHERE a.question.questionId = :questionId AND a.correctAnswer = true")
    Optional<Answer> findCorrectAnswerByQuestionId(Integer questionId);
}
