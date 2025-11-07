package com.example.examination_application.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tests_questions")
@Data
public class TestQuestion {

    @EmbeddedId
    private TestQuestionKey id;

    @ManyToOne
    @MapsId("testId")
    @JoinColumn(name = "test_id")
    private Test test;

    @ManyToOne
    @MapsId("questionId")
    @JoinColumn(name = "question_id")
    private Question question;
}
