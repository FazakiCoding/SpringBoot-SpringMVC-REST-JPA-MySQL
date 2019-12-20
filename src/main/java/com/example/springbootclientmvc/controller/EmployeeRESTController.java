package com.example.springbootclientmvc.controller;

import java.util.List;

import javax.validation.Valid;

import com.example.springbootclientmvc.domain.EmployeeVO;
import com.example.springbootclientmvc.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class EmployeeRESTController {

    @Autowired
    private IService service;


    @GetMapping(value = "/rest/employee", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public List<EmployeeVO> getAll() {
        return service.getEmployees();
    }

    @GetMapping(value = "/rest/employee/{id}")
    public ResponseEntity<Object>  getEmployeeById(@PathVariable(value = "id") Long empVoId) {
        EmployeeVO empVoFound = service.getEmpById(empVoId);
        if (empVoFound == null)
            return new ResponseEntity<>("employee doen't exist", HttpStatus.OK);
        return new ResponseEntity<>(empVoFound, HttpStatus.OK);
    }

    @PostMapping(value = "/rest/employee")
    public ResponseEntity<Object> createEmployee(@Valid @RequestBody EmployeeVO empVo) {
        service.save(empVo);
        return new ResponseEntity<>("employee is created successfully", HttpStatus.CREATED);
    }

    @PutMapping(value = "/rest/employee/{id}")
    public ResponseEntity<Object> updateEmployee(@PathVariable(name = "id") Long empVoId, @RequestBody EmployeeVO empVo) {
        EmployeeVO empVoFound = service.getEmpById(empVoId);
        if (empVoFound == null)
            return new ResponseEntity<>("employee doen't exist", HttpStatus.OK);
        empVo.setId(empVoId);
        service.save(empVo);
        return new ResponseEntity<>("Employee is updated successsfully", HttpStatus.OK);
    }


    @DeleteMapping(value = "/rest/employee/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable(name = "id") Long empVoId) {
        EmployeeVO empVoFound = service.getEmpById(empVoId);
        if (empVoFound == null)
            return new ResponseEntity<>("employee doen't exist", HttpStatus.OK);
        service.delete(empVoId);
        return new ResponseEntity<>("Employee is deleted successsfully", HttpStatus.OK);
    }


    @GetMapping(value = "/rest/sort/{fieldName}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public List<EmployeeVO> sortBy(@PathVariable String fieldName) {
        return service.sortBy(fieldName);
    }

    @GetMapping("/rest/pagination/{pageid}/{size}")
    public List<EmployeeVO> pagination(@PathVariable int pageid, @PathVariable int size, Model m) {
        return service.findAll(pageid, size);
    }

}
