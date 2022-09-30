/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mii.co.id.clientappwarehouse.controller.rest;

import java.util.List;
import lombok.AllArgsConstructor;
import mii.co.id.clientappwarehouse.model.Pengajuan;
import mii.co.id.clientappwarehouse.model.dto.request.PengajuanRequest;
import mii.co.id.clientappwarehouse.service.PengajuanService;
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
@RequestMapping("/pengajuan")
public class ResponsPengajuanController {
    
    private PengajuanService pengajuanService;
    
    @GetMapping("/getAll")
    public List<Pengajuan> getAll(){
        return pengajuanService.getAll();
    }
    
    @GetMapping("/{id}")
    public Pengajuan getById(@PathVariable Long id){
        return pengajuanService.getById(id);
    }
    
    @PostMapping
    public Pengajuan create(@RequestBody PengajuanRequest pengajuanRequest){
        return pengajuanService.create(pengajuanRequest);
    }
            
//            
//    @PutMapping("/{id}")
//    public Pengajuan update(@PathVariable Long id,@RequestBody Pengajuan pengajuan){
//        return pengajuanService.update(id, pengajuan);
//    }
    
    @PutMapping("/{id}")
    public Pengajuan update(@PathVariable Long id,@RequestBody PengajuanRequest pengajuanRequest){
        return pengajuanService.update(id, pengajuanRequest);
    }
    
    @DeleteMapping("/{id}")
    public Pengajuan delete(@PathVariable Long id){
        return pengajuanService.delete(id);
    }
}
