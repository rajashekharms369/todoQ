package com.todoq.repository;

import com.todoq.entity.ProjectShare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectShareRepository extends JpaRepository<ProjectShare, Long> {
    // Custom queries can be added here
} 