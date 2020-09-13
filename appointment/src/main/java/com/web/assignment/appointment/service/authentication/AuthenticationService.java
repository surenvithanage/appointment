package com.web.assignment.appointment.service.authentication;

import com.web.assignment.appointment.mapping.User;

public interface AuthenticationService {
    User authenticateUser(User user);
    User registerUser(User user);
}
