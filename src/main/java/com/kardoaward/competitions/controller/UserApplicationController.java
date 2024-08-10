package com.kardoaward.competitions.controller;

import com.kardoaward.competitions.dto.ApplicationDTO;
import com.kardoaward.competitions.dto.ApplicationResponseDTO;
import com.kardoaward.competitions.service.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("user/applications")
@Tag(name = "Управление заявками пользователя", description = "Операции, связанные с управлением заявками пользователя")
public class UserApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping
    @Operation(summary = "Отправка новой заявки")
    public ApplicationResponseDTO submitApplication(@RequestBody @Valid ApplicationDTO applicationDTO) {
        log.info("Запрос: отправить новую заявку");
        return applicationService.submitApplication(applicationDTO);
    }

    @GetMapping
    @Operation(summary = "Получение всех заявок пользователя")
    public List<ApplicationResponseDTO> findAllByUser(@Parameter(description = "ID пользователя") @RequestParam Long userId) {
        log.info("Запрос: получить все заявки пользователя с ID {}", userId);
        return applicationService.findAllByUserId(userId);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение заявки по ID")
    public ApplicationResponseDTO findById(@Parameter(description = "ID заявки") @PathVariable Long id) {
        log.info("Запрос: получить заявку по ID {}", id);
        return applicationService.findById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление заявки по ID")
    public void deleteById(@Parameter(description = "ID заявки") @PathVariable Long id) {
        log.info("Запрос: удалить заявку с ID {}", id);
        applicationService.deleteById(id);
    }
}
