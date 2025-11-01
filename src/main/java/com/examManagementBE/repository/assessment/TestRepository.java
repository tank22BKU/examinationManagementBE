package com.examManagementBE.repository.assessment;

import com.examManagementBE.entity.assessment.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Integer> {
}
