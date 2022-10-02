/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mii.co.id.clientappwarehouse.service;

import java.util.Collection;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import mii.co.id.clientappwarehouse.model.dto.request.LoginRequest;
import mii.co.id.clientappwarehouse.model.dto.response.LoginResponse;
import mii.co.id.clientappwarehouse.util.createHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author USER
 */
@AllArgsConstructor
@Service
public class LoginService {
 
    private RestTemplate restTemplate;
    
    private createHeaders createHeaders;
    
    @Value("${app.baseUrl}/login")
    private String url;

    @Autowired
    public LoginService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public LoginResponse login(LoginRequest loginRequest){
        LoginResponse loginResponse = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity(loginRequest), new ParameterizedTypeReference<LoginResponse>(){            
        }).getBody();
        setAuthentication(loginResponse, loginRequest);
        return loginResponse;
    }
    
    public void setAuthentication(LoginResponse loginResponse,LoginRequest loginRequest){
        Collection<GrantedAuthority> authorities = loginResponse.getAuthorities()
                .stream().map(authority -> new SimpleGrantedAuthority(authority))
                .collect(Collectors.toList());
        
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                loginResponse.getUsername(),
                createHeaders.makeBasicAuth(loginRequest.getUsername(), loginRequest.getPassword()),authorities);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
    
}