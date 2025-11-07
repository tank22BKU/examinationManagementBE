package com.example.examination_application.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "students_tests")
@Data
public class StudentTest {

    @EmbeddedId
    private StudentTestKey id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "start_time")
    private LocalDateTime startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "submit_time")
    private LocalDateTime submitTime;

    @Column(name = "actual_time")
    private LocalTime actualTime;

    // 🔹 Optional: nếu bạn có entity Student và Test, bạn có thể liên kết như sau:
     @ManyToOne
     @MapsId("userId")
     @JoinColumn(name = "user_id")
     private Student student;

     @ManyToOne
     @MapsId("testId")
     @JoinColumn(name = "test_id")
     private Test test;
}
