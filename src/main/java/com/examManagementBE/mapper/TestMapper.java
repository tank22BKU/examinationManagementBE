package com.examManagementBE.mapper;
import org.springframework.stereotype.Component;
import com.examManagementBE.pojo.response.assessment.TestResponse;
import com.examManagementBE.pojo.response.assessment.TestWithQuestionResponse;
import com.examManagementBE.pojo.request.assessment.TestCreationRequest;
import com.examManagementBE.entity.assessment.*;


@Component
public class TestMapper {
    public static Test testMapper(TestCreationRequest testRequest) {
        return Test.builder()
                .title(testRequest.getTittle())
                .description(testRequest.getDescription())
                .passCode(testRequest.getPassCode())
                .duration(testRequest.getDuration())
                .questions(testRequest.getQuestionCount())
                .submissions(testRequest.getSubmisssionCount())
                .creator(null)
                .build();
    }
    public static <T> TestWithQuestionResponse <T> testWithQuestionResponseMapper(Test test, java.util.List<T> questions) {
        return TestWithQuestionResponse.<T>builder()
                .description(test.getDescription())
                .title(test.getTitle())
                .duration(test.getDuration())
                .totalQuestions(test.getQuestions())
                .questions(questions)
                .build();
    }
    
}
