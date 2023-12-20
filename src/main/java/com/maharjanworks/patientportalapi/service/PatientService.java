package com.maharjanworks.patientportalapi.service;

import com.maharjanworks.patientportalapi.model.Patient;

import java.util.List;

public interface PatientService {

    List<Patient> findAll();

    Patient save(Patient patient);

    Patient getPatientById(Long patientId);

    Patient updatePatient(Long patientId, Patient newPatientDetails);
}
