package com.example.examination_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.examination_application.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, String> {
}
