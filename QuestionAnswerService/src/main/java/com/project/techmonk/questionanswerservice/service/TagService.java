package com.project.techmonk.questionanswerservice.service;

import com.project.techmonk.questionanswerservice.entity.TagDetails;
import com.project.techmonk.questionanswerservice.model.CreateTagRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TagService {
     TagDetails createTag(CreateTagRequest tagDetails);
     List<TagDetails> getAllTags();
     TagDetails getTag (String tag);
     List<TagDetails> searchTags(String searchQuery);
}
