package com.employee.pkg.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.employee.pkg.dto.EmployeeDto;
import com.employee.pkg.entity.Employee;
import com.employee.pkg.service.EmpService;

@RestController
public class EmpController {

	@Autowired
	EmpService empService;

	@GetMapping("/demo")
	public String test() {
		return "Hi";
	}

	@PostMapping("/register")
	public String registerEmp(@RequestBody EmployeeDto emp) {
		Employee e1 = new Employee();
		BeanUtils.copyProperties(emp, e1);
		System.out.println("In Coroller class :: Employee request ="+e1);

		String register = empService.empRegister(e1);
		return register;
	}

	@GetMapping("/getEmp/{id}")
	public Employee getEmpById(@PathVariable("id") int id) {
		Employee emp = empService.getById(id);
		return emp;
	}

	@DeleteMapping("/deleteEmp/{id}")
	public void deleteEmpById(@PathVariable("id") int id) {
		empService.deleteById(id);

	}

	// get all

	// update

}
