package com.examManagementBE.pojo.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignupRequest {
    String fullName;
    String email;
    String phoneNumber;
    String password;
    String profileImg; // Can be null if not provided
    Integer roleId;
}
