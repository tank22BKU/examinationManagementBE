package com.examManagementBE.controller;

import com.examManagementBE.entity.User;
import com.examManagementBE.entity.assessment.Test;
import com.examManagementBE.entity.student.StudentAnswerLog;
import com.examManagementBE.entity.student.StudentTestAttempt;
import com.examManagementBE.entity.assessment.Question;
import com.examManagementBE.entity.assessment.Answer;
import com.examManagementBE.entity.user.*;
import com.examManagementBE.mapper.TestMapper;
import com.examManagementBE.pojo.request.assessment.TestCreationRequest;
import com.examManagementBE.pojo.request.assessment.AnswerEditRequest;
import com.examManagementBE.pojo.request.assessment.QuestionDeleteRequest;
import com.examManagementBE.pojo.request.assessment.QuestionEditRequest;
import com.examManagementBE.pojo.request.student.StudentAnswerAttempt;
import com.examManagementBE.pojo.request.student.StudentSubmitRequest;
import com.examManagementBE.pojo.response.assessment.TestWithQuestionResponse;
import com.examManagementBE.repository.TeacherRepository;
import com.examManagementBE.repository.UserRepository;
import com.examManagementBE.repository.assessment.TestRepository;
import com.examManagementBE.repository.assessment.AnswerRepository;
import com.examManagementBE.repository.student.StudentAnswerLogRepository;
import com.examManagementBE.repository.student.StudentTestAttemptRepository;
import com.examManagementBE.service.assessment.QuestionService;
import com.examManagementBE.service.assessment.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.examManagementBE.common.constants.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Map;

@RestController
@RequestMapping(EndpointConstants.TEST)
@RequiredArgsConstructor
public class TestController {

    private final StudentAnswerLogRepository studentAnswerLogRepository;
    private final StudentTestAttemptRepository studentTestAttemptRepository;

    private final TestRepository testRepository;
    private final TeacherRepository teacherRepository;
    private final AnswerRepository answerRepository;
    private final QuestionService questionService;
    private final TestService testService;


    @GetMapping("/get_all_tests")
    public ResponseEntity<List<Test>> getAllTests() {
        List<Test> tests = testRepository.findAll();
        return ResponseEntity.ok(tests);
    }

    @PostMapping("/create_new_test")
    public ResponseEntity<Test> createTest(@RequestBody TestCreationRequest request) {
        Optional<Teacher> teacher = teacherRepository.findById(request.getCreatorId());
        Test test = Test.builder().title(request.getTittle())
                .description(request.getDescription())
                .passCode(request.getPassCode())
                .duration(request.getDuration())
                .questions(request.getQuestionCount())
                .submissions(request.getSubmisssionCount())
                .creator(null)
                .build();
        testRepository.save(test);
        questionService.SaveQuestionList(request.getQuestions());
        return ResponseEntity.ok(test);
    }

