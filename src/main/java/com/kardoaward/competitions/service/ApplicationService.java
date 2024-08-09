package com.kardoaward.competitions.service;

import com.kardoaward.competitions.dto.ApplicationDTO;
import com.kardoaward.competitions.dto.ApplicationResponseDTO;


import java.util.List;

public interface ApplicationService {
    ApplicationResponseDTO submitApplication(ApplicationDTO applicationDTO);

    List<ApplicationResponseDTO> findAll();

    List<ApplicationResponseDTO> findAllByUserId(Long userId);

    ApplicationResponseDTO findById(Long id);

    void deleteById(Long id);

    ApplicationResponseDTO updateStatus(Long id, String status);

    String mapExternalStatusToInternal(String status);
}
