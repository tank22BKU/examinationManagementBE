package com.examManagementBE.repository.assessment;

import com.examManagementBE.entity.assessment.StudentTestAttempt;
import com.examManagementBE.entity.assessment.StudentTestAttemptId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentTestRepository extends JpaRepository<StudentTestAttempt, StudentTestAttemptId> {
}
