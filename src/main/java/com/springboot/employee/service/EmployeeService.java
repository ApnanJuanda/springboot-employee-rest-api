package com.springboot.employee.service;

import com.springboot.employee.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto addEmployee(EmployeeDto employeeDto);

    List<EmployeeDto> getAllEmployees();

    EmployeeDto getEmployeeById(Long id);

    EmployeeDto updateEmployeeById(EmployeeDto employeeDto, Long id);

    void deleteEmployee(Long id);
}
