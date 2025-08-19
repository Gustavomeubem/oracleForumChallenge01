package com.forum.service;

import com.forum.dto.TopicRequest;
import com.forum.dto.TopicResponse;
import com.forum.model.Topic;
import com.forum.model.User;
import com.forum.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TopicService {
    
    private final TopicRepository topicRepository;
    
    public Page<TopicResponse> getAllTopics(Pageable pageable) {
        return topicRepository.findAll(pageable)
                .map(this::mapToResponse);
    }
    
    public Page<TopicResponse> searchTopics(String search, Pageable pageable) {
        return topicRepository.searchTopics(search, pageable)
                .map(this::mapToResponse);
    }
    
    public TopicResponse createTopic(TopicRequest request, User author) {
        Topic topic = new Topic();
        topic.setTitle(request.title());
        topic.setProblem(request.problem());
        topic.setSolution(request.solution());
        topic.setAuthor(author);
        
        Topic savedTopic = topicRepository.save(topic);
        return mapToResponse(savedTopic);
    }
    
    @PreAuthorize("hasRole('ADMIN') or #topic.author.username == authentication.name")
    public TopicResponse updateTopic(Long id, TopicRequest request, Topic topic) {
        topic.setTitle(request.title());
        topic.setProblem(request.problem());
        topic.setSolution(request.solution());
        
        Topic updatedTopic = topicRepository.save(topic);
        return mapToResponse(updatedTopic);
    }
    
    @PreAuthorize("hasRole('ADMIN') or #topic.author.username == authentication.name")
    public void deleteTopic(Topic topic) {
        topicRepository.delete(topic);
    }
    
    private TopicResponse mapToResponse(Topic topic) {
        return new TopicResponse(
            topic.getId(),
            topic.getTitle(),
            topic.getProblem(),
            topic.getSolution(),
            topic.getAuthor().getUsername(),
            topic.getStatus(),
            topic.getCreatedAt(),
            topic.getUpdatedAt()
        );
    }
}
