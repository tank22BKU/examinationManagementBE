package com.examManagementBE.repository.assessment;

import com.examManagementBE.entity.assessment.StudentTestAttempt;
import com.examManagementBE.entity.assessment.Test;
import com.examManagementBE.entity.user.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentTestAttemptRepository extends JpaRepository<StudentTestAttempt, Integer> {
    List<StudentTestAttempt> findAllByStudent(Student student);
    Optional<StudentTestAttempt> findByStudentAndTest(Student student, Test test);
    List<StudentTestAttempt> findAllByTest(Test test);
}
