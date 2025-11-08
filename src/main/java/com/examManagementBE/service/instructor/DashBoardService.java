package com.examManagementBE.service.instructor;

import com.examManagementBE.entity.assessment.StudentTestAttempt;
import com.examManagementBE.entity.assessment.Test;
import com.examManagementBE.pojo.response.instructor.DashBoardTestOverviewResponse;
import com.examManagementBE.repository.assessment.StudentTestAttemptRepository;
import com.examManagementBE.repository.assessment.TestRepository;
import com.examManagementBE.service.StudentService;
import com.examManagementBE.service.assessment.TestService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class DashBoardService {

    private final StudentTestAttemptRepository studentTestAttemptRepository;
    private final StudentService studentService;
    private final TestRepository testRepository;

    public List<DashBoardTestOverviewResponse> getTestStatisticOverview() {
        List<Test> listTest = testRepository.findAll();
        if(ObjectUtils.isEmpty(listTest)){
            return new java.util.ArrayList<>();
        }

        List<DashBoardTestOverviewResponse> result = new ArrayList<>();

        listTest.forEach(test -> {
            List<StudentTestAttempt> attemptList = studentTestAttemptRepository.findAllByTest(test);
            AtomicInteger maxScore = new AtomicInteger(Integer.MIN_VALUE);
            AtomicInteger minScore = new AtomicInteger(Integer.MAX_VALUE);
            AtomicInteger totalScore = new AtomicInteger(0);

            attemptList.forEach(attempt -> {
                int score = ObjectUtils.isNotEmpty(attempt.getScore()) ? attempt.getScore() : 0;
                totalScore.addAndGet(score);
                if(score > maxScore.get()){
                    maxScore.set(score);
                }
                if(score < minScore.get()){
                    minScore.set(score);
                }
            });

            int averageScore = (ObjectUtils.isEmpty(attemptList) || attemptList.size() == 0) ? 0 : totalScore.get() / attemptList.size();
            maxScore.compareAndSet(Integer.MIN_VALUE, 0);
            minScore.compareAndSet(Integer.MAX_VALUE, 0);
            DashBoardTestOverviewResponse testOverview = DashBoardTestOverviewResponse.builder().testId(test.getTestId()).testName(test.getTitle()).maxScore(maxScore.get()).minScore(minScore.get()).averageScore(averageScore).totalSubmissions(test.getSubmissions()).build();
            result.add(testOverview);

        });

        return result;
    }
}
