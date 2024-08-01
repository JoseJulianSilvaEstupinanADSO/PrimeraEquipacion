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
public class ClienteDAO extends Conexion{
    
    public boolean RegistrarCliente(Cliente c) {
        
        try {
            
            this.conectar();
            
            String sql = "INSERT INTO usuario(documento, nombre, telefono, id_rol) VALUES (?,?,?,?)";

            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1, c.getDocumento());
            pre.setString(2, c.getNombre());
            pre.setString(3, c.getTelefono());
            pre.setInt(4, Integer.parseInt(c.getRol()));
            
            return pre.executeUpdate() > 0;
            
            
        } catch (SQLException e) {
            
            e.printStackTrace();
            return false;
            
        } finally { 
            
            this.desconectar();
        }
        
    }
    
    public List<Cliente> ListarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        
        try {
            
            this.conectar();

            String sql = "SELECT * FROM usuario";

            PreparedStatement pre = this.getCon().prepareStatement(sql);

            ResultSet rs = pre.executeQuery();
            
            while (rs.next()) {                
                Cliente c = new Cliente();
                
                c.setId_cliente(rs.getString("id_usuario"));
                c.setNombre(rs.getString("nombre"));
                c.setDocumento(rs.getString("documento"));
                c.setTelefono(rs.getString("telefono"));
                
                clientes.add(c);
                
            }

            
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        } finally {
            this.desconectar();
        }
        
        
        return clientes;
    }
    
    public Cliente BuscarCliente(String documento){
        
        Cliente c = null;
        
        try {
            
            this.conectar();
            String sql = "SELECT * FROM usuario WHERE documento=?";
            
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1, documento);
            ResultSet rs;
            rs = pre.executeQuery();
            
            if (rs.next()) {
                c = new Cliente(               
                    rs.getString("id_usuario"),
                    rs.getString("documento"),
                    rs.getString("nombre"),
                    rs.getString("telefono"),
                    rs.getString("id_rol")
                );
                
            }
            
            
            
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        } finally {
            this.desconectar();
        }
        
        return c;
        
        
    }
    
}
