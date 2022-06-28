package com.example.VastEmpManagemnet.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.VastEmpManagemnet.dto.EmployeeRequestDto;
import com.example.VastEmpManagemnet.dto.EmployeeResponse;
import com.example.VastEmpManagemnet.dto.MessageResponseDto;
import com.example.VastEmpManagemnet.model.Employee;
import com.example.VastEmpManagemnet.service.EmployeeService;



@RestController
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	@GetMapping(path="/api/vast/v1/employees")
	public List<EmployeeResponse> getEmployees() {
		return employeeService.getEmployees();
	}
	@GetMapping(path="/api/vast/v1/employee/{employeeId}")
	public EmployeeResponse getEmployeeFromID(@PathVariable Long employeeId) {
		return employeeService.getEmployeeFromID(employeeId);
	}
	@PostMapping(path="/api/vast/v1/employee")
	public MessageResponseDto createEmployee(@RequestBody EmployeeRequestDto employeeRequestDto) {
		return employeeService.createEmployee(employeeRequestDto);
	}
	@PutMapping(path="/api/vast/v1/employee/{employeeId}")
	public MessageResponseDto updateEmployee(@RequestBody EmployeeRequestDto employeeRequestDto,@PathVariable Long employeeId) {
		return employeeService.updateEmployee(employeeRequestDto, employeeId);
	}
	@DeleteMapping(path = "/api/vast/v1/employee/{employeeId}")
	public MessageResponseDto deleteEmplyee(@PathVariable("employeeId") Long employeeId) {
		return employeeService.deleteEmplyee(employeeId);
	}
}
