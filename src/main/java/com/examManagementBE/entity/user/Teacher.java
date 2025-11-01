package com.examManagementBE.entity.user;

import com.examManagementBE.common.constants.EntityConstants;
import com.examManagementBE.entity.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = EntityConstants.TEACHER_TABLE)
@PrimaryKeyJoinColumn(name = "User_ID")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Teacher extends User {

    @Column(name = "Specialization", length = 100)
    String specialization;

    @Column(name = "Qualification", length = 100)
    String qualification;
}
