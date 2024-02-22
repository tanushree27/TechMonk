package com.project.techmonk.questionanswerservice.service;

import com.project.techmonk.questionanswerservice.entity.TagDetails;
import com.project.techmonk.questionanswerservice.exception.TagAlreadyPresentException;
import com.project.techmonk.questionanswerservice.exception.TagNotFoundException;
import com.project.techmonk.questionanswerservice.model.CreateTagRequest;
import com.project.techmonk.questionanswerservice.repository.TagDetailsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TagServiceImpl implements TagService {
    private TagDetailsRepository tagDetailsRepository;

    @Override
    public TagDetails createTag(CreateTagRequest createTagRequest) {
        tagDetailsRepository.findById(createTagRequest.getTag()).ifPresent(i -> {throw new TagAlreadyPresentException(); });
        TagDetails tagDetails = new TagDetails();
        tagDetails.setTag(createTagRequest.getTag());
        tagDetails.setCreatedByUserEmail(createTagRequest.getCreatedByUserEmail());
        return tagDetailsRepository.save(tagDetails);
    }

    @Override
    public List<TagDetails> getAllTags() {
        return tagDetailsRepository.findAll();
    }

    @Override
    public TagDetails getTag(String tag) {
        return tagDetailsRepository.findById(tag).orElseThrow(TagNotFoundException::new);
    }

    @Override
    public List<TagDetails> searchTags(String searchQuery) {
        if (searchQuery != null && !searchQuery.isBlank())
            return tagDetailsRepository.findAllByTagContainingIgnoreCase(searchQuery);
        return tagDetailsRepository.findAll();
    }


}
