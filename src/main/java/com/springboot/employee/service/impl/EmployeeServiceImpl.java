package com.springboot.employee.service.impl;

import com.springboot.employee.dto.EmployeeDto;
import com.springboot.employee.exception.ResourceNotFoundException;
import com.springboot.employee.model.Employee;
import com.springboot.employee.repository.EmployeeRepository;
import com.springboot.employee.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDto addEmployee(EmployeeDto employeeDto) {

        //Convert DTO to Model
        Employee employee = mapToModel(employeeDto);

        //Save to Database
        Employee newEmployee = employeeRepository.save(employee);

        //Convert Model to DTO
        EmployeeDto employeeResponse = mapToDto(newEmployee);

        return employeeResponse;
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        for(Employee employee: employees){
            EmployeeDto employeeTemp = mapToDto(employee);
            employeeDtos.add(employeeTemp);
        }
        return employeeDtos;
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        return mapToDto(employee);
    }

    @Override
    public EmployeeDto updateEmployeeById(EmployeeDto employeeDto, Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));

        employee.setName(employeeDto.getName() != null ? employeeDto.getName() : employee.getName());
        employee.setOccupation(employeeDto.getOccupation() != null ? employeeDto.getOccupation() : employee.getOccupation());

        Employee employeeResponse = employeeRepository.save(employee);

        return mapToDto(employeeResponse);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        employeeRepository.delete(employee);
    }

    private Employee mapToModel(EmployeeDto employeeDto){
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setOccupation(employeeDto.getOccupation());
        return employee;
    }

    private EmployeeDto mapToDto(Employee employee){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());
        employeeDto.setOccupation(employee.getOccupation());
        return employeeDto;
    }
}
