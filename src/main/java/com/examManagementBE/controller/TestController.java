package com.examManagementBE.controller;

import com.examManagementBE.entity.User;
import com.examManagementBE.entity.assessment.Test;
import com.examManagementBE.entity.user.*;
import com.examManagementBE.pojo.request.assessment.TestCreationRequest;
import com.examManagementBE.repository.TeacherRepository;
import com.examManagementBE.repository.UserRepository;
import com.examManagementBE.repository.assessment.TestRepository;
import com.examManagementBE.service.assessment.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TestController {

    private final TestRepository testRepository;
    private final TeacherRepository teacherRepository;
    private final QuestionService questionService;

    @GetMapping("/get_all_tests")
    public ResponseEntity<List<Test>> getAllTests() {
        List<Test> tests = testRepository.findAll();
        return ResponseEntity.ok(tests);
    }

    @PostMapping("/create_new_test")
    public ResponseEntity<Test> createTest(@RequestBody TestCreationRequest request) {
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
        return ResponseEntity.ok(test);
    }

}
