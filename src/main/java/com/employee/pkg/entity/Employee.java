package com.employee.pkg.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "EMP_AMMU")
public class Employee {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id; 
	private String name;
	private String lastName;
	private String email;
	private String phoneNo;
	private String empDate;
	private String empTime;
	

}
