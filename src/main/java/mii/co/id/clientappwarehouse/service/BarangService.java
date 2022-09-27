/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.co.id.clientappwarehouse.service;

import java.util.List;
import mii.co.id.clientappwarehouse.model.Barang;
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
public class BarangService {
    private RestTemplate restTemplate;
    
    @Value("${app.baseUrl}/barang")
    private String url;

    @Autowired
    public BarangService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public List<Barang> getAll() {
        return restTemplate.exchange(url, HttpMethod.GET, 
                null, new ParameterizedTypeReference<List<Barang>>() { 
                })
                .getBody();
    }
    
    public Barang create(Barang barang) {
        return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity(barang),
                new ParameterizedTypeReference<Barang>() {
                })
                .getBody();
    }
    
    public Barang getById(Long id) {
        return restTemplate.exchange(url.concat("/" + id), HttpMethod.GET,
                null, new ParameterizedTypeReference<Barang>() {
                })
                .getBody();
    }
    
    public Barang update(Long id, Barang barang) {
        return restTemplate.exchange(url.concat("/" + id), HttpMethod.PUT, 
                new HttpEntity(barang), new ParameterizedTypeReference<Barang>() {
                })
                .getBody();
    }
    
    public Barang delete(Long id) {
        return restTemplate.exchange(url.concat("/" + id), HttpMethod.DELETE,
                null, new ParameterizedTypeReference<Barang>() {
                })
                .getBody();
    }
        
    
}
