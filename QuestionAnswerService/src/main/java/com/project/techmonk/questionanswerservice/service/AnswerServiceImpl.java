package com.project.techmonk.questionanswerservice.service;

import com.project.techmonk.questionanswerservice.entity.AnswerDetails;
import com.project.techmonk.questionanswerservice.entity.AnswerMedia;
import com.project.techmonk.questionanswerservice.exception.AnswerNotFoundException;
import com.project.techmonk.questionanswerservice.exception.QuestionNotFoundException;
import com.project.techmonk.questionanswerservice.model.CreateAnswerRequest;
import com.project.techmonk.questionanswerservice.repository.AnswerDetailsRepository;
import com.project.techmonk.questionanswerservice.repository.AnswerMediaRepository;
import com.project.techmonk.questionanswerservice.repository.QuestionDetailsRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    AnswerDetailsRepository answerDetailsRepository;
    QuestionDetailsRepository questionDetailsRepository;
    AnswerMediaRepository answerMediaRepository;

    @Override
    @CacheEvict(value = "question", key = "#p0")
    public AnswerDetails createAnswer(Long questionId, CreateAnswerRequest createAnswerRequest) {
        AnswerDetails answerDetails = new AnswerDetails();
        answerDetails.setQuestion(questionDetailsRepository.findById(questionId).orElseThrow(QuestionNotFoundException::new));
        answerDetails.setText(createAnswerRequest.getAnswerText());
        answerDetails.setMediaList(new ArrayList<>());
        if(createAnswerRequest.getParentAnswerId() != null){
            answerDetails.setParentAnswer(answerDetailsRepository.findById(createAnswerRequest.getParentAnswerId()).orElseThrow(AnswerNotFoundException::new));
            answerDetails.setQuestion(null);
        }
        for (String mediaUrl : createAnswerRequest.getFileUrls()) {
            if (mediaUrl != null && !mediaUrl.isBlank()) {
                AnswerMedia answerMedia = new AnswerMedia();
                answerMedia.setAnswer(answerDetails);
                answerMedia.setMediaUrl(mediaUrl);
                answerDetails.getMediaList().add(answerMedia);
            }
        }
        return answerDetailsRepository.save(answerDetails);
    }
}
