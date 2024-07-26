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

    
    private ProductoDAO modelo = new ProductoDAO();
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
        String accion = request.getParameter("action");
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
            default:
                throw new AssertionError();
        }
    }
    
    private void ListarTallas(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException{
        
        List<Producto> productos = modelo.ListarTallas();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < productos.size(); i++) {
            Producto producto = productos.get(i);
             json.append(String.format("{\"talla\": \"%s\"}",
                    producto.getTalla()));
            if (i < productos.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");
        response.getWriter().write(json.toString());
    }
    
    private void RegistrarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        String nombre = request.getParameter("nombre");
        String precio = request.getParameter("precio");
        String talla = request.getParameter("talla");
        String stock = request.getParameter("stock");
        String tela = request.getParameter("tela");
        System.out.println(nombre+precio+talla+stock +tela);
        
        Producto p = new Producto(null, talla, nombre, precio, stock, tela);
        
        boolean resultado = modelo.AgregarProducto(p);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"resultado\": " + resultado + "}");
                
        
        
    }
    
    private void  ListarProductos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
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
