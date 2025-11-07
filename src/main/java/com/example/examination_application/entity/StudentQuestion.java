package com.example.examination_application.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "students_questions")
@Data
public class StudentQuestion {

    @EmbeddedId
    private StudentQuestionKey id;

    @Column(name = "student_answer_text", length = 500)
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
