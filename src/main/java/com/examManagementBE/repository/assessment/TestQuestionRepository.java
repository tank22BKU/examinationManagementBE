package com.examManagementBE.repository.assessment;

import com.examManagementBE.entity.assessment.TestQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestQuestionRepository extends JpaRepository<TestQuestion, Integer> {
    List<TestQuestion> findAllByTestId(int testId);
}
