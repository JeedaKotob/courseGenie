package com.course_genie.user;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

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

    public List<UserDTO> getProfessorsByDepartment(String departmentName) {
        return userRepository.findProfessorsByDepartmentName(departmentName)
                .stream()
                .map(userDTOMapper)
                .toList();
    }

    public UserDTO getCurrentUserProfile(String username) {
        return userDTOMapper.apply(findUserByUsername(username));
    }
    
    @Transactional
    public UserDTO updateCurrentUserProfile(String username, UserProfileUpdateRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Profile update is required.");
        }
    
        User user = findUserByUsername(username);
        user.setOffice(normalizeRequiredValue(request.office(), "Office location", 100));
        user.setOfficeHours(normalizeRequiredValue(request.officeHours(), "Office hours", 255));
        user.setPhone(normalizeRequiredValue(request.phone(), "Phone number", 50));
    
        return userDTOMapper.apply(userRepository.save(user));
    }
    
    private User findUserByUsername(String username) {
        return userRepository.findByUserName(username)
                .orElseThrow(() -> new EntityNotFoundException("User profile was not found."));
    }
    
    private String normalizeRequiredValue(String value, String fieldName, int maxLength) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " is required.");
        }

        String normalizedValue = value.trim();
        if (normalizedValue.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " is required.");
        }

        if (normalizedValue.length() > maxLength) {
            throw new IllegalArgumentException(fieldName + " must be " + maxLength + " characters or fewer.");
        }

        return normalizedValue;
    }
}
