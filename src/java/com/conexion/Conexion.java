/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Julian
 */
public class Conexion {
    private Connection con; // Objeto Connection para manejar la conexión a la base de datos

    // Retorna la conexión actual
    public Connection getCon() {
        return con;
    }

    // Establece la conexión
    public void setCon(Connection con) {
        this.con = con;
    }
    
    // Método para conectar a la base de datos
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

    // Método para cerrar la conexión a la base de datos
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