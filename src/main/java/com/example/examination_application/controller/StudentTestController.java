package com.example.examination_application.controller;

import com.example.examination_application.dto.StudentTestDTO;
import com.example.examination_application.service.StudentTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student-test")
@RequiredArgsConstructor
public class StudentTestController {
    private final StudentTestService studentTestService;
    @PostMapping
    public ResponseEntity<String> studentTest(@RequestBody StudentTestDTO studentTestDTO){
        studentTestService.saveStudentTest(studentTestDTO);
        return ResponseEntity.ok("Save student test successfully");
    }



//    private final StudentAnswerService studentAnswerService;
//    private final StudentTestService studentTestService;
//
//    // 1️⃣ Start Test
//    @PostMapping("/start-test")
//    public ResponseEntity<String> startTest(@RequestBody TestStartDTO dto) {
//        studentTestService.startTest(dto);
//        return ResponseEntity.ok("Test started successfully");
//    }
//
//    // 1️⃣ Auto-Save Progress
//    @PostMapping("/auto-save")
//    public ResponseEntity<String> autoSave(@RequestBody StudentAutoSaveDTO dto) {
//        studentAnswerService.autoSave(dto);
//        return ResponseEntity.ok("Auto-saved successfully");
//    }
//
//    // 2️⃣ Save Answer
//    @PostMapping("/answer")
//    public ResponseEntity<String> saveAnswer(@RequestBody StudentAnswerDTO dto) {
//        studentAnswerService.saveAnswer(dto);
//        return ResponseEntity.ok("Answer saved successfully");
//    }
//
//    // 3️⃣ Submit Test
//    @PostMapping("/submit")
//    public ResponseEntity<String> submitTest(@RequestBody TestSubmitDTO dto) {
//        studentTestService.submitTest(dto);
//        return ResponseEntity.ok("Test submitted successfully");
//    }
}
