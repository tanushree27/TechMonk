package com.project.techmonk.userservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.techmonk.userservice.utils.ConstantUtil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@Data
public class CreateUserRequest {

    @NotBlank(message = "email is mandatory")
    @Pattern(regexp = ConstantUtil.EMAIL_PATTERN, message = "Please enter a valid email!")
    String email;

    @NotBlank(message = "createdByUserEmail is mandatory")
    String username;

    @NotBlank(message = "fullName is mandatory")
    @JsonProperty("full_name")
    String fullName;

    @Size(min = 6, max = 15)
    String password;
}
