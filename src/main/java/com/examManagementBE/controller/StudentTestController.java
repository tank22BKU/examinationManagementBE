package com.examManagementBE.controller;

import com.examManagementBE.dto.StudentTestDTO;
import com.examManagementBE.service.StudentTestService;
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
    public ResponseEntity<String> studentTest(@RequestBody StudentTestDTO studentTestDTO) {
        studentTestService.saveStudentTest(studentTestDTO);
        return ResponseEntity.ok("Save student test successfully");
    }
}