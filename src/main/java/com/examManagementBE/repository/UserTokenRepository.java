package com.examManagementBE.repository;

import com.examManagementBE.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, Integer> {
    Optional<UserToken> findByAccessToken(String token);
}
