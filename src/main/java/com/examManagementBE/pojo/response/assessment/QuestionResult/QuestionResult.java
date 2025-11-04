package com.examManagementBE.pojo.response.assessment.QuestionResult;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionResult {
    Integer questionId;
    String questionText;
    Integer score;
    Integer maxScore;
    List<AnswerResultResponse> answers;
    List<AnswerResultResponse> studentAnswer;
}
