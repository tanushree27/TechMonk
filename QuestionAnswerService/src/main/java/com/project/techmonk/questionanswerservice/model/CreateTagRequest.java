package com.project.techmonk.questionanswerservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.project.techmonk.questionanswerservice.Utils.ConstantUtil.EMAIL_PATTERN;

@Data
@NoArgsConstructor
public class CreateTagRequest {

    @Pattern(regexp = "^[a-z]+$", message = "Tag must be in smallcase and should not be blank")
    private String tag;

    @JsonProperty("created_by_user_email")
    @Pattern(regexp = EMAIL_PATTERN, message = "Please enter valid email!")
    private String createdByUserEmail;
}
