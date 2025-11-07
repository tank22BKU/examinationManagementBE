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
/*
{
  "userId": "33333333-3333-3333-3333-333333333333",
  "testId": "66666666-6666-6666-6666-666666666666",
  "startTime": "2025-11-10T09:00:00",
  "submitTime":"2025-11-10T09:40:00",
  "actualTime":"00:40:00",
  "testStatus":"SUBMITTED"
}
 */