/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.modelo;

import com.conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Propietario
 */
public class ProductoDAO extends Conexion{
    
    public List<Producto> ListarTallas(){
        List<Producto> tallas = new ArrayList<>();
        
        try {
            this.conectar();
            
            String sql = "SELECT * FROM talla";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            
            while (rs.next()) {                
                Producto p = new Producto();
                p.setTalla(rs.getString("talla"));
                
                tallas.add(p);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        
        System.out.println(tallas);
        
        return tallas;
    }
    
    public boolean AgregarProducto(Producto p) {
        try {
            
            this.conectar();
            
           String sql = "INSERT INTO producto(nombre, precio) VALUES (?,?) RETURNING id_producto";
           PreparedStatement pre = this.getCon().prepareStatement(sql);
           System.out.println(p.getNombre()+ "+ " + p.getPrecio());
           pre.setString(1, p.getNombre());
           pre.setInt(2, Integer.parseInt(p.getPrecio()));
           
           ResultSet rs = pre.executeQuery();
           
           int id_producto = 0;
           
           if (rs.next()) {
                id_producto = rs.getInt("id_producto");
           }
           
            String sql2 = "INSERT INTO producto_desc(id_producto,talla,stock,tela) VALUES (?,?,?,?)";
            PreparedStatement pre2 = this.getCon().prepareStatement(sql2);
            pre2.setInt(1, id_producto);
            pre2.setString(2, p.getTalla());
            pre2.setInt(3, Integer.parseInt(p.getStock()));
            pre2.setString(4, p.getTela());

            
            return pre2.executeUpdate() > 0;
           
           
           
            
            
        } catch (Exception e) {
            return false;
        } finally {
            this.desconectar();
        }
    }
    
}
