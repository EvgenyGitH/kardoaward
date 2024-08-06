package com.kardoaward.competitions.repository;

import com.kardoaward.competitions.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}