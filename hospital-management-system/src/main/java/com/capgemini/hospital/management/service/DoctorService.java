package com.capgemini.hospital.management.service;

import com.capgemini.hospital.management.entity.Doctor;
import com.capgemini.hospital.management.exception.DuplicateResourceException;
import com.capgemini.hospital.management.exception.ResourceNotFoundException;
import com.capgemini.hospital.management.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository repo;

    public Doctor create(Doctor doctor) {
        if (repo.findByEmail(doctor.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Email already registered");
        }
        return repo.save(doctor);
    }

    public List<Doctor> getAll() {
        return repo.findAll();
    }

    public Doctor getById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));
    }

    public Doctor update(int id, Doctor d) {
        Doctor doc = getById(id);
        doc.setName(d.getName());
        doc.setSpecialization(d.getSpecialization());
        doc.setEmail(d.getEmail());
        doc.setPhone(d.getPhone());
        return repo.save(doc);
    }

    public void delete(int id) {
        repo.delete(getById(id));
    }
}