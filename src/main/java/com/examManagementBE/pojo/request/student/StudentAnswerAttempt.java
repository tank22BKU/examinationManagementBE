package com.examManagementBE.pojo.request.student;

import com.examManagementBE.entity.assessment.Answer;
import com.examManagementBE.entity.student.StudentAnswerLog;
import com.examManagementBE.pojo.request.assessment.AnswerEditRequest;
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
public class StudentAnswerAttempt {
    Integer selected_answer_id;
    String student_answer_text;
}
