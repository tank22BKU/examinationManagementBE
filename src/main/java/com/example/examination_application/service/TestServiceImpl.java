//package com.example.examination_application.service;
//
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import com.example.examination_application.dto.TestProgressDTO;
//import com.example.examination_application.dto.StudentAnswerDTO;
//import com.example.examination_application.service.TestService;
//import com.example.examination_application.entity.*;
//import com.example.examination_application.repository.*;
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class TestServiceImpl implements TestService {
//
//    private final TestRepository testRepository;
//    private final StudentAnswerRepository studentAnswerRepository;
//    private final AnswerRepository answerRepository;
//    private final QuestionRepository questionRepository;
//
//    @Override
//    @Transactional
//    public void autoSaveProgress(TestProgressDTO dto) {
//        Test test = testRepository.findById(dto.getTestId())
//                .orElseThrow(() -> new RuntimeException("Test not found"));
//        test.setTestStatus(dto.getTestStatus());
//        test.setTestScore(dto.getTestScore());
//        testRepository.save(test);
//    }
//
//    @Override
//    @Transactional
//    public void saveStudentAnswer(StudentAnswerDTO dto) {
//        StudentAnswer answer = studentAnswerRepository.findByUserIdAndAnswerId(dto.getUserId(), dto.getAnswerId())
//                .orElse(new StudentAnswer());
//
//        answer.setUserId(dto.getUserId());
//        answer.setAnswerId(dto.getAnswerId());
//        answer.setStudentAnswerText(dto.getStudentAnswerText());
//        studentAnswerRepository.save(answer);
//    }
//
//    @Override
//    @Transactional
//    public TestProgressDTO submitTest(String testId, String userId) {
//        Test test = testRepository.findById(testId)
//                .orElseThrow(() -> new RuntimeException("Test not found"));
//
//        // Khi nộp bài
//        test.setTestStatus(TestStatus.submitted);
//        testRepository.save(test);
//
//        // Tính điểm (giả lập)
//        double score = studentAnswerRepository.calculateScoreForTest(userId, testId);
//        test.setTestScore((int) score);
//        testRepository.save(test);
//
//        return TestProgressDTO.builder()
//                .testId(testId)
//                .userId(userId)
//                .testStatus(TestStatus.submitted)
//                .testScore((int) score)
//                .build();
//    }
//}
