package com.examManagementBE.pojo.request.assessment;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnswerDeleteRequest {
    Integer answerId;
    String answerText;
    Boolean correctAnswer;
    Integer questionId;
}
