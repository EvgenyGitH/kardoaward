package com.kardoaward.competitions.controller;

import com.kardoaward.competitions.dto.ApplicationResponseDTO;
import com.kardoaward.competitions.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/applications")
public class AdminApplicationController {


    @Autowired
    private ApplicationService applicationService;

    @GetMapping
    public ResponseEntity<List<ApplicationResponseDTO>> findAll() {
        return ResponseEntity.ok(applicationService.findAll());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApplicationResponseDTO> updateStatus(@PathVariable Long id, @RequestParam String status) {
        String internalStatus;

        switch (status) {
            case "accepted":
                internalStatus = "Принята";
                break;
            case "rejected":
                internalStatus = "Отклонена";
                break;
            default:
                return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.ok(applicationService.updateStatus(id, internalStatus));
    }
}
