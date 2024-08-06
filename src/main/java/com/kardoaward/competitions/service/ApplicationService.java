package com.kardoaward.competitions.service;

import com.kardoaward.competitions.dto.ApplicationDTO;
import com.kardoaward.competitions.model.Application;
import com.kardoaward.user.model.User;

import java.util.List;
import java.util.Optional;

public interface ApplicationService {
    Application createApplication(ApplicationDTO applicationDTO, User user);

    List<Application> findAll();

    Optional<Application> findById(Long id);

    void deleteById(Long id);
}
