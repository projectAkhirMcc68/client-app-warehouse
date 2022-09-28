/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mii.co.id.clientappwarehouse.controller.rest;

import java.util.List;
import lombok.AllArgsConstructor;
import mii.co.id.clientappwarehouse.model.History;
import mii.co.id.clientappwarehouse.service.HistoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author USER
 */
@AllArgsConstructor
@RestController
@RequestMapping("/history")
public class ResponsHistoryController {
    
    private HistoryService historyService;
   
     @GetMapping("/getAll")
    public List<History> getAll(){
        return historyService.getAll();
    }
    
    @GetMapping("/getId/{id}")
    public List<History> getByIdPengajuan(@PathVariable Long id){
        return historyService.getByIdPenjualan(id);
    }
    
}
