package com.examManagementBE.dto;

import lombok.Data;

@Data
public class AnswerDTO {
    private Integer answerId;
    private String answerText;
    private Boolean correct;
}
