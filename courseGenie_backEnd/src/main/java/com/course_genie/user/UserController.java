package com.course_genie.user;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Map<String, List<UserDTO>> getGroupedProfessors() {
        return userService.getProfessorsGroupedByDepartment();
    }

    @GetMapping("/professors")
    public List<UserDTO> getProfessorsByDepartment(@RequestParam String departmentName) {
        return userService.getProfessorsByDepartment(departmentName);
    }

    @GetMapping("/me")
    public UserDTO getCurrentUserProfile(Authentication authentication) {
        return userService.getCurrentUserProfile(authentication.getName());
    }
    
    @PutMapping("/me")
    public UserDTO updateCurrentUserProfile(
            Authentication authentication,
            @RequestBody UserProfileUpdateRequest request
    ) {
        return userService.updateCurrentUserProfile(authentication.getName(), request);
    }
}
