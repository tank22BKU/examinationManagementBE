package com.examManagementBE.service;

import com.examManagementBE.dto.StudentQuestionDTO;
import com.examManagementBE.entity.assessment.StudentQuestion;
import com.examManagementBE.entity.user.Student;
import com.examManagementBE.entity.assessment.Question;
import com.examManagementBE.entity.assessment.StudentQuestionKey;
import com.examManagementBE.repository.StudentRepository;
import com.examManagementBE.repository.assessment.QuestionRepository;
import com.examManagementBE.repository.assessment.StudentQuestionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentQuestionService {
    private final StudentQuestionRepository studentQuestionRepository;
    private final StudentRepository studentRepository;
    private final QuestionRepository questionRepository;
    @Transactional
    public void saveStudentAnswer(StudentQuestionDTO dto) {

        // 1. Tạo composite key
        StudentQuestionKey key = new StudentQuestionKey(
                dto.getUserId(),
                dto.getQuestionId()
        );

        // 2. Lấy entity thật trong DB
        Student student = studentRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));

        Question question = questionRepository.findById(dto.getQuestionId())
                .orElseThrow(() -> new RuntimeException("Question không tồn tại"));

        // 3. Tạo entity
        StudentQuestion entity = new StudentQuestion();
        entity.setId(key);
        entity.setStudent(student);
        entity.setQuestion(question);
        entity.setStudentAnswerText(dto.getStudentAnswerText());

        // 4. Lưu
        studentQuestionRepository.save(entity);
    }


}
