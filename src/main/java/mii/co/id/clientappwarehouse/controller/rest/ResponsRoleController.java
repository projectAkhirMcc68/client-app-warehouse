/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mii.co.id.clientappwarehouse.controller.rest;

import java.util.List;
import lombok.AllArgsConstructor;
import mii.co.id.clientappwarehouse.model.Role;
import mii.co.id.clientappwarehouse.service.RoleService;
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
@RequestMapping("/role")
public class ResponsRoleController {
    
    private RoleService roleService;
    
    @GetMapping("/getAll")
    public List<Role> getAll() {
        return roleService.getAll();
    }
    
    @GetMapping("/getId/{id}")
    public Role getById(@PathVariable Long id) {
        return roleService.getById(id);
    }
    
    @PostMapping
    public Role create(@RequestBody Role role) {
        return roleService.create(role);
    }
    
    @PutMapping("/{id}")
    public Role update(@PathVariable Long id, @RequestBody Role role) {
        return roleService.update(id, role);
    }
    
    @DeleteMapping("/{id}")
    public Role delete(@PathVariable Long id) {
        return roleService.delete(id);
    }
    
}
