package com.examManagementBE.controller;

import com.examManagementBE.dto.StudentQuestionDTO;
import com.examManagementBE.service.StudentQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student-answer")
public class StudentQuestionController {
    private final StudentQuestionService studentQuestionService;
    @PostMapping
    public ResponseEntity<String> saveStudentAnswer(@RequestBody StudentQuestionDTO studentQuestionDTO){
        studentQuestionService.saveStudentAnswer(studentQuestionDTO);
        return ResponseEntity.ok("Save student answer successfully");
    }
}
//{
//        "userId": "33333333-3333-3333-3333-333333333333",
//        "questionId": "77777777-7777-7777-7777-777777777777",
//        "studentAnswerText": "A "
//        }

