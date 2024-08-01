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

import com.modelo.Producto;
import com.modelo.ProductoDAO;

/**
 *
 * @author Propietario
 */
@WebServlet(name = "Productos", urlPatterns = {"/Productos"})
public class Productos extends HttpServlet {

    // Clase controladora para manejar las solicitudes relacionadas con los productos
 private static final long serialVersionUID = 1L;

 // Instancia de ProductoDAO para interactuar con la base de datos
 private ProductoDAO modelo = new ProductoDAO();

 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
     response.setContentType("text/html;charset=UTF-8");

     // Obtiene la acción desde los parámetros de la solicitud
     String accion = request.getParameter("action");

     // Selecciona la acción a realizar según el valor de 'accion'
     switch (accion) {
         case "ListarTallas":
             // Llama al método ListarTallas
             ListarTallas(request, response);
             break;

         case "RegistrarProducto":
             // Llama al método RegistrarProducto
             RegistrarProducto(request, response);
             break;

         case "ListarProductos":
             // Llama al método ListarProductos
             ListarProductos(request, response);
             break;

         case "ModificarProducto":
             // Llama al método ModificarProducto
             ModificarProducto(request, response);
             break;

         case "BuscarProducto":
             // Llama al método BuscarProducto
             BuscarProducto(request, response);
             break;

         default:
             // Lanza una excepción si la acción no coincide con ninguna conocida
             throw new AssertionError();
     }
 }

//  Método para listar las tallas de los productos
    private void ListarTallas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtiene la lista de tallas desde el modelo
        List<Producto> productos = modelo.ListarTallas();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Construye un array JSON con las tallas
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < productos.size(); i++) {
            Producto producto = productos.get(i);
            json.append(String.format("{\"talla\": \"%s\"}", producto.getTalla()));
            if (i < productos.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");

        // Escribe el JSON en la respuesta
        response.getWriter().write(json.toString());
    }

    // Método para registrar un nuevo producto
    private void RegistrarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtiene los parámetros de la solicitud
        String nombre = request.getParameter("nombre");
        String precio = request.getParameter("precio");
        String talla = request.getParameter("talla");
        String stock = request.getParameter("stock");
        String tela = request.getParameter("tela");

        // Crea un objeto Producto con los datos obtenidos
        Producto p = new Producto(null, talla, nombre, precio, stock, tela, null);

        // Llama al modelo para agregar el producto y obtiene el resultado
        boolean resultado = modelo.AgregarProducto(p);

        // Configura la respuesta como JSON y establece la codificación
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Escribe el resultado en la respuesta
        response.getWriter().write("{\"resultado\": " + resultado + "}");
    }

    // Método para listar todos los productos
    private void ListarProductos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtiene la lista de productos desde el modelo
        List<Producto> productos = modelo.ListarProductos();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Construye un array JSON con los productos
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            json.append(String.format("{\"id_producto\": \"%s\", \"nombre\": \"%s\", \"precio\": \"%s\", \"talla\": \"%s\", \"stock\": \"%s\", \"tela\": \"%s\"}",
                    p.getId_producto(), p.getNombre(), p.getPrecio(), p.getTalla(), p.getStock(), p.getTela()));
            if (i < productos.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");

        // Escribe el JSON en la respuesta
        response.getWriter().write(json.toString());
    }

    // Método para modificar un producto existente
    private void ModificarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtiene los parámetros de la solicitud
        String id_producto = request.getParameter("id_producto");
        String nombre = request.getParameter("nombre");
        String stock = request.getParameter("stock");
        String precio = request.getParameter("precio");
        String talla = request.getParameter("talla");

        // Crea un objeto Producto con los datos obtenidos
        Producto p = new Producto(id_producto, talla, nombre, precio, stock, null, null);

        // Llama al modelo para modificar el producto y obtiene el resultado
        boolean resultado = modelo.ModificarProducto(p);

        // Configura la respuesta como JSON y establece la codificación
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Escribe el resultado en la respuesta
        String jsonResponse = "{\"resultado\": " + resultado + "}";
        response.getWriter().write(jsonResponse);
    }

    // Método para buscar un producto
    private void BuscarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtiene los parámetros de la solicitud
        String id_producto = request.getParameter("id_producto");
        String talla = request.getParameter("talla");

        // Llama al modelo para buscar el producto y obtiene el resultado
        Producto p = modelo.BuscarProducto(id_producto, talla);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (p != null) {
            // Construye un JSON con los datos del producto encontrado
            String json = String.format("{\"id_usuario\": \"%s\", \"nombre\": \"%s\", \"precio\": \"%s\", \"estado\": \"%s\"}",
                    p.getId_producto(), p.getNombre(), p.getPrecio(), p.getEstado());
            response.getWriter().write(json);
            System.out.println(p.getEstado());
        } else {
            // Escribe un mensaje de error en la respuesta si no se encuentra el producto
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
