package com.examManagementBE.pojo.request.submission;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubmissionRequest {
    Integer testId;
    Integer studentId;
    List<StudentAnswerRequest> studentAnswerRequests;
}
