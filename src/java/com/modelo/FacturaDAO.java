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
                    pre2.setString(3, f.getId_usuario());
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

                return  pre3.executeUpdate() > 0;
            
            
            
        } catch (SQLException e) {
            
            e.printStackTrace();
            return false;
            
        } finally {
            
            this.desconectar();
        }

    }
    
}
