package com.example.humanresources.business.concretes;

import com.example.humanresources.business.abstracts.NationalValidationService;
import com.example.humanresources.entities.concretes.Candidate;
import org.springframework.stereotype.Service;

@Service
public class MernisManager implements NationalValidationService {


    @Override
    public boolean validate(Candidate candidate) {
        if(candidate.getNationalNumber().length()!=11){
            return false;
        }
        return true;
    }
}
