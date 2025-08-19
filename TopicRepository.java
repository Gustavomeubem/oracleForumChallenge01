package com.forum.repository;

import com.forum.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    Page<Topic> findByStatus(Topic.TopicStatus status, Pageable pageable);
    
    @Query("SELECT t FROM Topic t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', :search, '%')) " +
           "OR LOWER(t.problem) LIKE LOWER(CONCAT('%', :search, '%')) " +
           "OR LOWER(t.solution) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Topic> searchTopics(String search, Pageable pageable);
}
