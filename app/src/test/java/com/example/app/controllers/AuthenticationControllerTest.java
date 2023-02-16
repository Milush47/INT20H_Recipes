package com.example.app.controllers;

import com.example.app.dto.RegisterRequest;
import com.example.app.dto.SuccessResponse;
import com.example.app.services.EmailService;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.testcontainers.shaded.okhttp3.RequestBody;

import static com.example.app.errors.ExceptionMessage.EMAIl_IS_TAKEN;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmailService emailService;

    @Nested
    @DisplayName("POST /auth/register")
    class Register {
        private final RegisterRequest registerRequest = RegisterRequest.builder()
                .firstname("Milush")
                .lastname("Kulpiiev")
                .email("irror.fy.eagle@gmail.com")
                .password("Milush_1234")
                .confirmedPassword("Milush_1234")
                .build();

        private final String RequestJSONBody = asJsonString(registerRequest);

        @Test
        @DisplayName("should register user successfully")
        void success_registration() throws Exception {
            mockMvc.perform(
                    post("/auth/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(RequestJSONBody)
                    )
                    .andExpect(
                            status()
                                    .isOk()
                    )
                    .andExpect(
                            content()
                                    .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(
                            jsonPath("$.message")
                                    .value("User is registered")
                    )
                    .andExpect(
                            jsonPath("$.success")
                                    .value("true")

                    )
                    .andExpect(
                            jsonPath("$.data")
                                    .isNotEmpty()
                    );
        }

        @Test
        @DisplayName("should return 409 error")
        void failed_registration() throws Exception {
            when(emailService.isEmailExists(registerRequest.getEmail()))
                    .thenReturn(true);

            mockMvc.perform(
                post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(RequestJSONBody)
                    )
                    .andExpect(
                            status()
                                    .isConflict()
                    )
                    .andExpect(
                            jsonPath("$.statusCode")
                                    .value(409)
                    )
                    .andExpect(
                            jsonPath("$.timeStamp")
                                    .isNotEmpty()
                    )
                    .andExpect(
                            jsonPath("$.message")
                                    .value(EMAIl_IS_TAKEN)
                    )
                    .andExpect(
                            jsonPath("$.description")
                                    .value("uri=/auth/register")
                    );
        }
    }




    @Test
    void authenticate() {
    }

    @Test
    void logout() {
    }

    @Test
    void resetPassword() {
    }

    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}