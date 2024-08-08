package com.kardoaward.competitions.controller;

import com.kardoaward.competitions.dto.ApplicationDTO;
import com.kardoaward.competitions.dto.ApplicationResponseDTO;
import com.kardoaward.competitions.mapper.ApplicationMapper;
import com.kardoaward.competitions.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<ApplicationResponseDTO> submitApplication(@RequestBody ApplicationDTO applicationDTO) {
        return ResponseEntity.ok(applicationService.submitApplication(applicationDTO));
    }

    @GetMapping
    public ResponseEntity<List<ApplicationResponseDTO>> findAllByUser(@RequestParam Long userId) {
        return ResponseEntity.ok(applicationService.findAllByUserId(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationResponseDTO> findById(@PathVariable Long id) {
        return applicationService.findById(id)
                .map(app -> ResponseEntity.ok(ApplicationMapper.convertToDTO(app)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        applicationService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}