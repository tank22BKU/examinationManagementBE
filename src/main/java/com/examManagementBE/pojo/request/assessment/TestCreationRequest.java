package com.examManagementBE.pojo.request.assessment;

import com.examManagementBE.entity.assessment.Question;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TestCreationRequest {
    String tittle;
    String description;
    String passCode;
    Boolean status;
    Integer duration;
    Integer questionCount;
    Integer submisssionCount;
    Integer creatorId;
    List<Question> questions;
}
