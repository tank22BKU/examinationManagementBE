package com.examManagementBE.pojo.response.assessment.QuestionResult;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnswerResultResponse {
    Integer answerId;
    String answerText;
    Boolean isCorrect;
}
