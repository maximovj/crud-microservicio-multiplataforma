package com.github.maximovj;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "*", methods = {
    RequestMethod.GET,
    RequestMethod.POST, 
    RequestMethod.PATCH, 
    RequestMethod.PUT, 
    RequestMethod.DELETE 
})
@RestController
@RequestMapping("/")
public class ApiController {

    @GetMapping
    public String apiTest() {
        return "SpringBoot + Java 17 | API Loans v1.0";
    }
    
}
