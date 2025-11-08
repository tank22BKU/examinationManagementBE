package com.examManagementBE.pojo.request.ai;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

// --- DTOs cho Request đến Gemini ---

// 2. Nội dung chứa các phần
public record Content(List<Part> parts) {}