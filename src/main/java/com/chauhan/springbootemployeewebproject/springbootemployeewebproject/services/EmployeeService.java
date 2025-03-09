package com.chauhan.springbootemployeewebproject.springbootemployeewebproject.services;

import com.chauhan.springbootemployeewebproject.springbootemployeewebproject.dto.EmployeeDTO;
import com.chauhan.springbootemployeewebproject.springbootemployeewebproject.entities.EmployeeEntity;
import com.chauhan.springbootemployeewebproject.springbootemployeewebproject.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeeDTO getEmployeeById(Long id){
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        return modelMapper.map(employeeEntity,EmployeeDTO.class);
    }

    public List<EmployeeDTO>getAllEmployees(){
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity->modelMapper.map(employeeEntity,EmployeeDTO.class))
                .collect(Collectors.toList());
    }
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO){
        EmployeeEntity toSaveEntity = modelMapper.map(employeeDTO,EmployeeEntity.class);
        EmployeeEntity savedEntity = employeeRepository.save(toSaveEntity);
        return modelMapper.map(savedEntity,EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeeById(Long id, EmployeeDTO employeeDTO){
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO,EmployeeEntity.class);
        employeeEntity.setId(id);
        EmployeeEntity savedEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEntity,EmployeeDTO.class);
    }

    public void deleteEmployeeById(Long id){
        employeeRepository.deleteById(id);
    }
}
