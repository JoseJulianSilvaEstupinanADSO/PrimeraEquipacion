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
import com.modelo.FacturaDAO;
import com.modelo.Factura;
/**
 *
 * @author Julian
 */
@WebServlet(name = "Facturas", urlPatterns = {"/Facturas"})
public class Facturas extends HttpServlet {

    private FacturaDAO modelo = new FacturaDAO();
            
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("action");
        response.setContentType("text/html;charset=UTF-8");
        switch (accion) {
            case "NuevaVenta":
                NuevaVenta(request, response);
                
                break;
                
            case "AgregarProdcutosFactura":
                AgregarProdcutosFactura(request, response);
                
                break;
            default:
                throw new AssertionError();
        }
    }
    
    private void NuevaVenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String id_usuario = request.getParameter("id_usuario");
        String fecha = request.getParameter("fecha");
        String doc_cliente = request.getParameter("doc_cliente");
        String total = request.getParameter("total");
        
        Factura f = new Factura(null, id_usuario, null, null, fecha, doc_cliente, null, null);
        
        int respuesta = modelo.AgregarFactura(f, total);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
         String jsonResponse = "{\"resultado\": " + respuesta + "}";

        
        response.getWriter().write(jsonResponse);
    }
    
    private void AgregarProdcutosFactura(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        String id_factura = request.getParameter("id_factura");
        String id_producto = request.getParameter("id_producto");
        String cantidad = request.getParameter("cantidad");
        String precion = request.getParameter("precion");
        
        Factura f = new Factura(null, null, id_factura, id_producto, null, null, cantidad, precion);
        boolean respuesta = modelo.AgregarProdcutosFactura(f);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
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
