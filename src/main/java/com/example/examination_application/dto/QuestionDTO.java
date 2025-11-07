package com.example.examination_application.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {
    private String questionId;
    private String questionText;
    private Double questionScore;
    private String status; // 'Answer saved' | 'Not yet answered'
}
