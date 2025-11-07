package com.examManagementBE.entity.assessment;
import com.examManagementBE.entity.user.Student;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Students_Questions")
@Data
public class StudentQuestion {

    @EmbeddedId
    private StudentQuestionKey id;

    @Column(name = "Student_answer_text", length = 500)
    private String studentAnswerText;

    //🔹 Optional: bạn có thể thêm quan hệ nếu cần liên kết với Student hoặc Answer
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private Student student;

    @ManyToOne
    @MapsId("questionId")
    @JoinColumn(name = "question_id")
    private Question question;
}