package com.examManagementBE.pojo.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserResponse {
    Integer id;
    String fullName;
    String email;
    String phoneNumber;
    OffsetDateTime lastLogin;
    OffsetDateTime dateOfBirth;
    String profileImg;
    Boolean isActive;

    @QueryProjection
    public UserResponse(Integer id, String fullName, String email, String phoneNumber,
                         OffsetDateTime lastLogin, OffsetDateTime dateOfBirth,
                         String profileImg, Boolean isActive) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.lastLogin = lastLogin;
        this.dateOfBirth = dateOfBirth;
        this.profileImg = profileImg;
        this.isActive = isActive;
    }
}
