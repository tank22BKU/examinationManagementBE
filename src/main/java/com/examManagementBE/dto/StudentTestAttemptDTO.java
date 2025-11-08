package com.examManagementBE.dto;

import lombok.Data;

@Data
public class StudentTestAttemptDTO {
    private Integer studentId;
    private TestDTO test;
    private double totalScore;
}
