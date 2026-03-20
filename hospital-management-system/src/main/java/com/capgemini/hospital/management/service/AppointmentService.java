package com.capgemini.hospital.management.service;

import com.capgemini.hospital.management.entity.*;
import com.capgemini.hospital.management.enumtype.Status;
import com.capgemini.hospital.management.exception.ResourceNotFoundException;
import com.capgemini.hospital.management.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository repo;

    @Autowired
    private DoctorRepository doctorRepo;

    public Appointment create(int doctorId, Appointment appt) {
        Doctor doc = doctorRepo.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

        if (appt.getScheduledTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Invalid time");
        }

        appt.setDoctor(doc);
        return repo.save(appt);
    }

    public Page<Appointment> getAll(int doctorId, int page, int size) {
        Pageable p = PageRequest.of(page, size, Sort.by("scheduledTime").ascending());
        return repo.findByDoctorDoctorId(doctorId, p);
    }

    public Appointment getOne(int doctorId, int id) {
        Appointment a = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));

        if (a.getDoctor().getDoctorId() != doctorId) {
            throw new RuntimeException("Mismatch");
        }

        return a;
    }

    public void delete(int id) {
        repo.deleteById(id);
    }

    public Page<Appointment> getPending() {
        return repo.findByStatusOrderByScheduledTimeAsc(
                Status.PENDING,
                PageRequest.of(0, 5)
        );
    }
}