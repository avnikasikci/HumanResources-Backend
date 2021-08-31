package com.example.humanresources.dataAccess.abstracts;

import com.example.humanresources.entities.concretes.EmployerUpdate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerUpdateDao extends JpaRepository<EmployerUpdate, Integer> {
}
