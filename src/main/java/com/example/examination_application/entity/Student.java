package com.example.examination_application.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "students")
@Data
public class Student {

    @Id
    @Column(name = "user_id", columnDefinition = "CHAR(36)")
    private String userId;   // trùng với PK trong bảng students

    // Nếu bạn có bảng Users thì nên ánh xạ ManyToOne
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private User user;   // liên kết đến bảng users

    @Column(name = "grade_level")
    private String gradeLevel;

    @Column(name = "major")
    private String major;

    @Column(name = "education_level")
    private String educationLevel;
}
