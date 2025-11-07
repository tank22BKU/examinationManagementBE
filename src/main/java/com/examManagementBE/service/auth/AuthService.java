package com.examManagementBE.service.auth;

import com.examManagementBE.repository.UserRepository;
import com.examManagementBE.common.constants.EmailConstants;
import com.examManagementBE.entity.User;
import com.examManagementBE.exception.AppException;
import com.examManagementBE.exception.ErrorCode;
import com.examManagementBE.mapper.UserMapper;
import com.examManagementBE.pojo.request.*;
import com.examManagementBE.pojo.response.JwtResponse;
import com.examManagementBE.service.usertoken.IUserTokenService;
import com.examManagementBE.utils.JwtUtils;
import com.examManagementBE.utils.PasswordUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final BCryptPasswordEncoder passwordEncoder;
    private final IUserTokenService userTokenService;
    private final UserMapper userMapper;

    @Override
    public JwtResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_USERNAME_OR_PASSWORD));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new AppException(ErrorCode.INVALID_USERNAME_OR_PASSWORD);
        }

        validateAccountIsActive(user);

        String token = jwtUtils.generateToken(user.getEmail());

        userTokenService.saveToken(user, token);

        return new JwtResponse(
                token,
                ObjectUtils.isEmpty(user.getUser_ID()) ? null : user.getUser_ID().longValue(),
                user.getFullName(),
                user.getEmail()
        );
    }

    private void validateAccountIsActive(User user) {
        if (ObjectUtils.isEmpty(user.getIsActive()) || !user.getIsActive()) {
            throw new AppException(ErrorCode.ACCOUNT_NOT_ACTIVE);
        }
    }

    @Override
    public JwtResponse signup(SignupRequest signupRequest) {
        if (!signupRequest.getEmail().matches(EmailConstants.EMAIL_REGEX)) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }

        if (userRepository.findByEmail(signupRequest.getEmail()).isPresent()) {
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }

        User newUser = UserMapper.getUserBySignUpRequest(signupRequest);

        User savedUser = userRepository.save(newUser);
        String token = jwtUtils.generateToken(savedUser.getEmail());

        return new JwtResponse(
                token,
                savedUser.getUser_ID() != null ? savedUser.getUser_ID().longValue() : null,
                savedUser.getFullName(),
                savedUser.getEmail()
        );
    }

    @Override
    public Boolean changePassword(ChangePasswordRequest changePasswordRequest) {
        String email = JwtUtils.getCurrentUser();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        PasswordUtils.validatePassword(passwordEncoder, changePasswordRequest, user.getPassword());
        user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
        userRepository.save(user);
        return Boolean.TRUE;
    }
}
