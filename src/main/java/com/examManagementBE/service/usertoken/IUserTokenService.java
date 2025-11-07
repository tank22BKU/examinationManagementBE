package com.examManagementBE.service.usertoken;

import com.examManagementBE.entity.User;
import com.examManagementBE.entity.UserToken;

public interface IUserTokenService {
    UserToken saveToken(User admin, String token);
}
