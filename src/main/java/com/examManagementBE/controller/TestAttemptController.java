package com.examManagementBE.controller;

import com.examManagementBE.common.constants.EndpointConstants;
import com.examManagementBE.entity.assessment.StudentTestAttemptId;
import com.examManagementBE.pojo.response.assessment.TestAttemptHistoryDetailResponse;
import com.examManagementBE.pojo.response.assessment.TestAttemptHistoryResponse;
import com.examManagementBE.service.assessment.TestAttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(EndpointConstants.HISTORY)
@RequiredArgsConstructor
public class TestAttemptController {

    private final TestAttemptService testAttemptService;

    @GetMapping(EndpointConstants.TEST + EndpointConstants.ID)
    public ResponseEntity<List<TestAttemptHistoryResponse>> getTestOverviewHistory(@RequestParam int testId){
        List<TestAttemptHistoryResponse> data = testAttemptService.getAllTestAttemptHistoryOverviewOfTest(testId);
        return ResponseEntity.ok(data);
    }

    @GetMapping(EndpointConstants.STUDENT + EndpointConstants.ID)
    public ResponseEntity<List<TestAttemptHistoryResponse>> getUserOverviewHistory(@RequestParam int studentId){
        List<TestAttemptHistoryResponse> data = testAttemptService.getAllTestAttemptHistoryOverviewOfUser(studentId);
        return ResponseEntity.ok(data);
    }

    @PostMapping(EndpointConstants.STUDENT + EndpointConstants.DETAILS)
    public ResponseEntity<TestAttemptHistoryDetailResponse> getUserDetailHistory(@RequestBody StudentTestAttemptId studentTestAttemptId){
        return ResponseEntity.ok(testAttemptService.getDetailTestAttemptHistoryOfUser(studentTestAttemptId));
    }

}
