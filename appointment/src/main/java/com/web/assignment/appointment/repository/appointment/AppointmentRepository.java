package com.web.assignment.appointment.repository.appointment;

import com.web.assignment.appointment.mapping.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query(value = "select * from appointment where user_id = :id", nativeQuery = true)
    List<Appointment> appointmentList(long id);
}
