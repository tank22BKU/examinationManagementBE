package com.examManagementBE.pojo.request.submission;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentAnswerRequest {
    Integer questionId;
    String answer;
}
