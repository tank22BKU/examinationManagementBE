package com.examManagementBE.controller;

import com.examManagementBE.common.constants.EndpointConstants;
import com.examManagementBE.pojo.ApiResult;
import com.examManagementBE.pojo.request.ChangePasswordRequest;
import com.examManagementBE.pojo.request.LoginRequest;
import com.examManagementBE.pojo.request.SignupRequest;
import com.examManagementBE.pojo.response.JwtResponse;
import com.examManagementBE.service.auth.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(EndpointConstants.AUTH)
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService authService;

    @PostMapping(EndpointConstants.SIGN_IN)
    public ResponseEntity<ApiResult<JwtResponse>> authenticateUser(@RequestBody LoginRequest loginRequest) {
        JwtResponse jwtResponse = authService.login(loginRequest);
        return ResponseEntity.ok(ApiResult.success(jwtResponse));
    }

    @PostMapping(EndpointConstants.SIGN_UP)
    public ResponseEntity<ApiResult<JwtResponse>> registerUser(@RequestBody SignupRequest signupRequest) {
        JwtResponse jwtResponse = authService.signup(signupRequest);
        return ResponseEntity.ok(ApiResult.success(jwtResponse));
    }

    @PutMapping(EndpointConstants.CHANGE_PASSWORD)
    public ResponseEntity<ApiResult<Boolean>> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        Boolean result = authService.changePassword(changePasswordRequest);
        return ResponseEntity.ok(ApiResult.success(result));
    }
}
