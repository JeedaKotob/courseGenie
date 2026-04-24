package com.course_genie.user;

public record UserProfileUpdateRequest(
        String office,
        String officeHours,
        String phone
) {
}