package com.todoq.service;

import com.todoq.entity.Label;
import com.todoq.repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LabelService {
    @Autowired
    private LabelRepository labelRepository;

    public List<Label> getAllLabels() {
        return labelRepository.findAll();
    }

    public Optional<Label> getLabelById(Long id) {
        return labelRepository.findById(id);
    }

    public Label createLabel(Label label) {
        return labelRepository.save(label);
    }

    public Label updateLabel(Long id, Label labelDetails) {
        Label label = labelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Label not found with id: " + id));
        // Set fields as needed
        label.setName(labelDetails.getName());
        label.setColor(labelDetails.getColor());
        return labelRepository.save(label);
    }

    public void deleteLabel(Long id) {
        Label label = labelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Label not found with id: " + id));
        labelRepository.delete(label);
    }
} 