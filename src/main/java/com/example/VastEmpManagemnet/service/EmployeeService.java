package com.example.VastEmpManagemnet.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.VastEmpManagemnet.dto.EmployeeRequestDto;
import com.example.VastEmpManagemnet.dto.EmployeeResponse;
import com.example.VastEmpManagemnet.dto.MessageResponseDto;
import com.example.VastEmpManagemnet.exception.BadRequestException;
import com.example.VastEmpManagemnet.model.Employee;
import com.example.VastEmpManagemnet.repository.EmployeeRepository;


@Service
public class EmployeeService {
	
	@Autowired
	private  EmployeeRepository employeeRepository;
	
	public List<EmployeeResponse> getEmployees() {
		return employeeRepository.findAll().stream().map(emplyees -> {
			EmployeeResponse employeeResponse = new EmployeeResponse();
			employeeResponse.setEmpDesignation(emplyees.getEmpDesignation());
			employeeResponse.setEmpFirstName(emplyees.getEmpFirstName());
			employeeResponse.setEmpLastName(emplyees.getEmpLastName());
			employeeResponse.setEmpId(emplyees.getEmpId());
			employeeResponse.setEmpEmail(emplyees.getEmpEmail());
			employeeResponse.setEmpSalary(emplyees.getEmpSalary());
			return employeeResponse;
		}).toList();
	}
	
	public MessageResponseDto createEmployee(EmployeeRequestDto employeeRequestDto ) {
		
		if (employeeRequestDto.getEmpFirstName() == null || employeeRequestDto.getEmpFirstName().strip().equals("")) {
			throw new BadRequestException("Employee First name can not be null or empty string", "EmployeeFirstName.name.null");
		}
		if (employeeRequestDto.getEmpLastName() == null || employeeRequestDto.getEmpLastName().strip().equals("")) {
			throw new BadRequestException("Employee Last name can not be null or empty string", "EmployeeLastName.name.null");
		}
		if (employeeRequestDto.getEmpEmail() == null || employeeRequestDto.getEmpEmail().strip().equals("")) {
			throw new BadRequestException("Employee Email can not be null or empty string", "EmployeeEmail.Email.null");
		}
		if (employeeRequestDto.getEmpDesignation() == null || employeeRequestDto.getEmpDesignation().strip().equals("")) {
			throw new BadRequestException("Employee Designation can not be null or empty string", "EmployeeDesignation.name.null");
		}
		if (employeeRequestDto.getEmpSalary() == null ) {
			throw new BadRequestException("Employee Designation can not be null or empty string", "EmployeeSalary.Salary.null");
		}
		Employee oldEmployee = employeeRepository.findByEmpFirstNameAndEmpLastName(employeeRequestDto.getEmpFirstName(),employeeRequestDto.getEmpLastName());
		if (oldEmployee!= null) {
			throw new BadRequestException("Employee Already exist", "EmployeeAlreadyExist.alreadyEXist");
		}
		else {
			Employee newEmployee =  new Employee();
			newEmployee.setEmpFirstName(employeeRequestDto.getEmpFirstName().strip());
			newEmployee.setEmpLastName(employeeRequestDto.getEmpLastName().strip());
			newEmployee.setEmpDesignation(employeeRequestDto.getEmpDesignation().strip());
			newEmployee.setEmpEmail(employeeRequestDto.getEmpEmail().strip());
			newEmployee.setEmpSalary(employeeRequestDto.getEmpSalary());
			employeeRepository.save(newEmployee);
		}
		MessageResponseDto messageResponseDto = new MessageResponseDto();
		messageResponseDto.setMessage("Employee Account Created Successfully");
		return messageResponseDto;
	}

	

	public MessageResponseDto deleteEmplyee(Long employeeId) {
		Employee removeEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new BadRequestException("Employee not exist with id", null));
		employeeRepository.delete(removeEmployee);
		MessageResponseDto messageResponseDto = new MessageResponseDto();
		 messageResponseDto.setMessage("Employee Account Deleted Successfully");
		return messageResponseDto;
	}

	public MessageResponseDto updateEmployee(EmployeeRequestDto employeeRequestDto, Long employeeId) {
		 Employee updateEmployee = employeeRepository.findById(employeeId)
	                .orElseThrow(() -> new BadRequestException("Employee not exist with id", null));
		 updateEmployee.setEmpFirstName(employeeRequestDto.getEmpFirstName());
		 updateEmployee.setEmpLastName(employeeRequestDto.getEmpLastName());
		 updateEmployee.setEmpEmail(employeeRequestDto.getEmpEmail());
		 updateEmployee.setEmpDesignation(employeeRequestDto.getEmpDesignation());
		 updateEmployee.setEmpSalary(employeeRequestDto.getEmpSalary());
		 employeeRepository.save(updateEmployee);
		 MessageResponseDto messageResponseDto = new MessageResponseDto();
		 messageResponseDto.setMessage("Employee Account updated Successfully");
		return messageResponseDto;
	}

	public EmployeeResponse getEmployeeFromID(Long employeeId) {
		Employee viewEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new BadRequestException("Employee not exist with id", null));
		EmployeeResponse employeeResponse = new EmployeeResponse();
		employeeResponse.setEmpDesignation(viewEmployee.getEmpDesignation());
		employeeResponse.setEmpFirstName(viewEmployee.getEmpFirstName());
		employeeResponse.setEmpLastName(viewEmployee.getEmpLastName());
		employeeResponse.setEmpEmail(viewEmployee.getEmpEmail());
		employeeResponse.setEmpSalary(viewEmployee.getEmpSalary());
		employeeResponse.setEmpId(viewEmployee.getEmpId());
		return employeeResponse;
	}

	

	
}
