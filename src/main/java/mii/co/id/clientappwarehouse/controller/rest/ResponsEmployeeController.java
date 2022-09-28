/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mii.co.id.clientappwarehouse.controller.rest;

import java.util.List;
import lombok.AllArgsConstructor;
import mii.co.id.clientappwarehouse.model.Employee;
import mii.co.id.clientappwarehouse.model.dto.request.EmployeeRequest;
import mii.co.id.clientappwarehouse.service.EmployeeService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author USER
 */
@AllArgsConstructor
@RestController
@RequestMapping("/employee")
public class ResponsEmployeeController {
    
    private EmployeeService employeeService;
    
    @GetMapping("/getAll")
    public List<Employee> getAll(){
        return employeeService.getAll();
    }
    
    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id){
        return employeeService.getById(id);
    }
    
    @PostMapping
    public Employee create(@RequestBody EmployeeRequest employeeRequest){
        return employeeService.create(employeeRequest);
    }
    
    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id,@RequestBody Employee employee){
        return employeeService.update(id, employee);
    }
    
    @DeleteMapping("/{id}")
    public Employee delete(@PathVariable Long id){
        return employeeService.delete(id);
    }
    
}
