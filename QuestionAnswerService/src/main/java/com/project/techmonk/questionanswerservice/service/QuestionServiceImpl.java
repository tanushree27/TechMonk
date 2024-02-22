package com.project.techmonk.questionanswerservice.service;

import com.project.techmonk.questionanswerservice.entity.AnswerDetails;
import com.project.techmonk.questionanswerservice.entity.QuestionDetails;
import com.project.techmonk.questionanswerservice.entity.TagDetails;
import com.project.techmonk.questionanswerservice.exception.QuestionNotFoundException;
import com.project.techmonk.questionanswerservice.exception.UserUnauthorizedException;
import com.project.techmonk.questionanswerservice.model.CreateQuestionRequest;
import com.project.techmonk.questionanswerservice.model.SortType;
import com.project.techmonk.questionanswerservice.model.UpdateQuestionTagsRequest;
import com.project.techmonk.questionanswerservice.repository.QuestionDetailsRepository;
import com.project.techmonk.questionanswerservice.repository.QuestionVotesRepository;
import com.project.techmonk.questionanswerservice.repository.TagDetailsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.project.techmonk.questionanswerservice.Utils.ConstantUtil.CREATED_DATE;
import static com.project.techmonk.questionanswerservice.Utils.ConstantUtil.TOTAL_VOTES;

@Service
@AllArgsConstructor
@Slf4j
public class QuestionServiceImpl implements QuestionService {
    TagDetailsRepository tagDetailsRepository;
    QuestionDetailsRepository questionDetailsRepository;
    QuestionVotesRepository questionVotesRepository;

    @Override
    public QuestionDetails createQuestion(CreateQuestionRequest createQuestionRequest) {

        QuestionDetails questionDetails = new QuestionDetails();
        questionDetails.setQuestionText(createQuestionRequest.getQuestionText());
        questionDetails.setAuthorEmail(createQuestionRequest.getAuthorEmail());
        // filters out invalid tags
        List<TagDetails> tagDetailsList = tagDetailsRepository.findAllByTagIn(createQuestionRequest.getTags().stream().map(String::toLowerCase).toList());
        questionDetails.setTags(tagDetailsList);
        return questionDetailsRepository.save(questionDetails);
    }

    @Override
    public List<QuestionDetails> getQuestions (String searchQuery, SortType sortType, List<String> tags, int page, int limit) {
        Sort sort = Sort.by(TOTAL_VOTES).descending();

        if(sortType == SortType.MOST_RECENT)
            sort = Sort.by(CREATED_DATE).descending();

        if (tags != null && !tags.isEmpty() && searchQuery != null && !searchQuery.isBlank()) {
            return questionDetailsRepository.findAllByQuestionTextContainsIgnoreCaseAndTags_TagIn(
                    searchQuery, tags, PageRequest.of(page, limit, sort)
            );
        } else if (searchQuery != null && !searchQuery.isBlank())
            return questionDetailsRepository.findAllByQuestionTextContainsIgnoreCase(searchQuery, PageRequest.of(page, limit, sort));
        else if (tags != null && !tags.isEmpty())
            return questionDetailsRepository.findAllByTags_TagIn(tags, PageRequest.of(page, limit, sort));
        else
            return questionDetailsRepository.findAll(PageRequest.of(page, limit, sort)).toList();
    }

    @Override
    @Cacheable(value = "question", key = "#p0")
    public QuestionDetails getQuestion(Long id) {
        QuestionDetails questionDetails = questionDetailsRepository.findById(id).orElseThrow(QuestionNotFoundException::new);
        sortAnswersRecursively(questionDetails.getAnswers());
        return questionDetails;
    }

    private void sortAnswersRecursively (List<AnswerDetails> answerDetailsList) {
        answerDetailsList.sort((a, b) -> b.getCreatedDate().compareTo(a.getCreatedDate()));

        for (AnswerDetails answerDetails : answerDetailsList)
            if (!answerDetails.getNestedAnswers().isEmpty())
                sortAnswersRecursively(answerDetails.getNestedAnswers());
    }

    @Override
    @CacheEvict(value = "question", key = "#p0")
    public QuestionDetails updateTags(Long questionId, UpdateQuestionTagsRequest updateQuestionTagsRequest) {
        QuestionDetails questionDetails = questionDetailsRepository.findById(questionId).orElseThrow(QuestionNotFoundException::new);
        if (!questionDetails.getAuthorEmail().equals(updateQuestionTagsRequest.getEmail()))
            throw new UserUnauthorizedException();

        questionDetails.setTags(tagDetailsRepository.findAllByTagIn(updateQuestionTagsRequest.getTags().stream().map(String::toLowerCase).toList()));
        return questionDetailsRepository.save(questionDetails);
    }
}
