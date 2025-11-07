package com.example.examination_application.service;

import com.example.examination_application.entity.User;
import com.example.examination_application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> getUsers(){
        return userRepository.findAll();
    }
}
