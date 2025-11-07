package com.example.examination_application.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "questions")
@Data
public class Question {

    @Id
    @Column(name = "question_id", columnDefinition = "CHAR(36)")
    private String questionId;

    @Column(name = "user_id", columnDefinition = "CHAR(36)", nullable = false)
    private String userId;

    @Column(name = "question_text", length = 500, nullable = false)
    private String questionText;

    @Column(name = "question_score")
    private Double questionScore;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private QuestionStatus status;
}
