package com.kardoaward.competitions.repository;


import com.kardoaward.competitions.model.Direction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DirectionRepository extends JpaRepository<Direction, Long> {
    Optional<Direction> findByName(String name);
}