package com.examManagementBE.entity.student;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class StudentTestAttemptId implements Serializable {
    private Integer studentUserId;
    private Integer testId;
}