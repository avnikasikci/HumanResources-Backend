package com.example.humanresources.business.abstracts;

import com.example.humanresources.entities.concretes.Candidate;

public interface NationalValidationService {
    boolean validate(Candidate candidate);
}
