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
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.OutputStream;
/**
 *
 * @author Julian
 */
@WebServlet(name = "Facturas", urlPatterns = {"/Facturas"})
public class Facturas extends HttpServlet {

    // Instancia de FacturaDAO para interactuar con la base de datos
private FacturaDAO modelo = new FacturaDAO();

protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    
    // Obtiene la acción desde los parámetros de la solicitud
    String accion = request.getParameter("action");
    response.setContentType("text/html;charset=UTF-8");
    
    // Selecciona la acción a realizar según el valor de 'accion'
    switch (accion) {
        case "NuevaVenta":
            // Llama al método NuevaVenta
            NuevaVenta(request, response);
            break;
            
        case "AgregarProdcutosFactura":
            // Llama al método AgregarProdcutosFactura
            AgregarProdcutosFactura(request, response);
            break;
            
        case "ListarFacturas":
            // Llama al método ListarFacturas
            ListarFacturas(request, response);
            break;
            
        case "ListarProductosFactura":
            // Llama al método ListarProductosFactura
            ListarProductosFactura(request, response);
            break;
            
        case "ClientesCompras":
            // Llama al método ClientesCompras
            ClientesCompras(request, response);
            break;
        case "PdfProductosFactura":
            // Llama al método ClientesCompras
            PdfProductosFactura(request, response);
            break;
            
        default:
            // Lanza una excepción si la acción no coincide con ninguna conocida
            throw new AssertionError();
    }
}

// Método para registrar una nueva venta
private void NuevaVenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Obtiene los parámetros de la solicitud
    String id_usuario = request.getParameter("id_usuario");
    String fecha = request.getParameter("fecha");
    String doc_cliente = request.getParameter("doc_cliente");
    String total = request.getParameter("total");
    
    // Crea un objeto Factura con los datos obtenidos
    Factura f = new Factura(null, id_usuario, null, null, fecha, doc_cliente, null, null);
    
    // Llama al modelo para agregar la factura y obtiene la respuesta
    int respuesta = modelo.AgregarFactura(f, total);
    
    // Configura la respuesta como JSON y establece la codificación
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    
    // Escribe el resultado en la respuesta
    String jsonResponse = "{\"resultado\": " + respuesta + "}";
    response.getWriter().write(jsonResponse);
}

// Método para agregar productos a una factura
private void AgregarProdcutosFactura(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Obtiene los parámetros de la solicitud
    String id_factura = request.getParameter("id_factura");
    String id_producto = request.getParameter("id_producto");
    String cantidad = request.getParameter("cantidad");
    String precion = request.getParameter("precion");
    
    // Crea un objeto Factura con los datos obtenidos
    Factura f = new Factura(null, null, id_factura, id_producto, null, null, cantidad, precion);
    
    // Llama al modelo para agregar los productos a la factura y obtiene la respuesta
    boolean respuesta = modelo.AgregarProdcutosFactura(f);
    
    // Configura la respuesta como JSON y establece la codificación
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    
    // Escribe el resultado en la respuesta
    String jsonResponse = "{\"resultado\": " + respuesta + "}";
    response.getWriter().write(jsonResponse);
}

// Método para listar las facturas
private void ListarFacturas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Obtiene la lista de facturas del modelo
    List<Factura> facturas = modelo.ListarFacturas();
    
    // Configura la respuesta como JSON y establece la codificación
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    
    // Construye un array JSON con las facturas
    StringBuilder json = new StringBuilder("[");
    for (int i = 0; i < facturas.size(); i++) {
        Factura f = facturas.get(i);
        // Agrega cada factura al array JSON
        json.append(String.format("{\"id_venta\": \"%s\", \"id_usuario\": \"%s\", \"id_factura\": \"%s\", \"fecha_facturacion\": \"%s\", \"doc_cliente\": \"%s\", \"total\": \"%s\"}",
                f.getId_venta(), f.getId_usuario(), f.getId_factura(), f.getFecha(), f.getDoc_cliente(), f.getTotal()));
        if (i < facturas.size() - 1) {
            json.append(",");
        }
    }
    json.append("]");
    
    // Escribe el JSON en la respuesta
    response.getWriter().write(json.toString());
}

