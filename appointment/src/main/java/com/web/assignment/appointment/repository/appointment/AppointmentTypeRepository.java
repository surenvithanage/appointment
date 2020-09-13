package com.web.assignment.appointment.repository.appointment;

import com.web.assignment.appointment.mapping.AppointmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentTypeRepository extends JpaRepository<AppointmentType, Long> {

}
