package com.example.VastEmpManagemnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.VastEmpManagemnet.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Employee findByEmpFirstNameAndEmpLastName(String empFirstName,String empLastName);

}
