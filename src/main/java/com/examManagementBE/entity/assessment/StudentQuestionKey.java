package com.examManagementBE.entity.assessment;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class StudentQuestionKey implements Serializable {

    @Column(name = "user_id", columnDefinition = "CHAR(36)")
    private Integer userId;

    @Column(name = "question_id", columnDefinition = "CHAR(36)")
    private Integer questionId;

}
