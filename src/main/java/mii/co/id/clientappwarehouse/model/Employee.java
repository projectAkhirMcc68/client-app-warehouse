/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mii.co.id.clientappwarehouse.model;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author USER
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    
    private Long id;

    private String fullName;
    
    private String email;
    
    private Date dateOfBirth;
    
    private User user;

    
}
