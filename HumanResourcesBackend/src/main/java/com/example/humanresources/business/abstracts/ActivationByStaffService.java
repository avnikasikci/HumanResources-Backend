package com.example.humanresources.business.abstracts;

import com.example.humanresources.core.utilities.results.Result;
import com.example.humanresources.entities.concretes.Employer;

public interface ActivationByStaffService {
    void createActivationByStaff(Employer employer);
    Result activateEmployer(int employerId,int staffId);
}
