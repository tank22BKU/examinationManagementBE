package com.examManagementBE.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import com.examManagementBE.common.constants.EntityConstants;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = EntityConstants.USER_TABLE)
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "full_name", length = 50, nullable = false)
    String fullName;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    String email;

    @Column(name = "phone_number", length = 15, nullable = false)
    String phoneNumber;

    @Column(name = "password", length = 255, nullable = false)
    String password;

    @Column(name = "profile_img", length = 255)
    String profileImg;

    @Builder.Default
    @Column(name = "is_active", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    Boolean isActive = true;

    @Column(name = "role_id", insertable = false, updatable = false, nullable = false)
    Integer roleId;

    @Column(name = "last_login", nullable = false)
    OffsetDateTime lastLogin;

    @Column(name = "date_of_birth", nullable = false)
    OffsetDateTime dateOfBirth;
}