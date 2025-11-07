package com.examManagementBE.pojo.response.assessment;

import com.examManagementBE.entity.assessment.StudentTestAttemptId;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TestAttemptHistoryResponse {
    String StudentName;
    Integer StudentId;
    StudentTestAttemptId attemptId;
    Integer TestId;
    String TestName;
    String Duration;
    Integer TotalQuestions;
    Integer Score;
    Integer MaxScore;
    Boolean ReleasedAnswer;
    Boolean ReleasedScore;
}
