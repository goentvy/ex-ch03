package com.example.demo;

import com.example.demo.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AuthFlowTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @Rollback(false)
    public void testSignupLoginLogoutFlow() throws Exception {
        // 1. 회원가입
        SignupRequestDto signup = new SignupRequestDto();
        signup.setEmail("user@example.com");
        signup.setPassword("123");
        signup.setName("user");
        mockMvc.perform(post("/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signup)))
            .andExpect(status().isOk());

        // 2. 로그인
        LoginRequestDto login = new LoginRequestDto();
        login.setEmail("user@example.com");
        login.setPassword("123");
        MvcResult loginResult = mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(login)))
            .andExpect(status().isOk())
            .andReturn();

        String accessToken = loginResult.getResponse().getContentAsString();

        // 3. 로그아웃
        System.out.println("Access Token: " + accessToken);

        mockMvc.perform(post("/auth/logout")
                .header("Authorization", "Bearer " + accessToken)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

//        // 4. 재발급 시도 -> 실패 예상
//        TokenRequest reissue = new TokenRequest(tokens.getRefreshToken());
//        mockMvc.perform(post("/reissue")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(reissue)))
//                .andExpect(status().isUnauthorized());
    }
}
