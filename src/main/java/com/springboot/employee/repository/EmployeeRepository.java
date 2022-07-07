package com.springboot.employee.repository;

import com.springboot.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

//public interface EmployeeRepository extends CrudRepository<Employee, Long> {
//}

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
