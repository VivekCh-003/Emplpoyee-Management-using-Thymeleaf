package com.example.employeeMgmt.repository;

import com.example.employeeMgmt.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
