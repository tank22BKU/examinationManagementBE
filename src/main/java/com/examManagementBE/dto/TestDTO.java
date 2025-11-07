package com.examManagementBE.dto;

import lombok.Data;

import java.util.List;

@Data
public class TestDTO {
    private Integer testId;
    private String testName;
    private List<QuestionDTO> questions;
}
