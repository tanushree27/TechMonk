package com.project.techmonk.questionanswerservice.repository;

import com.project.techmonk.questionanswerservice.entity.AnswerDetails;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerDetailsRepository extends PagingAndSortingRepository<AnswerDetails, Long>, CrudRepository<AnswerDetails, Long> {
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update AnswerDetails ad set ad.totalVotes = ad.totalVotes + :addVotes where ad.id = :answerId")
    void updateVote (@Param("answerId") Long answerId, @Param("addVotes") int addVotes);
}
