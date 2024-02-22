package com.project.techmonk.questionanswerservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.project.techmonk.questionanswerservice.Utils.View;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@JsonView(View.Default.class)
public class QuestionDetails extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotBlank
    @Size(max = 4000, message = "Text must be at most 4000 characters")
    private String questionText;
    private int totalVotes;

    @NotBlank
    private String authorEmail;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @JsonView(View.Detailed.class)
    private List<AnswerDetails> answers;

    @ManyToMany
    List<TagDetails> tags;

    @OneToMany(mappedBy = "question")
    @JsonIgnore
    List<QuestionVotes> votes;
}
