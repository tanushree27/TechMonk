package com.project.techmonk.questionanswerservice.repository;

import com.project.techmonk.questionanswerservice.entity.AnswerMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerMediaRepository extends JpaRepository<AnswerMedia, Long> {
}
