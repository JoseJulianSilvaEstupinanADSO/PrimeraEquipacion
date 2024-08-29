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

import com.modelo.Producto;
import com.modelo.ProductoDAO;

/**
 * Servlet controlador que maneja las solicitudes relacionadas con los productos.
 * Ofrece funcionalidades como listar tallas, registrar nuevos productos, 
 * listar productos existentes, modificar productos, y buscar productos específicos.
 * 
 * @version 1.0
 * @author Propietario
 */
@WebServlet(name = "Productos", urlPatterns = {"/Productos"})
public class Productos extends HttpServlet {

    // Instancia de ProductoDAO para interactuar con la base de datos
    private ProductoDAO modelo = new ProductoDAO();

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

        // Obtiene la acción desde los parámetros de la solicitud
        String accion = request.getParameter("action");

        // Selecciona la acción a realizar según el valor de 'accion'
        switch (accion) {
            case "ListarTallas":
                ListarTallas(request, response);
                break;
            case "RegistrarProducto":
                RegistrarProducto(request, response);
                break;
            case "ListarProductos":
                ListarProductos(request, response);
                break;
            case "ModificarProducto":
                ModificarProducto(request, response);
                break;
            case "BuscarProducto":
                BuscarProducto(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }

    /**
     * Lista las tallas de los productos disponibles en el sistema.
     * Este método consulta al modelo para obtener una lista de productos y sus tallas,
     * y luego construye una respuesta en formato JSON que contiene las tallas.
     * 
     * @param request  El objeto HttpServletRequest que contiene la solicitud del cliente.
     * @param response El objeto HttpServletResponse que contiene la respuesta del servlet.
     * @throws ServletException Si ocurre un error específico del servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    private void ListarTallas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Producto> productos = modelo.ListarTallas();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < productos.size(); i++) {
            Producto producto = productos.get(i);
            json.append(String.format("{\"talla\": \"%s\"}", producto.getTalla()));
            if (i < productos.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");
        response.getWriter().write(json.toString());
    }

    /**
     * Registra un nuevo producto en el sistema.
     * Este método extrae los parámetros de la solicitud, crea un objeto Producto
     * y lo envía al modelo para ser registrado en la base de datos.
     * 
     * @param request  El objeto HttpServletRequest que contiene la solicitud del cliente.
     * @param response El objeto HttpServletResponse que contiene la respuesta del servlet.
     * @throws ServletException Si ocurre un error específico del servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    private void RegistrarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String precio = request.getParameter("precio");
        String talla = request.getParameter("talla");
        String stock = request.getParameter("stock");
        String tela = request.getParameter("tela");
        String estado = request.getParameter("estado");

        Producto p = new Producto(null, talla, nombre, precio, stock, tela, estado);
        boolean resultado = modelo.AgregarProducto(p);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write("{\"resultado\": " + resultado + "}");
    }

    /**
     * Lista todos los productos registrados en el sistema.
     * Este método consulta al modelo para obtener una lista de productos,
     * y luego construye una respuesta en formato JSON que contiene los datos de todos los productos.
     * 
     * @param request  El objeto HttpServletRequest que contiene la solicitud del cliente.
     * @param response El objeto HttpServletResponse que contiene la respuesta del servlet.
     * @throws ServletException Si ocurre un error específico del servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    private void ListarProductos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Producto> productos = modelo.ListarProductos();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            json.append(String.format("{\"id_producto\": \"%s\", \"nombre\": \"%s\", \"precio\": \"%s\", \"talla\": \"%s\", \"stock\": \"%s\", \"tela\": \"%s\", \"estado\": \"%s\"}",
                    p.getId_producto(), p.getNombre(), p.getPrecio(), p.getTalla(), p.getStock(), p.getTela(), p.getEstado()));
            if (i < productos.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");
        response.getWriter().write(json.toString());
    }

    /**
     * Modifica un producto existente en el sistema.
     * Este método extrae los parámetros de la solicitud, crea un objeto Producto
     * y lo envía al modelo para actualizar la información del producto en la base de datos.
     * 
     * @param request  El objeto HttpServletRequest que contiene la solicitud del cliente.
     * @param response El objeto HttpServletResponse que contiene la respuesta del servlet.
     * @throws ServletException Si ocurre un error específico del servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    private void ModificarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id_producto = request.getParameter("id_producto");
        String nombre = request.getParameter("nombre");
        String stock = request.getParameter("stock");
        String precio = request.getParameter("precio");
        String talla = request.getParameter("talla");
        String estado = request.getParameter("estado");

        Producto p = new Producto(id_producto, talla, nombre, precio, stock, null, estado);
        boolean resultado = modelo.ModificarProducto(p);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String jsonResponse = "{\"resultado\": " + resultado + "}";
        response.getWriter().write(jsonResponse);
    }

    /**
     * Busca un producto específico en el sistema.
     * Este método recibe un ID de producto y talla como parámetros,
     * consulta al modelo para buscar el producto en la base de datos,
     * y construye una respuesta en formato JSON con los datos del producto si se encuentra.
     * 
     * @param request  El objeto HttpServletRequest que contiene la solicitud del cliente.
     * @param response El objeto HttpServletResponse que contiene la respuesta del servlet.
     * @throws ServletException Si ocurre un error específico del servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    private void BuscarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id_producto = request.getParameter("id_producto");
        String talla = request.getParameter("talla");

        Producto p = modelo.BuscarProducto(id_producto, talla);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (p != null) {
            String json = String.format("{\"id_usuario\": \"%s\", \"nombre\": \"%s\", \"precio\": \"%s\", \"estado\": \"%s\", \"stock\": \"%s\"}",
                    p.getId_producto(), p.getNombre(), p.getPrecio(), p.getEstado(), p.getStock());
            response.getWriter().write(json);
            System.out.println(p.getEstado());
        } else {
            response.getWriter().write("{\"error\": \"Producto No encontrado\"}");
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
