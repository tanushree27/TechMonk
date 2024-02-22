package com.project.techmonk.questionanswerservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.project.techmonk.questionanswerservice.Utils.View;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@JsonView(View.Default.class)
public class TagDetails extends Auditable {
    @Id
    @Pattern(regexp="^[a-z]+$")
    String tag;

    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    List<QuestionDetails> questionDetailsList;

    String createdByUserEmail;
}
