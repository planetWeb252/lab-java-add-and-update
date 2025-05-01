package com.introLab.introLab.controllers.dto;

import com.introLab.introLab.model.Patient;

public class PatientDTO {
    private int  id;
    private String name;
    private String dateOfBirth; // ISO format: yyyy-MM-dd
    private EmployeeDTO employee;

    public PatientDTO() {}

    public PatientDTO(int id,String name, String dateOfBirth, EmployeeDTO employee) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PatientDTO(Patient patient) {
        this.name = patient.getName();
        this.dateOfBirth = patient.getDateOfBirth().toString();
        if (patient.getEmployee() != null) {
            this.employee = new EmployeeDTO(patient.getEmployee());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "PatientDTO{" +
                "name='" + name + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", employee=" + employee +
                '}';
    }
}
