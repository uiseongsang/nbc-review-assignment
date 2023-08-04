package com.example.nvcreviewassignment.controller;

import com.example.nvcreviewassignment.common.config.WebSecurityConfig;
import com.example.nvcreviewassignment.mvc.MockSpringSecurityFilter;
import com.example.nvcreviewassignment.user.controller.UserController;
import com.example.nvcreviewassignment.user.dto.AuthRequestDto;
import com.example.nvcreviewassignment.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
            controllers = {UserController.class},
            excludeFilters = {
                    @ComponentScan.Filter(
                            type = FilterType.ASSIGNABLE_TYPE,
                            classes = WebSecurityConfig.class
                    )
            }
    )
@MockBean(JpaMetamodelMappingContext.class)
public class UserControllerTest {
    private MockMvc mockMvc;

    private Principal mockPrincipal;

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    UserService userService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity(new MockSpringSecurityFilter()))
                .build();
    }
//        private void mockUserSetup() {
//            String nickname = "thomas";
//            String password = "thomas1234";
//            User testUser = new User(nickname,password);
//            UserDetailsImpl testUserDetails = new UserDetailsImpl(testUser);
//            mockPrincipal = new UsernamePasswordAuthenticationToken(testUserDetails,"",testUserDetails.getAuthorities());
//        }

    @Test
    @DisplayName("회원가입")
    void 회원가입을_성공_시킬수있다() throws Exception {
        // Given
        // When
        // Then
        mockMvc.perform(post("/api/auth/signup")
                        .content(objectMapper.writeValueAsString(new AuthRequestDto("thomas","thomas1234","thomas1234")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful());
    }
}
