package org.example.backendproject.security.jwt;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.backendproject.security.core.CustomUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    //JwtTokenFilter 모든 HTTP요청을 가로채서 JWT 토큰을 검사하는 필터 역할
    //OncePerRequestFilter는 한 요청당 딱 한번만 실행되는 필터 역할

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService customUserDetailsService;

    // HTTP 매 요청마다 호출
    @Override
    protected void doFilterInternal(HttpServletRequest request, //http 요청
                                    HttpServletResponse response, //http 응답
                                    FilterChain filterChain) throws ServletException, IOException {

        String accessToken = getTokenFromRequest(request); //요청 헤더에서 토큰 추출

        if (accessToken != null && jwtTokenProvider.validateToken(accessToken)) {


        }



    }

    //HTTP 요청 헤더에서 토큰을 추출하는 메서드
    public String getTokenFromRequest(HttpServletRequest request) {

        String token = null;

        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            token = bearerToken.substring(7);
        }

        return token;
    }


    private UsernamePasswordAuthenticationToken getAuthentication(String token) {

        //JWT 토큰
        Long userid = jwtTokenProvider.getUserIdFromToken(token);


    }
















}
