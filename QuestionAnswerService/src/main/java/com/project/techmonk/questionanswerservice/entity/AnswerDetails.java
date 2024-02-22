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
@Table(indexes = {@Index(columnList = "question_id"), @Index(columnList = "parent_answer_id")})
@EqualsAndHashCode(callSuper = true)
@JsonView(View.Detailed.class)
public class AnswerDetails extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    @JsonIgnore
    private QuestionDetails question;

    @ManyToOne
    @JsonIgnore
    private AnswerDetails parentAnswer;

    @OneToMany(mappedBy = "parentAnswer")
    private List<AnswerDetails> nestedAnswers;

    @NotBlank(message = "Text is required")
    @Size(max = 500, message = "Text must be at most 500 characters")
    private String text;

    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL)
    private List<AnswerMedia> mediaList;

    @OneToMany(mappedBy = "answer")
    @JsonIgnore
    List<AnswerVotes> votes;

    private int totalVotes;
}
