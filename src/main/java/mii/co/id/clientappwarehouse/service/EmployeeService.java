/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mii.co.id.clientappwarehouse.service;

import java.util.List;
import mii.co.id.clientappwarehouse.model.Employee;
import mii.co.id.clientappwarehouse.model.dto.request.EmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author USER
 */
@Service
public class EmployeeService {
    
    private RestTemplate restTemplate;
    
    @Autowired
    public EmployeeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    
    @Value("${app.baseUrl}/employee")
    private String url;
    
    public  List<Employee> getAll(){
        return restTemplate.exchange(url, HttpMethod.GET,null, new  ParameterizedTypeReference<List<Employee>>(){
        }).getBody();
    }
    
    public Employee getById(Long id){
        return restTemplate.exchange(url.concat("/"+id), HttpMethod.GET, null, new ParameterizedTypeReference<Employee>(){
        }).getBody();
    }
    
    public Employee create(EmployeeRequest employeeRequest){
        return restTemplate.exchange(url.concat("/dto/"), HttpMethod.POST, new HttpEntity<>(employeeRequest), new ParameterizedTypeReference<Employee>(){
            
        }).getBody();
    }
    
    public Employee update(Long id,Employee employee){
        return restTemplate.exchange(url.concat("/"+id), HttpMethod.PUT, new HttpEntity<>(employee), new ParameterizedTypeReference<Employee>(){
    }).getBody();
    }
    
    public Employee delete(Long id){
        return restTemplate.exchange(url.concat("/"+id), HttpMethod.DELETE, null , new ParameterizedTypeReference<Employee>(){
        }).getBody();
    }
    
}
