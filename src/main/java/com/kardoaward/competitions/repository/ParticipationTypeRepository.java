package com.kardoaward.competitions.repository;

import com.kardoaward.competitions.model.ParticipationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParticipationTypeRepository extends JpaRepository<ParticipationType, Long> {
    Optional<ParticipationType> findByName(String name);
}