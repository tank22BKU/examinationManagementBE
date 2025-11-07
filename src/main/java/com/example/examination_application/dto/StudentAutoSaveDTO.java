// dto/StudentAutoSaveDTO.java
package com.example.examination_application.dto;

import lombok.Data;

@Data
public class StudentAutoSaveDTO {
    private String testId;
    private String userId;
    private String questionId;
    private String studentAnswerText;
}
