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
 * La clase ClienteDAO maneja las operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * para la entidad Cliente en la base de datos. Esta clase extiende de la clase Conexion,
 * que proporciona la conexión a la base de datos.
 * 
 * Los métodos proporcionados permiten registrar un cliente, listar todos los clientes,
 * y buscar un cliente específico por su documento.
 * 
 * @author Propietario
 */
public class ClienteDAO extends Conexion {
    
    /**
     * Registra un nuevo cliente en la base de datos.
     * 
     * @param c Objeto Cliente que contiene los datos del cliente a registrar.
     * @return true si el cliente fue registrado exitosamente, false en caso contrario.
     */
    public boolean RegistrarCliente(Cliente c) {
        try {
            // Conecta a la base de datos
            this.conectar();
            
            // Consulta SQL para insertar un nuevo cliente en la tabla usuario
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
            // Maneja cualquier excepción SQL e imprime la traza de error
            e.printStackTrace();
            return false;
            
        } finally { 
            // Desconecta de la base de datos
            this.desconectar();
        }
    }
    
    /**
     * Lista todos los clientes registrados en la base de datos.
     * 
     * @return Una lista de objetos Cliente que representa a todos los clientes en la base de datos.
     */
    public List<Cliente> ListarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        
        try {
            // Conecta a la base de datos
            this.conectar();

            // Consulta SQL para seleccionar todos los clientes de la tabla usuario
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
            // Maneja cualquier excepción SQL e imprime la traza de error
            e.printStackTrace();
            
        } finally {
            // Desconecta de la base de datos
            this.desconectar();
        }
        
        return clientes;
    }
    
    /**
     * Busca un cliente en la base de datos por su documento.
     * 
     * @param documento El documento del cliente que se desea buscar.
     * @return Un objeto Cliente si se encuentra un cliente con el documento dado,
     *         o null si no se encuentra ningún cliente.
     */
    public Cliente BuscarCliente(String documento) {
        
        Cliente c = null;
        
        try {
            // Conecta a la base de datos
            this.conectar();
            
            // Consulta SQL para buscar un cliente por su documento en la tabla usuario
            String sql = "SELECT * FROM usuario WHERE documento=?";
            
            // Prepara la consulta SQL con el documento proporcionado
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1, documento);
            ResultSet rs = pre.executeQuery();
            
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
            // Maneja cualquier excepción SQL e imprime la traza de error
            e.printStackTrace();
            
        } finally {
            // Desconecta de la base de datos
            this.desconectar();
        }
        
        return c;
    }
}