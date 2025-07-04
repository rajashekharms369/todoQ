package com.todoq.service;

import com.todoq.entity.TaskShare;
import com.todoq.repository.TaskShareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskShareService {
    @Autowired
    private TaskShareRepository taskShareRepository;

    public List<TaskShare> getAllTaskShares() {
        return taskShareRepository.findAll();
    }

    public Optional<TaskShare> getTaskShareById(Long id) {
        return taskShareRepository.findById(id);
    }

    public TaskShare createTaskShare(TaskShare taskShare) {
        return taskShareRepository.save(taskShare);
    }

    public TaskShare updateTaskShare(Long id, TaskShare taskShareDetails) {
        TaskShare taskShare = taskShareRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TaskShare not found with id: " + id));
        // No updatable fields for TaskShare as per entity definition
        return taskShareRepository.save(taskShare);
    }

    public void deleteTaskShare(Long id) {
        TaskShare taskShare = taskShareRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TaskShare not found with id: " + id));
        taskShareRepository.delete(taskShare);
    }
} 