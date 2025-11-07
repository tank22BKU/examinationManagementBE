package com.examManagementBE.pojo.request.ai;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

// --- DTOs cho Request đến Gemini ---

// 1. Request Body chính
public record GeminiRequest(List<Content> contents) {}
