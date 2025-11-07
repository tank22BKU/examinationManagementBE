package com.examManagementBE.repository.assessment;

import com.examManagementBE.entity.assessment.StudentQuestion;
import com.examManagementBE.entity.assessment.StudentQuestionKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentQuestionRepository extends JpaRepository<StudentQuestion, StudentQuestionKey> {
}
