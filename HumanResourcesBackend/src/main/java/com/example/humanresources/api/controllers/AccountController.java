package com.example.humanresources.api.controllers;

        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

        import javax.validation.Valid;

        import com.example.humanresources.core.utilities.results.DataResult;
        import com.example.humanresources.core.utilities.results.SuccessDataResult;
        import com.example.humanresources.entities.dtos.UserLoginDto;
        import com.example.humanresources.entities.dtos.UserLoginReturnDto;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.MethodArgumentNotValidException;
        import org.springframework.web.bind.annotation.*;

        import com.example.humanresources.business.abstracts.UserService;
        import com.example.humanresources.core.entities.User;
        import com.example.humanresources.core.utilities.results.ErrorDataResult;

        import org.springframework.validation.FieldError;
        import org.springframework.http.HttpStatus;

@RestController
@RequestMapping(value="/api/account")
public class AccountController {

    private UserService userService;

    @Autowired
    public AccountController(UserService userService) {
        super();
        this.userService = userService;
    }
    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDto){
        DataResult<UserLoginReturnDto> result = this.userService.login(userLoginDto);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }else {
            return ResponseEntity.badRequest().body(result);
        }
    }

  /*  @PostMapping(value="/login")
    public ResponseEntity<?> login(@Valid @RequestBody User user) {

        return ResponseEntity.ok(this.userService.add(user)) ;
    }*/
    @PostMapping(value="/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user) {

        return ResponseEntity.ok(this.userService.update(user)) ;
    }

    @GetMapping("/getVerifyedUsers")
    DataResult<List<User>> getVerifyedUsers(){
        return this.userService.getVerifyedUsers();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException
            (MethodArgumentNotValidException exceptions){
        Map<String,String> validationErrors = new HashMap<String, String>();
        for(FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ErrorDataResult<Object> errors
                = new ErrorDataResult<Object>(validationErrors,"Doğrulama hataları");
        return errors;
    }



}
