package com.project.techmonk.questionanswerservice.service;

import com.project.techmonk.questionanswerservice.model.VoteRequest;
import org.springframework.stereotype.Service;

@Service
public interface VoteService {
    void voteQuestion (Long questionId,  VoteRequest voteRequest);
    void voteAnswer(Long answerId, VoteRequest voteRequest);
}
