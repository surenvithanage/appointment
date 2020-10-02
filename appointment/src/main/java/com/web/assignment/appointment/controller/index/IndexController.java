package com.web.assignment.appointment.controller.index;

import com.web.assignment.appointment.dto.AppointmentDto;
import com.web.assignment.appointment.mapping.AppointmentType;
import com.web.assignment.appointment.mapping.Report;
import com.web.assignment.appointment.mapping.Session;
import com.web.assignment.appointment.mapping.User;
import com.web.assignment.appointment.repository.appointment.AppointmentTypeRepository;
import com.web.assignment.appointment.repository.report.ReportRepository;
import com.web.assignment.appointment.repository.session.CacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class IndexController {

    @Autowired
    private AppointmentTypeRepository appointmentTypeRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private CacheRepository cacheRepository;

    @GetMapping
    public String index(Model model) {
        Optional<Session> optionalSession = cacheRepository.findById((long) 1);
        if (optionalSession.isPresent()) {
            model.addAttribute("loggedin", optionalSession.get().getUser());
        } else {
            model.addAttribute("loggedin", null);
        }
        return "index";
    }

    @GetMapping("report/{id}")
    public String report(Model model, @PathVariable("id") long id) {
        User user;
        Optional<Session> optionalSession = cacheRepository.findById((long) 1);
        if (optionalSession.isPresent()) {
            user = optionalSession.get().getUser();
            model.addAttribute("user", user);
            model.addAttribute("loggedin", user);
        } else {
            model.addAttribute("user", null);
            model.addAttribute("loggedin", null);
        }
        Optional<Report> report = reportRepository.findById(id);
        if (report.isPresent()) {
            Report reportObj = report.get();
            model.addAttribute("report", reportObj);
        }
        return "report";
    }

    @GetMapping("about")
    public String about(Model model) {
        Optional<Session> optionalSession = cacheRepository.findById((long) 1);
        if (optionalSession.isPresent()) {
            model.addAttribute("loggedin", optionalSession.get().getUser());
        } else {
            model.addAttribute("loggedin", null);
        }
        return "about";
    }

    @GetMapping("contact")
    public String contact(Model model) {
        Optional<Session> optionalSession = cacheRepository.findById((long) 1);
        if (optionalSession.isPresent()) {
            model.addAttribute("loggedin", optionalSession.get().getUser());
        } else {
            model.addAttribute("loggedin", null);
        }
        return "contact";
    }

    @GetMapping("treatments")
    public String treatments(Model model) {
        Optional<Session> optionalSession = cacheRepository.findById((long) 1);
        if (optionalSession.isPresent()) {
            model.addAttribute("loggedin", optionalSession.get().getUser());
        } else {
            model.addAttribute("loggedin", null);
        }
        return "treatments";
    }

    @GetMapping("appointment")
    public String appointment(Model model) {
        AppointmentDto appointment = new AppointmentDto();
        User user = null;
        Optional<Session> optionalSession = cacheRepository.findById((long) 1);
        if (optionalSession.isPresent()) {
            model.addAttribute("loggedin", optionalSession.get().getUser());
            user = optionalSession.get().getUser();
        } else {
            model.addAttribute("loggedin", null);
        }
        appointment.setUserId(user.getId());
        appointment.setAmount("2250");
        model.addAttribute("appointment", appointment);
        model.addAttribute("id", user.getId());
        model.addAttribute("name", user.getFname());
        model.addAttribute("email", user.getEmail());
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
        Optional<Session> optionalSession = cacheRepository.findById((long) 1);
        if (optionalSession.isPresent()) {
            model.addAttribute("loggedin", null);
            cacheRepository.save(null);
        }
        return "index";
    }
}
