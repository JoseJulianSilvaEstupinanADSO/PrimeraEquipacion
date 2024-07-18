

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

import com.modelo.Usuario;
import com.modelo.UsuarioDAO;

/**
 *
 * @author Julian
 */
@WebServlet(name = "Usuarios", urlPatterns = {"/Usuarios"})
public class Usuarios extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private UsuarioDAO modelo = new UsuarioDAO();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String action = request.getParameter("action");
        
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
                    
                    ModificarUsuario(request,response);
                    
                    break;
                    
                case "BuscarUsuario":
                    
                    BuscarUsuario(request,response);
                    
                    break;
                    
                    
                default:
                    response.getWriter().write("Acción no reconocida");
                    break;
            }
        }
        
    }
    
    private void validarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String usuario = request.getParameter("usuario");
        String contrasena = request.getParameter("contrasena");
        Usuario usuarioValido = modelo.validarUsuario(usuario, contrasena);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        if (usuarioValido != null) {
            String json = String.format("{\"idUsuario\": \"%s\", \"usuario\": \"%s\", \"nombre\": \"%s\", \"rol\": \"%s\"}",
                usuarioValido.getIdUsuario(), usuarioValido.getUsuario(), usuarioValido.getNombre(), usuarioValido.getRol());
            response.getWriter().write(json);
        } else {
            response.getWriter().write("{\"error\": \"Usuario o contraseña incorrectos\"}");
        }
    }
    
    private void RegistrarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException{
        
        String usuario = request.getParameter("usuario") ;
        String contraseña = request.getParameter("contrasena");
        String documento = request.getParameter("documento");
        String nombre = request.getParameter("nombre");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");
        String email = request.getParameter("email");
        String edad = request.getParameter("edad");
       
        Usuario u = new Usuario(null, usuario, contraseña, documento, nombre, telefono, direccion, email, edad, null);
        
        boolean resultado = modelo.insertarUsuario(u);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"resultado\": " + resultado + "}");
    }
    
    private void ListarUsuarios(HttpServletRequest request, HttpServletResponse response) throws IOException{
        
        List<Usuario> usuarios = modelo.ListarUsuarios();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
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
        response.getWriter().write(json.toString());
        
    }
    
    private void ModificarUsuario(HttpServletRequest request, HttpServletResponse response) throws  IOException{
        
        String idUsuario = request.getParameter("id");
        String rol = request.getParameter("id_rol");
        String nombre = request.getParameter("nombre");
        String documento = request.getParameter("documento");
        String usuario = request.getParameter("usuario");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");
        
        Usuario u = new Usuario(idUsuario,usuario,contrasena,documento,nombre,telefono,direccion,correo,null, rol);
        
        boolean resultado = modelo.ModificarUsuario(u);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String jsonResponse = "{\"resultado\": " + resultado + "}";

        
        response.getWriter().write(jsonResponse);
        
        
    }
    
    
    private void BuscarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException{
        
        String idUsuario = request.getParameter("id");
        Usuario u = modelo.BuscarUsuario(idUsuario);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        if (u != null) {
            String json = String.format("{\"idUsuario\": \"%s\", \"usuario\": \"%s\", \"nombre\": \"%s\", \"rol\": \"%s\", \"telefono\": \"%s\", \"direccion\": \"%s\", \"correo\": \"%s\", \"documento\": \"%s\", \"contrasena\": \"%s\"}",
                u.getIdUsuario(), u.getUsuario(), u.getNombre(), u.getRol(), u.getTelefono(), u.getDireccion(), u.getEmail(), u.getDocumento(), u.getContraseña());
            response.getWriter().write(json);
        }
        else{
            response.getWriter().write("{\"error\": \"Usuario No encontrado\"}");
        }
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
