package com.todoq.controller;

import com.todoq.entity.TaskShare;
import com.todoq.service.TaskShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taskshares")
@CrossOrigin(origins = "*")
public class TaskShareController {
    @Autowired
    private TaskShareService taskShareService;

    @GetMapping
    public List<TaskShare> getAllTaskShares() {
        return taskShareService.getAllTaskShares();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskShare> getTaskShareById(@PathVariable Long id) {
        return taskShareService.getTaskShareById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public TaskShare createTaskShare(@RequestBody TaskShare taskShare) {
        return taskShareService.createTaskShare(taskShare);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskShare> updateTaskShare(@PathVariable Long id, @RequestBody TaskShare taskShareDetails) {
        try {
            TaskShare updatedTaskShare = taskShareService.updateTaskShare(id, taskShareDetails);
            return ResponseEntity.ok(updatedTaskShare);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskShare(@PathVariable Long id) {
        try {
            taskShareService.deleteTaskShare(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
} 