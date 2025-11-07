package com.examManagementBE.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuestionDTO {
    private Integer questionId;
    private String questionText;
    private List<AnswerDTO> answers; // nếu muốn luôn kèm đáp án
    private String studentAnswerText;
}
