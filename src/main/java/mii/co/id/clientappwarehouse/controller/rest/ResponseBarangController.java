/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.co.id.clientappwarehouse.controller.rest;

import java.util.List;
import lombok.AllArgsConstructor;
import mii.co.id.clientappwarehouse.model.Barang;
import mii.co.id.clientappwarehouse.service.BarangService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author aditya jalu
 */
@RestController
@AllArgsConstructor
@RequestMapping("/barang")
public class ResponseBarangController {
    
    private BarangService barangService;
    
    @GetMapping("/getAll")
    public List<Barang> getAll() {
        return barangService.getAll();
    }
    
    @GetMapping("/getId/{id}")
    public Barang getById(@PathVariable Long id) {
        return barangService.getById(id);
    }
    
//    @PostMapping
//    public Barang create(@RequestBody Barang barang) {
//        
//    }
    
    
}
