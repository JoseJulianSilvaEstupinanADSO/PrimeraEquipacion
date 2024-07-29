/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.modelo;

/**
 *
 * @author Propietario
 */
public class Cliente {
    
    
    private String id_cliente;
    private String documento;
    private String nombre;
    private String telefono;
    private String rol;

    public Cliente() {
    }
   
    public Cliente(String id_cliente, String documento, String nombre, String telefono, String rol) {
        this.id_cliente = id_cliente;
        this.documento = documento;
        this.nombre = nombre;
        this.telefono = telefono;
        this.rol = rol;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
}
