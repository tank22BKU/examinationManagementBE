// repository/StudentTestRepository.java
package com.example.examination_application.repository;

import com.example.examination_application.entity.StudentTest;
import com.example.examination_application.entity.StudentTestKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentTestRepository extends JpaRepository<StudentTest, StudentTestKey> {
   // Optional<StudentTest> findByIdUserIdAndIdTestId(String userId, String testId);
}
