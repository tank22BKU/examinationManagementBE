package com.examManagementBE.repository.assessment;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TestWithQuestionResponse<T> {
   Integer testId;
   String description;
   String title;
   Integer duration;
   Integer totalQuestions;
   List<T> questions;
}
