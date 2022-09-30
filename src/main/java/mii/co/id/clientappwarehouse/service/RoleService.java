/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.co.id.clientappwarehouse.service;

import java.util.List;
import mii.co.id.clientappwarehouse.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author aditya jalu
 */
@Service
public class RoleService {
    private RestTemplate restTemplate;
    
    @Value("${app.baseUrl}/role")
    private String url;

    @Autowired
    public RoleService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public List<Role> getAll() {
        return restTemplate.exchange(url, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Role>>() {
                }).getBody();
    }
    
    public Role create(Role role) {
        return restTemplate.exchange(url, HttpMethod.POST,
                new HttpEntity(role), new ParameterizedTypeReference<Role>() {
                }).getBody();
    }
    
    public Role getById(Long id) {
        return restTemplate.exchange(url.concat("/"+ id), HttpMethod.GET,
                null, new ParameterizedTypeReference<Role>() {
                }).getBody();
    }
    
    public Role update(Long id, Role role) {
        return restTemplate.exchange(url.concat("/"+ id), HttpMethod.PUT,
                new HttpEntity(role), new ParameterizedTypeReference<Role>() {
                }).getBody();
    }
    
    public Role delete(Long id) {
        return restTemplate.exchange(url.concat("/"+ id), HttpMethod.DELETE,
                null, new ParameterizedTypeReference<Role>() {
                }).getBody();
    }
    
}
