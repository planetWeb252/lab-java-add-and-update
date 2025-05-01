package com.introLab.introLab.controllers;

import com.introLab.introLab.Enum.Status;
import com.introLab.introLab.controllers.dto.PatientDTO;
import com.introLab.introLab.model.Employee;
import com.introLab.introLab.model.Patient;
import com.introLab.introLab.repository.EmployeeRepository;
import com.introLab.introLab.repository.PatientRepository;
import com.introLab.introLab.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientRepository patientRepository;
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientRepository patientRepository, PatientService patientService) {
        this.patientRepository = patientRepository;
        this.patientService = patientService;

    }

    //    Get all patients: Create a route to get all patients.
    @GetMapping("/all")
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    //    Get patient by ID: Create a route to get a patient by patient_id.
    @GetMapping("/{patientId}")
    public Patient getPatientById(@PathVariable int patientId) {
        return patientRepository.findByPatientId(patientId);
    }

//    Get patients by date of birth range: Create a route to get patients date of birth within a specified range.
    // La he hecho gracias a este link de medium donde explican los Localdate, ya que creo que seria peor si fuesen
    // las fechas fuesen String
    //https://medium.com/swlh/spring-boot-and-the-localdate-request-parameter-2c9cdbb085bb

    @GetMapping("/dateofbirth")
    public List<Patient> findByDateOfBirthBetween(@RequestParam("startDate")
                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                  @RequestParam("endDate")
                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return patientRepository.findByDateOfBirthBetween(startDate, endDate);

    }

    //    Get patients by admitting doctor's department: Create a route to get patients by the department that their admitting doctor is in (For example, get all patients admitted by a doctor in cardiology).
    @GetMapping("/department/{department}")
    public List<Patient> getPatientsByDepartment(@PathVariable String department) {
        return patientRepository.findPatientsByEmployeeDepartment(department);
    }

    //    Get all patients with a doctor whose status is OFF: Create a route to get all patients with a doctor whose status is OFF.
    @GetMapping("/status/{status}")
    public List<Patient> getPatientsByStatus(@PathVariable Status status) {
        return patientRepository.findPatientsByEmployeeStatusEnum(status);
    }


    // LAB Java | Add and Update
    // Uso ResponseEntity porque ya lo conozco de antes y me es mas facil retornar el HHTPStatus
    //Add new patient: Create a route to add a new patient.
    @PostMapping
    public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientDTO patient) {
        PatientDTO patientDTO = patientService.addPatient(patient);
        return new ResponseEntity<PatientDTO>(patientDTO, HttpStatus.CREATED);
    }

    @PutMapping("/updatepatient")
    public ResponseEntity<PatientDTO> updatePatient(@RequestBody PatientDTO patientDTO) {
        Optional<PatientDTO> updatedPatient = patientService.updatePatient(patientDTO);
        if (updatedPatient.isPresent()) {
            return new ResponseEntity<>(updatedPatient.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}






