package com.examManagementBE.service.assessment;
import com.examManagementBE.entity.assessment.Test;
import com.examManagementBE.entity.student.StudentAnswerLog;
import com.examManagementBE.repository.assessment.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepository testRepository;

    public Optional<Test> verifyPassCode(String passCode) {
        return testRepository.findByPassCode(passCode);
    }
    public Test getTestByIdOptional(Integer id) {
        return testRepository.findByTestId(id);
    }
}
