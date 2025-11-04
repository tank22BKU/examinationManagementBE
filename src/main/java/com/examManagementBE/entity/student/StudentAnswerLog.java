package com.examManagementBE.entity.student;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import com.examManagementBE.common.constants.EntityConstants;
import com.examManagementBE.entity.assessment.Answer;
import com.examManagementBE.entity.user.Student;

import java.io.Serializable;

@Entity
@Table(name = "Student_Answer_Log")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@IdClass(StudentAnswerLogId.class)
public class StudentAnswerLog {

    @Id
    @Column(name = "Student_User_ID")
    Integer studentUserId;

    @Id
    @Column(name = "Selected_Answer_ID")
    Integer selectedAnswerId;

    @Column(name = "Student_answer_text", columnDefinition = "TEXT")
    String studentAnswerText;

    @ManyToOne
    @JoinColumn(name = "Student_User_ID", insertable = false, updatable = false)
    Student student;

    @ManyToOne
    @JoinColumn(name = "Selected_Answer_ID", insertable = false, updatable = false)
    Answer answer;
}