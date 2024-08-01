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
import java.util.ArrayList;
import java.util.List;
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
            case "ListarFacturas":
                ListarFacturas(request, response);
                
                break;
            case "ListarProductosFactura":
                ListarProductosFactura(request, response);

                break;
            case "ClientesCompras":
                ClientesCompras(request, response);

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
    
    private void ListarFacturas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<Factura> facturas = modelo.ListarFacturas();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < facturas.size(); i++) {
            Factura f = facturas.get(i);
             json.append(String.format("{\"id_venta\": \"%s\", \"id_usuario\": \"%s\", \"id_factura\": \"%s\", \"fecha_facturacion\": \"%s\", \"doc_cliente\": \"%s\", \"total\": \"%s\"}",
                    f.getId_venta(), f.getId_usuario(), f.getId_factura(), f.getFecha(), f.getDoc_cliente(), f.getTotal()));
            if (i < facturas.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");
        response.getWriter().write(json.toString());
    }
    
    private void  ListarProductosFactura(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        String id_factura = request.getParameter("id_factura");
        
        List<Factura> productos = modelo.ListarProductoFactura(id_factura);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < productos.size(); i++) {
            Factura f = productos.get(i);
             json.append(String.format("{\"id_producto\": \"%s\", \"precio\": \"%s\", \"cantidad\": \"%s\", \"nombre\": \"%s\", \"talla\": \"%s\"}",
                    f.getId_producto(), f.getPrecio(), f.getCantidad(), f.getNombre_p(), f.getTalla()));
            if (i < productos.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");
        response.getWriter().write(json.toString());
        
    }
    
    private void ClientesCompras(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String doc_cliente = request.getParameter("doc_cliente");
        List<Factura> compras = modelo.ClientesCompras(doc_cliente);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < compras.size(); i++) {
            Factura f = compras.get(i);
             json.append(String.format("{\"id_venta\": \"%s\", \"id_factura\": \"%s\", \"fecha_facturacion\": \"%s\", \"id_usuario\": \"%s\"}",
                    f.getId_venta(), f.getId_factura(), f.getFecha(), f.getId_usuario()));
            if (i < compras.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");
        response.getWriter().write(json.toString());
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
