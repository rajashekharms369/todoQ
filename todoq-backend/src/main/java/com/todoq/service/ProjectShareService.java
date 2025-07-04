package com.todoq.service;

import com.todoq.entity.ProjectShare;
import com.todoq.repository.ProjectShareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectShareService {
    @Autowired
    private ProjectShareRepository projectShareRepository;

    public List<ProjectShare> getAllProjectShares() {
        return projectShareRepository.findAll();
    }

    public Optional<ProjectShare> getProjectShareById(Long id) {
        return projectShareRepository.findById(id);
    }

    public ProjectShare createProjectShare(ProjectShare projectShare) {
        return projectShareRepository.save(projectShare);
    }

    public ProjectShare updateProjectShare(Long id, ProjectShare projectShareDetails) {
        ProjectShare projectShare = projectShareRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProjectShare not found with id: " + id));
        // No updatable fields for ProjectShare as per entity definition
        return projectShareRepository.save(projectShare);
    }

    public void deleteProjectShare(Long id) {
        ProjectShare projectShare = projectShareRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProjectShare not found with id: " + id));
        projectShareRepository.delete(projectShare);
    }
} 