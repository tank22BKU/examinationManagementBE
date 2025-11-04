package com.examManagementBE.repository.assessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.examManagementBE.entity.assessment.TestQuestion;
import com.examManagementBE.entity.assessment.TestQuestionId;

// import ch.qos.logback.core.joran.sanity.Pair;
import java.util.List;


@Repository
public interface TestQuestionRepository extends JpaRepository<TestQuestion, TestQuestionId> {
    List<TestQuestion> findByTestId(Integer id);
    void deleteById(TestQuestionId id);
}
