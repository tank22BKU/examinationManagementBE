package com.example.examination_application.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "question_answers")
@Data
public class QuestionAnswer {

    @EmbeddedId
    private QuestionAnswerKey id;

    @ManyToOne
    @MapsId("questionId")
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @MapsId("answerId")
    @JoinColumn(name = "answer_id")
    private Answer answer;
}
