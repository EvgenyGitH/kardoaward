package com.kardoaward.competitions.controller;

import com.kardoaward.competitions.dto.ApplicationResponseDTO;
import com.kardoaward.competitions.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/applications")
public class AdminApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping
    public List<ApplicationResponseDTO> findAll() {
        return applicationService.findAll();
    }

    @PatchMapping("/{id}")
    public ApplicationResponseDTO updateStatus(@PathVariable Long id, @RequestParam String status) {
        return applicationService.updateStatus(id, status);
    }
}
