package com.examManagementBE.repository.assessment;

import com.examManagementBE.entity.assessment.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
