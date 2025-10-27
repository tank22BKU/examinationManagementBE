package com.examManagementBE.mapper;

import com.examManagementBE.entity.User;
import com.examManagementBE.pojo.request.UserRequest;
import com.examManagementBE.pojo.request.SignupRequest;
import com.examManagementBE.pojo.response.UserResponse;
import com.examManagementBE.utils.DateTimeUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {

    public static UserResponse userMapper(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .lastLogin(user.getLastLogin())
                .isActive(user.getIsActive())
                .dateOfBirth(user.getDateOfBirth())
                .profileImg(user.getProfileImg())
                .build();
    }

    public static User userMapperByUserRequest(UserRequest request, String password) {
        return User.builder()
                .fullName(request.getName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(new BCryptPasswordEncoder().encode(password))
                .isActive(false)
                .dateOfBirth(request.getDateOfBirth())
                .build();
    }

    public static User getUserBySignUpRequest(SignupRequest signupRequest) {
        return User.builder()
                .fullName(signupRequest.getFullName())
                .email(signupRequest.getEmail())
                .phoneNumber(signupRequest.getPhoneNumber())
                .password(new BCryptPasswordEncoder().encode(signupRequest.getPassword()))
                .profileImg(signupRequest.getProfileImg())
                .roleId(signupRequest.getRoleId())
                .isActive(false)
                .dateOfBirth(DateTimeUtils.getDateTimeNow())
                .build();
    }
}
