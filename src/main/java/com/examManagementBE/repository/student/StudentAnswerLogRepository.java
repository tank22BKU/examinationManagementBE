package com.examManagementBE.repository.student;

import com.examManagementBE.entity.assessment.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.examManagementBE.entity.student.StudentAnswerLog;
import com.examManagementBE.entity.student.StudentAnswerLogId;
import java.util.*;

@Repository
public interface StudentAnswerLogRepository extends JpaRepository<StudentAnswerLog, StudentAnswerLogId> {    
    List<StudentAnswerLog> findAllBySelectedAnswerId(Integer selectedAnswerId);
}
