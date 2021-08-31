package com.example.humanresources.api.controllers;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

        import javax.validation.Valid;

        import com.example.humanresources.business.abstracts.ProductService;
        import com.example.humanresources.core.utilities.results.DataResult;
        import com.example.humanresources.core.utilities.results.SuccessDataResult;
        import com.example.humanresources.entities.concretes.Product;
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
@RequestMapping(value="/api/demo")
public class DemoController {

    private UserService userService;
    private ProductService productService;

    @Autowired
    public DemoController(UserService userService,ProductService productService) {
        super();
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/getall")
    public DataResult<List<Product>> getAll(){
        return this.productService.getAll();
    }
}
