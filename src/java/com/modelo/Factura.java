/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.modelo;

/**
 * La clase Factura representa una factura dentro del sistema.
 * Contiene información sobre la venta, el usuario que realiza la venta,
 * los productos incluidos, la fecha de la transacción, el cliente, 
 * y detalles adicionales como el precio, la cantidad, y el total.
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

    /**
     * Constructor vacío de la clase Factura.
     */
    public Factura() {
        
    }

    /**
     * Constructor de la clase Factura con parámetros para inicializar todos los campos.
     * 
     * @param id_venta Identificador de la venta.
     * @param id_usuario Identificador del usuario que realiza la venta.
     * @param id_factura Identificador de la factura.
     * @param id_producto Identificador del producto.
     * @param fecha Fecha de la transacción.
     * @param doc_cliente Documento del cliente.
     * @param cantidad Cantidad de productos.
     * @param precio Precio del producto.
     */
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

    /**
     * Obtiene la talla del producto.
     * 
     * @return Talla del producto.
     */
    public String getTalla() {
        return talla;
    }

    /**
     * Establece la talla del producto.
     * 
     * @param talla Talla del producto.
     */
    public void setTalla(String talla) {
        this.talla = talla;
    }

    /**
     * Obtiene el nombre del producto.
     * 
     * @return Nombre del producto.
     */
    public String getNombre_p() {
        return nombre_p;
    }

    /**
     * Establece el nombre del producto.
     * 
     * @param nombre_p Nombre del producto.
     */
    public void setNombre_p(String nombre_p) {
        this.nombre_p = nombre_p;
    }

    /**
     * Obtiene el total de la factura.
     * 
     * @return Total de la factura.
     */
    public String getTotal() {
        return total;
    }

    /**
     * Establece el total de la factura.
     * 
     * @param total Total de la factura.
     */
    public void setTotal(String total) {
        this.total = total;
    }

    /**
     * Obtiene el identificador de la venta.
     * 
     * @return Identificador de la venta.
     */
    public String getId_venta() {
        return id_venta;
    }

    /**
     * Establece el identificador de la venta.
     * 
     * @param id_venta Identificador de la venta.
     */
    public void setId_venta(String id_venta) {
        this.id_venta = id_venta;
    }

    /**
     * Obtiene el identificador del usuario que realizó la venta.
     * 
     * @return Identificador del usuario.
     */
    public String getId_usuario() {
        return id_usuario;
    }

    /**
     * Establece el identificador del usuario que realizó la venta.
     * 
     * @param id_usuario Identificador del usuario.
     */
    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    /**
     * Obtiene el identificador de la factura.
     * 
     * @return Identificador de la factura.
     */
    public String getId_factura() {
        return id_factura;
    }

    /**
     * Establece el identificador de la factura.
     * 
     * @param id_factura Identificador de la factura.
     */
    public void setId_factura(String id_factura) {
        this.id_factura = id_factura;
    }

    /**
     * Obtiene el identificador del producto.
     * 
     * @return Identificador del producto.
     */
    public String getId_producto() {
        return id_producto;
    }

    /**
     * Establece el identificador del producto.
     * 
     * @param id_producto Identificador del producto.
     */
    public void setId_producto(String id_producto) {
        this.id_producto = id_producto;
    }

    /**
     * Obtiene la fecha de la transacción.
     * 
     * @return Fecha de la transacción.
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha de la transacción.
     * 
     * @param fecha Fecha de la transacción.
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene el documento del cliente.
     * 
     * @return Documento del cliente.
     */
    public String getDoc_cliente() {
        return doc_cliente;
    }

    /**
     * Establece el documento del cliente.
     * 
     * @param doc_cliente Documento del cliente.
     */
    public void setDoc_cliente(String doc_cliente) {
        this.doc_cliente = doc_cliente;
    }

    /**
     * Obtiene la cantidad de productos en la factura.
     * 
     * @return Cantidad de productos.
     */
    public String getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad de productos en la factura.
     * 
     * @param cantidad Cantidad de productos.
     */
    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el precio del producto.
     * 
     * @return Precio del producto.
     */
    public String getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del producto.
     * 
     * @param precio Precio del producto.
     */
    public void setPrecio(String precio) {
        this.precio = precio;
    }
}

