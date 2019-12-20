package com.example.springbootclientmvc.domain;

import com.example.springbootclientmvc.modele.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeConverter {
    public static EmployeeVO toVo(Employee bo) {
        if (bo == null || bo.getId() ==null)
            return null;
        EmployeeVO vo = new EmployeeVO();
        vo.setId(bo.getId());
        vo.setName(bo.getName());
        vo.setSalary(bo.getSalary());
        vo.setFonction(bo.getFonction());
        return vo;
    }
    public static Employee toBo(EmployeeVO vo) {
        Employee bo = new Employee();
        bo.setId(vo.getId());
        bo.setName(vo.getName());
        bo.setSalary(vo.getSalary());
        bo.setFonction(vo.getFonction());
        return bo;
    }
    public static List<EmployeeVO> toListVo(List<Employee> listBo) {
        List<EmployeeVO> listVo = new ArrayList<>();
        for (Employee emp : listBo) {
            listVo.add(toVo(emp));
        }
        return listVo;
    }

}
