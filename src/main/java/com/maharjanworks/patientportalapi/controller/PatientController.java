package com.maharjanworks.patientportalapi.controller;

import com.maharjanworks.patientportalapi.model.Patient;
import com.maharjanworks.patientportalapi.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class PatientController {

    @Autowired private PatientService patientService;

    @GetMapping("/patient")
    List<Patient> findAll(){
       return this.patientService.findAll();
    }
}
