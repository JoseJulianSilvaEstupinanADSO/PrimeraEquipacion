/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.controlador;

import com.modelo.Cliente;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import com.modelo.ClienteDAO;

/**
 * Servlet que gestiona las operaciones relacionadas con los clientes, 
 * como registrar, listar y buscar clientes.
 * Este servlet recibe peticiones HTTP y devuelve respuestas en formato JSON.
 * 
 * @version 1.0
 * @author Propietario
 */
@WebServlet(name = "Clientes", urlPatterns = {"/Clientes"})
public class Clientes extends HttpServlet {

    /**
     * Instancia del modelo ClienteDAO para realizar operaciones con la base de datos.
     */
    private ClienteDAO modelo = new ClienteDAO();
    
    /**
     * Procesa las solicitudes tanto para los métodos HTTP GET como POST.
     * 
     * @param request  El objeto HttpServletRequest que contiene la solicitud del cliente.
     * @param response El objeto HttpServletResponse que contiene la respuesta del servlet.
     * @throws ServletException Si ocurre un error específico del servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    
    response.setContentType("text/html;charset=UTF-8");
    
    // Obtiene la acción a realizar desde los parámetros de la solicitud
    String action = request.getParameter("action");
    
    if(action != null){
        switch (action) {
            // Si la acción es "RegistrarUsuario", llama al método ReguistrarUsuario
            case "RegistrarUsuario":
                ReguistrarUsuario(request, response);
                break;
                
            // Si la acción es "ListarUsuario", llama al método ListarUusuario
            case "ListarUsuario":
                ListarUusuario(request, response);
                break;
                
            // Si la acción es "BuscarCliente", llama al método BuscarCliente
            case "BuscarCliente":
                BuscarCliente(request, response);
                break;
                
            // Si la acción no coincide con ninguna de las anteriores, lanza un error
            default:
                throw new AssertionError();
        }
    }
}

    /**
     * Registra un nuevo usuario en el sistema.
     * Este método extrae los parámetros de la solicitud, crea un objeto Cliente 
     * y lo envía al modelo para ser registrado en la base de datos.
     * 
     * @param request  El objeto HttpServletRequest que contiene la solicitud del cliente.
     * @param response El objeto HttpServletResponse que contiene la respuesta del servlet.
     * @throws ServletException Si ocurre un error específico del servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    private void ReguistrarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtiene los parámetros de la solicitud
        String nombre = request.getParameter("nombre");
        String documento = request.getParameter("documento");
        String telefono = request.getParameter("telefono");
        
        // Crea un nuevo objeto Cliente con los datos obtenidos
        Cliente c = new Cliente(null, documento, nombre, telefono, "4");
        
        // Llama al modelo para registrar el cliente y obtiene el resultado
        boolean resultado = modelo.RegistrarCliente(c);
        
        // Configura la respuesta como JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        // Escribe el resultado en la respuesta
        response.getWriter().write("{\"resultado\": " + resultado + "}");
    }

    /**
     * Lista todos los usuarios en el sistema.
     * Este método consulta al modelo para obtener una lista de clientes,
     * y luego construye una respuesta en formato JSON que contiene los datos de todos los clientes.
     * 
     * @param request  El objeto HttpServletRequest que contiene la solicitud del cliente.
     * @param response El objeto HttpServletResponse que contiene la respuesta del servlet.
     * @throws ServletException Si ocurre un error específico del servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    private void ListarUusuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtiene la lista de clientes del modelo
        List<Cliente> clientes = modelo.ListarClientes();
        
        // Configura la respuesta como JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        // Construye un objeto JSON con la lista de clientes
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < clientes.size(); i++) {
            Cliente c = clientes.get(i);
            json.append(String.format("{\"id_cliente\": \"%s\", \"documento\": \"%s\", \"nombre\": \"%s\", \"telefono\": \"%s\"}",
                    c.getId_cliente(), c.getDocumento(), c.getNombre(), c.getTelefono()));
            if (i < clientes.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");
        
        // Escribe el JSON en la respuesta
        response.getWriter().write(json.toString());
    }

    /**
     * Busca un cliente específico en el sistema.
     * Este método recibe un número de documento como parámetro, 
     * y devuelve los datos del cliente asociado en formato JSON, si se encuentra.
     * 
     * @param request  El objeto HttpServletRequest que contiene la solicitud del cliente.
     * @param response El objeto HttpServletResponse que contiene la respuesta del servlet.
     * @throws ServletException Si ocurre un error específico del servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    private void BuscarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtiene el documento del cliente a buscar desde los parámetros de la solicitud
        String documento = request.getParameter("documento");
        
        // Llama al modelo para buscar el cliente y obtiene el resultado
        Cliente c = modelo.BuscarCliente(documento);
        
        // Configura la respuesta como JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        // Si se encuentra el cliente, construye un objeto JSON con sus datos
        if (c != null) {
            String json = String.format("{\"id_cliente\": \"%s\", \"documento\": \"%s\", \"nombre\": \"%s\", \"telefono\": \"%s\"}",
                    c.getId_cliente(), c.getDocumento(), c.getNombre(), c.getTelefono());
            response.getWriter().write(json);
        } else {
            // Si no se encuentra el cliente, escribe un mensaje de error en la respuesta
            response.getWriter().write("{\"error\": \"Cliente No encontrado\"}");
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
