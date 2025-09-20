package com.course_genie.security;

import java.util.Set;

public record AuthQuery(
        String username,
        String password

) {
}
