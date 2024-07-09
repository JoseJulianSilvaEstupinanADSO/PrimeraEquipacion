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
 * @author Julian
 */
public class UsuarioDAO extends Conexion {
    
    public Usuario validarUsuario(String usuario, String contrasena){
        
        Usuario usuarioValido = null;
        
        try {
            
            this.conectar();
            String sql = "SELECT * FROM usuario WHERE usuario = ? AND contrasena = ?";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1, usuario);
            pre.setString(2, contrasena);
            ResultSet rs;
            rs = pre.executeQuery();
            if (rs.next()) {
                usuarioValido = new Usuario(
                    rs.getString("id_usuario"),
                    rs.getString("usuario"),
                    rs.getString("contrasena"),
                    rs.getString("documento"),
                    rs.getString("nombre"),
                    rs.getString("telefono"),
                    rs.getString("direccion"),
                    rs.getString("email"),
                    rs.getString("edad"),
                    rs.getString("id_rol")
                );
            }
            
        } catch (SQLException e) {
            
            e.printStackTrace();
            
        } finally {
            this.desconectar();
        }
        return usuarioValido;
    }
    
    
    public boolean  insertarUsuario(Usuario u){
        try {
            this.conectar();
            String sql = "INSERT INTO usuario(usuario, contrasena, documento, nombre, telefono, direccion, email, edad) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1, u.getUsuario());
            pre.setString(2, u.getContraseña());
            pre.setString(3, u.getDocumento());
            pre.setString(4, u.getNombre());
            pre.setString(5, u.getTelefono());
            pre.setString(6, u.getDireccion());
            pre.setString(7, u.getEmail());
            pre.setInt(8, Integer.parseInt(u.getEdad()));
            
            return  pre.executeUpdate() > 0;
            
            
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            this.desconectar();
        }
        
    }
    
    public List<Usuario> ListarUsuarios() {
        
            List<Usuario> usuarios = new ArrayList<>();
        try {
            
            this.conectar();
            String sql = "SELECT * FROM usuario";
            
            PreparedStatement pre = this.getCon().prepareStatement(sql);
                
            ResultSet rs = pre.executeQuery();
            
            while(rs.next()){
                
                Usuario u = new Usuario();
                
                u.setIdUsuario(rs.getString("id_usuario"));
                u.setUsuario(rs.getString("usuario"));
                u.setContraseña(rs.getString("contrasena"));
                u.setDocumento(rs.getString("documento"));
                u.setNombre(rs.getString("telefono"));
                u.setTelefono(rs.getString("telefono"));
                u.setDireccion(rs.getString("direccion"));
                u.setRol(rs.getString("id_rol"));
                
                usuarios.add(u);
            }
            
        } catch (SQLException e) {
              e.printStackTrace();
            
        } finally {
            this.desconectar();
        }
            
            return usuarios;
           
    }
       
}
