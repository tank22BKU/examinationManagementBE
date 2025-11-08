package com.examManagementBE.mapper;


import com.examManagementBE.entity.assessment.Test;
import com.examManagementBE.repository.assessment.TestWithQuestionResponse;

public class TestMapper {
    public static <T> TestWithQuestionResponse <T> testWithQuestionResponseMapper(Test test, java.util.List<T> questions) {
        return TestWithQuestionResponse.<T>builder()
                .testId(test.getTestId())
                .description(test.getDescription())
                .title(test.getTitle())
                .duration(test.getDuration())
                .totalQuestions(test.getQuestions())
                .questions(questions)
                .build();
    }
}
