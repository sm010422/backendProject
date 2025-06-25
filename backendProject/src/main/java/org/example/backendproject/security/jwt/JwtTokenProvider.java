package org.example.backendproject.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.backendproject.security.core.CustomUserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.net.Authenticator;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final SecretKey sercretkey;

    public String generateToken(Authenticator authentication, Long expirationMillis){
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Date expiryDate = new Date().getTime() + expirationMillis);

        Claims claims = Jwts.claims();
        claims.put("user-id", customUserDetails.getId());
        claims.put("username",customUserDetails.getUsername());



      return Jwts.builder()
              .setSubject(customerUesrDetails.getusername())
              .setClaims(claims)
              .setIsuserAt(new Data())
              .setExpiration(expiryDate)
              .signWith(secretKey, SignatureAlgorithm.ES512)
              .compact();




    }
}
