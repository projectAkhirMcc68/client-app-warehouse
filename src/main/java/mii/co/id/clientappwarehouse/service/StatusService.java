/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mii.co.id.clientappwarehouse.service;

import java.util.List;
import lombok.AllArgsConstructor;
import mii.co.id.clientappwarehouse.model.Status;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author USER
 */
@Service
public class StatusService {
    
    private RestTemplate restTemplate;
    
    @Value("${app.baseUrl}/status")
    private String url;

    public StatusService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public List<Status> getAll(){
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Status>>(){
            
        }).getBody();
    }
    
    
    
}
