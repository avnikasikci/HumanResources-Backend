package com.example.humanresources.business.concretes;

import com.example.humanresources.business.abstracts.*;
import com.example.humanresources.core.utilities.results.*;
import com.example.humanresources.dataAccess.abstracts.CandidateDao;
import com.example.humanresources.entities.concretes.Candidate;
import com.example.humanresources.entities.dtos.CandidateForRegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class CandidateManager implements CandidateService {

    private CandidateDao candidateDao;
    private NationalValidationService nationalValidationService;
    private UserService userService;
    private ActivationCodeService activationCodeService;
    private EmailService emailService;

    @Autowired
    public CandidateManager(CandidateDao candidateDao, NationalValidationService nationalValidationService, UserService userService, ActivationCodeService activationCodeService, EmailService emailService) {
        this.candidateDao = candidateDao;
        this.nationalValidationService=nationalValidationService;
        this.userService=userService;
        this.activationCodeService=activationCodeService;
        this.emailService=emailService;
    }

    @Override
    public DataResult<List<Candidate>> getAll() {
        return new SuccessDataResult<List<Candidate>>(this.candidateDao.findAll(),"Data listelendi");
    }

    @Override
    public DataResult<Candidate> getByNationalNumber(String nationalNumber) {
        return new SuccessDataResult<Candidate>(this.candidateDao.findByNationalNumber(nationalNumber),"Listelendi");
    }

    @Override
    public DataResult<Candidate> getByEmail(String email) {
        return new SuccessDataResult<Candidate>(this.candidateDao.findByEmail(email),"Listelendi");
    }

    @Override
    public Result add(CandidateForRegisterDto candidateDto) {
        if(!candidateDto.getPassword().equals(candidateDto.getRePassword())){
            return new ErrorResult("??ifreler e??le??miyor");
        }
        Candidate candidate=new Candidate();
        candidate.setFirstName(candidateDto.getFirstName());
        candidate.setLastName(candidateDto.getLastName());
        candidate.setNationalNumber(candidateDto.getNationalNumber());
        candidate.setDateOfBirth(candidateDto.getBirthDate());
        candidate.setEmail(candidateDto.getEmail());
        candidate.setPassword(candidateDto.getPassword());
        candidate.setUserType(candidateDto.getUserType());

        if(candidate.getPassword().length() <=6){
            return new ErrorResult("??ifre 6 karakterden uzun olmal??d??r");
        }else if(!isEmailValid(candidate.getEmail())){
            return new ErrorResult("Email ge??erli formatta de??il");
        }else if(getByNationalNumber(candidate.getNationalNumber()).getData() != null){
            return new ErrorResult("Bu kimlik numaras?? zaten kay??tl??");
        }else if(userService.getByEmail(candidate.getEmail()).getData() != null){
            return new ErrorResult("Bu email zaten kay??tl??");
        }else if(nationalValidationService.validate(candidate)){
            candidate.setMailVerify(false);
            this.candidateDao.save(candidate);
            this.emailService.sendVerifyEmail(candidate,this.activationCodeService.createActivationCode(candidate));
            return new SuccessResult(candidate.getEmail()+" Adresine do??rulama kodu g??nderildi");
        }else{
            return new ErrorResult("Kullan??c?? kimlik bilgileri hatal??");
        }


    }

    @Override
    public DataResult<List<Candidate>> getMailVerifyTrue() {
        return new SuccessDataResult<List<Candidate>>(this.candidateDao.findByMailVerifyTrue(),"Data listelendi");
    }

    private final String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+.(com|org|net|edu|gov|mil|biz|info|mobi)(.[A-Z]{2})?$";

    public boolean isEmailValid(String emailInput) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(emailInput).find();
    }
}
