package com.examManagementBE.entity.assessment;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentAnswerLogId implements Serializable {

    @Column(name = "Student_User_ID")
    Integer studentUserId;

    @Column(name = "Question_ID")
    Integer questionId;

    @Column(name = "Selected_Answer_ID")
    Integer selectedAnswerId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentAnswerLogId that = (StudentAnswerLogId) o;
        return Objects.equals(studentUserId, that.studentUserId) &&
                Objects.equals(questionId, that.questionId) &&
                Objects.equals(selectedAnswerId, that.selectedAnswerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentUserId, questionId, selectedAnswerId);
    }
}
