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
public class CreateAnswerRequest {
    @NotBlank(message = "Answer text is mandatory")
    @JsonProperty("answer_text")
    String answerText;

    @Pattern(regexp = EMAIL_PATTERN, message = "Please enter valid Email!")
    @JsonProperty("author_email")
    String authorEmail;

    @JsonProperty("parent_answer_id")
    Long parentAnswerId;

    @JsonProperty("file_urls")
    @NotNull
    List<@Pattern(regexp = "^https?://.*\\.(?:png|jpg|mp4|mov|mkv)$", message = "Please enter valid media urls!") String> fileUrls;
}
