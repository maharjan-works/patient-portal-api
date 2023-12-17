package com.maharjanworks.patientportalapi.service;

import com.maharjanworks.patientportalapi.model.Patient;
import com.maharjanworks.patientportalapi.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService{

    @Autowired private PatientRepository patientRepository;

    @Override
    public List<Patient> findAll() {
        return this.patientRepository.findAll();
    }

    @Override
    public Patient save(Patient patient) {
        return this.patientRepository.save(patient);
    }
}
