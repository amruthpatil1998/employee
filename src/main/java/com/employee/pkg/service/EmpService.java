package com.employee.pkg.service;

import com.employee.pkg.entity.Employee;

public interface EmpService {

	public String empRegister(Employee emp);

	public Employee getById(int id);

	public void deleteById(int id);

}
