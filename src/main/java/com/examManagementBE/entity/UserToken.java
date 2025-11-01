package com.examManagementBE.entity;

import com.examManagementBE.common.constants.EntityConstants;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = EntityConstants.USER_TOKENS_TABLE)
public class UserToken extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @Column(name = "access_token", length = 500, nullable = false)
    String accessToken;

    @Column(name = "refresh_token", length = 500)
    String refreshToken;

    @Column(name = "expires_at" ,nullable = false)
    OffsetDateTime expiresAt;
}
