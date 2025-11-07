package com.examManagementBE.entity.assessment;

import com.examManagementBE.common.constants.EntityConstants;
import com.examManagementBE.entity.user.Student;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = EntityConstants.STUDENT_ANSWER_LOG)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentAnswerLog {

    @EmbeddedId
    StudentAnswerLogId id;

    @ManyToOne
    @MapsId("studentUserId")
    @JoinColumn(name = "Student_User_ID", nullable = false)
    Student student;

    @ManyToOne
    @MapsId("questionId")
    @JoinColumn(name = "Question_ID")
    Question question;

    @ManyToOne
    @MapsId("testId")
    @JoinColumn(name = "Test_ID")
    Test test;

    @ManyToOne
    @JoinColumn(name = "Selected_Answer_ID")
    Answer selectedAnswer;

    @Column(name = "Student_answer_text", columnDefinition = "TEXT")
    String studentAnswerText;
}
