package com.example.humanresources.dataAccess.abstracts;

import com.example.humanresources.entities.concretes.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateDao extends JpaRepository<Candidate,Integer> {
    Candidate findByNationalNumber(String nationalNumber);
    Candidate findByEmail(String email);
    List<Candidate> findByMailVerifyTrue();
}
