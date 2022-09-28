/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mii.co.id.clientappwarehouse.service;

import java.util.List;
import lombok.Data;
import mii.co.id.clientappwarehouse.model.History;
import org.springframework.beans.factory.annotation.Autowired;
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
public class HistoryService {

    private RestTemplate restTemplate;
    
    @Value("${app.baseUrl}/history")
    private String url;

    @Autowired
    public HistoryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    
    
    //    @Value("${app.baseUrl}/employee")
    //    private String url;
    
    public List<History> getAll(){
        return restTemplate.exchange(url,HttpMethod.GET,null,new ParameterizedTypeReference<List<History>>(){
            
        }).getBody();
    }
    
    public List<History> getByIdPenjualan(Long id){
        return restTemplate.exchange(url.concat("/idPengajuan/"+id), HttpMethod.GET,null, new ParameterizedTypeReference<List<History>>(){
            
        }).getBody();
    }
}
