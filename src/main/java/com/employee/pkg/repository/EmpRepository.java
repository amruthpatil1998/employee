package com.employee.pkg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.employee.pkg.entity.Employee;

public interface EmpRepository extends JpaRepository<Employee, Integer> {

}
