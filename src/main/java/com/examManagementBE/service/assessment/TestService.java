package com.examManagementBE.service.assessment;

import com.examManagementBE.entity.assessment.Test;
import com.examManagementBE.entity.user.Teacher;
import com.examManagementBE.pojo.request.assessment.TestCreationRequest;
import com.examManagementBE.repository.TeacherRepository;
import com.examManagementBE.repository.assessment.TestRepository;
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
}
