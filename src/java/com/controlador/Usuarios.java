

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;
import com.modelo.Usuario;
import com.modelo.UsuarioDAO;
import java.rmi.ServerError;

/**
 *
 * @author Julian
 */
@WebServlet(name = "Usuarios", urlPatterns = {"/Usuarios"})
public class Usuarios extends HttpServlet {
    
    // Clase controladora para manejar las solicitudes relacionadas con los usuarios
private static final long serialVersionUID = 1L;

// Instancia de UsuarioDAO para interactuar con la base de datos
private UsuarioDAO modelo = new UsuarioDAO();

/**
 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
 *
 * @param request servlet request
 * @param response servlet response
 * @throws ServletException if a servlet-specific error occurs
 * @throws IOException if an I/O error occurs
 */
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    
    // Obtiene la acción desde los parámetros de la solicitud
    String action = request.getParameter("action");
    
    // Verifica si la acción no es nula y selecciona la acción a realizar según el valor de 'action'
    if(action != null){
        switch (action) {
            case "ValidarLogin":
                validarUsuario(request, response);
                break;
            case "RegistrarUsuario":
                RegistrarUsuario(request, response);
                break;
            case "ListaUsuarios":
                ListarUsuarios(request, response);
                break;
            case "ModificarUsuario":
                ModificarUsuario(request, response);
                break;
            case "BuscarUsuario":
                BuscarUsuario(request, response);
                break;
            case "ModificarContraseña":
                ModificarContraseña(request, response);
                break;
            default:
                response.getWriter().write("Acción no reconocida");
                break;
        }
    }
}

// Método para validar el inicio de sesión del usuario
private void validarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Obtiene los parámetros de la solicitud
    String usuario = request.getParameter("usuario");
    String contrasena = request.getParameter("contrasena");
    
    // Llama al modelo para validar el usuario y obtiene el resultado
    Usuario usuarioValido = modelo.validarUsuario(usuario, contrasena);
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    
    if (usuarioValido != null) {
        // Construye un JSON con los datos del usuario validado
        String json = String.format("{\"idUsuario\": \"%s\", \"usuario\": \"%s\", \"nombre\": \"%s\", \"rol\": \"%s\"}",
            usuarioValido.getIdUsuario(), usuarioValido.getUsuario(), usuarioValido.getNombre(), usuarioValido.getRol());
        response.getWriter().write(json);
    } else {
        // Escribe un mensaje de error en la respuesta si la validación falla
        response.getWriter().write("{\"error\": \"Usuario o contraseña incorrectos\"}");
    }
}

// Método para registrar un nuevo usuario
private void RegistrarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Obtiene los parámetros de la solicitud
    String usuario = request.getParameter("usuario");
    String contraseña = request.getParameter("contrasena");
    String documento = request.getParameter("documento");
    String nombre = request.getParameter("nombre");
    String telefono = request.getParameter("telefono");
    String direccion = request.getParameter("direccion");
    String email = request.getParameter("email");
    String edad = request.getParameter("edad");
    
    // Crea un objeto Usuario con los datos obtenidos
    Usuario u = new Usuario(null, usuario, contraseña, documento, nombre, telefono, direccion, email, edad, null);
    
    // Llama al modelo para agregar el usuario y obtiene el resultado
    boolean resultado = modelo.insertarUsuario(u);
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    
    // Escribe el resultado en la respuesta
    response.getWriter().write("{\"resultado\": " + resultado + "}");
}

