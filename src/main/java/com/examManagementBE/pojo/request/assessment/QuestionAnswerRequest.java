package com.examManagementBE.pojo.request.assessment;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionAnswerRequest {
    String questionText;
    Integer score;
    List<AnswerRequest> answers;
}
