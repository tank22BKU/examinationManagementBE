package com.examManagementBE.pojo.request.ai;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

// --- DTOs cho Response từ Gemini ---

public record Candidate(Content content) {}
