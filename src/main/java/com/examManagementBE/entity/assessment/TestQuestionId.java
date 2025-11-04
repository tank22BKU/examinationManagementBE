package com.examManagementBE.entity.assessment;

import lombok.*;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class TestQuestionId implements Serializable {
    private Integer testId;
    private Integer questionId;
}