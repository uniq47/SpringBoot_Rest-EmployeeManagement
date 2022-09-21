package com.uniam.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uniam.entity.Employee;
import com.uniam.exception.ResourceNotFoundexception;
import com.uniam.repo.EmployeeRepo;

import lombok.Delegate;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	private EmployeeRepo empRepo;
	//http://localhost:8081/empRest/api/employees
	@PostMapping("/employees")
	public ResponseEntity<String> createEmployee(@RequestBody Employee emp)
	{
		Employee saveEmp = empRepo.save(emp);
		if(saveEmp!=null)
			return new ResponseEntity<>("Employee Registered Sucessfully",HttpStatus.CREATED);
		else
			return new ResponseEntity<>("Employee Registered Sucessfully",HttpStatus.BAD_REQUEST);
	}
	
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees()
	{
		return empRepo.findAll();
	}
	
	//fetching specific data 
	//http://localhost:8081/empRest/api/employees/1
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) throws ResourceNotFoundexception
	{
		Employee emp= null;
		
			emp = empRepo.findById(id).orElseThrow(()
					-> new ResourceNotFoundexception
						("employee Is not Exist::"+id));
	
		
		return ResponseEntity.ok().body(emp);
	}
	
	//deleting 
	@DeleteMapping("/employees/{empid}")
	public Map<String,Boolean> deleteEmployee(@PathVariable Integer empid) throws ResourceNotFoundexception
	{
		
		Employee emp= null;
		
		emp = empRepo.findById(empid).orElseThrow(()
				-> new ResourceNotFoundexception
					("employee Is not Exist::"+empid));
		
		empRepo.delete(emp);
		Map<String,Boolean>response= new HashMap<>();
		response.put("Record is Deleted Sucessfuly", Boolean.TRUE);

		return null;
	}
	@PutMapping("/employees/{empId}")
	public ResponseEntity<?> updateEmployee(@PathVariable Integer empId,@RequestBody Employee employee) throws ResourceNotFoundexception
	{
	Employee emp= null;
		
		emp = empRepo.findById(empId).orElseThrow(()
				-> new ResourceNotFoundexception
					("employee Is not Exist::"+empId));
		emp.setEmail(employee.getEmail());
		emp.setFirstName(employee.getFirstName());
		emp.setLastName(employee.getLastName());
		
		Employee empSave = empRepo.save(emp);
		
		if(empSave!=null)
			return new ResponseEntity<Employee>(empSave, HttpStatus.OK);
		else
			return new ResponseEntity<String>("Employee record not Updated",HttpStatus.BAD_REQUEST);
		
	
	}
}
