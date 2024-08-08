package com.kardoaward.competitions.controller;

import com.kardoaward.competitions.dto.ApplicationDTO;
import com.kardoaward.competitions.dto.ApplicationResponseDTO;
import com.kardoaward.competitions.mapper.ApplicationMapper;
import com.kardoaward.competitions.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping
    public ApplicationResponseDTO submitApplication(@RequestBody ApplicationDTO applicationDTO) {
        return applicationService.submitApplication(applicationDTO);
    }

    @GetMapping
    public List<ApplicationResponseDTO> findAllByUser(@RequestParam Long userId) {
        return applicationService.findAllByUserId(userId);
    }

    @GetMapping("/{id}")
    public ApplicationResponseDTO findById(@PathVariable Long id) {
        return applicationService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        applicationService.deleteById(id);
    }
}