package com.project.techmonk.questionanswerservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "answer_id", "email" }) })
@Data
@EqualsAndHashCode(callSuper = true)
public class AnswerVotes extends Auditable {
    @Id
    @Column(updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    AnswerDetails answer;

    private String email;

}
