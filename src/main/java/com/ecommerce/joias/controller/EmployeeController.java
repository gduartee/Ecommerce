package com.ecommerce.joias.controller;

import com.ecommerce.joias.dto.CreateEmployeeDto;
import com.ecommerce.joias.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    @PostMapping
    public ResponseEntity<Integer> createEmployee(@RequestBody @Valid CreateEmployeeDto createEmployeeDto){
        Integer employeeId = employeeService.createEmployee(createEmployeeDto);

        return ResponseEntity.created(URI.create("/employee/" + employeeId.toString())).body(employeeId);
    }

}
