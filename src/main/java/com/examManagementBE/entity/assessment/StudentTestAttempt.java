package com.examManagementBE.entity.assessment;

import com.examManagementBE.common.constants.EntityConstants;
import com.examManagementBE.entity.user.Student;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalTime;
import java.time.OffsetDateTime;

@Entity
@Table(name = EntityConstants.STUDENT_TEST_ATTEMPT)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentTestAttempt {
    @EmbeddedId
    StudentTestAttemptId id;

    @ManyToOne
    @MapsId("studentUserId")
    @JoinColumn(name = "Student_User_ID", nullable = false)
    Student student;

    @ManyToOne
    @MapsId("testId")
    @JoinColumn(name = "Test_ID", nullable = false)
    Test test;

    @Column(name = "Start_time")
    OffsetDateTime startTime;

    @Column(name = "Submit_time")
    OffsetDateTime submitTime;

    @Column(name = "actual_time")
    LocalTime actualTime;

    @Column(name = "score")
    Integer score;
}
