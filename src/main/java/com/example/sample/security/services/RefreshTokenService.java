package com.example.sample.security.services;


import com.example.sample.exception.TokenRefreshException;
import com.example.sample.models.RefreshToken;
import com.example.sample.repository.RefreshTokenRepository;
import com.example.sample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    private final Long refreshTokenDurationMs = 36000L;

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    public Optional<RefreshToken> findByToken(String token){
        return this.refreshTokenRepository.findByToken(token);
    }
    public RefreshToken createRefreshToken(Long userId){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(userRepository.findById(userId).get());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = this.refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }
    public RefreshToken verifyExpiration(RefreshToken token){
        if(token.getExpiryDate().compareTo(Instant.now()) < 0 ) {
            this.refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }
        return token;
    }

    @Transactional
    public int deleteByUserId(Long userId){
        return this.refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
    }
}

