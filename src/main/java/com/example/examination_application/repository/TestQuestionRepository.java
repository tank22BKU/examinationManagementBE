package com.example.examination_application.repository;

import com.example.examination_application.entity.TestQuestion;
import com.example.examination_application.entity.TestQuestionKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestQuestionRepository extends JpaRepository<TestQuestion, TestQuestionKey> {}
