package com.examManagementBE.service.assessment;

import com.examManagementBE.entity.assessment.Test;
import com.examManagementBE.entity.assessment.Question;
import com.examManagementBE.entity.user.Teacher;
import com.examManagementBE.exception.AppException;
import com.examManagementBE.exception.ErrorCode;
import com.examManagementBE.mapper.TestMapper;
import com.examManagementBE.pojo.request.assessment.QuestionWithAnswerRequest;
import com.examManagementBE.pojo.request.assessment.TestCreationRequest;
import com.examManagementBE.repository.TeacherRepository;
import com.examManagementBE.repository.assessment.TestRepository;
import com.examManagementBE.repository.assessment.TestWithQuestionResponse;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;
    private final TeacherRepository teacherRepository;
    private final QuestionService questionService;

    public List<Test> getAllTests(){
        List<Test> tests = testRepository.findAll();

        if(ObjectUtils.isEmpty(tests)){
            return new ArrayList<>();
        }
        return tests;
    }

    public Optional<Test> verifyPassCode(String passCode) {
        return testRepository.findByPassCode(passCode);
    }
    public List<QuestionWithAnswerRequest> getTestWithAllQuestions(Test test) {
        List<Question> questions = questionService.getAllQuestionsById(test);
        List<QuestionWithAnswerRequest> questionWithAnswers = questions.stream().map(question -> {
            return QuestionWithAnswerRequest.builder()
                .questionId(question.getQuestionId())
                .questionText(question.getQuestionText())
                .score(question.getScore())
                .answers(questionService.getAllAnswersByQuestion(question))
                .build();
        }).toList();
        return questionWithAnswers;
    }
    public boolean saveTest(TestCreationRequest request){
        Optional<Teacher> teacher = teacherRepository.findById(request.getCreatorId());
        Test test = Test.builder().title(request.getTittle())
                .description(request.getDescription())
                .passCode(request.getPassCode())
                .duration(request.getDuration())
                .questions(request.getQuestionCount())
                .submissions(request.getSubmisssionCount())
                .creator(null)
                .build();
        testRepository.save(test);
        questionService.SaveQuestionList(request.getQuestions());

        return true;
    }
    public TestWithQuestionResponse<QuestionWithAnswerRequest> getTestWithPasscode(String passcode) {
        Optional<Test> testOptional = this.verifyPassCode(passcode);
        if (testOptional.isPresent()) {
            Test test = testOptional.get();
            List<QuestionWithAnswerRequest> questionWithAnswers = this.getTestWithAllQuestions(test);
            TestWithQuestionResponse<QuestionWithAnswerRequest> response = TestMapper.testWithQuestionResponseMapper(test, questionWithAnswers);
            return response;
        }else {
            throw new AppException(ErrorCode.NOT_FOUND);
        }
    }
}
