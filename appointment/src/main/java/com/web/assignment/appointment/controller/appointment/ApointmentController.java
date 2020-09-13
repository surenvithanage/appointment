package com.web.assignment.appointment.controller.appointment;

import com.google.gson.Gson;
import com.web.assignment.appointment.dto.AppointmentDto;
import com.web.assignment.appointment.dto.UserDto;
import com.web.assignment.appointment.mapping.Appointment;
import com.web.assignment.appointment.mapping.Payment;
import com.web.assignment.appointment.repository.appointment.AppointmentRepository;
import com.web.assignment.appointment.repository.payment.PaymentRepository;
import com.web.assignment.appointment.repository.redis.RedisRepository;
import com.web.assignment.appointment.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import java.io.IOException;

@Controller
public class ApointmentController {

    @Autowired
    private RedisRepository redisRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private EmailService emailService;

    @PostMapping("placeappointment")
    public String placeappointment(Model model, @ModelAttribute("appointment") AppointmentDto appointment) {
        String cache = redisRepository.findById("USER");
        Gson gson = new Gson();
        UserDto userCache = gson.fromJson(cache, UserDto.class);
        model.addAttribute("loggedin", cache);

        Appointment appointment1 = new Appointment();
        appointment1.setUserId(userCache.getId());
        appointment1.setAppointmentTypeId(appointment.getAppointmentTypeId());
        appointment1.setDate(appointment.getDate());
        appointment1.setDoctor(appointment.getDoctor());
        appointment1.setHospital(appointment.getHospital());
        appointment1.setTime(appointment.getTime());

        Payment payment = new Payment();
        payment.setAmount(appointment.getAmount());
        payment.setCardnumber(appointment.getCardnumber());
        payment.setCvv(appointment.getCvv());
        payment.setPin(appointment.getPin());
        payment.setType(appointment.getType());
        payment.setUserId(userCache.getId());

        Appointment appointment2 = appointmentRepository.save(appointment1);

        Payment payment1 = paymentRepository.save(payment);

        try {
            emailService.sendmail("Appointment", "Successfully placed appointment", "Your appointment id : " + appointment2.getId(), userCache.getEmail());
            emailService.sendmail("Payment", "Payment confirmation", "Your payment reference id : #000" + payment.getId(), userCache.getEmail());
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "index";
    }
}
