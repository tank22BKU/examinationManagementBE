package com.examManagementBE.entity.assessment;

import com.examManagementBE.common.constants.EntityConstants;
import com.examManagementBE.entity.user.Teacher;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = EntityConstants.QUESTION_TABLE)
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

    @ManyToOne
    @JoinColumn(name = "Composer_Teacher_ID")
    Teacher composer;
}