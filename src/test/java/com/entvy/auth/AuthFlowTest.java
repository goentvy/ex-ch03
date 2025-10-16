package com.entvy.auth;

import com.entvy.auth.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
public class AuthFlowTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testSignupLoginLogoutFlow() throws Exception {
        String email = "user@example.com";
        String password = "123";

        // 1. 회원가입
        SignupRequestDto signup = new SignupRequestDto(email, password, "user");
        performJsonPost("/auth/signup", signup)
            .andExpect(status().isOk());

        // 2. 로그인
        LoginRequestDto login = new LoginRequestDto(email, password);
        MvcResult loginResult = performJsonPost("/auth/login", login)
                .andExpect(status().isOk())
                .andReturn();

        // accessToken, refreshToken 저장
        String accessToken = loginResult.getResponse().getContentAsString();
        String refreshToken = extractRefreshTokenFromCookie(loginResult);

        // 사용자 정보 확인
        mockMvc.perform(get("/member/me")
                .header("Authorization", "Bearer " + accessToken))
                        .andExpect(status().isOk());

        // 3. 로그아웃
        mockMvc.perform(post("/auth/logout")
                .header("Authorization", "Bearer " + accessToken)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // 4. 재발급 시도 -> 실패 예상
        TokenRequestDto reissue = new TokenRequestDto(refreshToken);
        performJsonPost("/auth/reissue", reissue)
                .andExpect(status().isOk())
                .andDo(print());
    }

    // 공통 JSON POST 요청 메서드
    private ResultActions performJsonPost(String url, Object dto) throws Exception {
        return mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)));
    }

    // 쿠키에서 refreshToken 추출
    private String extractRefreshTokenFromCookie(MvcResult result) {
        String setCookie = result.getResponse().getHeader("Set-Cookie");
        if(setCookie != null && setCookie.contains("refreshToken=")) {
            int start = setCookie.indexOf("refreshToken=") + "refreshToken=".length();
            int end = setCookie.indexOf(";", start);
            return setCookie.substring(start, end);
        }
        return null;
    }
}
