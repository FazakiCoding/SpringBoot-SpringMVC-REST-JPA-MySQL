package com.example.springbootclientmvc.dao;

import com.example.springbootclientmvc.modele.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findBySalary(Double salary);
    List<Employee> findByFonction(String designation);
    List<Employee> findBySalaryAndFonction(Double salary, String fonction);
    @Query(" SELECT e from Emp e where e.salary=(select MAX(salary) as salary FROM Emp)")
    Employee getEmpHavaingMaxSalary();

}
