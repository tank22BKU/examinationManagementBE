package com.examManagementBE.repository.student;

import com.examManagementBE.entity.assessment.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.examManagementBE.entity.student.StudentTestAttempt;
import com.examManagementBE.entity.student.StudentTestAttemptId;
import java.util.*;

@Repository
public interface StudentTestAttemptRepository extends JpaRepository<StudentTestAttempt, StudentTestAttemptId> {
    
}
