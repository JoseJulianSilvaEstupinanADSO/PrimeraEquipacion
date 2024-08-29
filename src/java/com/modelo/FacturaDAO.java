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
 * La clase FacturaDAO extiende de Conexion y maneja las operaciones CRUD para la entidad Factura.
 * Proporciona métodos para agregar facturas, agregar productos a facturas, listar facturas,
 * listar productos de una factura, listar compras de clientes y obtener datos para generar PDFs.
 * 
 * @author Julian
 */
public class FacturaDAO extends Conexion {

    /**
     * Agrega una nueva factura a la base de datos y devuelve el ID de la factura generada.
     * 
     * @param f Objeto Factura que contiene la información de la nueva factura.
     * @param total Total de la factura.
     * @return ID de la nueva factura si la inserción fue exitosa, 0 en caso contrario.
     */
    public int AgregarFactura(Factura f, String total) {
        int id_factura = 0;
        try {
            // Conecta a la base de datos
            this.conectar();
            
            // Consulta SQL para insertar una nueva venta
            String sql = "INSERT INTO venta(id_usuario) VALUES (?)";
            PreparedStatement pre = this.getCon().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pre.setInt(1, Integer.parseInt(f.getId_usuario()));
            
            // Ejecuta la consulta de inserción y obtiene el ID generado
            int exito = pre.executeUpdate();
            if (exito > 0) {
                ResultSet rs = pre.getGeneratedKeys();
                if (rs.next()) {
                    int id_venta = rs.getInt(1);
                    
                    // Consulta SQL para insertar una nueva factura asociada a la venta
                    String sql2 = "INSERT INTO factura(fecha_facturacion, id_venta, doc_cliente, total) VALUES (?,?,?,?)";
                    PreparedStatement pre2 = this.getCon().prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
                    pre2.setString(1, f.getFecha());
                    pre2.setInt(2, id_venta);
                    pre2.setString(3, f.getDoc_cliente());
                    pre2.setInt(4, Integer.parseInt(total));
                    
                    // Ejecuta la consulta de inserción y obtiene el ID de la factura
                    int exito2 = pre2.executeUpdate();
                    if (exito2 > 0) {
                        ResultSet rs2 = pre2.getGeneratedKeys();
                        if (rs2.next()) {
                            id_factura = rs2.getInt(1);
                            return id_factura; // Retorna el ID de la nueva factura
                        }
                    }
                }
            }
            
        } catch (SQLException e) {
            // Maneja cualquier excepción SQL y retorna el ID de la factura
            e.printStackTrace();
            return id_factura;
            
        } finally {
            // Desconecta de la base de datos
            this.desconectar();
        }
        return id_factura;
    }
    
