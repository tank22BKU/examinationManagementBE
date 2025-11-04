package com.examManagementBE.pojo.request.assessment;

import com.examManagementBE.entity.assessment.Answer;
import com.examManagementBE.pojo.request.assessment.AnswerEditRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionEditRequest {
    Integer questionId;
    String questionText;
    List<String> options;
    List<AnswerEditRequest> answers;
}
