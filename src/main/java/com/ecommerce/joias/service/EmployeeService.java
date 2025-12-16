package com.ecommerce.joias.service;

import com.ecommerce.joias.dto.CreateEmployeeDto;
import com.ecommerce.joias.entity.Employee;
import com.ecommerce.joias.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public Integer createEmployee(CreateEmployeeDto createEmployeeDto){
        // DTO -> ENTITY
        Employee employeeEntity = new Employee();
        employeeEntity.setName(createEmployeeDto.name());
        employeeEntity.setEmail(createEmployeeDto.email());
        employeeEntity.setPassword(createEmployeeDto.password());
        employeeEntity.setRole(createEmployeeDto.role());

        var employeeSaved = employeeRepository.save(employeeEntity);

        return employeeSaved.getEmployeeId();
    }
}
