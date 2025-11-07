package com.example.examination_application.repository;

import com.example.examination_application.entity.QuestionAnswer;
import com.example.examination_application.entity.QuestionAnswerKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer, QuestionAnswerKey> {}

