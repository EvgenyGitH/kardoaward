package com.kardoaward.competitions.repository;

import com.kardoaward.competitions.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {
}