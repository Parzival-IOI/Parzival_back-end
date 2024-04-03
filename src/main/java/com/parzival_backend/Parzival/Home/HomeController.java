package com.parzival_backend.Parzival.Home;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home")
public class HomeController {
    @GetMapping("/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String index() {
        return "Hello";
    }
}
