package com.kardoaward.competitions.controller;

import com.kardoaward.competitions.dto.CompetitionDTO;
import com.kardoaward.competitions.dto.DirectionDTO;
import com.kardoaward.competitions.dto.LocationDTO;
import com.kardoaward.competitions.dto.ParticipationTypeDTO;
import com.kardoaward.competitions.dto.CompetitionSummaryDTO;
import com.kardoaward.competitions.model.Competition;
import com.kardoaward.competitions.model.Direction;
import com.kardoaward.competitions.model.Location;
import com.kardoaward.competitions.model.ParticipationType;
import com.kardoaward.competitions.service.CompetitionService;
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
@RequestMapping("/admin/competitions")
@Tag(name = "Управление соревнованиями", description = "Операции, связанные с управлением соревнованиями")
public class AdminCompetitionController {

    @Autowired
    private CompetitionService competitionService;

    @PostMapping
    @Operation(summary = "Создание нового соревнования")
    public Competition createCompetition(@RequestBody @Valid CompetitionDTO competitionDTO) {
        log.info("Запрос: создать новое соревнование");
        return competitionService.createCompetition(competitionDTO);
    }

    @GetMapping
    @Operation(summary = "Получение списка всех соревнований")
    public List<CompetitionSummaryDTO> findAll() {
        log.info("Запрос: получить все соревнования");
        return competitionService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение соревнования по ID")
    public Competition findById(@Parameter(description = "ID соревнования") @PathVariable Long id) {
        log.info("Запрос: получить соревнование по ID {}", id);
        return competitionService.findById(id);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Обновление информации о соревновании")
    public Competition updateCompetition(@Parameter(description = "ID соревнования") @PathVariable Long id,
                                         @RequestBody @Valid CompetitionDTO competitionDTO) {
        log.info("Запрос: обновить информацию о соревновании с ID {}", id);
        return competitionService.updateCompetition(id, competitionDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление соревнования по ID")
    public void deleteById(@Parameter(description = "ID соревнования") @PathVariable Long id) {
        log.info("Запрос: удалить соревнование с ID {}", id);
        competitionService.deleteById(id);
    }

    @PostMapping("/directions")
    @Operation(summary = "Добавление нового направления")
    public Direction addDirection(@RequestBody @Valid DirectionDTO directionDTO) {
        log.info("Запрос: добавить новое направление");
        return competitionService.addDirection(directionDTO);
    }

    @PostMapping("/participation-types")
    @Operation(summary = "Добавление нового типа участия")
    public ParticipationType addParticipationType(@RequestBody @Valid ParticipationTypeDTO participationTypeDTO) {
        log.info("Запрос: добавить новый тип участия");
        return competitionService.addParticipationType(participationTypeDTO);
    }

    @PostMapping("/locations")
    @Operation(summary = "Добавление нового места проведения")
    public Location addLocation(@RequestBody @Valid LocationDTO locationDTO) {
        log.info("Запрос: добавить новое место проведения");
        return competitionService.addLocation(locationDTO);
    }

    @GetMapping("/locations")
    @Operation(summary = "Получение списка всех мест проведения")
    public List<Location> getAllLocations() {
        log.info("Запрос: получить все места проведения");
        return competitionService.findAllLocations();
    }

    @DeleteMapping("/locations/{id}")
    @Operation(summary = "Удаление места проведения по ID")
    public void deleteLocation(@Parameter(description = "ID места проведения") @PathVariable Long id) {
        log.info("Запрос: удалить место проведения с ID {}", id);
        competitionService.deleteLocationById(id);
    }

    @GetMapping("/participation-types")
    @Operation(summary = "Получение списка всех типов участия")
    public List<ParticipationType> getAllParticipationTypes() {
        log.info("Запрос: получить все типы участия");
        return competitionService.findAllParticipationTypes();
    }

    @DeleteMapping("/participation-types/{id}")
    @Operation(summary = "Удаление типа участия по ID")
    public void deleteParticipationType(@Parameter(description = "ID типа участия") @PathVariable Long id) {
        log.info("Запрос: удалить тип участия с ID {}", id);
        competitionService.deleteParticipationTypeById(id);
    }

    @GetMapping("/directions")
    @Operation(summary = "Получение списка всех направлений")
    public List<Direction> getAllDirections() {
        log.info("Запрос: получить все направления");
        return competitionService.findAllDirections();
    }

    @DeleteMapping("/directions/{id}")
    @Operation(summary = "Удаление направления по ID")
    public void deleteDirection(@Parameter(description = "ID направления") @PathVariable Long id) {
        log.info("Запрос: удалить направление с ID {}", id);
        competitionService.deleteDirectionById(id);
    }
}
