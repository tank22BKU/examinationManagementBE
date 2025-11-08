package com.examManagementBE.pojo.request.ai;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO mới nhận nội dung text trực tiếp từ FE
 */
public record ExplanationTextRequest(
    @JsonProperty("question_text")
    String questionText,
    
    @JsonProperty("incorrect_answer_text")
    String incorrectAnswerText,
    
    @JsonProperty("correct_answer_text")
    String correctAnswerText
) {}