package com.project.techmonk.questionanswerservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.project.techmonk.questionanswerservice.Utils.ConstantUtil.EMAIL_PATTERN;

@Data
@NoArgsConstructor
public class VoteRequest {
    @JsonProperty("vote_type")
    @NotNull
    VoteType voteType;

    @JsonProperty("user_email")
    @Pattern(regexp = EMAIL_PATTERN, message = "Please enter valid Email!")
    String userEmail;
}
