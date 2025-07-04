package com.todoq.repository;

import com.todoq.entity.TaskShare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskShareRepository extends JpaRepository<TaskShare, Long> {
    // Custom queries can be added here
} 