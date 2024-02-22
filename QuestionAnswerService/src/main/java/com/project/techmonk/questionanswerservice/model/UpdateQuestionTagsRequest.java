package com.project.techmonk.questionanswerservice.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.project.techmonk.questionanswerservice.Utils.ConstantUtil.EMAIL_PATTERN;

@Data
@NoArgsConstructor
public class UpdateQuestionTagsRequest {
    @Pattern(regexp = EMAIL_PATTERN, message = "Please enter a valid email!")
    String email;
    @NotNull
    List<String> tags;
}
