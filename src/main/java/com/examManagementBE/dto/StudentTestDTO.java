package com.examManagementBE.dto;


import com.examManagementBE.entity.assessment.TestStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentTestDTO {
    private Integer userId;
    private Integer testId;
    private OffsetDateTime startTime;
    private OffsetDateTime submitTime;
    private LocalTime actualTime;
    private TestStatus testStatus;
}
