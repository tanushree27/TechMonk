package com.project.techmonk.questionanswerservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "question_id", "email" }) })
@Data
@EqualsAndHashCode(callSuper = true)
public class QuestionVotes extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    QuestionDetails question;

    private String email;

}
