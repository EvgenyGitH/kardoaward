package com.kardoaward.competitions.repository;


import com.kardoaward.competitions.model.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DirectionRepository extends JpaRepository<Direction, Long> {
    Optional<Direction> findByName(String name);

    List<Direction> findByNameIn(List<String> names);

}