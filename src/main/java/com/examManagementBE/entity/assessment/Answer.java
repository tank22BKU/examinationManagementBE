package com.examManagementBE.entity.assessment;

import com.examManagementBE.common.constants.EntityConstants;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = EntityConstants.ANSWER_TABLE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Answer_ID")
    Integer answerId;

    @Column(name = "Answer_text", columnDefinition = "TEXT", nullable = false)
    String answerText;

    @Column(name = "Correct_answer")
    Boolean correctAnswer = false;

    @ManyToOne
    @JoinColumn(name = "Question_ID")
    Question question;
}