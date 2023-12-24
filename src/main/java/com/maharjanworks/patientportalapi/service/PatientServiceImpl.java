package com.maharjanworks.patientportalapi.service;

import com.maharjanworks.patientportalapi.exception.ResourceNotFoundException;
import com.maharjanworks.patientportalapi.model.Patient;
import com.maharjanworks.patientportalapi.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Patient getPatientById(Long patientId) {
        return this.patientRepository.findById(patientId)
                .orElseThrow(()->new ResourceNotFoundException("Patient not exist with id : "+ patientId));
    }

    @Override
    public Patient updatePatient(Long patientId, Patient newPatientDetails) {
       Patient dbPatient = this.patientRepository.findById(patientId)
                .orElseThrow(()->new ResourceNotFoundException("Patient not exist with id : "+ patientId));

       dbPatient.setFirstName(newPatientDetails.getFirstName());
       dbPatient.setLastName(newPatientDetails.getLastName());
       dbPatient.setDob(newPatientDetails.getDob());
       dbPatient.setEmail(newPatientDetails.getEmail());
       dbPatient.setPhoneNumber(newPatientDetails.getPhoneNumber());

       return this.patientRepository.save(dbPatient);
    }

    @Override
    public Map<String, Boolean> deletePatient(Long patientId) {
        Patient dbPatient = this.patientRepository.findById(patientId)
                .orElseThrow(()-> new ResourceNotFoundException("Patient not exist with Id: " + patientId));

        this.patientRepository.delete(dbPatient);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
