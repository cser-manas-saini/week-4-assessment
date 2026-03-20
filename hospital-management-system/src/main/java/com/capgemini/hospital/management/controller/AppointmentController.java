package com.capgemini.hospital.management.controller;

import com.capgemini.hospital.management.entity.Appointment;
import com.capgemini.hospital.management.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AppointmentController {

    @Autowired
    private AppointmentService service;

    @PostMapping("/doctors/{id}/appointments")
    public Appointment create(@PathVariable int id, @RequestBody Appointment a) {
        return service.create(id, a);
    }

    @GetMapping("/doctors/{id}/appointments")
    public Page<Appointment> getAll(@PathVariable int id,
                                   @RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "5") int size) {
        return service.getAll(id, page, size);
    }

    @GetMapping("/appointments/pending")
    public Page<Appointment> pending() {
        return service.getPending();
    }
}