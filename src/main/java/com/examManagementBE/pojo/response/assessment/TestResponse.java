package com.examManagementBE.pojo.response.assessment;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TestResponse {
    Integer testId;
    String title;
    Boolean Status;
    Integer totalQuestions;
    Integer totalSubmissions;
}
