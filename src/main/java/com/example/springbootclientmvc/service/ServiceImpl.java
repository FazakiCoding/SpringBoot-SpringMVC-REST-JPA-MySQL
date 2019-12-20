package com.example.springbootclientmvc.service;

import com.example.springbootclientmvc.dao.EmployeeRepository;
import com.example.springbootclientmvc.domain.EmployeeConverter;
import com.example.springbootclientmvc.domain.EmployeeVO;
import com.example.springbootclientmvc.modele.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServiceImpl implements IService, CommandLineRunner {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeVO> getEmployees() {
        List<Employee> list = employeeRepository.findAll();
        return EmployeeConverter.toListVo(list);

    }

    @Override
    public void save(EmployeeVO emp) {
        employeeRepository.save(EmployeeConverter.toBo(emp));
    }

    @Override
    public EmployeeVO getEmpById(Long id) {
        boolean find = employeeRepository.existsById(id);
        if (!find)
            return null;
        return EmployeeConverter.toVo(employeeRepository.getOne(id));

    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<EmployeeVO> findBySalary(Double salary) {
        List<Employee> list = employeeRepository.findBySalary(salary);
        return EmployeeConverter.toListVo(list);

    }

    @Override
    public List<EmployeeVO> findByFonction(String designation) {
        List<Employee> list = employeeRepository.findByFonction(designation);
        return EmployeeConverter.toListVo(list);

    }

    @Override
    public List<EmployeeVO> findBySalaryAndFonction(Double salary, String fonction) {
        List<Employee> list = employeeRepository.findBySalaryAndFonction(salary, fonction);
        return EmployeeConverter.toListVo(list);

    }

    @Override
    public EmployeeVO getEmpHavaingMaxSalary() {
        return EmployeeConverter.toVo(employeeRepository.getEmpHavaingMaxSalary());
    }

    @Override
    public List<EmployeeVO> findAll(int pageId, int size) {
        Page<Employee> result = employeeRepository.findAll(PageRequest.of(pageId, size, Sort.Direction.ASC, "name"));
        return EmployeeConverter.toListVo(result.getContent());

    }

    @Override
    public List<EmployeeVO> sortBy(String fieldName) {
        return EmployeeConverter.toListVo(employeeRepository.findAll(Sort.by(fieldName)));    }

    @Override
    public void run(String... args) throws Exception {
        employeeRepository.deleteAll();
        employeeRepository.save(new Employee("name1", 8500d, "Developper"));
        employeeRepository.save(new Employee("name2", 8500d, "Developper"));
        employeeRepository.save(new Employee("name3", 8500d, "Developper"));
        employeeRepository.save(new Employee("name4", 8500d, "Accounter"));
        employeeRepository.save(new Employee("name5", 10000d, "Accounter"));
        employeeRepository.save(new Employee("name6", 15000d, "Project manager"));
        employeeRepository.save(new Employee("name7", 17500d, "Project manager"));
        employeeRepository.save(new Employee("name8", 10000d, "Project manager"));

    }
}
