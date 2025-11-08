package com.examManagementBE.pojo.request.ai;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

// --- DTOs cho Request đến Gemini ---

// 3. Phần chứa văn bản prompt
public record Part(String text) {}