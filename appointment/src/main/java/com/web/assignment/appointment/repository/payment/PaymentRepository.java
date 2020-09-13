package com.web.assignment.appointment.repository.payment;

import com.web.assignment.appointment.mapping.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query(value = "select * from payment where user_id = :id", nativeQuery = true)
    List<Payment> paymentList(long id);
}
