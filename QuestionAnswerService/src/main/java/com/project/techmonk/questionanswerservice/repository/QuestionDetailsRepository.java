package com.project.techmonk.questionanswerservice.repository;

import com.project.techmonk.questionanswerservice.entity.QuestionDetails;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDetailsRepository extends PagingAndSortingRepository<QuestionDetails, Long>, CrudRepository<QuestionDetails, Long> {
    List<QuestionDetails> findAllByTags_TagIn (List<String> tags, Pageable pageable);
    List<QuestionDetails> findAllByQuestionTextContainsIgnoreCase(String test, Pageable pageable);
    List<QuestionDetails> findAllByQuestionTextContainsIgnoreCaseAndTags_TagIn(String test, List<String> tags, Pageable pageable);
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update QuestionDetails qd set qd.totalVotes = qd.totalVotes + :addVotes where qd.id = :questionId")
    void updateVote (@Param("questionId") Long questionId, @Param("addVotes") int addVotes);
}
