package com.kardoaward.competitions.service.impl;

import com.kardoaward.competitions.dto.ApplicationDTO;
import com.kardoaward.competitions.model.Application;
import com.kardoaward.competitions.model.Competition;
import com.kardoaward.competitions.model.Direction;
import com.kardoaward.competitions.repository.ApplicationRepository;
import com.kardoaward.competitions.repository.CompetitionRepository;
import com.kardoaward.competitions.repository.DirectionRepository;
import com.kardoaward.competitions.service.ApplicationService;
import com.kardoaward.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private DirectionRepository directionRepository;

    @Override
    public Application createApplication(ApplicationDTO applicationDTO, User user) {
        Competition competition = competitionRepository.findById(applicationDTO.getCompetitionId())
                .orElseThrow(() -> new RuntimeException("Competition not found"));

        Set<Direction> directions = applicationDTO.getDirections().stream()
                .map(name -> directionRepository.findByName(name)
                        .orElseThrow(() -> new RuntimeException("Direction not found: " + name)))
                .collect(Collectors.toSet());

        Application application = new Application();
        application.setCompetition(competition);
        application.setUser(user);
        application.setDirections(directions);
        application.setStatus("отправлена");

        return applicationRepository.save(application);
    }

    @Override
    public List<Application> findAll() {
        return applicationRepository.findAll();
    }

    @Override
    public Optional<Application> findById(Long id) {
        return applicationRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        applicationRepository.deleteById(id);
    }
}
