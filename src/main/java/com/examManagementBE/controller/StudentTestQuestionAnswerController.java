package com.examManagementBE.controller;

import com.examManagementBE.dto.StudentTestAttemptDTO;
import com.examManagementBE.service.StudentTestQuestionAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student-test-question-answer")
@RequiredArgsConstructor
public class StudentTestQuestionAnswerController {

    private final StudentTestQuestionAnswerService studentTestQuestionAnswerService;

    // API GET để lấy danh sách attempt của một student
    @GetMapping("/{studentId}")
    public ResponseEntity<List<StudentTestAttemptDTO>> getStudentTestAttempts(
            @PathVariable Integer studentId) {

        List<StudentTestAttemptDTO> attempts = studentTestQuestionAnswerService.getAttemptsByStudentId(studentId);

        return ResponseEntity.ok(attempts);
    }
}
