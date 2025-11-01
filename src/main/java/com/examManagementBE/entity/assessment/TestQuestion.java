package com.examManagementBE.entity.assessment;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "Test_Question")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@IdClass(TestQuestionId.class)
public class TestQuestion {

    @Id
    @Column(name = "Test_ID")
    Integer testId;

    @Id
    @Column(name = "Question_ID")
    Integer questionId;

    @ManyToOne
    @JoinColumn(name = "Test_ID", insertable = false, updatable = false)
    Test test;

    @ManyToOne
    @JoinColumn(name = "Question_ID", insertable = false, updatable = false)
    Question question;
}