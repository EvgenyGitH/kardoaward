package com.kardoaward.competitions.repository;

import com.kardoaward.competitions.model.ParticipationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParticipationTypeRepository extends JpaRepository<ParticipationType, Long> {
    Optional<ParticipationType> findByName(String name);
}