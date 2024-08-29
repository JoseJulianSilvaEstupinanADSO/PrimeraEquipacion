/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.modelo;

/**
 * La clase Cliente representa a un cliente dentro del sistema.
 * Esta clase contiene información básica del cliente como su ID, documento,
 * nombre, teléfono y rol.
 * 
 * Se proporcionan métodos getter y setter para acceder y modificar los atributos
 * del cliente.
 * 
 * Esta clase se utiliza para gestionar los datos de los clientes en la aplicación.
 * 
 * @author Propietario
 */
public class Cliente {

    // Identificador único del cliente
    private String id_cliente;

    // Documento de identificación del cliente
    private String documento;

    // Nombre completo del cliente
    private String nombre;

    // Número de teléfono del cliente
    private String telefono;

    // Rol del cliente en el sistema (puede ser cliente regular, VIP, etc.)
    private String rol;

    /**
     * Constructor por defecto de la clase Cliente.
     * Crea una instancia vacía de Cliente.
     */
    public Cliente() {
    }

    /**
     * Constructor con parámetros de la clase Cliente.
     * Permite la creación de un Cliente con todos sus atributos.
     * 
     * @param id_cliente Identificador único del cliente
     * @param documento Documento de identificación del cliente
     * @param nombre Nombre completo del cliente
     * @param telefono Número de teléfono del cliente
     * @param rol Rol del cliente en el sistema
     */
    public Cliente(String id_cliente, String documento, String nombre, String telefono, String rol) {
        this.id_cliente = id_cliente;
        this.documento = documento;
        this.nombre = nombre;
        this.telefono = telefono;
        this.rol = rol;
    }

    /**
     * Obtiene el ID del cliente.
     * 
     * @return ID del cliente
     */
    public String getId_cliente() {
        return id_cliente;
    }

    /**
     * Establece el ID del cliente.
     * 
     * @param id_cliente ID del cliente
     */
    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    /**
     * Obtiene el documento de identificación del cliente.
     * 
     * @return Documento del cliente
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * Establece el documento de identificación del cliente.
     * 
     * @param documento Documento del cliente
     */
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    /**
     * Obtiene el nombre completo del cliente.
     * 
     * @return Nombre del cliente
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre completo del cliente.
     * 
     * @param nombre Nombre del cliente
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el número de teléfono del cliente.
     * 
     * @return Teléfono del cliente
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el número de teléfono del cliente.
     * 
     * @param telefono Teléfono del cliente
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene el rol del cliente en el sistema.
     * 
     * @return Rol del cliente
     */
    public String getRol() {
        return rol;
    }

    /**
     * Establece el rol del cliente en el sistema.
     * 
     * @param rol Rol del cliente
     */
    public void setRol(String rol) {
        this.rol = rol;
    }
}