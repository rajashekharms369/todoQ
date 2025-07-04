package com.todoq.controller;

import com.todoq.entity.Priority;
import com.todoq.service.PriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/priorities")
@CrossOrigin(origins = "*")
public class PriorityController {
    @Autowired
    private PriorityService priorityService;

    @GetMapping
    public List<Priority> getAllPriorities() {
        return priorityService.getAllPriorities();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Priority> getPriorityById(@PathVariable Long id) {
        return priorityService.getPriorityById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Priority createPriority(@RequestBody Priority priority) {
        return priorityService.createPriority(priority);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Priority> updatePriority(@PathVariable Long id, @RequestBody Priority priorityDetails) {
        try {
            Priority updatedPriority = priorityService.updatePriority(id, priorityDetails);
            return ResponseEntity.ok(updatedPriority);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePriority(@PathVariable Long id) {
        try {
            priorityService.deletePriority(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
} 