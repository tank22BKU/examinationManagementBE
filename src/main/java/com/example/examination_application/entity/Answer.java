package com.example.examination_application.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "answers")
@Data
public class Answer {

    @Id
    @Column(name = "answer_id", columnDefinition = "CHAR(36)")
    private String answerId;

    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "question_id")
    private Question question;

    @Column(name = "answer_text", length = 500)
    private String answerText;

    @Column(name = "correct_answer")
    private boolean correctAnswer;
}
