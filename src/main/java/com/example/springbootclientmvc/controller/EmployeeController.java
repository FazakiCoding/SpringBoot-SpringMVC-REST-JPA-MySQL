package com.example.springbootclientmvc.controller;

import com.example.springbootclientmvc.domain.EmployeeVO;
import com.example.springbootclientmvc.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private IService service;


    @RequestMapping("/")
    public String showWelcomeFile(Model m) {
        return "index";
    }


    @RequestMapping("/employeeform")
    public String showform(Model m) {
        m.addAttribute("employeeVo", new EmployeeVO());
        return "employeeform";
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("employeeVo") EmployeeVO employee) {
        service.save(employee);
        return "redirect:/viewemployee";
    }

    @RequestMapping("/viewemployee")
    public String viewemployee(Model m) {
        List<EmployeeVO> list = service.getEmployees();
        m.addAttribute("list", list);
        return "viewemployee";
    }

    @RequestMapping(value = "/editemployee/{id}")
    public String edit(@PathVariable Long id, Model m) {
        EmployeeVO employee = service.getEmpById(id);
        m.addAttribute("employeeVO", employee);
        return "employeeeditform";
    }


    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public String editsave(@ModelAttribute("employeeVO") EmployeeVO employee) {
        service.save(employee);
        return "redirect:/viewemployee";
    }

    @RequestMapping(value = "/deleteemployee/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/viewemployee";
    }

    @RequestMapping("/salary/{salary}")
    public String getBySalary(@PathVariable Double salary, Model m) {
        List<EmployeeVO> list = service.findBySalary(salary);
        m.addAttribute("list", list);
        return "viewemployee";
    }


    @RequestMapping("/fonction/{fonction}")
    public String getByFonction(@PathVariable String fonction, Model m) {
        List<EmployeeVO> list = service.findByFonction(fonction);
        m.addAttribute("list", list);
        return "viewemployee";
    }


    @RequestMapping("/salary_and_fonction/{salary}/{fonction}")
    public String getBySalaryAndFonction(@PathVariable Double salary, @PathVariable String fonction, Model m) {
        List<EmployeeVO> list = service.findBySalaryAndFonction(salary, fonction);
        m.addAttribute("list", list);
        return "viewemployee";
    }

    @RequestMapping("/max_salary")
    public String getMaxSalary(Model m) {
        EmployeeVO empVo = service.getEmpHavaingMaxSalary();
        List<EmployeeVO> list = new ArrayList<>();
        list.add(empVo);
        m.addAttribute("list", list);
        return "viewemployee";
    }


    @RequestMapping("/pagination/{pageid}/{size}")
    public String pagination(@PathVariable int pageid, @PathVariable int size, Model m) {
        List<EmployeeVO> list = service.findAll(pageid, size);
        m.addAttribute("list", list);
        return "viewemployee";
    }

    @RequestMapping("/sort/{fieldName}")
    public String sortBy(@PathVariable String fieldName, Model m) {
        List<EmployeeVO> list = service.sortBy(fieldName);
        m.addAttribute("list", list);
        return "viewemployee";
    }


}
