package com.web.assignment.appointment.repository.session;

import com.web.assignment.appointment.mapping.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Project appointment
 * User : suren_v
 * Date : 10/2/2020
 * Time : 4:44 PM
 */
@Repository
public interface CacheRepository extends JpaRepository<Session, Long> {
}
