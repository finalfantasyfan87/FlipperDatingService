package com.dating.controller;

import com.dating.model.User;
import com.dating.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)

class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockitoBean
	private UserService userService;
	private User user;

	@BeforeEach
	void setup() {
		user = new User();
		user.setId("13252350sgew353tyugh");
		user.setUserName("love_hurts943");
		user.setEmail("love_hurts943@example.com");
		user.setZodiacSign("gemini");
		user.setGender("female");
		user.setInterests(Arrays.asList("singing","hiking"));
		user.setPassword("cuew2431pis3e02fgsg");
		user.setProfilePictureUrl("");
	}

	@Test
	void shouldCreateNewUser() throws Exception {
		when(userService.saveUser(any(User.class))).thenReturn(user);
		mockMvc.perform(post("/userapi/addUser")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(user)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").value("13252350sgew353tyugh"));
	}

	@Test
	void shouldGetUsers() throws Exception {
		when(userService.getAllUsers()).thenReturn(Arrays.asList(user));
		mockMvc.perform(get("/userapi/users"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray());
	}

	@Test
	void shouldGetUserById() throws Exception {
		when(userService.getUserById(user.getId())).thenReturn(Optional.of(user));
            mockMvc.perform(get("/userapi/user/"+user.getId()))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value("13252350sgew353tyugh"));
    }

	@Test
	void shouldGetUserByUserName() throws Exception {
		when(userService.findUserByUserName(user.getUserName())).thenReturn(user);
		mockMvc.perform(get("/userapi/user/name/"+user.getUserName()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.userName").value("love_hurts943"));
	}

	@Test
	void shouldGetUsersByZodiacSign() throws Exception {
		User user2 = new User();
		user2 = new User();
		user2.setId("13252350sgew353tyugh");
		user2.setUserName("love_cures84");
		user2.setEmail("love_cures84@example.com");
		user2.setZodiacSign("gemini");
		user2.setGender("male");
		user2.setInterests(Arrays.asList("singing","hiking"));
		user2.setPassword("valentin3252%$%");
		user2.setProfilePictureUrl("");
		List<User> users =Arrays.asList(user,user2);
		when(userService.getUsersByZodiacSign("gemini")).thenReturn(users);
		mockMvc.perform(get("/userapi/user/zodiac/"+user.getZodiacSign()))
				.andExpectAll(status().isOk()).andExpectAll(jsonPath("$").isArray())
				.andExpectAll(jsonPath("$.size()").value(2));
	}

	@Test
	void shouldReturnTestString() throws Exception {
		mockMvc.perform(get("/userapi/test"))
				.andExpect(status().isOk())
				.andExpect(content().string("API is working!"));
	}


}
