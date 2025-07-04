package com.todoq.controller;

import com.todoq.entity.Label;
import com.todoq.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/labels")
@CrossOrigin(origins = "*")
public class LabelController {
    @Autowired
    private LabelService labelService;

    @GetMapping
    public List<Label> getAllLabels() {
        return labelService.getAllLabels();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Label> getLabelById(@PathVariable Long id) {
        return labelService.getLabelById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Label createLabel(@RequestBody Label label) {
        return labelService.createLabel(label);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Label> updateLabel(@PathVariable Long id, @RequestBody Label labelDetails) {
        try {
            Label updatedLabel = labelService.updateLabel(id, labelDetails);
            return ResponseEntity.ok(updatedLabel);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLabel(@PathVariable Long id) {
        try {
            labelService.deleteLabel(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
} 