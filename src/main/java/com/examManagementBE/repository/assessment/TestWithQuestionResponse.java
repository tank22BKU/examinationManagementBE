package com.examManagementBE.repository.assessment;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.*;

import com.examManagementBE.entity.assessment.Question;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TestWithQuestionResponse<T> {
   String description;
   String title;
   Integer duration;
   Integer totalQuestions;
   List<T> questions;
}
