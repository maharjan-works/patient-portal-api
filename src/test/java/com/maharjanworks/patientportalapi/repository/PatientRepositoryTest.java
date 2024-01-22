package com.maharjanworks.patientportalapi.repository;

import com.maharjanworks.patientportalapi.model.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)

class PatientRepositoryTest {

    @Autowired
    private PatientRepository patientRepository;


    Patient patient1,patient2;


    @BeforeEach
    void setUp() {

        patient2 = new Patient(101L,"kamala","Harris", LocalDate.of(1940,12,30),"kamala.harris@gmail.com","21-313-4141");

    }

    @Test
    public void  testSave(){
        //act
        Patient savedPatient = this.patientRepository.save(patient1);
        //
        assertNotNull(savedPatient);
    }

    @Test
    public void testFinAll(){
        //arrange
        List<Patient> patientList = new ArrayList<>();
        patientList.add(patient1);
        patientList.add(patient2);
        this.patientRepository.save(patient1);
        this.patientRepository.save(patient2);
        //act
        List<Patient> dbPatientList = this.patientRepository.findAll();
        //assert
        assertNotNull(patientList);
        assertThat(patientList).isEqualTo(patientList);
    }





}