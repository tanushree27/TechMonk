package com.project.techmonk.questionanswerservice.service;

import com.project.techmonk.questionanswerservice.entity.QuestionDetails;
import com.project.techmonk.questionanswerservice.model.CreateQuestionRequest;
import com.project.techmonk.questionanswerservice.model.SortType;
import com.project.techmonk.questionanswerservice.model.UpdateQuestionTagsRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService {
    QuestionDetails createQuestion (CreateQuestionRequest createQuestionRequest);
    List<QuestionDetails> getQuestions (String searchQuery, SortType sortType, List<String> tags, int page, int limit);
    QuestionDetails getQuestion (Long id);
    QuestionDetails updateTags(Long questionId, UpdateQuestionTagsRequest updateQuestionTagsRequest);
}
