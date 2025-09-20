package com.course_genie.security;

import com.course_genie.professor.ProfessorDTO;
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
        ProfessorDTO professorDTO = ldapService.authenticate(authQuery.username(), authQuery.password());
        if (professorDTO != null) {
            return ResponseEntity.ok(professorDTO);
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
