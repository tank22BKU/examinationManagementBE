package com.examManagementBE.repository.assessment;

import com.examManagementBE.entity.assessment.Question;
import com.examManagementBE.entity.assessment.StudentAnswerLog;
import com.examManagementBE.entity.user.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentAnswerLogRepository extends JpaRepository<StudentAnswerLog, Integer> {
    List<StudentAnswerLog> findByStudentAndQuestion(Student student, Question question);
}
