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
public class StudentTestAttemptId implements Serializable {
    @Column(name = "Student_User_ID")
    Integer studentUserId;

    @Column(name = "Test_ID")
    Integer testId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentTestAttemptId that = (StudentTestAttemptId) o;
        return Objects.equals(studentUserId, that.studentUserId) &&
                Objects.equals(testId, that.testId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentUserId, testId);
    }
}
