package com.project.techmonk.questionanswerservice.controller;

import com.project.techmonk.questionanswerservice.entity.TagDetails;
import com.project.techmonk.questionanswerservice.model.CreateTagRequest;
import com.project.techmonk.questionanswerservice.service.TagService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class TagController {
    private TagService tagService;

    @PostMapping("/tags")
    public TagDetails createTag(@RequestBody @Valid CreateTagRequest createTagRequest) {
        return tagService.createTag(createTagRequest);
    }

    @GetMapping("/tag/{tag}")
    @Cacheable(value = "getTag")
    public TagDetails getTag(@PathVariable("tag") String tag) {
        return tagService.getTag(tag);
    }

    @GetMapping("/tags")
    @Cacheable(value = "getTags")
    public List<TagDetails> getTags(@RequestParam(value = "searchQuery", required = false) String searchQuery) {
        return tagService.searchTags(searchQuery);
    }

}
