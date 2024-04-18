package com.example.demo.configuration;

import com.example.demo.models.Token;
import com.example.demo.repositories.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
public class CustomLogoutHandler implements LogoutHandler {

    private final TokenRepository tokenRepository;

    public CustomLogoutHandler(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication) {
        String authHeader = request.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        String token = authHeader.substring(7);
        // Token storedToken = tokenRepository.findByToken(token).orElse(null);

        // if(storedToken != null) {
        //     storedToken.setLoggedOut(true);
        //     tokenRepository.save(storedToken);
        // }

        try {
            Optional<Token> optionalToken = tokenRepository.findByToken(token);
            if (optionalToken.isPresent()) {
                Token storedToken = optionalToken.get();
                storedToken.setLoggedOut(true);
                tokenRepository.save(storedToken);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (ExecutionException | InterruptedException e) {
            // Xử lý ngoại lệ nếu cần thiết
            e.printStackTrace();
            // Hoặc trả về mã lỗi HTTP tương ứng
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}