package com.parzival_backend.Parzival.Authentication;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    private final TokenService tokenService;

    @PostMapping("/token")
    public String token(Authentication authentication) {
        LOG.debug("Token Request User {}", authentication.getName());
        String token = tokenService.generateToken(authentication);
        LOG.debug("Token Response User {}", token);
        return token;
    }
}
