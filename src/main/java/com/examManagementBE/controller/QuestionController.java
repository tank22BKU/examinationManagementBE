package com.examManagementBE.controller;

import com.examManagementBE.entity.assessment.Question;
import com.examManagementBE.entity.assessment.Test;
import com.examManagementBE.pojo.request.assessment.TestCreationRequest;
import com.examManagementBE.repository.assessment.QuestionRepository;
import com.examManagementBE.repository.assessment.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionRepository questionRepository;

}
