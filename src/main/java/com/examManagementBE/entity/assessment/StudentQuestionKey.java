package com.examManagementBE.entity.assessment;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class StudentQuestionKey implements Serializable {

    @Column(name = "Student_User_ID", columnDefinition = "CHAR(36)")
    private Integer userId;

    @Column(name = "Question_ID", columnDefinition = "CHAR(36)")
    private Integer questionId;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        StudentQuestionKey that = (StudentQuestionKey) o;
//        return Objects.equals(userId, that.userId) &&
//                Objects.equals(questionId, that.questionId);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(userId, questionId);
//    }
}
