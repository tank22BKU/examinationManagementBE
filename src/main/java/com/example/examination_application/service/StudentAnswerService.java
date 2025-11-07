//package com.example.examination_application.service;
//
//import com.example.examination_application.dto.StudentAutoSaveDTO;
//import com.example.examination_application.dto.StudentAnswerDTO;
//import com.example.examination_application.entity.StudentAnswer;
//import com.example.examination_application.entity.StudentAnswerKey;
//import com.example.examination_application.repository.StudentAnswerRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@RequiredArgsConstructor
//public class StudentAnswerService {
//
//    private final StudentAnswerRepository studentAnswerRepository;
//
//    /**
//     * Auto save (lưu tạm)
//     */
//    @Transactional
//    public void autoSave(StudentAutoSaveDTO dto) {
//        StudentAnswerKey key = new StudentAnswerKey();
//        key.setUserId(dto.getUserId());
//        key.setAnswerId(dto.getQuestionId()); // Nếu bạn có answerId riêng thì đổi lại cho đúng
//
//        StudentAnswer studentAnswer = studentAnswerRepository.findById(key)
//                .orElseGet(() -> {
//                    StudentAnswer sa = new StudentAnswer();
//                    sa.setId(key);
//                    return sa;
//                });
//
//        studentAnswer.setStudentAnswerText(dto.getStudentAnswerText());
//        studentAnswerRepository.save(studentAnswer);
//    }
//
//    /**
//     * Save chính thức (người dùng chọn câu trả lời)
//     */
//    @Transactional
//    public void saveAnswer(StudentAnswerDTO dto) {
//        StudentAnswerKey key = new StudentAnswerKey();
//        key.setUserId(dto.getUserId());
//        key.setAnswerId(dto.getQuestionId()); // hoặc dto.getAnswerId() nếu có
//
//        StudentAnswer studentAnswer = studentAnswerRepository.findById(key)
//                .orElseGet(() -> {
//                    StudentAnswer sa = new StudentAnswer();
//                    sa.setId(key);
//                    return sa;
//                });
//
//        studentAnswer.setStudentAnswerText(dto.getStudentAnswerText());
//        studentAnswerRepository.save(studentAnswer);
//    }
//}
