package com.pcsell.user.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.pcsell.user.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
/*@SpringBootTest(classes=UserController.class)
@AutoConfigureMockMvc*/
public class SpringTestUserController {

	@MockBean
	private UserService userService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void userForm() throws Exception {
		mockMvc.perform(get("/user/form")).andExpect(status().isOk()).andExpect(view().name("/user/form"));
	}

	@Test
	public void createUser() throws Exception {
		mockMvc.perform(post("/user").param("userId", "spring").param("password", "1").param("name", "spring")
				.param("email", "spring@sk.com")).andDo(print()).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/user"));
	}

	@Test
	public void userList() throws Exception {
		mockMvc.perform(get("/user")).andExpect(status().isOk()).andExpect(view().name("/user/list"));
	}

	@Test
	public void updateUser_unAutenticatedUser() throws Exception {
		mockMvc.perform(get("/user/1/form")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/user/loginForm"));
	}

	@Test
	public void updateUser_AutenticatedUser() throws Exception {
		mockMvc.perform(get("/user/1/form")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/user/loginForm"));
	}
}
