package com.examManagementBE.controller;

import com.examManagementBE.common.constants.EndpointConstants;
import com.examManagementBE.entity.assessment.Test;
import com.examManagementBE.pojo.request.assessment.TestCreationRequest;
import com.examManagementBE.service.assessment.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(EndpointConstants.TEST)
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping(EndpointConstants.ALL_TEST)
    public ResponseEntity<List<Test>> getAllTests() {
        List<Test> tests = testService.getAllTests();
        return ResponseEntity.ok(tests);
    }

    @PostMapping("/")
    public ResponseEntity<Boolean> createTest(@RequestBody TestCreationRequest request) {
        boolean res = testService.saveTest(request);
        return ResponseEntity.ok(res);
    }

}
