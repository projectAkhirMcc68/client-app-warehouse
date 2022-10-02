/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mii.co.id.clientappwarehouse.controller;

import lombok.AllArgsConstructor;
import mii.co.id.clientappwarehouse.model.dto.request.LoginRequest;
import mii.co.id.clientappwarehouse.service.LoginService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author USER
 */
@AllArgsConstructor
@Controller
@RequestMapping("/login")
public class LoginController {
    
    private LoginService loginService;
    
    @GetMapping
    public String loginView(LoginRequest loginRequest){
     
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth instanceof AnonymousAuthenticationToken) {
            return "auth/login";
        }
        
        return "redirect:/index";
        
    }
    
    @PostMapping
    public String login(LoginRequest loginRequest){
        loginService.login(loginRequest);
        return "index";
    }
    
    
}
