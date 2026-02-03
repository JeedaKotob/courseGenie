package com.course_genie.user;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDTOMapper implements Function<User, UserDTO> {
    @Override
    public UserDTO apply(User user) {
        return UserDTO.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .userName(user.getUserName())
                .departmentName(user.getDepartment() != null ? user.getDepartment().getDepartmentName(): "Unassigned")
                .email(user.getEmail())
                .phone(user.getPhone())
                .officeHours(user.getOfficeHours())
                .office(user.getOffice())
                .roles(user.getRoles())
                .build();
    }
}
