package com.examManagementBE.pojo.request.assessment;

import com.examManagementBE.entity.assessment.Question;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnswerRequest {
    Integer answerId;
    String answerText;
    Boolean correctAnswer;
}
