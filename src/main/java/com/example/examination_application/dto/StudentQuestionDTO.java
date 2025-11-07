package com.example.examination_application.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentQuestionDTO {
    private String studentAnswerText;
    private String userId;
    private String questionId;
}
