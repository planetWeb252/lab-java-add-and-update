package com.introLab.introLab.service;

import com.introLab.introLab.Enum.Status;
import com.introLab.introLab.controllers.dto.EmployeeDTO;
import com.introLab.introLab.model.Employee;
import com.introLab.introLab.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setDepartment(employeeDTO.getDepartment());
        employee.setStatusEnum(employeeDTO.getStatus());
        Employee savedEmployee = employeeRepository.save(employee);

        return new EmployeeDTO(savedEmployee);
    }

    public EmployeeDTO updateEmployeeStatus(EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findByEmployeeId(employeeDTO.getEmployeeId());
        if (employee != null) {
            employee.setStatusEnum(employeeDTO.getStatus());
            Employee updatedEmployee = employeeRepository.save(employee);
            return new EmployeeDTO(updatedEmployee);
        }
        throw new RuntimeException("Employee not found");
    }

    public EmployeeDTO updateEmployeeDepartment(EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findByEmployeeId(employeeDTO.getEmployeeId());
        if (employee != null) {
            employee.setDepartment(employeeDTO.getDepartment());
            Employee updatedEmployee = employeeRepository.save(employee);
            return new EmployeeDTO(updatedEmployee);
        }
        throw new RuntimeException("Employee not found");
    }
}
