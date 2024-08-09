package com.kardoaward.competitions.controller;

import com.kardoaward.competitions.dto.ApplicationResponseDTO;
import com.kardoaward.competitions.service.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/admin/applications")
@Tag(name = "Управление заявками администратора", description = "Операции для управления заявками администратором")
public class AdminApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping
    @Operation(summary = "Получение всех заявок")
    public List<ApplicationResponseDTO> findAll() {
        log.info("Запрос: получить все заявки");
        return applicationService.findAll();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Обновление статуса заявки")
    public ApplicationResponseDTO updateStatus(@Parameter(description = "ID заявки") @PathVariable Long id,
                                               @Parameter(description = "Новый статус заявки") @RequestParam String status) {
        log.info("Запрос: обновить статус заявки с ID {} на {}", id, status);
        return applicationService.updateStatus(id, status);
    }
}