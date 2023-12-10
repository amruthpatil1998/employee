package com.employee.pkg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.pkg.entity.Employee;
import com.employee.pkg.repository.EmpRepository;
import com.employee.pkg.util.EmpUtil;

@Service
public class EmpServiceImpl implements EmpService {

	@Autowired
	EmpUtil empUtil;
	
	@Autowired
	EmpRepository repo;

	@Override
	public String empRegister(Employee emp) {

		// validating employee
		boolean validate = empUtil.validate(emp);

		if (validate == true) { // if success only
			
			String setDate = empUtil.setDate();
			emp.setEmpDate(setDate);
			
			String setTime = empUtil.setTime();
			emp.setEmpTime(setTime);
			
			System.out.println(emp);
			//encri
			
			String encPhoneNot = empUtil.encrypt(emp.getPhoneNo());
			emp.setPhoneNo(encPhoneNot);
			
			String encEmail = empUtil.encrypt(emp.getEmail());
			emp.setEmail(encEmail);
			// save to db
			Employee save = repo.save(emp);
			System.out.println(save);
			
			return "Registered";
		} 

		return "Not Registered";
	}

	@Override
	public Employee getById(int id) {
		Employee byId = repo.findById(id).get();
		
		System.out.println("Encript DATA : "+byId);
		
		//decript
		String email = byId.getEmail();
		String decEmail = empUtil.decrypt(email);
		byId.setEmail(decEmail);
		
		String phoneNo = byId.getPhoneNo();
		String decPhNo = empUtil.decrypt(phoneNo);
		byId.setPhoneNo(decPhNo);
		
		System.out.println("Decript DATA : "+byId);
		
		return byId;
	}

	@Override
	public void deleteById(int id) {
		repo.deleteById(id);
	}

}
