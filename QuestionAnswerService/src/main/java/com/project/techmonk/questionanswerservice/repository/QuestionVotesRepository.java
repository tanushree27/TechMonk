package com.project.techmonk.questionanswerservice.repository;

import com.project.techmonk.questionanswerservice.entity.QuestionDetails;
import com.project.techmonk.questionanswerservice.entity.QuestionVotes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionVotesRepository extends JpaRepository<QuestionVotes, Long> {
//    List<TagDetails> find(List<String> tags);
    Optional<QuestionVotes> findByQuestionAndEmail(QuestionDetails questionDetails, String email);
}
