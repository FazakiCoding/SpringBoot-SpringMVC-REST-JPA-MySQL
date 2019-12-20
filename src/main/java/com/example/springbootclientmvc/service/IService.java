package com.example.springbootclientmvc.service;

import com.example.springbootclientmvc.domain.EmployeeVO;

import java.util.List;

public interface IService {
    List<EmployeeVO> getEmployees();
    void save(EmployeeVO emp);
    EmployeeVO getEmpById(Long id);
    void delete(Long id);
    List<EmployeeVO> findBySalary(Double salary);
    List<EmployeeVO> findByFonction(String designation);
    List<EmployeeVO> findBySalaryAndFonction(Double salary, String fonction);
    EmployeeVO getEmpHavaingMaxSalary();

    List<EmployeeVO> findAll(int pageId, int size);

    List<EmployeeVO> sortBy(String fieldName);
}
