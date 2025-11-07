package com.examManagementBE.entity.user;

import com.examManagementBE.common.constants.EntityConstants;
import com.examManagementBE.entity.User;
import com.examManagementBE.entity.assessment.StudentQuestion;
import com.examManagementBE.entity.assessment.TestQuestion;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = EntityConstants.STUDENT_TABLE)
@PrimaryKeyJoinColumn(name = "User_ID")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student extends User {

    @Column(name = "Grade_level", length = 50)
    String gradeLevel;

    @Column(name = "Major", length = 100)
    String major;

    @Column(name = "Education_level", length = 100)
    String educationLevel;

    @OneToMany(mappedBy = "student")
    private List<StudentQuestion> studentQuestions;
}
