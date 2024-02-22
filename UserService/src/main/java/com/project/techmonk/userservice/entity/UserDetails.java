package com.project.techmonk.userservice.entity;

import com.project.techmonk.userservice.model.UserStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDetails extends Auditable {
    @Id
    String email;

    @Column(unique = true)
    private String username;
    private String password;
    private String fullName;
    private UserStatus userStatus;
}
