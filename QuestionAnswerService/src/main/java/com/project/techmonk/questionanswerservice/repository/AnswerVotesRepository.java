package com.project.techmonk.questionanswerservice.repository;

import com.project.techmonk.questionanswerservice.entity.AnswerDetails;
import com.project.techmonk.questionanswerservice.entity.AnswerVotes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnswerVotesRepository extends JpaRepository<AnswerVotes, Long> {
    Optional<AnswerVotes> findByAnswerAndEmail(AnswerDetails answerDetails, String email);
}
