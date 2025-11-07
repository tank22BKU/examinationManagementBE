package com.example.examination_application.dto;

import com.example.examination_application.entity.TestStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentTestDTO {
    private String userId;
    private String testId;
    private LocalDateTime startTime;
    private LocalDateTime submitTime;
    private LocalTime actualTime;
    private TestStatus testStatus;
}
