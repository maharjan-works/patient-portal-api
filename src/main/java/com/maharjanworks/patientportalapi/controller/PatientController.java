package com.maharjanworks.patientportalapi.controller;

import com.maharjanworks.patientportalapi.model.Patient;
import com.maharjanworks.patientportalapi.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin( value = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class PatientController {

    @Autowired private PatientService patientService;

    @GetMapping("/patient")
    List<Patient> findAll(){
       return this.patientService.findAll();
    }

    @PostMapping("/patient")
    public Patient createPatient(@RequestBody Patient patient){
        return this.patientService.save(patient);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long patientId){
        return ResponseEntity.ok(this.patientService.getPatientById(patientId));
    }

    @PutMapping("/patient/{patientId}")
    public ResponseEntity<Patient> updatePatient(@PathVariable("patientId") Long patientId,@RequestBody Patient newPatientDetails){
          return ResponseEntity.ok(this.patientService.updatePatient(patientId, newPatientDetails));
    }



}
