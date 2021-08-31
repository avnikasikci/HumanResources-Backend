package com.example.humanresources.dataAccess.abstracts;

import com.example.humanresources.entities.concretes.ActivationByStaff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivationByStaffDao extends JpaRepository<ActivationByStaff,Integer> {
    ActivationByStaff findByEmployeId(int id);

}
