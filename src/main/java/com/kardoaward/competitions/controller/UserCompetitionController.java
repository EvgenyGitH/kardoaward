package com.kardoaward.competitions.controller;

import com.kardoaward.competitions.mapper.CompetitionSummaryDTO;
import com.kardoaward.competitions.service.CompetitionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@Slf4j
@RequestMapping("/user/competitions")
@Tag(name = "Управление соревнованиями пользователем", description = "Операции, связанные с управлением соревнованиями пользователем")
public class UserCompetitionController {
    @Autowired
    private CompetitionService competitionService;
    @GetMapping
    @Operation(summary = "Получение списка всех соревнований")
    public List<CompetitionSummaryDTO> findAll() {
        log.info("Запрос: получить все соревнования");
        return competitionService.findAll();
    }
}
