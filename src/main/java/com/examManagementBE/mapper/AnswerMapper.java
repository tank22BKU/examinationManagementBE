package com.examManagementBE.mapper;

import com.examManagementBE.entity.assessment.Answer;
import com.examManagementBE.pojo.response.assessment.QuestionResult.AnswerResultResponse;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.FIELD, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnswerMapper {

    @Mapping(target = "isCorrect", source = "answer.correctAnswer")
    AnswerResultResponse toAnswerResultResponse(Answer answer);
}
