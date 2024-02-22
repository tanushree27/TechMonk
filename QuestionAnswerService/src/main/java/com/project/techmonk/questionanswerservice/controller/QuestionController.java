package com.project.techmonk.questionanswerservice.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.techmonk.questionanswerservice.Utils.View;
import com.project.techmonk.questionanswerservice.entity.AnswerDetails;
import com.project.techmonk.questionanswerservice.entity.QuestionDetails;
import com.project.techmonk.questionanswerservice.model.*;
import com.project.techmonk.questionanswerservice.service.AnswerService;
import com.project.techmonk.questionanswerservice.service.QuestionService;
import com.project.techmonk.questionanswerservice.service.VoteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class QuestionController {
    private QuestionService questionService;
    private AnswerService answerService;
    private VoteService voteService;

    @PostMapping("/questions")
    public QuestionDetails createQuestion (@Valid @RequestBody CreateQuestionRequest createQuestionRequest) {
        return questionService.createQuestion(createQuestionRequest);
    }

    @GetMapping("/question/{question_id}")
    @JsonView(View.Detailed.class)
    public QuestionDetails getQuestionDetails (@PathVariable("question_id") Long questionId) {
        return questionService.getQuestion(questionId);
    }

    @PutMapping("/question/{question_id}/tags")
    public QuestionDetails updateQuestionTags (@PathVariable("question_id") Long questionId, @RequestBody @Valid UpdateQuestionTagsRequest updateQuestionTagsRequest) {
        return questionService.updateTags(questionId, updateQuestionTagsRequest);
    }

    @GetMapping("/questions")
    @JsonView(View.Default.class)
    public List<QuestionDetails> getQuestions (@RequestParam(name = "page", defaultValue = "0") Integer page,
                                               @RequestParam(name = "limit", defaultValue = "20") Integer limit,
                                               @RequestParam(name = "sort_type", defaultValue = "TOP_RATED") SortType sortType,
                                               @RequestParam(name = "tags", required = false) List<String> tags,
                                               @RequestParam(name = "searchQuery", required = false) String searchQuery) {
        return questionService.getQuestions(searchQuery, sortType, tags, page, limit);
    }

    @PostMapping("/question/{question_id}/answers")
    public AnswerDetails createAnswer (@PathVariable("question_id") Long questionId, @Valid @RequestBody CreateAnswerRequest createAnswerRequest) {
        return answerService.createAnswer(questionId, createAnswerRequest);
    }

    @PutMapping("/question/{question_id}/vote")
    public void updateQuestionVote (@PathVariable("question_id") Long questionId, @RequestBody @Valid VoteRequest voteRequest) {
        voteService.voteQuestion(questionId, voteRequest);
    }

    @PutMapping("/question/{question_id}/answer/{answer_id}/vote")
    public void updateQuestionVote (@PathVariable("question_id") Long questionId, @PathVariable("answer_id") Long answerId, @RequestBody @Valid VoteRequest voteRequest) {
        voteService.voteAnswer(answerId, voteRequest);
    }
}
