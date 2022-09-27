/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.co.id.clientappwarehouse.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import mii.co.id.clientappwarehouse.model.Barang;
import mii.co.id.clientappwarehouse.service.BarangService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author aditya jalu
 */
@Controller
@AllArgsConstructor
@RequestMapping("/barang")
public class BarangController {
    
    private BarangService barangService;
    
    @GetMapping
    public String index(Model model) {
        model.addAttribute("barangs", barangService.getAll());
        model.addAttribute("name", "Hello jalu");
        return "barang/index";
    }
    
    @GetMapping("/get-all")
    @ResponseBody
    public List<Barang> getAll() {
        return barangService.getAll();
    }
        
    
    @GetMapping("/create")
    public String createView(Barang barang) {
      return "barang/create-form";  
    }
    
    @PostMapping
    public String create(Barang barang) {
        barangService.create(barang);
        return "redirect:/barang";
    }
    
    @GetMapping("/update/{id}")
    public String updateView(Model model, Barang barang, @PathVariable Long id) {
        model.addAttribute("barang", barangService.getById(id));
        return "barang/update-form";
    }
    
    @PutMapping("/{id}")
    public String update(Barang barang, @PathVariable Long id) {
        barangService.update(barang, id);
        return "redirect:/barang";
    }
    
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        barangService.delete(id);
        return "redirect:/barang";
    }
    
}
