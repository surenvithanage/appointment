package com.web.assignment.appointment.service.email;

import javax.mail.MessagingException;
import java.io.IOException;

public interface EmailService {
    void sendmail(String title,String subject, String content, String email) throws MessagingException, IOException;
}
