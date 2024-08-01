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
 * @author Julian
 */
public class FacturaDAO extends Conexion{
    
    public int AgregarFactura(Factura f, String total) {
        
        int id_factura = 0;
            
        try {
            
            this.conectar();
            
            
            String sql = "INSERT INTO venta(id_usuario) VALUES (?)";
            PreparedStatement pre = this.getCon().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pre.setInt(1, Integer.parseInt(f.getId_usuario()));
            
            int exito = pre.executeUpdate();
            
            if (exito > 0) {
                ResultSet rs = pre.getGeneratedKeys();
                
                if (rs.next()) {
                    int id_venta = rs.getInt(1);
                    
                    String sql2 = "INSERT INTO factura(fecha_facturacion,id_venta,doc_cliente,total) VALUES (?,?,?,?)";
                    PreparedStatement pre2 = this.getCon().prepareStatement(sql2,Statement.RETURN_GENERATED_KEYS);
                    pre2.setString(1, f.getFecha());
                    pre2.setInt(2, id_venta);
                    pre2.setString(3, f.getDoc_cliente());
                    pre2.setInt(4, Integer.parseInt(total));
                    
                    int exito2 = pre2.executeUpdate();
                    
                    if (exito2 > 0) {
                       
                        ResultSet rs2 = pre2.getGeneratedKeys();
                        
                        if (rs2.next()) {
                            id_factura = rs2.getInt(1);

                            return  id_factura;
                            
                        }
                        
                        
                    }
                    
                    
                    
                }
            }
            
        } catch (SQLException e) {
            
            e.printStackTrace();
            return id_factura;
            
        } finally {
            this.desconectar();
        }
        return id_factura;
    }
    
    public boolean AgregarProdcutosFactura(Factura f) {
        
        try {
            
            this.conectar();
            
                String sql3 = "INSERT INTO factura_producto(id_factura, id_producto, cantidad, precio) VALUES (?,?,?,?)";
                PreparedStatement pre3 = this.getCon().prepareCall(sql3);
                pre3.setInt(1, Integer.parseInt(f.getId_factura()));
                pre3.setInt(2, Integer.parseInt(f.getId_producto()));
                pre3.setInt(3, Integer.parseInt(f.getCantidad()));
                pre3.setDouble(4, Double.parseDouble(f.getPrecio()));

                pre3.executeUpdate();
                
                String sql = "UPDATE producto_desc SET stock = stock - ? WHERE id_producto = ?";
                
                PreparedStatement pre = this.getCon().prepareStatement(sql);
                
                pre.setInt(1, Integer.parseInt(f.getCantidad()));
                pre.setInt(2, Integer.parseInt(f.getId_producto()));
                
                return pre.executeUpdate() > 0;
            
            
            
        } catch (SQLException e) {
            
            e.printStackTrace();
            return false;
            
        } finally {
            
            this.desconectar();
        }

    }
    
    public List<Factura> ListarFacturas() {
        List<Factura> facturas = new ArrayList<>();
        
        try {
            
            this.conectar();
            String sql = "SELECT f.id_factura, f.fecha_facturacion, f.id_venta, f.doc_cliente, f.total, v.id_usuario FROM factura f JOIN venta v ON f.id_venta = v.id_venta";
            
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            
            ResultSet rs = pre.executeQuery();
            
            while (rs.next()) {                
                Factura f = new Factura();
                
                f.setId_venta(rs.getString("id_venta"));
                f.setId_usuario(rs.getString("id_usuario"));
                f.setId_factura(rs.getString("id_factura"));
                f.setFecha(rs.getString("fecha_facturacion"));
                f.setDoc_cliente(rs.getString("doc_cliente"));
                f.setTotal(rs.getString("total"));

                facturas.add(f);
            }
            
            
        } catch (SQLException e) {
        } finally {
            this.desconectar();
        }
        
        
        return facturas;
    }
    
    public List<Factura> ListarProductoFactura(String id_factura) {
        List<Factura> productos = new ArrayList<>();
        
        try {
            
            this.conectar();
            
            String sql = "SELECT fp.id_producto, fp.precio, fp.cantidad, p.nombre, pd.talla FROM factura_producto fp JOIN producto p ON fp.id_producto = p.id_producto JOIN producto_desc pd ON p.id_producto = pd.id_producto WHERE fp.id_factura = ?";
            
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, Integer.parseInt(id_factura));
            ResultSet rs = pre.executeQuery();
            
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
            e.printStackTrace();
            
        } finally {
            this.desconectar();
        }
        
        
        return  productos;
    }
    
    public List<Factura> ClientesCompras(String doc_cliente) {
        List<Factura> compras = new ArrayList<>();
        
        try {
            
            this.conectar();
            
            String sql = "SELECT f.id_venta, f.id_factura, f.fecha_facturacion, v.id_usuario FROM factura f JOIN venta v ON f.id_venta = v.id_venta WHERE f.doc_cliente = ?";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1, doc_cliente);
            
            ResultSet rs = pre.executeQuery();
            
            while (rs.next()) {                
                Factura c = new Factura();
                c.setId_venta(rs.getString("id_venta"));
                c.setId_factura(rs.getString("id_factura"));
                c.setFecha(rs.getString("fecha_facturacion"));
                c.setId_usuario(rs.getString("id_usuario"));
                
                compras.add(c);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        
        return compras;
    }
    
}