    @GetMapping("/get_test_by_id/{testId}")
    public ResponseEntity<TestWithQuestionResponse<Question>> getTestById(@PathVariable Integer testId) {
        Test test = testRepository.findById(testId).orElse(null);
        if (test == null) {
            return ResponseEntity.notFound().build();
        }
        List<Question> questions = questionService.getAllQuestionsById(test);
        TestWithQuestionResponse<Question> response = TestMapper.testWithQuestionResponseMapper(test, questions);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/verify_passcode/{passcode}")
    public ResponseEntity<TestWithQuestionResponse<Question>> verifyPasscode(@PathVariable String passcode){
        Optional<Test> testOptional = testService.verifyPassCode(passcode);
        if (testOptional.isPresent()) {
            Test test = testOptional.get();
            List<Question> questions = questionService.getAllQuestionsById(test);
            TestWithQuestionResponse<Question> response = TestMapper.testWithQuestionResponseMapper(test, questions);
            return ResponseEntity.ok(response);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/edit_test/{testId}")
    public ResponseEntity<?> editTest(@PathVariable Integer testId, @RequestBody List<QuestionEditRequest> questions) {
        // Implementation for editing a test
        Test test = testService.getTestByIdOptional(testId);
        if (test == null) {
            return ResponseEntity.notFound().build();
        }
        for (QuestionEditRequest question: questions) {
            Question existedQuestion = questionService.getAllQuestionsById(test).stream()
            .filter(q -> q.getQuestionId().equals(question.getQuestionId()))
            .findFirst().orElse(null);
            if (existedQuestion == null)  {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question not found");
            }
            existedQuestion.setQuestionText(question.getQuestionText());
            List<AnswerEditRequest> editedAnswer = question.getAnswers();
            List<Answer> existedAnswers = questionService.getAnswersByQuestionId(existedQuestion.getQuestionId());
            existedAnswers.stream().forEach(ans -> {
                AnswerEditRequest updatedAns = editedAnswer.stream()
                .filter(a -> a.getAnswerId().equals(ans.getAnswerId()))
                .findFirst().orElse(null);
                if (updatedAns != null) {
                    ans.setAnswerText(updatedAns.getAnswerText());
                    ans.setCorrectAnswer(updatedAns.getCorrectAnswer());
                }else {
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body("Answer of this question not found");
                }
            });

        }
        TestWithQuestionResponse<QuestionEditRequest> response = TestMapper.testWithQuestionResponseMapper(test, questions);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/delete_test/{testId}")
    public ResponseEntity<?> deleteTest(@PathVariable Integer testId, @RequestBody TestCreationRequest request) {
        // Implementation for deleting a test
        Test test = testService.getTestByIdOptional(testId);
        if (test != null) {
            testRepository.delete(test);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/delete_question/{testId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Integer testId, @RequestBody List<QuestionDeleteRequest> questions) {
        // Implementation for deleting a question
        Test test = testService.getTestByIdOptional(testId);
        if (test == null) {
            return ResponseEntity.notFound().build();
        }else {
            for (QuestionDeleteRequest question: questions) {
                boolean foundQuestionId = questionService.deleteQuestionById(question.getQuestionId(), testId);
                if (!foundQuestionId) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question not found");
                }
            }
        TestWithQuestionResponse<QuestionDeleteRequest> response = TestMapper.testWithQuestionResponseMapper(test, questions);
        return ResponseEntity.ok(response);
        }
    
    }
    @PostMapping("/student_submit_test/{testId}")
    public ResponseEntity<?> studentSubmitTest(@PathVariable Integer testId, @RequestBody List<StudentSubmitRequest> studentAnswers) {
        // Lưu các câu trả lời của student vào bảng Student_Answer_Log
        if (testRepository.findById(testId).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Not-found test"));
        }
        List<StudentAnswerLog> answerLogsToSave = new java.util.ArrayList<>();
        for (StudentSubmitRequest sa : studentAnswers) {
            List<StudentAnswerAttempt> logs = sa.getAnswerLogs();
            if (logs != null) {
                for (StudentAnswerAttempt saaSelected : logs) {
                    Integer answerId = saaSelected.getSelected_answer_id();

                    // Kiểm tra answerId có tồn tại trong DB không
                    if (!answerRepository.existsById(answerId)) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(Map.of("error", "Invalid Answer_ID: " + answerId));
                    }
                    answerLogsToSave.add(StudentAnswerLog.builder()
                            .studentUserId(sa.getStudentId())
                            .selectedAnswerId(saaSelected.getSelected_answer_id())
                            .build());
                }
            }
        }
        studentAnswerLogRepository.saveAll(answerLogsToSave);
        // Lưu thông tin làm bài của student vào bảng Student_Test_Attempt
        studentAnswers.stream().forEach(sa -> {
            StudentTestAttempt sta = StudentTestAttempt.builder()
                    .studentUserId(sa.getStudentId())
                    .testId(testId)
                    .startTime(sa.getStartTime())
                    .submitTime(sa.getSubmitTime())
                    .build();
            studentTestAttemptRepository.save(sta);
        });
        return ResponseEntity.ok(Map.of("message", "Student test submitted successfully"));
    }
}
