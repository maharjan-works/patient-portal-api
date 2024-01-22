package com.maharjanworks.patientportalapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.maharjanworks.patientportalapi.model.Patient;
import com.maharjanworks.patientportalapi.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PatientController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    private Patient patient1,patient2;
    private List<Patient> patientList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        //arrange for all test methods.
        patient1 = new Patient(100L,"joe","biden", LocalDate.of(1942,11,20),"joe.biden@gmail.com","212-212-2121");
        patient2 = new Patient(101L,"kamala","harris", LocalDate.of(1942,11,20),"kamala.harris@gmail.com","212-212-2121");
        patientList.add(patient1);
        patientList.add(patient2);
    }

    @Test
    void testFindAll() throws Exception {
        //+arrange
        when(patientService.findAll()).thenReturn(patientList);
        //act + assert
        this.mockMvc.perform(get("/api/v1/patient"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testGetPatientById() throws Exception {
        when(this.patientService.getPatientById(100L)).thenReturn(patient1);
        this.mockMvc.perform(get("/api/v1/patient/100"))
                .andDo(print()).andExpect(status().isOk());
    }


    @Test
    void testDeletePatient() throws Exception {
        //arrange
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        when(this.patientService.deletePatient(100L)).thenReturn(response);
        //act,assert
        this.mockMvc.perform(delete("/api/v1/patient/100"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void testCreatePatient() throws Exception {
        Patient patient = new Patient(100l,"joe","biden",null, "joe.biden@gmail.com","212-212-2121");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE,false);
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = objectWriter.writeValueAsString(patient);

        when(this.patientService.save(patient)).thenReturn(patient);
        this.mockMvc.perform(post("/api/v1/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isCreated());
    }



    @Test
    void updatePatient() throws Exception {
        Patient patient = new Patient(100l,"joe","biden",null, "joe.biden@gmail.com","212-212-2121");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE,false);
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson = objectWriter.writeValueAsString(patient);

        when(this.patientService.updatePatient(100L,patient)).thenReturn(patient);
        this.mockMvc.perform(put("/api/v1/patient/100")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk());
    }

}