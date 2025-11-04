package com.examManagementBE.entity.student;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import com.examManagementBE.common.constants.EntityConstants;
import com.examManagementBE.entity.assessment.Test;
import com.examManagementBE.entity.user.Student;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "Student_Test_Attempt")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@IdClass(StudentTestAttemptId.class)
public class StudentTestAttempt {

    @Id
    @Column(name = "Student_User_ID")
    Integer studentUserId;

    @Id
    @Column(name = "Test_ID")
    Integer testId;

    @Column(name = "Start_time")
    LocalDateTime startTime;

    @Column(name = "Submit_time")
    LocalDateTime submitTime;

    // Result là thuộc tính dẫn xuất → không lưu

    @ManyToOne
    @JoinColumn(name = "Student_User_ID", insertable = false, updatable = false)
    Student student;

    @ManyToOne
    @JoinColumn(name = "Test_ID", insertable = false, updatable = false)
    Test test;
}