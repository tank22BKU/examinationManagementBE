package com.example.examination_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.examination_application.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, String> {
}
