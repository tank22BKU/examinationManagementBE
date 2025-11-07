package com.examManagementBE.service.usertoken;

import com.examManagementBE.entity.User;
import com.examManagementBE.entity.UserToken;
import com.examManagementBE.repository.UserTokenRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class UserTokenService implements IUserTokenService {

    final UserTokenRepository userTokenRepository;
    public UserTokenService(UserTokenRepository userTokenRepository) {
        this.userTokenRepository = userTokenRepository;
    }

    @Override
    public UserToken saveToken(User user, String token) {
        OffsetDateTime now = OffsetDateTime.now();
        OffsetDateTime expiresAt = now.plusHours(24);
        String refreshToken = UUID.randomUUID().toString();

        UserToken userToken = UserToken.builder()
                .user(user)
                .accessToken(token)
                .refreshToken(refreshToken)
                .expiresAt(expiresAt)
                .build();

        return userTokenRepository.save(userToken);
    }
}
