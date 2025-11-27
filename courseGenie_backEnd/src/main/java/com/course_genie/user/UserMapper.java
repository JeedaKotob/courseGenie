package com.course_genie.user;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserMapper implements Function<UserDTO, User> {
    @Override
    public User apply(UserDTO userDTO) {
        return User.builder()
                .userId(userDTO.getUserId())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .userName(userDTO.getUserName())
                .email(userDTO.getEmail())
                .phone(userDTO.getPhone())
                .officeHours(userDTO.getOfficeHours())
                .office(userDTO.getOffice())
                .roles((userDTO.getRoles()))
                .build();
    }
}
