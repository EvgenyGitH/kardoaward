package com.kardoaward.competitions.repository;

import com.kardoaward.competitions.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findAllByUserId(Long userId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO application_directions (application_id, direction_id) VALUES (:applicationId, :directionId)", nativeQuery = true)
    void saveApplicationDirection(@Param("applicationId") Long applicationId, @Param("directionId") Long directionId);
}

