//package com.example.examination_application.controller;
//
//import org.springframework.web.bind.annotation.*;
//import lombok.RequiredArgsConstructor;
//import com.example.examination_application.dto.*;
//import com.example.examination_application.service.TestService;
//
//@RestController
//@RequestMapping("/api/tests")
//@RequiredArgsConstructor
//public class TestController {
//
//    private final TestService testService;
//
//    // 1️⃣ Auto-save progress
//    @PostMapping("/auto-save")
//    public String autoSaveProgress(@RequestBody TestProgressDTO progressDTO) {
//        testService.autoSaveProgress(progressDTO);
//        return "Progress auto-saved successfully.";
//    }
//
//    // 2️⃣ Save or update answer
//    @PostMapping("/save-answer")
//    public String saveAnswer(@RequestBody StudentAnswerDTO answerDTO) {
//        testService.saveStudentAnswer(answerDTO);
//        return "Answer saved successfully.";
//    }
//
//    // 3️⃣ Confirm submission
//    @PostMapping("/submit")
//    public TestProgressDTO submitTest(@RequestParam String testId, @RequestParam String userId) {
//        return testService.submitTest(testId, userId);
//    }
//}
