package com.dating.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dating.model.User;
import com.dating.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Checks that each controller method calls the service once per request, and that
 * addUser validates the email. These tests use only existing endpoints.
 */
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerServiceCallTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId("13252350sgew353tyugh");
        user.setUserName("love_hurts943");
        user.setEmail("love_hurts943@example.com");
        user.setZodiacSign("gemini");
        user.setGender("female");
    }

    @Test
    @DisplayName("GET /userapi/users calls getAllUsers exactly once")
    void listUsersCallsServiceOnce() throws Exception {
        when(userService.getAllUsers()).thenReturn(List.of(user));

        mockMvc.perform(get("/userapi/users")).andExpect(status().isOk());

        verify(userService, times(1)).getAllUsers();
    }

    @Test
    @DisplayName("GET /userapi/user/name/{userName} calls findUserByUserName exactly once")
    void userByNameCallsServiceOnce() throws Exception {
        when(userService.findUserByUserName(user.getUserName())).thenReturn(user);

        mockMvc.perform(get("/userapi/user/name/" + user.getUserName()))
                .andExpect(status().isOk());

        verify(userService, times(1)).findUserByUserName(user.getUserName());
    }

    @Test
    @DisplayName("GET /userapi/user/zodiac/{sign} calls getUsersByZodiacSign exactly once")
    void usersByZodiacCallsServiceOnce() throws Exception {
        when(userService.getUsersByZodiacSign("gemini")).thenReturn(List.of(user));

        mockMvc.perform(get("/userapi/user/zodiac/gemini")).andExpect(status().isOk());

        verify(userService, times(1)).getUsersByZodiacSign("gemini");
    }

    @Test
    @DisplayName("POST /userapi/addUser rejects a malformed email with 400 and does not save")
    void addUserRejectsMalformedEmail() throws Exception {
        user.setEmail("not-an-email");
        when(userService.saveUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/userapi/addUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest());

        verify(userService, never()).saveUser(any(User.class));
    }

    @Test
    @DisplayName("POST /userapi/addUser accepts a valid email and returns 201")
    void addUserAcceptsValidEmail() throws Exception {
        when(userService.saveUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/userapi/addUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated());
    }
}
