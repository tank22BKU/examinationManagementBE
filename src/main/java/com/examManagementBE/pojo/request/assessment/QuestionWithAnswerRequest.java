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
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionWithAnswerRequest {
    Integer questionId;
    String questionText;
    Integer score;
    // Integer composerTeacherId;
    List<Answer> answers;
}
