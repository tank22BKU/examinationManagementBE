package com.examManagementBE.pojo.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.OffsetDateTime;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {
    Integer id;
    String name;
    String phoneNumber;
    String email;
    OffsetDateTime dateOfBirth;
    Boolean isActive;
}
