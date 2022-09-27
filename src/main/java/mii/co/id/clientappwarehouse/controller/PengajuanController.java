/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mii.co.id.clientappwarehouse.controller;

import lombok.AllArgsConstructor;
import mii.co.id.clientappwarehouse.service.PengajuanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author USER
 */
@Controller
@AllArgsConstructor
@RequestMapping("/pengajuan")
public class PengajuanController {
    
    private final PengajuanService pengajuanService;
    
   @GetMapping
   public String index(){
       return "pengajuan/index";
   }
    
}
