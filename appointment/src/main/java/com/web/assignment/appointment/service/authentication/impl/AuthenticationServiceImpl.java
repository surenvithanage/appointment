package com.web.assignment.appointment.service.authentication.impl;

import com.web.assignment.appointment.mapping.User;
import com.web.assignment.appointment.repository.authentication.AuthenticationRepository;
import com.web.assignment.appointment.service.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationRepository authenticationRepository;

    @Override
    public User authenticateUser(User user) {
        return authenticationRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
    }

    @Override
    public User registerUser(User user) {
        return authenticationRepository.save(user);
    }
}
