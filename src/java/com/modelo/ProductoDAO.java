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
import java.sql.Statement;


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
            
        } catch (SQLException e) {
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
            
           String sql = "INSERT INTO producto(nombre, precio) VALUES (?,?)";
           PreparedStatement pre = this.getCon().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
           pre.setString(1, p.getNombre());
           pre.setInt(2, Integer.parseInt(p.getPrecio()));
           
           int exito = pre.executeUpdate();
           
            if (exito > 0) {
                ResultSet rs = pre.getGeneratedKeys();
                if (rs.next()) {
                    
                    int id_producto = rs.getInt(1);
                    
                    System.out.println(id_producto+ " el id es");
                    
                    String sql2 = "INSERT INTO producto_desc(id_producto,talla,stock,tela) VALUES (?,?,?,?)";
                    PreparedStatement pre2 = this.getCon().prepareStatement(sql2);
                    pre2.setInt(1, id_producto);
                    pre2.setString(2, p.getTalla());
                    pre2.setInt(3, Integer.parseInt(p.getStock()));
                    pre2.setString(4, p.getTela());
                    
                    pre2.executeUpdate();
                    
                    return true;
                }
            }
         
      
            
        } catch (SQLException e) {
            return false;
        } finally {
            this.desconectar();
        }
           return false;
    }
    
}
