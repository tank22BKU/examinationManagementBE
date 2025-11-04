package com.examManagementBE.pojo.request.student;

import com.examManagementBE.entity.assessment.Answer;
import com.examManagementBE.entity.student.StudentAnswerLog;
import com.examManagementBE.pojo.request.student.StudentAnswerAttempt;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;

import java.util.List;

import org.springframework.cglib.core.Local;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentSubmitRequest {
    LocalDateTime submitTime;
    LocalDateTime startTime;
    Integer studentId;
    List<StudentAnswerAttempt> answerLogs;
}
