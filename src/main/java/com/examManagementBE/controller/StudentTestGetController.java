package com.examManagementBE.controller;

import com.examManagementBE.dto.StudentTestDTO;
import com.examManagementBE.service.StudentTestGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/student-test-get")
@RequiredArgsConstructor
public class StudentTestGetController {
     private final StudentTestGetService studentTestGetService;
     @GetMapping
     List<StudentTestDTO> getStudentTest(){
         return studentTestGetService.getStudentTestDTO();
     }
}
