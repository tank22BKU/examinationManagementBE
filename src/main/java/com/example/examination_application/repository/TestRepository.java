package com.example.examination_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.examination_application.entity.Test;

public interface TestRepository extends JpaRepository<Test, String> {
}
