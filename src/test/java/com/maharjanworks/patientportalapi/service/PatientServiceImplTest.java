package com.maharjanworks.patientportalapi.service;

import com.maharjanworks.patientportalapi.model.Patient;
import com.maharjanworks.patientportalapi.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PatientServiceImplTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientServiceImpl patientService;

    private Patient patient1, patient2;
    private List<Patient> patientList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        //arrange
        patient1 = new Patient(100L, "joe","biden",LocalDate.of(1942,11,20),"joe.biden@gmail.com","212-212-2121");
        patient2 = new Patient(101L, "kamala","harris",LocalDate.of(1964,10,20),"kamala.harris@gmail.com","212-212-2121");
        patientList.add(patient1);
        patientList.add(patient2);
    }

    @Test
    void testSave() {
        //+arrange
        when(this.patientRepository.save(patient1)).thenReturn(patient1);
        //act
        Patient patient = this.patientService.save(patient1);
        //assert
        assertNotNull(patient);
        assertThat(patient).isEqualTo(patient);
    }

    @Test
    void testFindAll() {
        //+arrange
        when(this.patientService.findAll()).thenReturn(patientList);
        //act
        List<Patient> dbPatientList = this.patientService.findAll();
        //assert
        assertNotNull(dbPatientList);
        assertThat(dbPatientList).isEqualTo(patientList);
    }


    @Test
    void getPatientById() {
        //+arrange
        when(this.patientRepository.findById(100L)).thenReturn(Optional.ofNullable(patient1));
        //act
        Patient dbPatient = this.patientService.getPatientById(100L);
        //assert
        assertNotNull(dbPatient);
        assertThat(dbPatient).isEqualTo(patient1);
    }

    @Test
    void updatePatient() {
        //+arrange
        when(patientRepository.findById(100L)).thenReturn(Optional.ofNullable(patient1));
        when(patientRepository.save(patient1)).thenReturn(patient1);
        //act
        Patient dbPatient =patientService.updatePatient(100L,patient1);
        //assert
        assertNotNull(dbPatient);
        assertThat(dbPatient).isEqualTo(patient1);
    }

    @Test
    void deletePatient() {
        //+arrange
        when(this.patientRepository.findById(patient1.getPatientId())).thenReturn(Optional.ofNullable(patient1));
        //act + assert
        assertAll(()->patientService.deletePatient(100L));
    }
}