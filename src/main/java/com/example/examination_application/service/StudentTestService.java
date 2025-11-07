// service/StudentTestService.java
package com.example.examination_application.service;

import com.example.examination_application.dto.StudentTestDTO;
import com.example.examination_application.dto.TestStartDTO;
import com.example.examination_application.dto.TestSubmitDTO;
import com.example.examination_application.entity.*;
import com.example.examination_application.repository.StudentRepository;
import com.example.examination_application.repository.StudentTestRepository;
import com.example.examination_application.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StudentTestService {

    private final StudentTestRepository studentTestRepository;
    private final StudentRepository studentRepository;
    private final TestRepository testRepository;


    @Transactional
    public void saveStudentTest(StudentTestDTO dto){
        StudentTestKey key = new StudentTestKey(
        dto.getUserId(),
        dto.getTestId()
        );
        Student student = studentRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));

        Test test = testRepository.findById(dto.getTestId())
                .orElseThrow(() -> new RuntimeException("Test không tồn tại"));
        test.setTestStatus(dto.getTestStatus());
        testRepository.save(test);

        StudentTest entity= new StudentTest();
        entity.setId(key);
        entity.setStudent(student);
        entity.setTest(test);
        entity.setStartTime(dto.getStartTime());
        entity.setActualTime(dto.getActualTime());
        entity.setSubmitTime(dto.getSubmitTime());

        studentTestRepository.save(entity);


    }
//    // 1️⃣ Start Test
//    @Transactional
//    public void startTest(TestStartDTO dto) {
//        StudentTestKey key = new StudentTestKey();
//        key.setUserId(dto.getUserId());
//        key.setTestId(dto.getTestId());
//
//        StudentTest test = studentTestRepository.findById(key)
//                .orElseGet(() -> {
//                    StudentTest newTest = new StudentTest();
//                    newTest.setId(key);
//                    return newTest;
//                });
//
//        test.setStartTime(LocalDateTime.now());
//        studentTestRepository.save(test);
//    }
//    @Transactional
//    public void submitTest(TestSubmitDTO dto) {
//        StudentTestKey key = new StudentTestKey();
//        key.setUserId(dto.getUserId());
//        key.setTestId(dto.getTestId());
//
//        StudentTest test = studentTestRepository.findById(key)
//                .orElseThrow(() -> new RuntimeException("Test not found for student"));
//
//        LocalDateTime now = LocalDateTime.now();
//        test.setSubmitTime(now);
//
//        if (test.getStartTime() != null)
//            test.setActualTime(java.time.LocalTime.ofNanoOfDay(
//                    Duration.between(test.getStartTime(), now).toNanos()));
//
//        studentTestRepository.save(test);
//    }
}
