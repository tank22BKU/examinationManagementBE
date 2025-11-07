package com.examManagementBE.service;

import com.examManagementBE.dto.StudentTestDTO;
import com.examManagementBE.entity.assessment.StudentTestAttempt;
import com.examManagementBE.entity.assessment.StudentTestAttemptId;
import com.examManagementBE.entity.assessment.Test;
import com.examManagementBE.entity.user.Student;
import com.examManagementBE.repository.StudentRepository;
import com.examManagementBE.repository.assessment.StudentTestRepository;
import com.examManagementBE.repository.assessment.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudentTestService {

    private final StudentTestRepository studentTestRepository;
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

        studentTestRepository.save(entity);


    }
}
