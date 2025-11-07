package com.examManagementBE.pojo.response.assessment;

import com.examManagementBE.pojo.response.assessment.QuestionResult.QuestionResult;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TestAttemptHistoryDetailResponse {
    String testName;
    OffsetDateTime attemptDate;
    String totalTimes;
    Integer totalQuestions;
    Integer totalCorrectQuestions;
    Integer score;
    Integer maxScore;
    List<QuestionResult> questionResultDetails;
}
