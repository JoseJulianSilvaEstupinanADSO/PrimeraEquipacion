

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;
import com.modelo.Usuario;
import com.modelo.UsuarioDAO;

/**
 * Servlet que gestiona las operaciones relacionadas con los usuarios, tales como:
 * - Validación de inicio de sesión
 * - Registro de nuevos usuarios
 * - Modificación de datos de usuarios existentes
 * - Listado y búsqueda de usuarios
 * 
 * Este servlet está mapeado a la URL "/Usuarios" y acepta solicitudes tanto GET como POST.
 */
@WebServlet(name = "Usuarios", urlPatterns = {"/Usuarios"})
public class Usuarios extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Instancia de UsuarioDAO que se encarga de las operaciones de acceso a la base de datos
    private UsuarioDAO modelo = new UsuarioDAO();

    /**
     * Método que procesa todas las solicitudes HTTP, ya sean GET o POST.
     * Según el parámetro "action" en la solicitud, se delega la acción correspondiente
     * a un método especializado.
     *
     * @param request Objeto HttpServletRequest que contiene la solicitud del cliente
     * @param response Objeto HttpServletResponse que se utiliza para enviar la respuesta al cliente
     * @throws ServletException Si ocurre un error específico del servlet
     * @throws IOException Si ocurre un error de entrada/salida
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        // Establece el tipo de contenido de la respuesta como HTML con codificación UTF-8
        response.setContentType("text/html;charset=UTF-8");

        // Obtiene el parámetro "action" de la solicitud, que indica la acción a realizar
        String action = request.getParameter("action");

        // Si la acción no es nula, se procede a ejecutar la acción correspondiente
        if (action != null) {
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
                    // En caso de que la acción no esté definida, se envía un mensaje de error
                    response.getWriter().write("Acción no reconocida");
                    break;
            }
        }
    }

    /**
     * Método para validar el inicio de sesión de un usuario.
     * Verifica las credenciales proporcionadas contra la base de datos.
     *
     * @param request HttpServletRequest que contiene los parámetros "usuario" y "contrasena"
     * @param response HttpServletResponse que devuelve el resultado en formato JSON
     * @throws IOException Si ocurre un error de entrada/salida
     */
    private void validarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Obtiene los valores de "usuario" y "contrasena" de la solicitud
        String usuario = request.getParameter("usuario");
        String contrasena = request.getParameter("contrasena");

        // Valida las credenciales a través del modelo
        Usuario usuarioValido = modelo.validarUsuario(usuario, contrasena);
        
        // Establece el tipo de contenido de la respuesta como JSON con codificación UTF-8
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (usuarioValido != null) {
            // Si las credenciales son correctas, se construye un JSON con los datos del usuario
            String json = String.format("{\"idUsuario\": \"%s\", \"usuario\": \"%s\", \"nombre\": \"%s\", \"rol\": \"%s\"}",
                usuarioValido.getIdUsuario(), usuarioValido.getUsuario(), usuarioValido.getNombre(), usuarioValido.getRol());
            response.getWriter().write(json);
        } else {
            // Si las credenciales son incorrectas, se envía un mensaje de error en formato JSON
            response.getWriter().write("{\"error\": \"Usuario o contraseña incorrectos\"}");
        }
    }

    /**
     * Método para registrar un nuevo usuario en la base de datos.
     * Recibe los datos del nuevo usuario desde la solicitud HTTP.
     *
     * @param request HttpServletRequest que contiene los datos del nuevo usuario
     * @param response HttpServletResponse que devuelve el resultado de la operación en formato JSON
     * @throws IOException Si ocurre un error de entrada/salida
     */
    private void RegistrarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Obtiene los parámetros de la solicitud para crear un nuevo usuario
        String usuario = request.getParameter("usuario");
        String contraseña = request.getParameter("contrasena");
        String documento = request.getParameter("documento");
        String nombre = request.getParameter("nombre");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");
        String email = request.getParameter("email");
        String edad = request.getParameter("edad");

        // Crea un nuevo objeto Usuario con los datos obtenidos
        Usuario u = new Usuario(null, usuario, contraseña, documento, nombre, telefono, direccion, email, edad, null);

        // Llama al método del modelo para insertar el nuevo usuario en la base de datos
        boolean resultado = modelo.insertarUsuario(u);
        
        // Establece el tipo de contenido de la respuesta como JSON con codificación UTF-8
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Escribe el resultado de la operación en la respuesta
        response.getWriter().write("{\"resultado\": " + resultado + "}");
    }

    /**
     * Método para listar todos los usuarios almacenados en la base de datos.
     * Devuelve un JSON con la lista de usuarios.
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse que devuelve la lista de usuarios en formato JSON
     * @throws IOException Si ocurre un error de entrada/salida
     */
    private void ListarUsuarios(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Obtiene la lista de usuarios desde la base de datos utilizando el modelo
        List<Usuario> usuarios = modelo.ListarUsuarios();
        
        // Establece el tipo de contenido de la respuesta como JSON con codificación UTF-8
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Construye un array JSON a partir de la lista de usuarios
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
        
        // Escribe el array JSON en la respuesta
        response.getWriter().write(json.toString());
    }

    /**
     * Método para modificar un usuario existente en la base de datos.
     * Los nuevos datos del usuario se envían a través de la solicitud HTTP.
     *
     * @param request HttpServletRequest que contiene los datos actualizados del usuario
     * @param response HttpServletResponse que devuelve el resultado de la operación en formato JSON
     * @throws IOException Si ocurre un error de entrada/salida
     */
    private void ModificarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Obtiene los parámetros de la solicitud con los datos del usuario a modificar
        String idUsuario = request.getParameter("id");
        String rol = request.getParameter("id_rol");
        String nombre = request.getParameter("nombre");
        String documento = request.getParameter("documento");
        String usuario = request.getParameter("usuario");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");

        // Crea un objeto Usuario con los nuevos datos
        Usuario u = new Usuario(idUsuario, usuario, contrasena, documento, nombre, telefono, direccion, correo, null, rol);

        // Llama al método del modelo para modificar los datos del usuario en la base de datos
        boolean resultado = modelo.ModificarUsuario(u);
        
        // Establece el tipo de contenido de la respuesta como JSON con codificación UTF-8
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Escribe el resultado de la operación en la respuesta
        String jsonResponse = "{\"resultado\": " + resultado + "}";
        response.getWriter().write(jsonResponse);
    }

    /**
     * Método para buscar un usuario en la base de datos por su ID.
     * Devuelve un JSON con los datos del usuario encontrado.
     *
     * @param request HttpServletRequest que contiene el parámetro "id" del usuario a buscar
     * @param response HttpServletResponse que devuelve los datos del usuario en formato JSON
     * @throws ServletException Si ocurre un error específico del servlet
     * @throws IOException Si ocurre un error de entrada/salida
     */
    private void BuscarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtiene el parámetro "id" de la solicitud para buscar al usuario
        String idUsuario = request.getParameter("id");

        // Llama al método del modelo para buscar el usuario en la base de datos
        Usuario u = modelo.BuscarUsuario(idUsuario);
        
        // Establece el tipo de contenido de la respuesta como JSON con codificación UTF-8
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (u != null) {
            // Si se encuentra el usuario, se construye un JSON con sus datos
            String json = String.format("{\"idUsuario\": \"%s\", \"usuario\": \"%s\", \"nombre\": \"%s\", \"rol\": \"%s\", \"telefono\": \"%s\", \"direccion\": \"%s\", \"correo\": \"%s\", \"documento\": \"%s\", \"contrasena\": \"%s\"}",
                u.getIdUsuario(), u.getUsuario(), u.getNombre(), u.getRol(), u.getTelefono(), u.getDireccion(), u.getEmail(), u.getDocumento(), u.getContraseña());
            response.getWriter().write(json);
        } else {
            // Si no se encuentra el usuario, se envía un mensaje de error en formato JSON
            response.getWriter().write("{\"error\": \"Usuario No encontrado\"}");
        }
    }

    /**
     * Método para modificar la contraseña de un usuario.
     * Verifica la contraseña actual y la reemplaza por una nueva.
     *
     * @param request HttpServletRequest que contiene los parámetros "id_usuario", "contrasena" y "nueva"
     * @param response HttpServletResponse que devuelve el resultado de la operación en formato JSON
     * @throws ServletException Si ocurre un error específico del servlet
     * @throws IOException Si ocurre un error de entrada/salida
     */
    private void ModificarContraseña(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtiene los parámetros de la solicitud para verificar y cambiar la contraseña
        String id_usuario = request.getParameter("id_usuario");
        String contrasena = request.getParameter("contrasena");
        String nueva = request.getParameter("nueva");

        // Busca al usuario en la base de datos utilizando su ID
        Usuario u = modelo.BuscarUsuario(id_usuario);

        // Obtiene la contraseña actual del usuario desde la base de datos
        String con = u.getContraseña();

        // Variable para almacenar el resultado de la operación
        boolean respuesta = false;

        // Verifica si la contraseña proporcionada coincide con la almacenada en la base de datos
        if (BCrypt.checkpw(contrasena, con)) {
            // Si coincide, modifica la contraseña del usuario en la base de datos
            respuesta = modelo.ModificarContraseña(nueva, id_usuario);
        }

        // Establece el tipo de contenido de la respuesta como JSON con codificación UTF-8
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Escribe el resultado de la operación en la respuesta
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
