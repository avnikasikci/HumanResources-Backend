package com.example.humanresources.business.abstracts;

import com.example.humanresources.core.entities.User;
import com.example.humanresources.core.utilities.results.Result;
import com.example.humanresources.entities.concretes.ActivationCode;

public interface ActivationCodeService {
    ActivationCode getByCode(String code);
    String createActivationCode(User user);
    Result activateUser(String code);
}
