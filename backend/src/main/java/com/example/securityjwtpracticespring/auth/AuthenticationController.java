package com.example.securityjwtpracticespring.auth;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "${spring.mvc.cors.allowed-origins}")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public void register(
            @RequestBody RegisterRequest request
    ) {
        authenticationService.register(request);
    }
    @GetMapping("/confirm")
    public String confirm(@RequestParam("token") String token) {
        return authenticationService.confirmToken(token);
    }
    @GetMapping("/authenticate")
    public int authenticate(
          @RequestParam("mailAddress") String mailAddress,
          @RequestParam("password") String password
    ) {
        return authenticationService.authenticate(mailAddress, password);
    }
}
