package com.example.examination_application.service;

import com.example.examination_application.dto.StudentQuestionDTO;
import com.example.examination_application.entity.Question;
import com.example.examination_application.entity.Student;
import com.example.examination_application.entity.StudentQuestion;
import com.example.examination_application.entity.StudentQuestionKey;
import com.example.examination_application.repository.QuestionRepository;
import com.example.examination_application.repository.StudentQuestionRepository;
import com.example.examination_application.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
/*

 */