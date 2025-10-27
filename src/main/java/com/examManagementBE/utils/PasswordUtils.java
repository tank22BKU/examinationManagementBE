package com.examManagementBE.utils;

import com.examManagementBE.common.constants.CommonConstants;
import com.examManagementBE.common.constants.PasswordConstants;
import com.examManagementBE.exception.AppException;
import com.examManagementBE.exception.ErrorCode;
import com.examManagementBE.pojo.request.ChangePasswordRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {
    public static void validatePassword(BCryptPasswordEncoder passwordEncoder,
                                        ChangePasswordRequest changePasswordRequest,
                                        String currentPasswordHash) {

        if (!passwordEncoder.matches(changePasswordRequest.getOldPassword(), currentPasswordHash)) {
            throw new AppException(ErrorCode.INVALID_USERNAME_OR_PASSWORD);
        }
        if (passwordEncoder.matches(changePasswordRequest.getNewPassword(), currentPasswordHash)) {
            throw new AppException(ErrorCode.PASSWORD_MATCHES_OLD_PASSWORD);
        }
        if (!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmPassword())) {
            throw new AppException(ErrorCode.PASSWORDS_NOT_MATCH);
        }
        String newPassword = changePasswordRequest.getNewPassword();
        if (newPassword.length() < PasswordConstants.MIN_LENGTH) {
            throw new AppException(ErrorCode.PASSWORD_TOO_SHORT);
        }
        if (!newPassword.matches(PasswordConstants.UPPERCASE_REGEX)) {
            throw new AppException(ErrorCode.PASSWORD_NO_UPPERCASE);
        }
        if (!newPassword.matches(PasswordConstants.NUMBER_REGEX)) {
            throw new AppException(ErrorCode.PASSWORD_NO_NUMBER);
        }
        if (!newPassword.matches(PasswordConstants.SPECIAL_CHAR_REGEX)) {
            throw new AppException(ErrorCode.PASSWORD_NO_SPECIAL_CHAR);
        }
        if (newPassword.contains(CommonConstants.SPACE)) {
            throw new AppException(ErrorCode.PASSWORD_CONTAINS_SPACE);
        }
    }
}
