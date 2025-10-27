package com.examManagementBE.controller;

import com.examManagementBE.common.constants.EndpointConstants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(EndpointConstants.API)
public class UserController {
    @GetMapping("/users")
    public String getUser(){
        return "Get all users";
    }
}
