package com.project.techmonk.questionanswerservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.project.techmonk.questionanswerservice.Utils.View;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@JsonView(View.Detailed.class)
public class AnswerMedia extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotBlank
    String mediaUrl;

    @ManyToOne
    @JsonIgnore
    private AnswerDetails answer;

}
