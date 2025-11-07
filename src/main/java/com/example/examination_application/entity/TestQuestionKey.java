package com.example.examination_application.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class TestQuestionKey implements Serializable {

    @Column(name = "test_id", columnDefinition = "CHAR(36)")
    private String testId;

    @Column(name = "question_id", columnDefinition = "CHAR(36)")
    private String questionId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestQuestionKey that = (TestQuestionKey) o;
        return Objects.equals(testId, that.testId) &&
                Objects.equals(questionId, that.questionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(testId, questionId);
    }
}
