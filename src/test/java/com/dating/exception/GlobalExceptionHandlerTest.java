package com.dating.exception;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dating.controller.UserController;
import com.dating.model.User;
import com.dating.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId("123");
        user.setUserName("love_hurts943");
        user.setEmail("love_hurts943@example.com");
        user.setZodiacSign("gemini");
        user.setGender("female");
    }

    @Test
    @DisplayName("GET /userapi/user/{id} returns ApiErrorResponse for missing user")
    void getUserByIdReturnsApiErrorResponseWhenUserNotFound() throws Exception {
        when(userService.getUserById("missing-id")).thenReturn(Optional.empty());

        mockMvc.perform(get("/userapi/user/missing-id"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("NOT_FOUND"))
                .andExpect(jsonPath("$.message").value("User was not found"));
    }

    @Test
    @DisplayName("POST /userapi/addUser returns ApiErrorResponse for invalid email")
    void addUserReturnsApiErrorResponseForInvalidEmail() throws Exception {
        user.setEmail("not-an-email");

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/userapi/addUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("A valid email address is required"));
    }
}
