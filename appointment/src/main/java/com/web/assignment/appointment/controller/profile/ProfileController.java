package com.web.assignment.appointment.controller.profile;

import com.google.gson.Gson;
import com.web.assignment.appointment.dto.UserDto;
import com.web.assignment.appointment.mapping.Appointment;
import com.web.assignment.appointment.mapping.Payment;
import com.web.assignment.appointment.mapping.Report;
import com.web.assignment.appointment.mapping.User;
import com.web.assignment.appointment.repository.appointment.AppointmentRepository;
import com.web.assignment.appointment.repository.payment.PaymentRepository;
import com.web.assignment.appointment.repository.redis.RedisRepository;
import com.web.assignment.appointment.repository.report.ReportRepository;
import com.web.assignment.appointment.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisRepository redisRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping("profile")
    public String profile(Model model) {
        User user = new User();
        String cache = redisRepository.findById("USER");
        Gson gson = new Gson();
        UserDto userCache = gson.fromJson(cache, UserDto.class);
        long reportlist1 = 0;
        long appointmentlist1 = 0;
        Optional<User> userList = userRepository.findById(userCache.getId());
        if(userList.isPresent()) {
            user = userList.get();
            List<Appointment> appointmentList = appointmentRepository.appointmentList(user.getId());
            List<Report> reportList = reportRepository.reportList(user.getId());
            List<Payment> paymentList = paymentRepository.paymentList(user.getId());
            reportlist1 = reportList.size();
            appointmentlist1 = appointmentList.size();
            model.addAttribute("paymentList", paymentList);
            model.addAttribute("appointmentList", appointmentList);
            model.addAttribute("reportList", reportList);
        }
        model.addAttribute("loggedin", cache);
        model.addAttribute("user", user);
        model.addAttribute("userId", user.getId());
        model.addAttribute("appointmentcount", appointmentlist1);
        model.addAttribute("reportcount", reportlist1);

        return "profile";
    }
}
