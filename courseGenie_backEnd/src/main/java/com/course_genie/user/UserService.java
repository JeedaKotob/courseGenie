package com.course_genie.user;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;

    public UserService(UserRepository userRepository, UserDTOMapper userDTOMapper) {
        this.userRepository = userRepository;
        this.userDTOMapper = userDTOMapper;
    }

    public Map<String, List<UserDTO>> getProfessorsGroupedByDepartment(){
        List<User> professors=userRepository.findByRoles("ROLE_PROFESSOR");
        return professors.stream()
                .map(userDTOMapper)
                .collect(Collectors.groupingBy(UserDTO::getDepartmentName));
    }
}
