package com.forum.dto;

import com.forum.model.Topic;

import java.time.LocalDateTime;

public record TopicResponse(
    Long id,
    String title,
    String problem,
    String solution,
    String author,
    Topic.TopicStatus status,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}
