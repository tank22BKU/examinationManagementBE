package com.examManagementBE.controller;

import com.examManagementBE.common.constants.EndpointConstants;
import com.examManagementBE.pojo.response.instructor.DashBoardTestOverviewResponse;
import com.examManagementBE.service.instructor.DashBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(EndpointConstants.DASH_BOARD)
@RequiredArgsConstructor
public class InstructorDashBoardController {

    private final DashBoardService dashBoardService;

    @GetMapping(EndpointConstants.INSTRUCTOR)
    public ResponseEntity<List<DashBoardTestOverviewResponse>> getInstructorDashboard(){
        return ResponseEntity.ok(dashBoardService.getTestStatisticOverview());
    }

}
