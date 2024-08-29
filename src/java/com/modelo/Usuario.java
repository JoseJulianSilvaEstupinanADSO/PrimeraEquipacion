/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.modelo;

/**
 * La clase Usuario representa un usuario en el sistema, con atributos que describen
 * la identidad y detalles del usuario. Esta clase proporciona un modelo para almacenar
 * y gestionar la información del usuario.
 * 
 * @author Julian
 */
public class Usuario {
    // Atributos privados para almacenar la información del usuario
    private String idUsuario;    // Identificador único del usuario
    private String usuario;      // Nombre de usuario para el acceso al sistema
    private String contraseña;   // Contraseña del usuario
    private String documento;    // Documento de identificación del usuario (por ejemplo, DNI, pasaporte)
    private String nombre;       // Nombre completo del usuario
    private String telefono;     // Número de teléfono del usuario
    private String direccion;    // Dirección del usuario
    private String email;        // Correo electrónico del usuario
    private String edad;         // Edad del usuario
    private String rol;          // Rol del usuario en el sistema (por ejemplo, administrador, cliente)

    /**
     * Constructor por defecto. Crea una instancia de Usuario sin inicializar los atributos.
     */
    public Usuario() {}

    /**
     * Constructor que inicializa todos los atributos del usuario.
     * 
     * @param idUsuario - Identificador único del usuario.
     * @param usuario - Nombre de usuario para el acceso al sistema.
     * @param contraseña - Contraseña del usuario.
     * @param documento - Documento de identificación del usuario.
     * @param nombre - Nombre completo del usuario.
     * @param telefono - Número de teléfono del usuario.
     * @param direccion - Dirección del usuario.
     * @param email - Correo electrónico del usuario.
     * @param edad - Edad del usuario.
     * @param rol - Rol del usuario en el sistema.
     */
    public Usuario(String idUsuario, String usuario, String contraseña, String documento, String nombre, String telefono, String direccion, String email, String edad, String rol) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.documento = documento;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
        this.edad = edad;
        this.rol = rol;
    }

    /**
     * Obtiene el identificador único del usuario.
     * 
     * @return String - Identificador único del usuario.
     */
    public String getIdUsuario() {
        return idUsuario;
    }

    /**
     * Establece el identificador único del usuario.
     * 
     * @param idUsuario - Identificador único del usuario.
     */
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Obtiene el nombre de usuario para el acceso al sistema.
     * 
     * @return String - Nombre de usuario.
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Establece el nombre de usuario para el acceso al sistema.
     * 
     * @param usuario - Nombre de usuario.
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene la contraseña del usuario.
     * 
     * @return String - Contraseña del usuario.
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     * Establece la contraseña del usuario.
     * 
     * @param contraseña - Contraseña del usuario.
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    /**
     * Obtiene el documento de identificación del usuario.
     * 
     * @return String - Documento de identificación.
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * Establece el documento de identificación del usuario.
     * 
     * @param documento - Documento de identificación.
     */
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    /**
     * Obtiene el nombre completo del usuario.
     * 
     * @return String - Nombre completo del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre completo del usuario.
     * 
     * @param nombre - Nombre completo del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el número de teléfono del usuario.
     * 
     * @return String - Número de teléfono del usuario.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el número de teléfono del usuario.
     * 
     * @param telefono - Número de teléfono del usuario.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene la dirección del usuario.
     * 
     * @return String - Dirección del usuario.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección del usuario.
     * 
     * @param direccion - Dirección del usuario.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     * 
     * @return String - Correo electrónico del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del usuario.
     * 
     * @param email - Correo electrónico del usuario.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene la edad del usuario.
     * 
     * @return String - Edad del usuario.
     */
    public String getEdad() {
        return edad;
    }

    /**
     * Establece la edad del usuario.
     * 
     * @param edad - Edad del usuario.
     */
    public void setEdad(String edad) {
        this.edad = edad;
    }

    /**
     * Obtiene el rol del usuario en el sistema.
     * 
     * @return String - Rol del usuario.
     */
    public String getRol() {
        return rol;
    }

    /**
     * Establece el rol del usuario en el sistema.
     * 
     * @param rol - Rol del usuario.
     */
    public void setRol(String rol) {
        this.rol = rol;
    }
}
