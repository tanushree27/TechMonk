package com.project.techmonk.questionanswerservice.repository;

import com.project.techmonk.questionanswerservice.entity.TagDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagDetailsRepository extends JpaRepository<TagDetails, String> {
    List<TagDetails> findAllByTagIn(List<String> tags);
    List<TagDetails> findAllByTagContainingIgnoreCase(String searchQuery);
}
