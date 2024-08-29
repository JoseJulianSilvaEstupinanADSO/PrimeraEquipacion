/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.modelo;

/**
 * La clase Producto representa un producto en el sistema. Incluye atributos como ID, talla, nombre, precio,
 * stock, tela y estado del producto, junto con sus métodos de acceso y modificación.
 * 
 * @author Propietario
 */
public class Producto {
    
    private String id_producto;
    private String talla;
    private String nombre;
    private String precio;
    private String stock;
    private String tela;
    private String estado;

    /**
     * Constructor por defecto de la clase Producto.
     */
    public Producto() {
    }

    /**
     * Constructor de la clase Producto con todos los atributos.
     * 
     * @param id_producto ID del producto.
     * @param talla Talla del producto.
     * @param nombre Nombre del producto.
     * @param precio Precio del producto.
     * @param stock Stock disponible del producto.
     * @param tela Tela del producto.
     * @param estado Estado del producto.
     */
    public Producto(String id_producto, String talla, String nombre, String precio, String stock, String tela, String estado) {
        this.id_producto = id_producto;
        this.talla = talla;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.tela = tela;
        this.estado = estado;
    }
    
    /**
     * Obtiene el estado del producto.
     * 
     * @return Estado del producto.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado del producto.
     * 
     * @param estado Estado del producto.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el ID del producto.
     * 
     * @return ID del producto.
     */
    public String getId_producto() {
        return id_producto;
    }

    /**
     * Establece el ID del producto.
     * 
     * @param id_producto ID del producto.
     */
    public void setId_producto(String id_producto) {
        this.id_producto = id_producto;
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
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del producto.
     * 
     * @param nombre Nombre del producto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    /**
     * Obtiene el stock disponible del producto.
     * 
     * @return Stock del producto.
     */
    public String getStock() {
        return stock;
    }

    /**
     * Establece el stock disponible del producto.
     * 
     * @param stock Stock del producto.
     */
    public void setStock(String stock) {
        this.stock = stock;
    }

    /**
     * Obtiene la tela del producto.
     * 
     * @return Tela del producto.
     */
    public String getTela() {
        return tela;
    }

    /**
     * Establece la tela del producto.
     * 
     * @param tela Tela del producto.
     */
    public void setTela(String tela) {
        this.tela = tela;
    }
}
