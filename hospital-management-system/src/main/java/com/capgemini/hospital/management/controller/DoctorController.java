package com.capgemini.hospital.management.controller;

import com.capgemini.hospital.management.entity.Doctor;
import com.capgemini.hospital.management.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService service;

    @PostMapping
    public Doctor create(@RequestBody Doctor d) {
        return service.create(d);
    }

    @GetMapping
    public List<Doctor> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Doctor get(@PathVariable int id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Doctor update(@PathVariable int id, @RequestBody Doctor d) {
        return service.update(id, d);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}