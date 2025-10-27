package com.examManagementBE.service.auth;

import com.examManagementBE.pojo.request.*;
import com.examManagementBE.pojo.response.JwtResponse;

public interface IAuthService {
    JwtResponse login(LoginRequest loginRequest);
    JwtResponse signup(SignupRequest signupRequest);
    Boolean changePassword(ChangePasswordRequest changePasswordRequest);
}
