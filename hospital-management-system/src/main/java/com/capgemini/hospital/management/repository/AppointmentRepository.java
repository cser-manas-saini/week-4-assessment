package com.capgemini.hospital.management.repository;

import com.capgemini.hospital.management.entity.Appointment;
import com.capgemini.hospital.management.enumtype.Status;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    Page<Appointment> findByDoctorDoctorId(int doctorId, Pageable pageable);

    Page<Appointment> findByStatusOrderByScheduledTimeAsc(Status status, Pageable pageable);
}