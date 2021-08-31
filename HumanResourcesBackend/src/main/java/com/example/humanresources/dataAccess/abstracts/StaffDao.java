package com.example.humanresources.dataAccess.abstracts;

import com.example.humanresources.entities.concretes.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffDao extends JpaRepository<Staff,Integer> {
}
