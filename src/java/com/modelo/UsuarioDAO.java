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
import org.mindrot.jbcrypt.BCrypt;
/**
 *
 * @author Julian
 */
public class UsuarioDAO extends Conexion {
    
    public Usuario validarUsuario(String usuario, String contrasena){
        
        Usuario usuarioValido = null;
        
        try {
            
            this.conectar();
            String sql = "SELECT * FROM usuario WHERE usuario = ? ";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1, usuario);
            ResultSet rs;
            rs = pre.executeQuery();
            if (rs.next()) {
                
                 String encriptada = rs.getString("contrasena");
                 
                 if (BCrypt.checkpw(contrasena, encriptada)) {
                    
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
            
            String password = BCrypt.hashpw(u.getContraseña(), BCrypt.gensalt());
            
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1, u.getUsuario());
            pre.setString(2, password);
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
                u.setNombre(rs.getString("nombre"));
                u.setTelefono(rs.getString("telefono"));
                u.setDireccion(rs.getString("direccion"));
                u.setEmail(rs.getString("email"));
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
    
    public boolean ModificarUsuario(Usuario u){
        
        try {
            this.conectar();
            String sql = "UPDATE usuario SET usuario = ?, id_rol = ?, nombre = ?, telefono = ?, direccion = ?, email = ? WHERE id_usuario = ?";
            
            PreparedStatement pre = this.getCon().prepareStatement(sql);
                    
            pre.setString(1, u.getUsuario());
            pre.setInt(2, Integer.parseInt(u.getRol()));
            pre.setString(3, u.getNombre());
            pre.setString(4, u.getTelefono());
            pre.setString(5, u.getDireccion());
            pre.setString(6, u.getEmail());
            pre.setInt(7, Integer.parseInt(u.getIdUsuario()));
 
            return pre.executeUpdate() > 0;

  
                       
        } catch (SQLException e) {
                e.printStackTrace();
            
            return false;
            
        } finally {
            this.desconectar();
        }
        
    }
    
    public  Usuario BuscarUsuario(String id){
     
        Usuario u = null;
        
        try {
            
            this.conectar();
            String sql = "SELECT * FROM usuario WHERE id_usuario = ?";
            
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            
            pre.setInt(1, Integer.parseInt(id));            
            ResultSet rs;              
            rs = pre.executeQuery();
            
            if (rs.next()) {
                
                u = new Usuario(
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
        
        
        return u;
        
    }
       
}
