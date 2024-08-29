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
import org.mindrot.jbcrypt.BCrypt;

/**
 * La clase UsuarioDAO proporciona métodos para realizar operaciones CRUD (Crear, Leer, 
 * Actualizar y Eliminar) sobre la entidad Usuario en la base de datos. Esta clase extiende 
 * de la clase Conexion, que maneja la conexión con la base de datos.
 * 
 * @author Julian
 */
public class UsuarioDAO extends Conexion {

    /**
     * Valida un usuario mediante nombre de usuario y contraseña.
     * 
     * @param usuario - Nombre de usuario a validar.
     * @param contrasena - Contraseña del usuario a validar.
     * @return Usuario - El usuario válido si las credenciales son correctas, de lo contrario, null.
     */
    public Usuario validarUsuario(String usuario, String contrasena) {
        Usuario usuarioValido = null;

        try {
            // Conecta a la base de datos
            this.conectar();

            // Consulta SQL para seleccionar el usuario basado en el nombre de usuario
            String sql = "SELECT * FROM usuario WHERE usuario = ?";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1, usuario);
            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                String encriptada = rs.getString("contrasena");

                // Verifica la contraseña utilizando BCrypt
                if (BCrypt.checkpw(contrasena, encriptada)) {
                    usuarioValido = new Usuario(
                        rs.getString("id_usuario"),
                        rs.getString("usuario"),
                        rs.getString("contrasena"),
                        rs.getString("documento"),
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("direccion"),
                        rs.getString("email"),
                        rs.getString("edad"),
                        rs.getString("id_rol")
                    );
                }
            }

        } catch (SQLException e) {
            // Maneja cualquier excepción SQL
            e.printStackTrace();
        } finally {
            // Desconecta de la base de datos
            this.desconectar();
        }
        return usuarioValido;
    }

    /**
     * Inserta un nuevo usuario en la base de datos.
     * 
     * @param u - Objeto Usuario que contiene la información del nuevo usuario.
     * @return boolean - true si la inserción fue exitosa, de lo contrario, false.
     */
    public boolean insertarUsuario(Usuario u) {
        try {
            // Conecta a la base de datos
            this.conectar();

            // Consulta SQL para insertar un nuevo usuario
            String sql = "INSERT INTO usuario(usuario, contrasena, documento, nombre, telefono, direccion, email, edad) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            String password = BCrypt.hashpw(u.getContraseña(), BCrypt.gensalt());

            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1, u.getUsuario());
            pre.setString(2, password);
            pre.setString(3, u.getDocumento());
            pre.setString(4, u.getNombre());
            pre.setString(5, u.getTelefono());
            pre.setString(6, u.getDireccion());
            pre.setString(7, u.getEmail());
            pre.setInt(8, Integer.parseInt(u.getEdad()));

            // Ejecuta la inserción y retorna true si fue exitosa
            return pre.executeUpdate() > 0;

        } catch (SQLException e) {
            // Maneja cualquier excepción SQL y retorna false
            e.printStackTrace();
            return false;
        } finally {
            // Desconecta de la base de datos
            this.desconectar();
        }
    }

    /**
     * Lista todos los usuarios, excluyendo aquellos con rol 4.
     * 
     * @return List<Usuario> - Lista de usuarios.
     */
    public List<Usuario> ListarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            // Conecta a la base de datos
            this.conectar();

            // Consulta SQL para seleccionar todos los usuarios excluyendo aquellos con rol 4
            String sql = "SELECT u.id_usuario, u.usuario, u.contrasena, u.documento, u.nombre, u.telefono, u.direccion, u.email, r.nombre as rol FROM usuario u JOIN rol r ON u.id_rol = r.id_rol WHERE u.id_rol != 4 OR u.id_rol IS NULL";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            ResultSet rs = pre.executeQuery();

            // Procesa los resultados y agrega los usuarios a la lista
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getString("id_usuario"));
                u.setUsuario(rs.getString("usuario"));
                u.setContraseña(rs.getString("contrasena"));
                u.setDocumento(rs.getString("documento"));
                u.setNombre(rs.getString("nombre"));
                u.setTelefono(rs.getString("telefono"));
                u.setDireccion(rs.getString("direccion"));
                u.setEmail(rs.getString("email"));
                u.setRol(rs.getString("rol"));

                usuarios.add(u);
            }

        } catch (SQLException e) {
            // Maneja cualquier excepción SQL
            e.printStackTrace();
        } finally {
            // Desconecta de la base de datos
            this.desconectar();
        }

        return usuarios;
    }

    /**
     * Modifica la información de un usuario existente en la base de datos.
     * 
     * @param u - Objeto Usuario que contiene la información actualizada del usuario.
     * @return boolean - true si la actualización fue exitosa, de lo contrario, false.
     */
    public boolean ModificarUsuario(Usuario u) {
        try {
            // Conecta a la base de datos
            this.conectar();

            // Consulta SQL para actualizar la información del usuario
            String sql = "UPDATE usuario SET usuario = ?, id_rol = ?, nombre = ?, telefono = ?, direccion = ?, email = ? WHERE id_usuario = ?";
            PreparedStatement pre = this.getCon().prepareStatement(sql);

            pre.setString(1, u.getUsuario());
            pre.setInt(2, Integer.parseInt(u.getRol()));
            pre.setString(3, u.getNombre());
            pre.setString(4, u.getTelefono());
            pre.setString(5, u.getDireccion());
            pre.setString(6, u.getEmail());
            pre.setInt(7, Integer.parseInt(u.getIdUsuario()));

            // Ejecuta la actualización y retorna true si fue exitosa
            return pre.executeUpdate() > 0;

        } catch (SQLException e) {
            // Maneja cualquier excepción SQL y retorna false
            e.printStackTrace();
            return false;
        } finally {
            // Desconecta de la base de datos
            this.desconectar();
        }
    }

    /**
     * Busca un usuario específico por su ID.
     * 
     * @param id - ID del usuario a buscar.
     * @return Usuario - El usuario correspondiente al ID, o null si no se encuentra.
     */
    public Usuario BuscarUsuario(String id) {
        Usuario u = null;
        try {
            // Conecta a la base de datos
            this.conectar();

            // Consulta SQL para seleccionar un usuario por ID
            String sql = "SELECT * FROM usuario WHERE id_usuario = ?";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, Integer.parseInt(id));
            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                u = new Usuario(
                    rs.getString("id_usuario"),
                    rs.getString("usuario"),
                    rs.getString("contrasena"),
                    rs.getString("documento"),
                    rs.getString("nombre"),
                    rs.getString("telefono"),
                    rs.getString("direccion"),
                    rs.getString("email"),
                    rs.getString("edad"),
                    rs.getString("id_rol")
                );
            }

        } catch (SQLException e) {
            // Maneja cualquier excepción SQL
            e.printStackTrace();
        } finally {
            // Desconecta de la base de datos
            this.desconectar();
        }

        return u;
    }

    /**
     * Modifica la contraseña de un usuario en la base de datos.
     * 
     * @param contrasena - Nueva contraseña del usuario.
     * @param id_usuario - ID del usuario cuya contraseña se debe modificar.
     * @return boolean - true si la actualización fue exitosa, de lo contrario, false.
     */
    public boolean ModificarContraseña(String contrasena, String id_usuario) {
        try {
            // Conecta a la base de datos
            this.conectar();
            String password = BCrypt.hashpw(contrasena, BCrypt.gensalt());

            // Consulta SQL para actualizar la contraseña del usuario
            String sql = "UPDATE usuario SET contrasena = ? WHERE id_usuario = ?";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1, password);
            pre.setInt(2, Integer.parseInt(id_usuario));

            // Ejecuta la actualización y retorna true si fue exitosa
            return pre.executeUpdate() > 0;

        } catch (SQLException e) {
            // Maneja cualquier excepción SQL y retorna false
            e.printStackTrace();
            return false;
        } finally {
            // Desconecta de la base de datos
            this.desconectar();
        }
    }
}
