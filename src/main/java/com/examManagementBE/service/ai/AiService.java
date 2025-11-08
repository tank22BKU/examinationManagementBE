package com.examManagementBE.service.ai;

import com.examManagementBE.pojo.request.ai.*;
import com.examManagementBE.entity.assessment.*;
import com.examManagementBE.exception.AppException;
import com.examManagementBE.exception.ErrorCode;
import com.examManagementBE.repository.assessment.AnswerRepository;
import com.examManagementBE.repository.assessment.QuestionRepository;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import com.examManagementBE.pojo.response.ai.AiExplanationResponse;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@Service
public class AiService {
    private final String apiUrl;
    private final String apiKey;
    private final WebClient webClient;
    private final String modelName;

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public AiService(
            WebClient.Builder webClientBuilder,
            @Value("${ai.api.base-url}") String apiUrl,
            @Value("${ai.api.key:}") String apiKey,
            QuestionRepository questionRepository,
            @Value("${ai.api.model}") String modelName,
            AnswerRepository answerRepository
    ) {
        this.webClient = webClientBuilder
                .baseUrl(apiUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        this.apiKey = apiKey;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.apiUrl = apiUrl;
        this.modelName = modelName;
    }
    public String getExplanationFromText(ExplanationTextRequest request) {
        
        // 1. Xây dựng prompt (trực tiếp từ request, không cần query DB)
        String prompt = buildPrompt(
                request.questionText(),
                request.incorrectAnswerText(),
                request.correctAnswerText()
        );

        // 2. Xây dựng request Gemini (Giữ nguyên)
        Part part = new Part(prompt);
        Content content = new Content(List.of(part));
        GeminiRequest aiRequest = new GeminiRequest(List.of(content));

        // 3. Gọi API Gemini (Giữ nguyên)
        String path = "/v1beta/models/" + this.modelName + ":generateContent"; // (Hoặc path hardcode)
        GeminiResponse aiResponse = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path(path)
                        .queryParam("key", apiKey)
                        .build())
                .bodyValue(aiRequest)
                .retrieve()
                .bodyToMono(GeminiResponse.class)
                .block();

        // 4. Trích xuất văn bản (Giữ nguyên)
        if (aiResponse != null && aiResponse.extractText() != null) {
            String explanationText = aiResponse.extractText().trim();
            
            // Trả về DTO Response (Giữ nguyên từ lần trước)
            return explanationText;
            
        } else {
            // Ném lỗi (Giữ nguyên từ lần trước)
            throw new AppException(ErrorCode.NOT_FOUND); 
        }
    }
    public String getExplanationFromIds(StudentAnswerRequest request) {
        // 1. Truy vấn DB để lấy dữ liệu text
        // (Trong ứng dụng thực tế, bạn nên xử lý .orElseThrow() tốt hơn)
        Question question = questionRepository.findById(request.questionId())
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        Answer incorrectAnswer = answerRepository.findById(request.selectedAnswerId())
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        Answer correctAnswer = answerRepository.findCorrectAnswerByQuestionId(request.questionId())
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        // 2. Xây dựng prompt
        String prompt = buildPrompt(
                question.getQuestionText(),
                incorrectAnswer.getAnswerText(),
                correctAnswer.getAnswerText()
        );

        // 3. Xây dựng request cho Gemini (tái sử dụng code)
        Part part = new Part(prompt);
        Content content = new Content(List.of(part));
        GeminiRequest aiRequest = new GeminiRequest(List.of(content));

        // 4. Gọi API Gemini (tái sử dụng code)
        String path = "/v1beta/models/" + this.modelName + ":generateContent";
        GeminiResponse aiResponse = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path(path) // <-- **THÊM ĐƯỜNG DẪN VÀO ĐÂY**
                        .queryParam("key", apiKey)
                        .build())
                .bodyValue(aiRequest)
                .retrieve()
                .bodyToMono(GeminiResponse.class)
                .block();// Chờ kết quả đồng bộ

        // 5. Trích xuất văn bản (tái sử dụng code)
        if (aiResponse != null && aiResponse.extractText() != null) {
            String explanationText = aiResponse.extractText().trim();
            return explanationText;
        } else {
            throw new AppException(ErrorCode.NOT_FOUND);
        }
    }

    private String buildPrompt(String questionText, String incorrectOptionText, String correctOptionText) {
        boolean isCorrect = (incorrectOptionText == null || incorrectOptionText.isBlank());
        if (isCorrect) {
                return """
                Bạn là một trợ lý giảng dạy AI. Một sinh viên đã trả lời ĐÚNG một câu hỏi trắc nghiệm.
               
               Nhiệm vụ của bạn là:
               1. Đưa ra một lời chúc mừng ngắn gọn (ví dụ: "Chính xác!", "Chúc mừng!").
               2. Ngay sau đó, cung cấp một lời giải thích rõ ràng, súc tích để củng cố kiến thức, giải thích tại sao câu trả lời của họ lại chính xác.

               QUAN TRỌNG: Chỉ trả về lời chúc mừng và phần giải thích. KHÔNG thêm bất kỳ lời chào riêng biệt nào (ví dụ: "Chào bạn") hoặc câu kết (ví dụ: "Hy vọng điều này hữu ích!").

               ---
               Nội dung câu hỏi:
               %s

               Câu trả lời đúng (sinh viên đã chọn):
               %s
               ---
               Giải thích (BẮT ĐẦU TỪ ĐÂY):
               """.formatted(
                questionText,
                correctOptionText
               );                
        }
        return """
               Bạn là một trợ lý giảng dạy AI. Một sinh viên đã trả lời sai một câu hỏi trắc nghiệm.
               Nhiệm vụ của bạn là cung cấp một lời giải thích rõ ràng, súc tích tại sao câu trả lời của sinh viên lại sai và tại sao câu trả lời đúng lại chính xác.

               QUAN TRỌNG: Chỉ trả về phần văn bản giải thích. KHÔNG thêm bất kỳ lời chào nào (ví dụ: "Chào bạn"), lời mở đầu (ví dụ: "Đây là giải thích của bạn:") hoặc câu kết (ví dụ: "Hy vọng điều này hữu ích!"). CHỈ trả về phần giải thích.

               ---
               Nội dung câu hỏi:
               %s

               Câu trả lời sai của sinh viên:
               %s

               Câu trả lời đúng:
               %s
               ---
               Giải thích (BẮT ĐẦU TỪ ĐÂY):
               """.formatted(
                questionText,
                incorrectOptionText,
                correctOptionText
        );
    }
}
