package com.web.assignment.appointment.repository.report;

import com.web.assignment.appointment.mapping.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    @Query(value = "select * from report where user_id = :id", nativeQuery = true)
    List<Report> reportList(long id);
}