    /**
     * Agrega productos a una factura existente.
     * 
     * @param f Objeto Factura que contiene la información de los productos a agregar.
     * @return true si los productos se agregaron correctamente, false en caso contrario.
     */
    public boolean AgregarProdcutosFactura(Factura f) {
        try {
            // Conecta a la base de datos
            this.conectar();
            
            // Consulta SQL para insertar productos en una factura
            String sql3 = "INSERT INTO factura_producto(id_factura, id_producto, cantidad, precio) VALUES (?,?,?,?)";
            PreparedStatement pre3 = this.getCon().prepareStatement(sql3);
            pre3.setInt(1, Integer.parseInt(f.getId_factura()));
            pre3.setInt(2, Integer.parseInt(f.getId_producto()));
            pre3.setInt(3, Integer.parseInt(f.getCantidad()));
            pre3.setDouble(4, Double.parseDouble(f.getPrecio()));

            // Ejecuta la consulta de inserción
            pre3.executeUpdate();
            
            // Consulta SQL para actualizar el stock del producto
            String sql = "UPDATE producto_desc SET stock = stock - ? WHERE id_producto = ?";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, Integer.parseInt(f.getCantidad()));
            pre.setInt(2, Integer.parseInt(f.getId_producto()));
            
            // Ejecuta la actualización del stock y retorna true si la operación fue exitosa
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
    
    /**
     * Lista todas las facturas en la base de datos.
     * 
     * @return Lista de objetos Factura que representa todas las facturas.
     */
    public List<Factura> ListarFacturas() {
        List<Factura> facturas = new ArrayList<>();
        try {
            // Conecta a la base de datos
            this.conectar();
            
            // Consulta SQL para seleccionar todas las facturas junto con el ID de la venta asociada
            String sql = "SELECT f.id_factura, f.fecha_facturacion, f.id_venta, f.doc_cliente, f.total, v.id_usuario, u.nombre FROM factura f JOIN venta v ON f.id_venta = v.id_venta JOIN usuario u ON u.id_usuario = v.id_usuario";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            
            // Ejecuta la consulta y procesa los resultados
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {                
                Factura f = new Factura();
                
                f.setId_venta(rs.getString("id_venta"));
                f.setId_usuario(rs.getString("nombre"));
                f.setId_factura(rs.getString("id_factura"));
                f.setFecha(rs.getString("fecha_facturacion"));
                f.setDoc_cliente(rs.getString("doc_cliente"));
                f.setTotal(rs.getString("total"));

                facturas.add(f);
            }
            
        } catch (SQLException e) {
            // Maneja cualquier excepción SQL
            e.printStackTrace();
        } finally {
            // Desconecta de la base de datos
            this.desconectar();
        }
        
        return facturas;
    }
    
    /**
     * Lista los productos de una factura específica.
     * 
     * @param id_factura ID de la factura de la cual se listarán los productos.
     * @return Lista de objetos Factura que representa los productos asociados a la factura.
     */
    public List<Factura> ListarProductoFactura(String id_factura) {
        List<Factura> productos = new ArrayList<>();
        try {
            // Conecta a la base de datos
            this.conectar();
            
            // Consulta SQL para seleccionar productos asociados a una factura específica
            String sql = "SELECT fp.id_producto, fp.precio, fp.cantidad, p.nombre, pd.talla FROM factura_producto fp JOIN producto p ON fp.id_producto = p.id_producto JOIN producto_desc pd ON p.id_producto = pd.id_producto WHERE fp.id_factura = ?";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, Integer.parseInt(id_factura));
            ResultSet rs = pre.executeQuery();
            
            // Procesa los resultados y agrega los productos a la lista
            while (rs.next()) {                
                Factura f = new Factura();
                
                f.setId_producto(rs.getString("id_producto"));
                f.setPrecio(rs.getString("precio"));
                f.setCantidad(rs.getString("cantidad"));
                f.setNombre_p(rs.getString("nombre"));
                f.setTalla(rs.getString("talla"));
                
                productos.add(f);
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
    
    /**
     * Lista todas las compras realizadas por un cliente específico.
     * 
     * @param doc_cliente Documento del cliente cuya compra se listará.
     * @return Lista de objetos Factura que representa las compras del cliente.
     */
    public List<Factura> ClientesCompras(String doc_cliente) {
        List<Factura> compras = new ArrayList<>();
        try {
            // Conecta a la base de datos
            this.conectar();
            
            // Consulta SQL para seleccionar todas las compras de un cliente basado en su documento
            String sql = "SELECT f.id_venta, f.id_factura, f.fecha_facturacion, v.id_usuario, u.nombre FROM factura f JOIN venta v ON f.id_venta = v.id_venta JOIN usuario u ON v.id_usuario = u.id_usuario WHERE f.doc_cliente = ?";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1, doc_cliente);
            
            // Ejecuta la consulta y procesa los resultados
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {                
                Factura c = new Factura();
                c.setId_venta(rs.getString("id_venta"));
                c.setId_factura(rs.getString("id_factura"));
                c.setFecha(rs.getString("fecha_facturacion"));
                c.setId_usuario(rs.getString("nombre"));
                
                compras.add(c);
            }
            
        } catch (SQLException e) {
            // Maneja cualquier excepción SQL
            e.printStackTrace();
        } finally {
            // Desconecta de la base de datos
            this.desconectar();
        }
        
        return compras;
    }

    /**
     * Obtiene los datos necesarios para generar un PDF de una factura específica.
     * 
     * @param id_factura ID de la factura para la cual se obtendrán los datos.
     * @return Lista de objetos Factura que contiene los datos para el PDF.
     */
    public List<Factura> PdfProductoFactura(String id_factura) {
        List<Factura> productos = new ArrayList<>();
        try {
            // Conecta a la base de datos
            this.conectar();
            
            // Consulta SQL para seleccionar productos asociados a una factura específica
            String sql = "SELECT f.fecha_facturacion, f.doc_cliente, f.total, fp.id_producto, fp.precio, fp.cantidad, p.nombre, pd.talla FROM factura f JOIN factura_producto fp  ON f.id_factura = fp.id_factura JOIN producto p ON fp.id_producto = p.id_producto JOIN producto_desc pd ON p.id_producto = pd.id_producto WHERE fp.id_factura = ?";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, Integer.parseInt(id_factura));
            ResultSet rs = pre.executeQuery();
            
            // Procesa los resultados y agrega los productos a la lista
            while (rs.next()) {                
                Factura f = new Factura();
                
                f.setFecha(rs.getString("fecha_facturacion"));
                f.setDoc_cliente(rs.getString("doc_cliente"));
                f.setTotal(rs.getString("total"));
                f.setId_producto(rs.getString("id_producto"));
                f.setPrecio(rs.getString("precio"));
                f.setCantidad(rs.getString("cantidad"));
                f.setNombre_p(rs.getString("nombre"));
                f.setTalla(rs.getString("talla"));
                
                productos.add(f);
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
}
