package com.examManagementBE.entity.assessment;

import com.examManagementBE.common.constants.EntityConstants;
import com.examManagementBE.entity.user.Teacher;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = EntityConstants.QUESTION_TABLE)
//@Table(name = "question")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Question_ID")
    Integer questionId;

    @Column(name = "Question_text", columnDefinition = "TEXT", nullable = false)
    String questionText;

    @Column(name="question_score", columnDefinition = "INT", nullable = false)
    Integer score;

    @ManyToOne
    @JoinColumn(name = "Composer_Teacher_ID")
    Teacher composer;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers;
}