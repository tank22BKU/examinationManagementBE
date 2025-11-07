package com.example.examination_application.repository;

import com.example.examination_application.entity.StudentQuestion;
import com.example.examination_application.entity.StudentQuestionKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentQuestionRepository extends JpaRepository<StudentQuestion, StudentQuestionKey> {
}
