package com.course_genie.security;

import com.course_genie.professor.Professor;
import com.course_genie.professor.ProfessorDTO;
import com.course_genie.professor.ProfessorDTOMapper;
import com.course_genie.professor.ProfessorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/admin/roles")
public class RoleManagementController {

    private final ProfessorRepository professorRepository;
    private final ProfessorDTOMapper professorDTOMapper;

    public RoleManagementController(ProfessorRepository professorRepository, ProfessorDTOMapper professorDTOMapper) {
        this.professorRepository = professorRepository;
        this.professorDTOMapper = professorDTOMapper;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/assign")
    public ResponseEntity<?> assignRole(@RequestParam String username, @RequestParam String role) {
        Optional<Professor> optionalProfessor = professorRepository.findByUserName(username);
        if (optionalProfessor.isPresent()) {
            Professor professor = optionalProfessor.get();
            Set<String> roles = professor.getRoles();

            // Ensure role is properly formatted (should start with ROLE_)
            if (!role.startsWith("ROLE_")) {
                role = "ROLE_" + role;
            }

            roles.add(role);
            professor.setRoles(roles);
            professorRepository.save(professor);

            ProfessorDTO professorDTO = professorDTOMapper.apply(professor);
            return ResponseEntity.ok(professorDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/remove")
    public ResponseEntity<?> removeRole(@RequestParam String username, @RequestParam String role) {
        Optional<Professor> optionalProfessor = professorRepository.findByUserName(username);
        if (optionalProfessor.isPresent()) {
            Professor professor = optionalProfessor.get();
            Set<String> roles = professor.getRoles();

            // Ensure role is properly formatted (should start with ROLE_)
            if (!role.startsWith("ROLE_")) {
                role = "ROLE_" + role;
            }

            roles.remove(role);
            professor.setRoles(roles);
            professorRepository.save(professor);

            ProfessorDTO professorDTO = professorDTOMapper.apply(professor);
            return ResponseEntity.ok(professorDTO);
        }

        return ResponseEntity.notFound().build();
    }
}