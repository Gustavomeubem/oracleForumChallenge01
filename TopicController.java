package com.forum.controller;

import com.forum.dto.TopicRequest;
import com.forum.dto.TopicResponse;
import com.forum.model.Topic;
import com.forum.model.User;
import com.forum.service.TopicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/topics")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class TopicController {
    
    private final TopicService topicService;
    
    @GetMapping
    @Operation(summary = "Get all topics")
    public ResponseEntity<Page<TopicResponse>> getAllTopics(Pageable pageable) {
        return ResponseEntity.ok(topicService.getAllTopics(pageable));
    }
    
    @GetMapping("/search")
    @Operation(summary = "Search topics")
    public ResponseEntity<Page<TopicResponse>> searchTopics(
            @RequestParam String query, Pageable pageable) {
        return ResponseEntity.ok(topicService.searchTopics(query, pageable));
    }
    
    @PostMapping
    @Operation(summary = "Create a new topic")
    public ResponseEntity<TopicResponse> createTopic(
            @Valid @RequestBody TopicRequest request,
            @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(topicService.createTopic(request, user));
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update a topic")
    public ResponseEntity<TopicResponse> updateTopic(
            @PathVariable Long id,
            @Valid @RequestBody TopicRequest request,
            @AuthenticationPrincipal User user) {
        // Implementation would include finding the topic first
        return ResponseEntity.ok(topicService.updateTopic(id, request, new Topic()));
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a topic")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        topicService.deleteTopic(new Topic()); // Simplified for example
        return ResponseEntity.noContent().build();
    }
}
