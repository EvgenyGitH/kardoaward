package com.kardoaward.competitions.service.impl;

import com.kardoaward.competitions.dto.ApplicationDTO;
import com.kardoaward.competitions.dto.ApplicationResponseDTO;
import com.kardoaward.competitions.mapper.ApplicationMapper;
import com.kardoaward.competitions.model.Application;
import com.kardoaward.competitions.model.Competition;
import com.kardoaward.competitions.model.Direction;
import com.kardoaward.competitions.model.ParticipationType;
import com.kardoaward.competitions.repository.ApplicationRepository;
import com.kardoaward.competitions.repository.CompetitionRepository;
import com.kardoaward.competitions.repository.DirectionRepository;
import com.kardoaward.competitions.repository.ParticipationTypeRepository;
import com.kardoaward.competitions.service.ApplicationService;
import com.kardoaward.exception.DataConflictException;
import com.kardoaward.exception.NotFoundException;
import com.kardoaward.user.model.User;
import com.kardoaward.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
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

    @Autowired
    private DirectionRepository directionRepository;

    @Override
    public ApplicationResponseDTO submitApplication(ApplicationDTO applicationDTO) {
        Competition competition = competitionRepository.findById(applicationDTO.getCompetitionId())
                .orElseThrow(() -> new NotFoundException("Competition not found: " + applicationDTO.getCompetitionId()));

        User user = userRepository.findById(applicationDTO.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found: " + applicationDTO.getUserId()));

        if (applicationDTO.getFirstName() != null) user.setFirstName(applicationDTO.getFirstName());
        if (applicationDTO.getLastName() != null) user.setLastName(applicationDTO.getLastName());
        if (applicationDTO.getMiddleName() != null) user.setMiddleName(applicationDTO.getMiddleName());
        if (applicationDTO.getPhone() != null) user.setPhone(applicationDTO.getPhone());
        if (applicationDTO.getGender() != null) user.setGender(applicationDTO.getGender());
        if (applicationDTO.getBirthday() != null) user.setBirthday(applicationDTO.getBirthday());
        if (applicationDTO.getCitizenship() != null) user.setCitizenship(applicationDTO.getCitizenship());

        userRepository.save(user);

        ParticipationType participationType = participationTypeRepository.findByName(applicationDTO.getApplicationType())
                .orElseThrow(() -> new NotFoundException("Participation type not found: " + applicationDTO.getApplicationType()));

        if (!competition.getParticipationTypes().contains(participationType)) {
            throw new DataConflictException("Participation type is not valid for this competition.");
        }

        Application application = new Application();
        application.setCompetition(competition);
        application.setUser(user);
        application.setApplicationType(applicationDTO.getApplicationType());
        application.setStatus("Отправлен");

        application.setDirections(new HashSet<>());

        List<Direction> selectedDirections = directionRepository.findByNameIn(applicationDTO.getDirections());
        if (selectedDirections.isEmpty()) {
            throw new DataConflictException("Selected directions are not valid for this competition.");
        }

        application.getDirections().addAll(selectedDirections);

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
    public ApplicationResponseDTO findById(Long id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Application not found: " + id));
        return ApplicationMapper.convertToDTO(application);
    }

    @Override
    public void deleteById(Long id) {
        applicationRepository.deleteById(id);
    }

    @Override
    public ApplicationResponseDTO updateStatus(Long id, String status) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Application not found: " + id));

        String internalStatus = mapExternalStatusToInternal(status);

        application.setStatus(internalStatus);
        applicationRepository.save(application);
        return ApplicationMapper.convertToDTO(application);
    }

    @Override
    public String mapExternalStatusToInternal(String status) {
        switch (status) {
            case "accepted":
                return "Принята";
            case "rejected":
                return "Отклонена";
            default:
                throw new DataConflictException("Invalid status: " + status);
        }
    }
}
