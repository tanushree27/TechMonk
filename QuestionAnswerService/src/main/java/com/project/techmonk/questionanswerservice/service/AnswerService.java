package com.project.techmonk.questionanswerservice.service;

import com.project.techmonk.questionanswerservice.entity.AnswerDetails;
import com.project.techmonk.questionanswerservice.model.CreateAnswerRequest;
import org.springframework.stereotype.Service;

@Service
public interface AnswerService {
    AnswerDetails createAnswer (Long questionId, CreateAnswerRequest createAnswerRequest);
}
