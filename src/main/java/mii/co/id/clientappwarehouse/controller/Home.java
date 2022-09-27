/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.co.id.clientappwarehouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author aditya jalu
 */
@Controller
public class Home {
    
    @GetMapping
    public String index() {
        return "index";
    }
}
