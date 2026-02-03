package com.course_genie.user;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

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
}
