package com.examManagementBE.service;

import com.examManagementBE.dto.StudentTestDTO;
import com.examManagementBE.entity.assessment.StudentTestAttempt;
import com.examManagementBE.repository.assessment.StudentTestAttemptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentTestGetService {

    private final StudentTestAttemptRepository studentTestAttemptRepository;

    public List<StudentTestDTO> getStudentTestDTO() {
        List<StudentTestAttempt> attempts = studentTestAttemptRepository.findAll();

        return attempts.stream().map(attempt -> {
            StudentTestDTO dto = new StudentTestDTO();
            dto.setUserId(attempt.getStudent().getUser_ID());
            dto.setTestId(attempt.getTest().getTestId());
            dto.setStartTime(attempt.getStartTime());
            dto.setSubmitTime(attempt.getSubmitTime()); // nếu đã lưu điểm
            dto.setTestStatus(attempt.getTest().getTestStatus());
            dto.setActualTime(attempt.getActualTime());
            // Bạn có thể map thêm danh sách câu hỏi nếu muốn
            return dto;
        }).toList();
    }
}
