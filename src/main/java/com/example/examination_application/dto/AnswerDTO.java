package com.example.examination_application.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTO {
    private String answerId;
    private String answerText;
    private boolean correctAnswer;
}
