package com.example.examination_application.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "tests")
@Data
public class Test {

    @Id
    @Column(name = "test_id", columnDefinition = "CHAR(36)")
    private String testId;

    @Column(name = "user_id", columnDefinition = "CHAR(36)", nullable = false)
    private String userId;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "test_score")
    private Integer testScore;

    @Enumerated(EnumType.STRING)
    @Column(name = "test_status")
    private TestStatus testStatus;

    @Column(name = "question_number")
    private int questionNumber;

    @Column(name = "duration")
    private int duration;

    @Column(name = "pass_code")
    private String passCode;

    @Column(name = "released_answer")
    private Boolean releasedAnswer = false;

    @Column(name = "released_score")
    private Boolean releasedScore = false;
}
