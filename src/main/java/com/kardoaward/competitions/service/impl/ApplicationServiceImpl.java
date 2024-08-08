package com.kardoaward.competitions.service.impl;

import com.kardoaward.competitions.dto.ApplicationDTO;
import com.kardoaward.competitions.dto.ApplicationResponseDTO;
import com.kardoaward.competitions.mapper.ApplicationMapper;
import com.kardoaward.competitions.model.Application;
import com.kardoaward.competitions.model.Competition;
import com.kardoaward.competitions.model.ParticipationType;
import com.kardoaward.competitions.repository.ApplicationRepository;
import com.kardoaward.competitions.repository.CompetitionRepository;
import com.kardoaward.competitions.repository.ParticipationTypeRepository;
import com.kardoaward.competitions.service.ApplicationService;
import com.kardoaward.user.model.User;
import com.kardoaward.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParticipationTypeRepository participationTypeRepository;

    @Override
    public ApplicationResponseDTO submitApplication(ApplicationDTO applicationDTO) {
        Competition competition = competitionRepository.findById(applicationDTO.getCompetitionId())
                .orElseThrow(() -> new RuntimeException("Competition not found: " + applicationDTO.getCompetitionId()));

        User user = userRepository.findById(applicationDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found: " + applicationDTO.getUserId()));

        if (applicationDTO.getFirstName() != null) user.setFirstName(applicationDTO.getFirstName());
        if (applicationDTO.getLastName() != null) user.setLastName(applicationDTO.getLastName());
        if (applicationDTO.getMiddleName() != null) user.setMiddleName(applicationDTO.getMiddleName());
        if (applicationDTO.getPhone() != null) user.setPhone(applicationDTO.getPhone());
        if (applicationDTO.getGender() != null) user.setGender(applicationDTO.getGender());
        if (applicationDTO.getBirthday() != null) user.setBirthday(applicationDTO.getBirthday());
        if (applicationDTO.getCitizenship() != null) user.setCitizenship(applicationDTO.getCitizenship());
        if (applicationDTO.getSocialMediaLink() != null) user.setSocialMediaLink(applicationDTO.getSocialMediaLink());

        userRepository.save(user);

        ParticipationType participationType = participationTypeRepository.findByName(applicationDTO.getApplicationType())
                .orElseThrow(() -> new RuntimeException("Participation type not found: " + applicationDTO.getApplicationType()));

        if (!competition.getParticipationTypes().contains(participationType)) {
            throw new RuntimeException("Participation type is not valid for this competition.");
        }

        Application application = new Application();
        application.setCompetition(competition);
        application.setUser(user);
        application.setApplicationType(applicationDTO.getApplicationType());
        application.setStatus("Отправлен");

        Application savedApplication = applicationRepository.save(application);

        return ApplicationMapper.convertToDTO(savedApplication);
    }

    @Override
    public List<ApplicationResponseDTO> findAll() {
        return applicationRepository.findAll().stream()
                .map(ApplicationMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ApplicationResponseDTO> findAllByUserId(Long userId) {
        return applicationRepository.findAllByUserId(userId).stream()
                .map(ApplicationMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Application> findById(Long id) {
        return applicationRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        applicationRepository.deleteById(id);
    }

    @Override
    public ApplicationResponseDTO updateStatus(Long id, String status) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found: " + id));
        application.setStatus(status);
        applicationRepository.save(application);
        return ApplicationMapper.convertToDTO(application);
    }
}
