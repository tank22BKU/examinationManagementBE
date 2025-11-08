package com.examManagementBE.pojo.request.ai;

import com.fasterxml.jackson.annotation.JsonProperty;
public record StudentAnswerRequest (
    Integer testId,
    Integer questionId,
    @JsonProperty("answer") // Ánh xạ "answer" từ JSON sang "selectedAnswerId"
    Integer selectedAnswerId, 
    Integer studentId
) {}
