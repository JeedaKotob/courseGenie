package com.course_genie;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RBACTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = "ADMIN")
    void adminCanAssignRole() throws Exception {
        mockMvc.perform(post("/api/admin/roles/assign")
                        .param("username", "axacad5")
                        .param("role", "PROFESSOR"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "PROFESSOR")
    void professorCannotAssignRole() throws Exception {
        mockMvc.perform(post("/api/admin/roles/assign")
                        .param("username", "axacad5")
                        .param("role", "PROFESSOR"))
                .andExpect(status().isForbidden());
    }

}
