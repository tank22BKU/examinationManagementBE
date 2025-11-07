package com.examManagementBE.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentQuestionDTO {
    private String studentAnswerText;
    private Integer userId;
    private Integer questionId;
}
