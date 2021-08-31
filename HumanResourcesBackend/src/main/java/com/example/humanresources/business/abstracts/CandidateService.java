package com.example.humanresources.business.abstracts;

import com.example.humanresources.core.utilities.results.DataResult;
import com.example.humanresources.core.utilities.results.Result;
import com.example.humanresources.entities.concretes.Candidate;
import com.example.humanresources.entities.dtos.CandidateForRegisterDto;
import com.example.humanresources.entities.concretes.Candidate;

import java.util.List;

public interface CandidateService {
    DataResult<List<Candidate>> getAll();
    DataResult<Candidate> getByNationalNumber(String nationalNumber);
    DataResult<Candidate> getByEmail(String email);
    Result add(CandidateForRegisterDto candidateForRegisterDto);
    DataResult<List<Candidate>> getMailVerifyTrue();
}
