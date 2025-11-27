package com.course_genie.security;

import com.course_genie.user.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class JWTAuthenticationController {
    private final LdapService ldapService;

    public JWTAuthenticationController(LdapService ldapService) {
        this.ldapService = ldapService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthQuery authQuery) {
        UserDTO userDTO = ldapService.authenticate(authQuery.username(), authQuery.password());
        if (userDTO != null) {
            return ResponseEntity.ok(userDTO);
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
