package com.example.examination_application.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class QuestionAnswerKey implements Serializable {

    @Column(name = "question_id", columnDefinition = "CHAR(36)")
    private String questionId;

    @Column(name = "answer_id", columnDefinition = "CHAR(36)")
    private String answerId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionAnswerKey that = (QuestionAnswerKey) o;
        return Objects.equals(questionId, that.questionId) &&
                Objects.equals(answerId, that.answerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, answerId);
    }
}
