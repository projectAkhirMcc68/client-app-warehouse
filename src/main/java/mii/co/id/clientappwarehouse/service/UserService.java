/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mii.co.id.clientappwarehouse.service;

import java.util.List;
import mii.co.id.clientappwarehouse.model.User;
import mii.co.id.clientappwarehouse.model.dto.request.UserRequest;
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
public class UserService {
    
    private RestTemplate restTemplate;

    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    @Value("${app.baseUrl}/user")
    private String url;
    
    public List<User> getAll(){
        return restTemplate.exchange(url, HttpMethod.GET,null,new ParameterizedTypeReference<List<User>>(){ 
        }).getBody();
    }
    
    public User getId(Long id){
        return restTemplate.exchange(url.concat("/"+id), HttpMethod.GET,null,new ParameterizedTypeReference<User>(){
            
        }).getBody();
    }
    
    public User update(Long id,UserRequest userRequest){
        return restTemplate.exchange(url.concat("/"+id), HttpMethod.PUT, new HttpEntity<>(userRequest),new ParameterizedTypeReference<User>(){
            
        }).getBody();
    }
    
    public User delete(Long id){
        return restTemplate.exchange(url.concat("/"+id), HttpMethod.DELETE, null,new ParameterizedTypeReference<User>(){
            
        }).getBody();
    }
    
}
