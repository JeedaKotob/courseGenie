package com.course_genie.security;

import com.course_genie.user.User;
import com.course_genie.user.UserDTO;
import com.course_genie.user.UserDTOMapper;
import com.course_genie.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/admin/roles")
public class RoleManagementController {

    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;

    public RoleManagementController(UserRepository userRepository, UserDTOMapper userDTOMapper) {
        this.userRepository = userRepository;
        this.userDTOMapper = userDTOMapper;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/assign")
    public ResponseEntity<?> assignRole(@RequestParam String username, @RequestParam String role) {
        Optional<User> optionalProfessor = userRepository.findByUserName(username);
        if (optionalProfessor.isPresent()) {
            User professor = optionalProfessor.get();
            Set<String> roles = professor.getRoles();

            // Ensure role is properly formatted (should start with ROLE_)
            if (!role.startsWith("ROLE_")) {
                role = "ROLE_" + role;
            }

            roles.add(role);
            professor.setRoles(roles);
            userRepository.save(professor);

            UserDTO userDTO = userDTOMapper.apply(professor);
            return ResponseEntity.ok(userDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/remove")
    public ResponseEntity<?> removeRole(@RequestParam String username, @RequestParam String role) {
        Optional<User> optionalProfessor = userRepository.findByUserName(username);
        if (optionalProfessor.isPresent()) {
            User professor = optionalProfessor.get();
            Set<String> roles = professor.getRoles();

            // Ensure role is properly formatted (should start with ROLE_)
            if (!role.startsWith("ROLE_")) {
                role = "ROLE_" + role;
            }

            roles.remove(role);
            professor.setRoles(roles);
            userRepository.save(professor);

            UserDTO userDTO = userDTOMapper.apply(professor);
            return ResponseEntity.ok(userDTO);
        }

        return ResponseEntity.notFound().build();
    }
}