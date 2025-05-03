package com.introLab.introLab.service;

import com.introLab.introLab.Enum.Status;
import com.introLab.introLab.controllers.dto.PatientDTO;
import com.introLab.introLab.model.Employee;
import com.introLab.introLab.model.Patient;
import com.introLab.introLab.repository.EmployeeRepository;
import com.introLab.introLab.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PatientService {
    //Repos
    private final PatientRepository patientRepository;
    private final EmployeeRepository employeeRepository;

    // Constructor repos
    public PatientService(PatientRepository patientRepository, EmployeeRepository employeeRepository) {
        this.patientRepository = patientRepository;
        this.employeeRepository = employeeRepository;
    }

    //Add new patient: Create a route to add a new patient.
    public PatientDTO addPatient(PatientDTO patientDTO) {
        Patient patient = new Patient();
        // set Patient properties
        patient.setName(patientDTO.getName());
        patient.setDateOfBirth(LocalDate.parse(patientDTO.getDateOfBirth()));
        // set Employee
        Employee admmitingDoctor = employeeRepository.findByEmployeeId(patientDTO.getEmployee().getEmployeeId());
        if (admmitingDoctor == null) {
            throw new IllegalArgumentException("Employee with ID " + patientDTO.getEmployee() + " not found.");
        }
        patient.setEmployee(admmitingDoctor);
        Patient savedPatient = patientRepository.save(patient);
        // Respose PAtientDTO
        return new PatientDTO(savedPatient);

    }

    //Update patient information: Create a route to update patient information
    // (the user should be able to update any patient information through this route).
    public Optional<PatientDTO> updatePatient(PatientDTO patientDTO) {
        Optional<Patient> optionalPatient = patientRepository.findById(patientDTO.getId());

        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();
            // Update properties
            patient.setName(patientDTO.getName());
            patient.setDateOfBirth(LocalDate.parse(patientDTO.getDateOfBirth()));
            // set Employee
            Employee admmitingDoctor = employeeRepository.findByEmployeeId(patientDTO.getEmployee().getEmployeeId());
            if (admmitingDoctor == null) {
                throw new IllegalArgumentException("Employee with ID " + patientDTO.getEmployee() + " not found.");
            }
            patient.setEmployee(admmitingDoctor);
            Patient updatedPatient = patientRepository.save(patient);
            return Optional.of(new PatientDTO(updatedPatient));
        } else {
            return Optional.empty();
        }
    }
}
