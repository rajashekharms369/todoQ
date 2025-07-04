package com.todoq.controller;

import com.todoq.entity.ProjectShare;
import com.todoq.service.ProjectShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projectshares")
@CrossOrigin(origins = "*")
public class ProjectShareController {
    @Autowired
    private ProjectShareService projectShareService;

    @GetMapping
    public List<ProjectShare> getAllProjectShares() {
        return projectShareService.getAllProjectShares();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectShare> getProjectShareById(@PathVariable Long id) {
        return projectShareService.getProjectShareById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ProjectShare createProjectShare(@RequestBody ProjectShare projectShare) {
        return projectShareService.createProjectShare(projectShare);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectShare> updateProjectShare(@PathVariable Long id, @RequestBody ProjectShare projectShareDetails) {
        try {
            ProjectShare updatedProjectShare = projectShareService.updateProjectShare(id, projectShareDetails);
            return ResponseEntity.ok(updatedProjectShare);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjectShare(@PathVariable Long id) {
        try {
            projectShareService.deleteProjectShare(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
} 