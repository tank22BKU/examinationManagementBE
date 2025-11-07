package com.examManagementBE.entity.assessment;

import jakarta.persistence.Column;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class TestQuestionId implements Serializable {
    @Column(name = "Test_ID")
    private Integer testId;
    @Column(name = "Question_ID")
    private Integer questionId;
}