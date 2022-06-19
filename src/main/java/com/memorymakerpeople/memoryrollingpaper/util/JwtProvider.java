package com.memorymakerpeople.memoryrollingpaper.util;

import com.memorymakerpeople.memoryrollingpaper.service.MemberService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtProvider {

    // Jwt 생성
    public String makeJwtToken(String id) {
        Date now = new Date();
        log.info("JwtProvider makeJwtToken id = ", id);
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // (1)
                .setIssuer("memoryMaker") // (2)
                .setIssuedAt(now) // (3)
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(60).toMillis())) // (4)
                .claim("id", id) // (5)
                .signWith(SignatureAlgorithm.HS256, "secret") // (6)
                .compact();
    }
    //@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        //Claims claims = JwtProvider.parseJwtToken(authorizationHeader);
        Claims claims = parseJwtToken(authorizationHeader);

        filterChain.doFilter(request, response);
    }

    public Claims parseJwtToken(String authorizationHeader) {
        validationAuthorizationHeader(authorizationHeader); // (1)
        String token = extractToken(authorizationHeader); // (2)

        return Jwts.parser()
                .setSigningKey("secret") // (3)
                .parseClaimsJws(token) // (4)
                .getBody();
    }


    private void validationAuthorizationHeader(String header) {
        if (header == null || !header.startsWith("Bearer ")) {
            throw new IllegalArgumentException();
        }
    }

    private String extractToken(String authorizationHeader) {
        return authorizationHeader.substring("Bearer ".length());
    }
}