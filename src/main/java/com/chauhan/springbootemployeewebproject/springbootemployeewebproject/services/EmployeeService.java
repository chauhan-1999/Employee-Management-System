package com.chauhan.springbootemployeewebproject.springbootemployeewebproject.services;

import com.chauhan.springbootemployeewebproject.springbootemployeewebproject.dto.EmployeeDTO;
import com.chauhan.springbootemployeewebproject.springbootemployeewebproject.entities.EmployeeEntity;
import com.chauhan.springbootemployeewebproject.springbootemployeewebproject.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.data.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id){
//        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
//        return modelMapper.map(employeeEntity,EmployeeDTO.class);

        return employeeRepository
                .findById(id)
                .map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class));
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
        boolean exists = isExistsByEmployeeId(id);
        if(!exists) return null;

        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();

        // Update only the necessary fields
        // Update only non-null fields from employeeDTO to employeeEntity
        if (employeeDTO.getName() != null) {
            employeeEntity.setName(employeeDTO.getName());
        }
        if (employeeDTO.getAge() != null) {
            employeeEntity.setAge(employeeDTO.getAge());
        }
        if (employeeDTO.getDateOfJoining() != null) {
            employeeEntity.setDateOfJoining(employeeDTO.getDateOfJoining());
        }
        if (employeeDTO.getIsActive() != null) {
            employeeEntity.setIsActive(employeeDTO.getIsActive());
        }

        EmployeeEntity updatedEmployee = employeeRepository.save(employeeEntity);
        return modelMapper.map(updatedEmployee, EmployeeDTO.class);
    }

    public boolean deleteEmployeeById(Long id){
        boolean exists = isExistsByEmployeeId(id);
        if(!exists) return false;
        employeeRepository.deleteById(id);
        return true;
    }

    public boolean isExistsByEmployeeId(Long id){
        return employeeRepository.existsById(id);
    }

    public EmployeeDTO updatePartialEmployeeById(Map<String,Object> updates, Long employeeId) {
        boolean exists = isExistsByEmployeeId(employeeId);
        if(!exists) return null;

        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        updates.forEach((field,value)->{
            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(EmployeeEntity.class,field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated,employeeEntity,value);
        });
        EmployeeEntity savedEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEntity,EmployeeDTO.class);
    }
}
