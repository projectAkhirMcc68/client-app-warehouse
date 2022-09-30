/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mii.co.id.clientappwarehouse.controller.rest;

import java.util.List;
import lombok.AllArgsConstructor;
import mii.co.id.clientappwarehouse.model.User;
import mii.co.id.clientappwarehouse.model.dto.request.UserRequest;
import mii.co.id.clientappwarehouse.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/user")
public class ResponsUserController {
    
    private final UserService userService;
    
    @GetMapping("/getAll")
    public List<User> getAll(){
        return userService.getAll();
    }
    
    @GetMapping("/{id}")
    public User getIdl(@PathVariable Long id){
        return userService.getId(id);
    }
    
    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody UserRequest userRequest){
        return userService.update(id, userRequest);
    }
}