// Método para listar todos los usuarios
private void ListarUsuarios(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Obtiene la lista de usuarios desde el modelo
    List<Usuario> usuarios = modelo.ListarUsuarios();
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    
    // Construye un array JSON con los usuarios
    StringBuilder json = new StringBuilder("[");
    for (int i = 0; i < usuarios.size(); i++) {
        Usuario usuario = usuarios.get(i);
        json.append(String.format("{\"idUsuario\": \"%s\", \"usuario\": \"%s\", \"nombre\": \"%s\", \"email\": \"%s\", \"rol\": \"%s\", \"documento\": \"%s\", \"telefono\": \"%s\", \"direccion\": \"%s\", \"email\": \"%s\"}",
            usuario.getIdUsuario(), usuario.getUsuario(), usuario.getNombre(), usuario.getEmail(), usuario.getRol(), usuario.getDocumento(), usuario.getTelefono(), usuario.getDireccion(), usuario.getEmail()));
        if (i < usuarios.size() - 1) {
            json.append(",");
        }
    }
    json.append("]");
    
    // Escribe el JSON en la respuesta
    response.getWriter().write(json.toString());
}

// Método para modificar un usuario existente
private void ModificarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Obtiene los parámetros de la solicitud
    String idUsuario = request.getParameter("id");
    String rol = request.getParameter("id_rol");
    String nombre = request.getParameter("nombre");
    String documento = request.getParameter("documento");
    String usuario = request.getParameter("usuario");
    String telefono = request.getParameter("telefono");
    String direccion = request.getParameter("direccion");
    String correo = request.getParameter("correo");
    String contrasena = request.getParameter("contrasena");
    
    // Crea un objeto Usuario con los datos obtenidos
    Usuario u = new Usuario(idUsuario, usuario, contrasena, documento, nombre, telefono, direccion, correo, null, rol);
    
    // Llama al modelo para modificar el usuario y obtiene el resultado
    boolean resultado = modelo.ModificarUsuario(u);
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    
    // Escribe el resultado en la respuesta
    String jsonResponse = "{\"resultado\": " + resultado + "}";
    response.getWriter().write(jsonResponse);
}

// Método para buscar un usuario por ID
private void BuscarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Obtiene el parámetro de la solicitud
    String idUsuario = request.getParameter("id");
    
    // Llama al modelo para buscar el usuario y obtiene el resultado
    Usuario u = modelo.BuscarUsuario(idUsuario);
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    
    if (u != null) {
        // Construye un JSON con los datos del usuario encontrado
        String json = String.format("{\"idUsuario\": \"%s\", \"usuario\": \"%s\", \"nombre\": \"%s\", \"rol\": \"%s\", \"telefono\": \"%s\", \"direccion\": \"%s\", \"correo\": \"%s\", \"documento\": \"%s\", \"contrasena\": \"%s\"}",
            u.getIdUsuario(), u.getUsuario(), u.getNombre(), u.getRol(), u.getTelefono(), u.getDireccion(), u.getEmail(), u.getDocumento(), u.getContraseña());
        response.getWriter().write(json);
    } else {
        // Escribe un mensaje de error en la respuesta si no se encuentra el usuario
        response.getWriter().write("{\"error\": \"Usuario No encontrado\"}");
    }
}

// Método para modificar la contraseña de un usuario
private void ModificarContraseña(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Obtiene los parámetros de la solicitud
    String id_usuario = request.getParameter("id_usuario");
    String contrasena = request.getParameter("contrasena");
    String nueva = request.getParameter("nueva");
    
    // Llama al modelo para buscar el usuario por ID
    Usuario u = modelo.BuscarUsuario(id_usuario);
    
    // Obtiene la contraseña actual del usuario
    String con = u.getContraseña();
    
    // Variable para almacenar la respuesta
    boolean respuesta = false;
    
    // Verifica si la contraseña actual coincide con la proporcionada
    if (BCrypt.checkpw(contrasena, con)){  
        // Llama al modelo para modificar la contraseña y obtiene el resultado
        respuesta = modelo.ModificarContraseña(nueva, id_usuario);
    }
    
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    
    // Escribe el resultado en la respuesta
    String jsonResponse = "{\"resultado\": " + respuesta + "}";
    response.getWriter().write(jsonResponse);
}

    
    
    
    
    
    
    
    
    
    
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
