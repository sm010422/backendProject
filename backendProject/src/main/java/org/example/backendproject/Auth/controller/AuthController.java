package org.example.backendproject.Auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.example.backendproject.Auth.dto.LoginRequestDTO;
import org.example.backendproject.Auth.dto.LoginResponseDTO;
import org.example.backendproject.Auth.dto.SignUpRequestDTO;
import org.example.backendproject.Auth.service.AuthService;
import org.example.backendproject.user.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    /** 회원가입 **/
    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO){
        try {
            authService.signUp(signUpRequestDTO);
            return ResponseEntity.ok("회원가입 성공");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage()); //401
        }
    }

    /** 로그인 **/
    @PostMapping("/loginSecurity")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO){
        LoginResponseDTO loginResponseDTO = authService.login(loginRequestDTO);
        return ResponseEntity.ok(loginResponseDTO);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestHeader(value = "Authorization", required = false)
                                          String authorizatioinHeader, HttpServletRequest request){
        String refreshToken = null;

        if(request.getCookies() != null){
            for (Cookie cookie : request.getCookies()){
                if("refreshToken".equals(cookie.getName())){
                    refreshToken = cookie.getValue();
                }
            }
        }

        if(refreshToken  == null && authorizatioinHeader != null && authorizatioinHeader.startsWith("Bearer ")){
            refreshToken = authorizatioinHeader.replace("Bear ","").trim();
        }
        if(refreshToken == null || refreshToken.isEmpty()){
            throw new IllegalArgumentException("리프레시 토큰이 없습니다.");
        }
        String newAccessToken = authService.refreshToken(refreshToken);

        Map<String, String> res = new HashMap<>();
        res.put("accassToken", newAccessToken);
        res.put("refreshToken", refreshToken);

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }



}
