/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.co.id.clientappwarehouse.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author aditya jalu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Barang {
    private Long id;
    private String nama_barang;
    private String kategory;
    private Integer stock;
    private Integer harga;
    private Date tanggal;
}
