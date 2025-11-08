package com.examManagementBE.controller;

import com.examManagementBE.common.constants.EndpointConstants;
import com.examManagementBE.pojo.request.ai.ExplanationTextRequest;
import com.examManagementBE.service.ai.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(EndpointConstants.AI)
public class AiController {
    private final AiService aiService;

    @PostMapping(
            value = "/assessment",
            consumes = MediaType.APPLICATION_JSON_VALUE, // Nhận vào JSON
            produces = {MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_JSON_VALUE}      // Trả về văn bản thô hoặc JSON nếu có lỗi
    )
    public String createExplanation(@RequestBody ExplanationTextRequest request) {
        // Gọi phương thức service MỚI
        return aiService.getExplanationFromText(request);
    }
}
