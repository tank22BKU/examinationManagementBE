package com.examManagementBE.pojo.request.assessment;

import com.examManagementBE.entity.assessment.Answer;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class AnswerEditRequest {
    Integer answerId;
    String answerText;
    Boolean correctAnswer;
    Integer questionId;
}
