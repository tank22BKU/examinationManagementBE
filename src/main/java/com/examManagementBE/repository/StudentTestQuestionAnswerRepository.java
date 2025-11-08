package com.examManagementBE.repository;

import com.examManagementBE.entity.assessment.StudentTestAttempt;
import com.examManagementBE.entity.assessment.StudentTestAttemptId;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentTestQuestionAnswerRepository extends JpaRepository<StudentTestAttempt, StudentTestAttemptId> {
    @EntityGraph(attributePaths = {"test", "test.testQuestions", "test.testQuestions.question", "test.testQuestions.question.answers"})
    @Query("SELECT a FROM StudentTestAttempt a WHERE a.student.User_ID = :id")
    List<StudentTestAttempt> findByStudentId(@Param("id") Integer id);



}
