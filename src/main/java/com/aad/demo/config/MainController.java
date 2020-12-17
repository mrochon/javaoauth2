package com.aad.demo.config;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/")
    String home(Principal user) {
        if (user == null)
            return "unauthenticated";
        else
            return "hello " + user.getName();
    }
}