// Método para listar los productos de una factura
private void ListarProductosFactura(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Obtiene el ID de la factura desde los parámetros de la solicitud
    String id_factura = request.getParameter("id_factura");
    
    // Llama al modelo para listar los productos de la factura y obtiene la lista de productos
    List<Factura> productos = modelo.ListarProductoFactura(id_factura);
    
    // Configura la respuesta como JSON y establece la codificación
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    
    // Construye un array JSON con los productos
    StringBuilder json = new StringBuilder("[");
    for (int i = 0; i < productos.size(); i++) {
        Factura f = productos.get(i);
        // Agrega cada producto al array JSON
        json.append(String.format("{\"id_producto\": \"%s\", \"precio\": \"%s\", \"cantidad\": \"%s\", \"nombre\": \"%s\", \"talla\": \"%s\"}",
                f.getId_producto(), f.getPrecio(), f.getCantidad(), f.getNombre_p(), f.getTalla()));
        if (i < productos.size() - 1) {
            json.append(",");
        }
    }
    json.append("]");
    
    // Escribe el JSON en la respuesta
    response.getWriter().write(json.toString());
}

// Método para listar las compras de un cliente
private void ClientesCompras(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Obtiene el documento del cliente desde los parámetros de la solicitud
    String doc_cliente = request.getParameter("doc_cliente");
    
    // Llama al modelo para listar las compras del cliente y obtiene la lista de compras
    List<Factura> compras = modelo.ClientesCompras(doc_cliente);
    
    // Configura la respuesta como JSON y establece la codificación
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    
    // Construye un array JSON con las compras
    StringBuilder json = new StringBuilder("[");
    for (int i = 0; i < compras.size(); i++) {
        Factura f = compras.get(i);
        // Agrega cada compra al array JSON
        json.append(String.format("{\"id_venta\": \"%s\", \"id_factura\": \"%s\", \"fecha_facturacion\": \"%s\", \"id_usuario\": \"%s\"}",
                f.getId_venta(), f.getId_factura(), f.getFecha(), f.getId_usuario()));
        if (i < compras.size() - 1) {
            json.append(",");
        }
    }
    json.append("]");
    
    // Escribe el JSON en la respuesta
    response.getWriter().write(json.toString());
}


private void PdfProductosFactura(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtiene el ID de la factura desde los parámetros de la solicitud
        String id_factura = request.getParameter("id_factura");
        
        // Llama al modelo para listar los productos de la factura y obtiene la lista de productos
        List<Factura> productos = modelo.PdfProductoFactura(id_factura);
        
        System.out.println(productos);
        
        // Configura la respuesta como PDF y establece la codificación
        response.setContentType("application/pdf");
        try {
            OutputStream out = response.getOutputStream();

            Document documento = new Document();
            PdfWriter.getInstance(documento, out);

            documento.open();
            Paragraph paragraph = new Paragraph("DETALLES DE FACTURA");
            paragraph.setAlignment(Element.ALIGN_CENTER);
            documento.add(paragraph);
            documento.add(new Paragraph(" ")); 
            documento.add(new Paragraph(String.format("PRODUCTOS")));  
            documento.add(new Paragraph(" "));  
            // Añade los productos al documento PDF
            double total = 0;
            String fecha = "";
            String doc = "";
            for (Factura f : productos) {
                fecha = f.getFecha();
                doc = f.getDoc_cliente();
                total += Double.parseDouble(f.getPrecio());
                documento.add(new Paragraph(String.format("ID Producto: %s", f.getId_producto())));
                documento.add(new Paragraph(String.format("Nombre: %s", f.getNombre_p())));
                documento.add(new Paragraph(String.format("Talla: %s", f.getTalla())));
                documento.add(new Paragraph(String.format("Cantidad: %s", f.getCantidad())));
                documento.add(new Paragraph(String.format("Precio: %s", f.getPrecio())));
                documento.add(new Paragraph(" "));  // Añade un espacio en blanco entre productos
            }
            documento.add(new Paragraph(String.format("FECHA FACTURACION: %s", fecha)));  
            documento.add(new Paragraph(" "));  
            documento.add(new Paragraph(String.format("DOCUMENTO DE CLIENTE: %s", doc)));  
            documento.add(new Paragraph(" "));  
            documento.add(new Paragraph(String.format("TOTAL: %s", total)));
            documento.close();
        
        } catch (Exception e) {
           e.getMessage();


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
