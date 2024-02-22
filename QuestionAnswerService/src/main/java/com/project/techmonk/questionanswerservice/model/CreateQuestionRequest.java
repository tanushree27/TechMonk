package com.project.techmonk.questionanswerservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.project.techmonk.questionanswerservice.Utils.ConstantUtil.EMAIL_PATTERN;

@NoArgsConstructor
@Data
public class CreateQuestionRequest {
    @NotBlank(message = "Question text is mandatory")
    @JsonProperty("question_text")
    String questionText;

    @Pattern(regexp = EMAIL_PATTERN, message = "Please enter valid Email!")
    @JsonProperty("author_email")
    String authorEmail;

    @NotNull
    List<String> tags;
}
