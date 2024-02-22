package com.project.techmonk.questionanswerservice.service;

import com.project.techmonk.questionanswerservice.entity.AnswerDetails;
import com.project.techmonk.questionanswerservice.entity.AnswerVotes;
import com.project.techmonk.questionanswerservice.entity.QuestionDetails;
import com.project.techmonk.questionanswerservice.entity.QuestionVotes;
import com.project.techmonk.questionanswerservice.model.VoteRequest;
import com.project.techmonk.questionanswerservice.model.VoteType;
import com.project.techmonk.questionanswerservice.repository.AnswerDetailsRepository;
import com.project.techmonk.questionanswerservice.repository.AnswerVotesRepository;
import com.project.techmonk.questionanswerservice.repository.QuestionDetailsRepository;
import com.project.techmonk.questionanswerservice.repository.QuestionVotesRepository;
import com.project.techmonk.questionanswerservice.exception.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VoteServiceImpl implements VoteService {
    QuestionVotesRepository questionVotesRepository;
    QuestionDetailsRepository questionDetailsRepository;
    AnswerDetailsRepository answerDetailsRepository;
    AnswerVotesRepository answerVotesRepository;

    @Override
    public void voteQuestion(Long questionId, VoteRequest voteRequest) {
        QuestionDetails questionDetails = questionDetailsRepository
                .findById(questionId).orElseThrow(QuestionNotFoundException::new);
        if (voteRequest.getVoteType() == VoteType.ADD) {
            questionVotesRepository.findByQuestionAndEmail(questionDetails, voteRequest.getUserEmail())
                    .ifPresent(s -> { throw  new QuestionAlreadyVotedException(); });

            QuestionVotes questionVotes = new QuestionVotes();
            questionVotes.setQuestion(questionDetails);
            questionVotes.setEmail(voteRequest.getUserEmail());
            questionVotesRepository.save(questionVotes);
            questionDetailsRepository.updateVote(questionId, 1);
        } else {
            questionVotesRepository.delete(
                    questionVotesRepository.findByQuestionAndEmail(questionDetails, voteRequest.getUserEmail())
                            .orElseThrow(QuestionVoteAlreadyRemovedException::new)
            );
            questionDetailsRepository.updateVote(questionId, -1);
        }

    }

    @Override
    @Transactional
    public void voteAnswer(Long answerId, VoteRequest voteRequest) {
        AnswerDetails answerDetails = answerDetailsRepository.findById(answerId)
                .orElseThrow(AnswerNotFoundException::new);
        if (voteRequest.getVoteType() == VoteType.ADD) {
            answerVotesRepository.findByAnswerAndEmail(answerDetails, voteRequest.getUserEmail())
                    .ifPresent(s -> { throw  new AnswerAlreadyVotedException(); });

            AnswerVotes answerVotes = new AnswerVotes();
            answerVotes.setAnswer(answerDetails);
            answerVotes.setEmail(voteRequest.getUserEmail());
            answerVotesRepository.saveAndFlush(answerVotes);
            answerDetailsRepository.updateVote(answerId, 1);
        } else {
            answerVotesRepository.delete(
                    answerVotesRepository.findByAnswerAndEmail(answerDetails, voteRequest.getUserEmail())
                            .orElseThrow(AnswerVoteAlreadyRemovedException::new)
            );
            answerDetailsRepository.updateVote(answerId, -1);
        }

    }
}
