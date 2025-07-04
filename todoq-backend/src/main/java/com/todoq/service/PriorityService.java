package com.todoq.service;

import com.todoq.entity.Priority;
import com.todoq.repository.PriorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriorityService {
    @Autowired
    private PriorityRepository priorityRepository;

    public List<Priority> getAllPriorities() {
        return priorityRepository.findAll();
    }

    public Optional<Priority> getPriorityById(Long id) {
        return priorityRepository.findById(id);
    }

    public Priority createPriority(Priority priority) {
        return priorityRepository.save(priority);
    }

    public Priority updatePriority(Long id, Priority priorityDetails) {
        Priority priority = priorityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Priority not found with id: " + id));
        priority.setLevel(priorityDetails.getLevel());
        return priorityRepository.save(priority);
    }

    public void deletePriority(Long id) {
        Priority priority = priorityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Priority not found with id: " + id));
        priorityRepository.delete(priority);
    }
} 