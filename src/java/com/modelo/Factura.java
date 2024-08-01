/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.modelo;

import java.util.stream.Stream;

/**
 *
 * @author Julian
 */
public class Factura {
    
    private String id_venta;
    private String id_usuario;
    private String id_factura;
    private String id_producto;
    private String fecha;
    private String doc_cliente;
    private String cantidad;
    private String precio;
    private String total;
    private String nombre_p;
    private String talla;

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public Factura() {
        
    }
   
    public Factura(String id_venta, String id_usuario, String id_factura, String id_producto, String fecha, String doc_cliente, String cantidad, String precio) {
        this.id_venta = id_venta;
        this.id_usuario = id_usuario;
        this.id_factura = id_factura;
        this.id_producto = id_producto;
        this.fecha = fecha;
        this.doc_cliente = doc_cliente;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public String getNombre_p() {
        return nombre_p;
    }

    public void setNombre_p(String nombre_p) {
        this.nombre_p = nombre_p;
    }
 

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
    
    public String getId_venta() {
        return id_venta;
    }

    public void setId_venta(String id_venta) {
        this.id_venta = id_venta;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getId_factura() {
        return id_factura;
    }

    public void setId_factura(String id_factura) {
        this.id_factura = id_factura;
    }

    public String getId_producto() {
        return id_producto;
    }

    public void setId_producto(String id_producto) {
        this.id_producto = id_producto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDoc_cliente() {
        return doc_cliente;
    }

    public void setDoc_cliente(String doc_cliente) {
        this.doc_cliente = doc_cliente;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
    
    
}


