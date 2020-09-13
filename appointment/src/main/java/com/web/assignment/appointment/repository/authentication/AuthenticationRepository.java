package com.web.assignment.appointment.repository.authentication;

import com.web.assignment.appointment.mapping.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepository extends JpaRepository<User, Long> {
    User findByEmailAndPassword(String email, String password);
}
