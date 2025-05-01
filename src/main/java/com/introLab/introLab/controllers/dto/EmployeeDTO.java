package com.introLab.introLab.controllers.dto;

import com.introLab.introLab.Enum.Status;
import com.introLab.introLab.model.Employee;

public class EmployeeDTO {
    private Integer employeeId;
    private String name;
    private String department;
    private Status status;


    public EmployeeDTO() {}

    public EmployeeDTO(Integer employeeId, String name, String department, Status status) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.status = status;
    }

    public EmployeeDTO(Employee employee) {

        this.name = employee.getName();
        this.department = employee.getDepartment();
        this.status = employee.getStatusEnum();
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Employee toEntity() {
        Employee employee = new Employee();
        employee.setName(this.name);
        employee.setDepartment(this.department);
        employee.setStatusEnum(this.status);
        return employee;
    }
}
