package com.web.assignment.appointment.controller.index;

import com.google.gson.Gson;
import com.web.assignment.appointment.dto.AppointmentDto;
import com.web.assignment.appointment.dto.UserDto;
import com.web.assignment.appointment.mapping.Appointment;
import com.web.assignment.appointment.mapping.AppointmentType;
import com.web.assignment.appointment.mapping.User;
import com.web.assignment.appointment.repository.appointment.AppointmentTypeRepository;
import com.web.assignment.appointment.repository.redis.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private RedisRepository redisRepository;

    @Autowired
    private AppointmentTypeRepository appointmentTypeRepository;

    @GetMapping
    public String index(Model model) {
        String cache = redisRepository.findById("USER");
        model.addAttribute("loggedin", cache);
        return "index";
    }

    @GetMapping("about")
    public String about(Model model) {
        String cache = redisRepository.findById("USER");
        model.addAttribute("loggedin", cache);
        return "about";
    }

    @GetMapping("contact")
    public String contact(Model model) {
        String cache = redisRepository.findById("USER");
        model.addAttribute("loggedin", cache);
        return "contact";
    }

    @GetMapping("treatments")
    public String treatments(Model model) {
        String cache = redisRepository.findById("USER");
        model.addAttribute("loggedin", cache);
        return "treatments";
    }

    @GetMapping("appointment")
    public String appointment(Model model) {
        AppointmentDto appointment = new AppointmentDto();
        String cache = redisRepository.findById("USER");
        Gson gson = new Gson();
        UserDto userCache = gson.fromJson(cache, UserDto.class);
        model.addAttribute("loggedin", cache);
        appointment.setUserId(userCache.getId());
        appointment.setAmount("2250");
        model.addAttribute("appointment", appointment);
        model.addAttribute("id", userCache.getId());
        model.addAttribute("name", userCache.getFname());
        model.addAttribute("email", userCache.getEmail());
        List<AppointmentType> appointmentTypeList = appointmentTypeRepository.findAll();
        model.addAttribute("appointmentTypeList", appointmentTypeList);
        List<String> listPayment = Arrays.asList("VISA", "MASTER", "AMEX");
        model.addAttribute("listPayment", listPayment);
        return "appointment";
    }

    @GetMapping("login")
    public String login(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @GetMapping("register")
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);

        List<String> listGender = Arrays.asList("Male", "Female", "Other");
        model.addAttribute("listGender", listGender);
        return "register";
    }

    @GetMapping("logout")
    public String logout(Model model) {
        redisRepository.save("USER", null);
        String cache = redisRepository.findById("USER");
        model.addAttribute("loggedin", cache);
        return "index";
    }
}
