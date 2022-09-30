/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.co.id.clientappwarehouse.controller;

import lombok.AllArgsConstructor;
import mii.co.id.clientappwarehouse.model.Role;
import mii.co.id.clientappwarehouse.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author aditya jalu
 */
@Controller
@AllArgsConstructor
@RequestMapping("/role")
public class RoleController {
    private RoleService roleService;
    
    @GetMapping
    public String index(Model model) {
        model.addAttribute("roles", roleService.getAll());
        return "role/index";
    }
    
//    @GetMapping("/get-all")
//    @ResponseBody
//    public List<Role> getAll() {
//        return roleService.getAll();
//    }
//        
//    
//    @GetMapping("/create")
//    public String createView(Role role) {
//      return "role/create-form";  
//    }
//    
//    @PostMapping
//    public String create(Role role) {
//        roleService.create(role);
//        return "redirect:/role";
//    }
    
    @GetMapping("/update/{id}")
    public String updateView(Model model, Role role, @PathVariable Long id) {
        model.addAttribute("role", roleService.getById(id));
        return "role/index";
    }
    
//    @PutMapping("/{id}")
//    public String update(Role role, @PathVariable Long id) {
//        roleService.update(role, id);
//        return "redirect:/role";
//    }
//    
//    @DeleteMapping("/{id}")
//    public String delete(@PathVariable Long id) {
//        roleService.delete(id);
//        return "redirect:/role";
//    }
    
}
