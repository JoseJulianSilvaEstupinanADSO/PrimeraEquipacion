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
// Clase ClienteDAO que extiende de Conexion, manejando operaciones CRUD para la entidad Cliente
public class ClienteDAO extends Conexion {
    
    // Método para registrar un nuevo cliente en la base de datos
    public boolean RegistrarCliente(Cliente c) {
        
        try {
            // Conecta a la base de datos
            this.conectar();
            
            // Consulta SQL para insertar un nuevo cliente
            String sql = "INSERT INTO usuario(documento, nombre, telefono, id_rol) VALUES (?,?,?,?)";

            // Prepara la consulta SQL con los valores del objeto Cliente
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1, c.getDocumento());
            pre.setString(2, c.getNombre());
            pre.setString(3, c.getTelefono());
            pre.setInt(4, Integer.parseInt(c.getRol()));
            
            // Ejecuta la consulta y retorna true si se inserta al menos una fila
            return pre.executeUpdate() > 0;
            
        } catch (SQLException e) {
            // Maneja cualquier excepción SQL y retorna false
            e.printStackTrace();
            return false;
            
        } finally { 
            // Desconecta de la base de datos
            this.desconectar();
        }
    }
    
    // Método para listar todos los clientes de la base de datos
    public List<Cliente> ListarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        
        try {
            // Conecta a la base de datos
            this.conectar();

            // Consulta SQL para seleccionar todos los clientes
            String sql = "SELECT * FROM usuario";

            // Prepara y ejecuta la consulta SQL
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            
            // Recorre los resultados y agrega cada cliente a la lista
            while (rs.next()) {                
                Cliente c = new Cliente();
                
                c.setId_cliente(rs.getString("id_usuario"));
                c.setNombre(rs.getString("nombre"));
                c.setDocumento(rs.getString("documento"));
                c.setTelefono(rs.getString("telefono"));
                
                clientes.add(c);
            }

        } catch (SQLException e) {
            // Maneja cualquier excepción SQL
            e.printStackTrace();
            
        } finally {
            // Desconecta de la base de datos
            this.desconectar();
        }
        
        return clientes;
    }
    
    // Método para buscar un cliente en la base de datos por su documento
    public Cliente BuscarCliente(String documento) {
        
        Cliente c = null;
        
        try {
            // Conecta a la base de datos
            this.conectar();
            // Consulta SQL para buscar un cliente por su documento
            String sql = "SELECT * FROM usuario WHERE documento=?";
            
            // Prepara la consulta SQL con el documento proporcionado
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1, documento);
            ResultSet rs;
            rs = pre.executeQuery();
            
            // Si se encuentra un resultado, crea un objeto Cliente con los datos
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
            // Maneja cualquier excepción SQL
            e.printStackTrace();
            
        } finally {
            // Desconecta de la base de datos
            this.desconectar();
        }
        
        return c;
    }
}
