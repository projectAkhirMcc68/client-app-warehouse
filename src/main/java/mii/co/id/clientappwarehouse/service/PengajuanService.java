    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mii.co.id.clientappwarehouse.service;

import java.util.List;
import mii.co.id.clientappwarehouse.model.Pengajuan;
import mii.co.id.clientappwarehouse.model.dto.request.PengajuanRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

/**
 *
 * @author USER
 */
@Service
public class PengajuanService {
    private RestTemplate restTemplate;
    
    @Value("${app.baseUrl}/pengajuan")
    private String url;
    
    @Autowired
    public PengajuanService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public  List<Pengajuan> getAll(){
        return restTemplate.exchange(url,HttpMethod.GET,null, new ParameterizedTypeReference<List<Pengajuan>>(){
        }).getBody();
    }
    
    public Pengajuan getById(Long id){
        return restTemplate.exchange(url.concat("/"+id), HttpMethod.GET,null, new ParameterizedTypeReference<Pengajuan>(){
        }).getBody();
    }
    
    public Pengajuan create(PengajuanRequest pengajuanRequest){
        return restTemplate.exchange(url.concat("/dto/"), HttpMethod.POST, new HttpEntity<>(pengajuanRequest),new ParameterizedTypeReference<Pengajuan>(){
        }).getBody();
    }
    
    public Pengajuan update(Long id,PengajuanRequest pengajuanRequest){
//        pengajuanRequest.setId(id);
        return restTemplate.exchange(url.concat("/"+id), HttpMethod.PUT, new HttpEntity<>(pengajuanRequest), new ParameterizedTypeReference<Pengajuan>(){
            
        }).getBody();
    }
    
//    public Pengajuan update(Long id,Pengajuan pengajuan){
//        return restTemplate.exchange(url.concat("/"+id), HttpMethod.PUT, new HttpEntity<>(pengajuan), new ParameterizedTypeReference<Pengajuan>(){
//            
//        }).getBody();
//    }
    
    public Pengajuan delete(Long id){
        return restTemplate.exchange(url.concat("/"+id), HttpMethod.DELETE, null, new ParameterizedTypeReference<Pengajuan>(){
            
        }).getBody();
    }
    
    
    
    
}
