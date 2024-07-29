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
    
    public List<Producto> ListarProductos(){
        List<Producto> productos = new ArrayList<>();
        
        try {
            this.conectar();
            
            String sql = "SELECT producto.id_producto, nombre, precio, talla, stock, tela FROM producto JOIN producto_desc ON producto.id_producto = producto_desc.id_producto ";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            
            while (rs.next()) {                
                Producto p = new Producto();
                p.setId_producto(rs.getString("id_producto"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getString("precio"));
                p.setTalla(rs.getString("talla"));
                p.setStock(rs.getString("stock"));
                p.setTela(rs.getString("tela"));
                
                productos.add(p);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        
        
        return productos;
    }
    
    
    public boolean ModificarProducto (Producto p) {
        
        try {
            
            this.conectar();
            
            String sql = "UPDATE producto p JOIN producto_desc pd ON p.id_producto = pd.id_producto JOIN talla t ON pd.talla = t.talla SET nombre = ?, precio = ?, stock = ? WHERE p.id_producto = ? AND t.talla=?";   
            
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            
            System.out.println(p.getId_producto());
            
            pre.setString(1, p.getNombre());
            pre.setInt(2, Integer.parseInt(p.getPrecio()));
            pre.setInt(3, Integer.parseInt(p.getStock()));
            pre.setInt(4, Integer.parseInt(p.getId_producto()));
            pre.setString(5, p.getTalla());
            
            return pre.executeUpdate() > 0;
            
        } catch (SQLException e) {
            
            e.printStackTrace();
            
            return false;
            
            
        } finally {
            this.desconectar();
            
        }
           
    }
    
    public Producto BuscarProducto(String id_producto, String talla){
        
        Producto p = null;
        
        try {
            
            this.conectar();
            String sql = "SELECT p.id_producto, p.nombre, p.precio, pd.stock, pd.talla, pd.tela FROM producto p JOIN producto_desc pd ON p.id_producto = pd.id_producto JOIN talla t ON pd.talla = t.talla WHERE p.id_producto = ? AND t.talla = ?";
            
            PreparedStatement pre = this.getCon().prepareStatement(sql);
                    
            pre.setInt(1, Integer.parseInt(id_producto));
            pre.setString(2, talla);
            
            ResultSet rs;
            rs = pre.executeQuery();
            
            if (rs.next()) {
                p = new Producto(
                        rs.getString("id_producto"),
                        rs.getString("talla"),
                        rs.getString("nombre"),
                        rs.getString("precio"),
                        rs.getString("stock"),
                        rs.getString("tela")
                );
            }
            System.out.println(p);
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        
        return p;
        
    }
    
}
