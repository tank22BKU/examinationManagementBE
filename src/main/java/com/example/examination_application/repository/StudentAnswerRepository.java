//// repository/StudentAnswerRepository.java
//package com.example.examination_application.repository;
//
//import com.example.examination_application.entity.StudentAnswer;
//import com.example.examination_application.entity.StudentAnswerKey;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.Optional;
//
//public interface StudentAnswerRepository extends JpaRepository<StudentAnswer, StudentAnswerKey> {
//    Optional<StudentAnswer> findByIdUserIdAndIdAnswerId(String userId, String answerId);
//}
