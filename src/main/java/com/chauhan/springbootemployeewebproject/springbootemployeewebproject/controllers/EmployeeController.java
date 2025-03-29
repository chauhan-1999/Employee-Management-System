package com.chauhan.springbootemployeewebproject.springbootemployeewebproject.controllers;

import com.chauhan.springbootemployeewebproject.springbootemployeewebproject.dto.EmployeeDTO;
import com.chauhan.springbootemployeewebproject.springbootemployeewebproject.services.EmployeeService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(path = "/create")
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
        System.out.println("creating..");
        return employeeService.createEmployee(inputEmployee);
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable(name="employeeId") Long id){
        return employeeService.getEmployeeById(id);
    }

    @GetMapping(path = "/all")
    public List<EmployeeDTO> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @PutMapping(path = "/{employeeId}")
    public EmployeeDTO updateEmployeeById(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long employeeId){
        return employeeService.updateEmployeeById(employeeId,employeeDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable Long id){
        employeeService.deleteEmployeeById(id);
    }

    @PatchMapping(path = "/{employeeId}")
    public EmployeeDTO updatePartialEmployeeById(@RequestBody Map<String,Object>updates,@PathVariable Long employeeId){
        return employeeService.updatePartialEmployeeById(updates,employeeId);
    }

}
