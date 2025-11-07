package com.example.examination_application.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    String userId; // CHAR(36) → String (UUID)

    @Column(name = "full_name")
    String fullName;

    @Column(name = "is_active")
    Boolean isActive = true;

    @Column(name = "bdate")
    LocalDate birthDate; // đặt tên rõ nghĩa hơn (Java convention)

    @Column(name = "address", columnDefinition = "TEXT")
    String address;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "email", unique = true)
    String email;

    @Column(name = "last_login")
    LocalDateTime lastLogin;

    @Column(name = "profile_img")
    String profileImage;

    @Column(name = "create_at", updatable = false)
    LocalDateTime createdAt;

    @Column(name = "update_at")
    LocalDateTime updatedAt;

    @Column(name = "password", nullable = false)
    String password; // sẽ mã hóa bằng BCrypt khi lưu

    @Enumerated(EnumType.STRING)
    @Column(name = "sex")
    Gender sex;

    public enum Gender {
        Male, Female, Other
    }
}

