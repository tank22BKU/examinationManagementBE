package com.examManagementBE.pojo.request.ai;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

// --- DTOs cho Response từ Gemini ---

// 1. Response Body chính
public record GeminiResponse(List<Candidate> candidates) {
    // Phương thức trợ giúp để lấy văn bản giải thích một cách an toàn
    public String extractText() {
        try {
            return this.candidates.get(0)
                                  .content()
                                  .parts()
                                  .get(0)
                                  .text();
        } catch (Exception e) {
            // Log lỗi ở đây nếu cần
            return "Không thể trích xuất giải thích từ AI.";
        }
    }
}
