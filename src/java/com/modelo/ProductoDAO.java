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
// Clase ProductoDAO que extiende de Conexion, manejando operaciones CRUD para la entidad Producto
public class ProductoDAO extends Conexion {
    
    // Método para listar todas las tallas disponibles
    public List<Producto> ListarTallas() {
        List<Producto> tallas = new ArrayList<>();
        
        try {
            // Conecta a la base de datos
            this.conectar();
            
            // Consulta SQL para seleccionar todas las tallas
            String sql = "SELECT * FROM talla";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            
            // Procesa los resultados y agrega las tallas a la lista
            while (rs.next()) {                
                Producto p = new Producto();
                p.setTalla(rs.getString("talla"));
                
                tallas.add(p);
            }
            
        } catch (SQLException e) {
            // Maneja cualquier excepción SQL
            e.printStackTrace();
        } finally {
            // Desconecta de la base de datos
            this.desconectar();
        }
        
        return tallas;
    }
    
    // Método para agregar un nuevo producto a la base de datos
    public boolean AgregarProducto(Producto p) {
        try {
            // Conecta a la base de datos
            this.conectar();
            
            // Consulta SQL para insertar un nuevo producto
            String sql = "INSERT INTO producto(nombre, precio,estado) VALUES (?,?,?)";
            PreparedStatement pre = this.getCon().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pre.setString(1, p.getNombre());
            pre.setInt(2, Integer.parseInt(p.getPrecio()));
            pre.setInt(3, Integer.parseInt(p.getEstado()));
            
            // Ejecuta la consulta de inserción y obtiene el ID generado
            int exito = pre.executeUpdate();
            
            if (exito > 0) {
                ResultSet rs = pre.getGeneratedKeys();
                if (rs.next()) {
                    int id_producto = rs.getInt(1);
                    
                    System.out.println(id_producto); // Imprime el ID del producto
                    
                    // Consulta SQL para insertar detalles del producto en otra tabla
                    String sql2 = "INSERT INTO producto_desc(id_producto,talla,stock,tela) VALUES (?,?,?,?)";
                    PreparedStatement pre2 = this.getCon().prepareStatement(sql2);
                    pre2.setInt(1, id_producto);
                    pre2.setString(2, p.getTalla());
                    pre2.setInt(3, Integer.parseInt(p.getStock()));
                    pre2.setString(4, p.getTela());
                    
                    // Ejecuta la inserción de detalles del producto
                    pre2.executeUpdate();
                    
                    return true; // Retorna true si la operación fue exitosa
                }
            }
         
        } catch (SQLException e) {
            // Maneja cualquier excepción SQL y retorna false
            e.printStackTrace();
            return false;
            
        } finally {
            // Desconecta de la base de datos
            this.desconectar();
        }
        
        return false;
    }
    
    // Método para listar todos los productos con sus detalles
    public List<Producto> ListarProductos() {
        List<Producto> productos = new ArrayList<>();
        
        try {
            // Conecta a la base de datos
            this.conectar();
            
            // Consulta SQL para seleccionar todos los productos y sus detalles
            String sql = "SELECT producto.id_producto, nombre, precio, estado, talla, stock, tela FROM producto JOIN producto_desc ON producto.id_producto = producto_desc.id_producto";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            
            // Procesa los resultados y agrega los productos a la lista
            while (rs.next()) {                
                Producto p = new Producto();
                p.setId_producto(rs.getString("id_producto"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getString("precio"));
                p.setTalla(rs.getString("talla"));
                p.setStock(rs.getString("stock"));
                p.setTela(rs.getString("tela"));
                p.setEstado(rs.getString("estado"));
                
                productos.add(p);
            }
            
        } catch (SQLException e) {
            // Maneja cualquier excepción SQL
            e.printStackTrace();
        } finally {
            // Desconecta de la base de datos
            this.desconectar();
        }
        
        return productos;
    }
    
    // Método para modificar un producto existente en la base de datos
    public boolean ModificarProducto(Producto p) {
        try {
            // Conecta a la base de datos
            this.conectar();
            
            // Consulta SQL para actualizar los detalles del producto
            String sql = "UPDATE producto p JOIN producto_desc pd ON p.id_producto = pd.id_producto JOIN talla t ON pd.talla = t.talla SET nombre = ?, precio = ?, stock = ?, estado = ? WHERE p.id_producto = ? AND t.talla=?";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            
            System.out.println(p.getId_producto()); // Imprime el ID del producto
            
            // Establece los valores para la consulta de actualización
            pre.setString(1, p.getNombre());
            pre.setInt(2, Integer.parseInt(p.getPrecio()));
            pre.setInt(3, Integer.parseInt(p.getStock()));
            pre.setInt(4, Integer.parseInt(p.getEstado()));
            pre.setInt(5, Integer.parseInt(p.getId_producto()));
            pre.setString(6, p.getTalla());

            
            // Ejecuta la actualización y retorna true si la operación fue exitosa
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
    
    // Método para buscar un producto específico por ID y talla
    public Producto BuscarProducto(String id_producto, String talla) {
        Producto p = null;
        
        try {
            // Conecta a la base de datos
            this.conectar();
            
            // Consulta SQL para seleccionar un producto específico por ID y talla
            String sql = "SELECT p.id_producto, p.nombre, p.precio, p.estado, pd.stock, pd.talla, pd.tela FROM producto p JOIN producto_desc pd ON p.id_producto = pd.id_producto JOIN talla t ON pd.talla = t.talla WHERE p.id_producto = ? AND t.talla = ? AND estado = 1";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
                    
            pre.setInt(1, Integer.parseInt(id_producto));
            pre.setString(2, talla);
            
            // Ejecuta la consulta y procesa los resultados
            ResultSet rs = pre.executeQuery();
            
            if (rs.next()) {
                p = new Producto(
                        rs.getString("id_producto"),
                        rs.getString("talla"),
                        rs.getString("nombre"),
                        rs.getString("precio"),
                        rs.getString("stock"),
                        rs.getString("tela"),
                        rs.getString("estado")
                );
            }
            System.out.println(p); // Imprime el producto encontrado
            
        } catch (SQLException e) {
            // Maneja cualquier excepción SQL
            e.printStackTrace();
        } finally {
            // Desconecta de la base de datos
            this.desconectar();
        }
        
        return p;
    }
}

