package com.web.assignment.appointment.controller.authentication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.assignment.appointment.mapping.User;
import com.web.assignment.appointment.repository.redis.RedisRepository;
import com.web.assignment.appointment.service.authentication.AuthenticationService;
import com.web.assignment.appointment.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import java.io.IOException;

@Controller
public class AuthenticateController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RedisRepository redisRepository;

    @PostMapping("authenticate")
    public String authentication(Model model, @ModelAttribute("user") User user) {
        User userInfo = authenticationService.authenticateUser(user);
        if (userInfo != null) {
            ObjectMapper Obj = new ObjectMapper();
            String jsonStr;
            try {
                jsonStr = Obj.writeValueAsString(userInfo);
                redisRepository.save("USER", jsonStr);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            String cache = redisRepository.findById("USER");
            model.addAttribute("loggedin", cache);
            return "index";
        } else {
            model.addAttribute("key", "Invalid Credentials");
        }
        return "login";
    }

    @PostMapping("signup")
    public String signUp(@ModelAttribute("user") User user) {
        authenticationService.registerUser(user);
        try {
            emailService.sendmail("Registeration", "Successfully Registered", "You have successfully been registered to mercy care labs. Your username is " + user.getEmail() + " and password is " + user
                    .getPassword(), user.getEmail());
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "login";
    }
}
