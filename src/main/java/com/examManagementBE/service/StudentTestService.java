package com.examManagementBE.service;

import com.examManagementBE.dto.StudentTestDTO;
import com.examManagementBE.entity.assessment.*;
import com.examManagementBE.entity.user.Student;
import com.examManagementBE.repository.StudentRepository;
import com.examManagementBE.repository.assessment.StudentTestAttemptRepository;
import com.examManagementBE.repository.assessment.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudentTestService {

    private final StudentTestAttemptRepository studentTestAttemptRepository;
    private final StudentRepository studentRepository;
    private final TestRepository testRepository;


    @Transactional
    public void saveStudentTest(StudentTestDTO dto){
        StudentTestAttemptId key = new StudentTestAttemptId(
                dto.getUserId(),
                dto.getTestId()
        );
        Student student = studentRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));

        Test test = testRepository.findById(dto.getTestId())
                .orElseThrow(() -> new RuntimeException("Test không tồn tại"));
        test.setTestStatus(dto.getTestStatus());
        testRepository.save(test);

        StudentTestAttempt entity= new StudentTestAttempt();
        entity.setId(key);
        entity.setStudent(student);
        entity.setTest(test);
        entity.setStartTime(dto.getStartTime());
        entity.setActualTime(dto.getActualTime());
        entity.setSubmitTime(dto.getSubmitTime());

        studentTestAttemptRepository.save(entity);


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
