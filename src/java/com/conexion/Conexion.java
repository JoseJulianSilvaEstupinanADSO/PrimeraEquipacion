/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * La clase Conexion gestiona la conexión a una base de datos MySQL.
 * Proporciona métodos para establecer y cerrar la conexión, así como para acceder y modificar el objeto de conexión.
 * 
 * @version 1.0
 * @author Julian
 */
public class Conexion {
    
    /**
     * Objeto Connection que representa la conexión activa a la base de datos.
     * Es utilizado por los métodos de la clase para realizar operaciones sobre la base de datos.
     */
    private Connection con;

    /**
     * Obtiene la conexión actual a la base de datos.
     * 
     * @return Connection que representa la conexión actual a la base de datos.
     */
    public Connection getCon() {
        return con;
    }

    /**
     * Establece un nuevo objeto Connection para la conexión a la base de datos.
     * 
     * @param con Objeto Connection que representa la nueva conexión a la base de datos.
     */
    public void setCon(Connection con) {
        this.con = con;
    }
    
    /**
     * Establece una conexión a la base de datos MySQL utilizando el DriverManager.
     * El método intenta cargar el driver JDBC de MySQL y conectarse a la base de datos especificada.
     * 
     * En caso de error durante la conexión, se captura la excepción y se imprime un mensaje de error.
     */
    public void conectar() {
        try {
            // Carga el driver de MySQL
            Class.forName("com.mysql.jdbc.Driver");
            // Establece la conexión a la base de datos
            con = DriverManager.getConnection("jdbc:mysql://localhost/db_proyecto", "root", "");
        } catch (Exception e) {
            // Maneja cualquier excepción que ocurra al conectar
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    /**
     * Cierra la conexión a la base de datos si está activa.
     * 
     * El método verifica si la conexión no está cerrada antes de intentar cerrarla.
     * En caso de error durante el cierre de la conexión, se captura la excepción y se imprime un mensaje de error.
     */
    public void desconectar() {
        try {
            // Verifica si la conexión no está cerrada antes de intentar cerrarla
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (Exception e) {
            // Maneja cualquier excepción que ocurra al cerrar la conexión
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}