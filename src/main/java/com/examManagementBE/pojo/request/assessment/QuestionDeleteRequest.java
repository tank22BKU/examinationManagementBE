package com.examManagementBE.pojo.request.assessment;

import com.examManagementBE.entity.assessment.Question;
import com.examManagementBE.entity.assessment.Answer;
import com.examManagementBE.pojo.request.assessment.AnswerDeleteRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class QuestionDeleteRequest {
    private Integer questionId;
    private String questionText;
}
