package com.agree.chattingapi.conf;

import ch.qos.logback.classic.Logger;
import com.agree.chattingapi.dtos.user.UserDetailsDto;
import com.agree.chattingapi.entities.UserInfo;
import com.agree.chattingapi.responses.CommonResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@Configuration
public class CustomAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private static final Logger log = (Logger) LoggerFactory.getLogger(CustomAuthSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserInfo user = ((UserDetailsDto) authentication.getPrincipal()).getUserInfo();

        log.warn(generateToken(user));

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter printWriter = response.getWriter();
        printWriter.print(new ObjectMapper().writeValueAsString(new CommonResponse<>(generateToken(user))));
        printWriter.flush();
        printWriter.close();
    }

    private String generateToken(UserInfo userInfo) {
        String secretKey = new ApplicationConfig().getJWTKey();

        String strJWT = Jwts.builder()
                .setSubject(userInfo.getId())
                .setIssuer("http://chatting-app.agree.com")
                .claim("user", userInfo)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + 3600000))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        return strJWT;
    }
}
