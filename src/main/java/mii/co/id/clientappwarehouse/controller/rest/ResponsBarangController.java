/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mii.co.id.clientappwarehouse.controller.rest;

import java.util.List;
import lombok.AllArgsConstructor;
import mii.co.id.clientappwarehouse.model.Barang;
import mii.co.id.clientappwarehouse.service.BarangService;
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
@RequestMapping("/barang")
public class ResponsBarangController {
    
    private BarangService barangService;
    
   @GetMapping("/getAll")
    public List<Barang> getAll(){
        return barangService.getAll();
    } 
    
    @GetMapping("/{id}")
    public Barang getById(@PathVariable Long id){
        return barangService.getById(id);
    }
    
    @PostMapping
    public Barang create(@RequestBody Barang barang){
        return barangService.create(barang);
    }
    
    @PutMapping("/{id}")
    public Barang update(@PathVariable Long id,@RequestBody Barang barang){
        return barangService.update(id, barang);
    }
    
    @DeleteMapping("/{id}")
    public Barang delete(@PathVariable Long id){
        return barangService.delete(id);
    }
    
}
