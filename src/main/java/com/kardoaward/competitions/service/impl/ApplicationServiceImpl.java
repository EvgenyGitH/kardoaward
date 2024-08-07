package com.kardoaward.competitions.service.impl;

import com.kardoaward.competitions.dto.ApplicationDTO;
import com.kardoaward.competitions.model.Application;
import com.kardoaward.competitions.model.Competition;
import com.kardoaward.competitions.repository.ApplicationRepository;
import com.kardoaward.competitions.repository.CompetitionRepository;
import com.kardoaward.competitions.service.ApplicationService;
import com.kardoaward.user.model.User;
import com.kardoaward.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Application submitApplication(ApplicationDTO applicationDTO) {
        Competition competition = competitionRepository.findById(applicationDTO.getCompetitionId())
                .orElseThrow(() -> new RuntimeException("Competition not found: " + applicationDTO.getCompetitionId()));

        User user = userRepository.findById(applicationDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found: " + applicationDTO.getUserId()));

        Application application = new Application();
        application.setCompetition(competition);
        application.setUser(user);
        application.setApplicationType(applicationDTO.getApplicationType());
        application.setStatus(applicationDTO.getStatus());

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
