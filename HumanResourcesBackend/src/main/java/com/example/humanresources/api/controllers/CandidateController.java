package com.example.humanresources.api.controllers;

import com.example.humanresources.business.abstracts.CandidateService;
import com.example.humanresources.business.abstracts.EmailService;
import com.example.humanresources.core.utilities.results.DataResult;
import com.example.humanresources.core.utilities.results.Result;
import com.example.humanresources.entities.concretes.Candidate;
import com.example.humanresources.entities.dtos.CandidateForRegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
@CrossOrigin
public class CandidateController {

    private CandidateService candidateService;
    private EmailService emailService;

    @Autowired
    public CandidateController(
            CandidateService candidateService
            , EmailService emailService
    ) {
        this.candidateService = candidateService;
        this.emailService = emailService;
    }

    @GetMapping("/getall")
    public DataResult<List<Candidate>> getAll(){
        return candidateService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody CandidateForRegisterDto candidateForRegisterDto){
        Result result=this.candidateService.add(candidateForRegisterDto);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/getMailVerifyTrue")
    DataResult<List<Candidate>> getMailVerifyTrue(){
        return this.candidateService.getMailVerifyTrue();
    }
    @GetMapping("/sendSimpleMessage")
    public void sendSimpleMessage(){
        //     String to, String subject, String text
         //this.emailService.sendSimpleMessage("avni.kasikci.01@gmail.com","Demo Test","Hello World");
        //(String receiver, String sender, String message, String filenameAndLocation);
        this.emailService.sendSimpleMessageV2("avni.kasikci.01@gmail.com","bilgi@avnikasikci.com","Hellow World","");
    }
}
