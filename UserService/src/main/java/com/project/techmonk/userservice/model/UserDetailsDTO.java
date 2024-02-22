package com.project.techmonk.userservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDetailsDTO {
    String username;
    @JsonProperty("full_name")
    String fullName;
    String email;
    @JsonProperty("user_status")
    UserStatus userStatus;
}

