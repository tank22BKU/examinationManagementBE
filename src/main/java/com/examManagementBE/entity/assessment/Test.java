package com.examManagementBE.entity.assessment;

import com.examManagementBE.common.constants.EntityConstants;
import com.examManagementBE.entity.user.Teacher;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
//@Table(name = EntityConstants.TEST_TABLE)
@Table(name ="Test")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Test_ID")
    Integer testId;

    @Column(name = "Title", length = 255)
    String title;

    @Column(name = "Description", columnDefinition = "TEXT")
    String description;

    @Column(name = "Pass_code", length = 50)
    String passCode;

    @Column(name = "Status")
    Boolean status;

    @Column(name = "Duration")
    Integer duration;

    @Column(name = "Questions")
    Integer questions;

    @Column(name = "Submissions")
    Integer submissions;

    @Column(name = "Released_Answer")
    Boolean releasedAnswer;

    @Column(name = "Released_Score")
    Boolean releasedScore;

    @Enumerated(EnumType.STRING)
    @Column(name = "test_status")
    TestStatus testStatus;

    @ManyToOne
    @JoinColumn(name = "Creator_Teacher_ID")
    Teacher creator;

    @OneToMany(mappedBy = "test")
    private List<TestQuestion> testQuestions;
}