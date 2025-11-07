package com.examManagementBE.controller;

import com.examManagementBE.common.constants.EndpointConstants;
import com.examManagementBE.entity.assessment.StudentTestAttemptId;
import com.examManagementBE.pojo.request.submission.SubmissionRequest;
import com.examManagementBE.pojo.response.assessment.TestAttemptHistoryDetailResponse;
import com.examManagementBE.service.TestSubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(EndpointConstants.PROGRESS)
@RequiredArgsConstructor
public class TestSubmissionController {

    private final TestSubmissionService testSubmissionService;

    @PostMapping(EndpointConstants.SUBMISSION)
    public ResponseEntity<Boolean> submitTest(@RequestBody SubmissionRequest request) {
        return ResponseEntity.ok(testSubmissionService.submitTest(request));
    }

    @GetMapping(EndpointConstants.CURRENT_PROGRESS)
    public ResponseEntity<TestAttemptHistoryDetailResponse> getCurrentProgress(@RequestBody StudentTestAttemptId studentTestAttemptId){
        return ResponseEntity.ok(testSubmissionService.getCurrentProgress(studentTestAttemptId));
    }

}
