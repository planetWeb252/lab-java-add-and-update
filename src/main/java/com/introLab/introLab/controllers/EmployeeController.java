package com.introLab.introLab.controllers;

import com.introLab.introLab.Enum.Status;
import com.introLab.introLab.controllers.dto.EmployeeDTO;
import com.introLab.introLab.model.Employee;
import com.introLab.introLab.repository.EmployeeRepository;
import com.introLab.introLab.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class EmployeeController {
    //imports repository
    private final EmployeeRepository employeeRepository;
    private final EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository,EmployeeService employeeService) {
        this.employeeRepository = employeeRepository;
        this.employeeService = employeeService;
    }

    //Get all doctors: Create a route to get all doctors.
    @GetMapping("/all")
    public List<Employee> getAllEmployees() {

        return employeeRepository.findAll();
    }

//    Get doctor by ID: Create a route to get a doctor by employee_id.

    @GetMapping("/{employeeId}")
    public Employee getEmployeeById(@PathVariable int employeeId) {
        return employeeRepository.findByEmployeeId( employeeId);
    }

//    Get doctors by status: Create a route to get doctors by status.
    @GetMapping("/status/{status}")
    public List<Employee> getEmployeesByStatus(@PathVariable Status status) {
        return employeeRepository.findByStatusEnum(status);
    }

//    Get doctors by department: Create a route to get doctors by department.
    @GetMapping("/department/{department}")
    public List<Employee> getEmployeesByDepartment(@PathVariable String department) {
        return employeeRepository.findByDepartment(department);
    }

    // LAB Java | Add and Update
    //USo ResposeEntity porque ya lo conozco de antes y me es mas facil retornar el HHTPStatus
    //Add new doctor: Create a route to add a new doctor.
    @PostMapping
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO employeeDtoSaved= employeeService.addEmployee(employeeDTO);
        return new ResponseEntity<>(employeeDtoSaved, HttpStatus.CREATED);
    }



    @PatchMapping("/changeStatus")
    public ResponseEntity<EmployeeDTO> updateEmployeeStatus(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO employeeDtoUpdated= employeeService.updateEmployeeStatus(employeeDTO);
        return new ResponseEntity<>(employeeDtoUpdated, HttpStatus.OK);
    }

    @PatchMapping("/updateDepartment")
    public ResponseEntity<EmployeeDTO> updateEmployeeDepartment(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO employeeDtoUpdated= employeeService.updateEmployeeDepartment(employeeDTO);
        return new ResponseEntity<>(employeeDtoUpdated, HttpStatus.OK);
    }

}
