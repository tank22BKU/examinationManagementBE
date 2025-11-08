package com.examManagementBE.pojo.response.instructor;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DashBoardTestOverviewResponse {
    Integer testId;
    String testName;
    Integer maxScore;
    Integer minScore;
    Integer averageScore;
    Integer totalSubmissions;
}
