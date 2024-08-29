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
 * Servlet que gestiona las operaciones relacionadas con las facturas,
 * como registrar nuevas ventas, agregar productos a facturas, listar facturas,
 * listar productos de una factura, y generar un PDF con los detalles de una factura.
 * 
 * @version 1.0
 * @author Julian
 */
@WebServlet(name = "Facturas", urlPatterns = {"/Facturas"})
public class Facturas extends HttpServlet {

    /**
     * Instancia de FacturaDAO para interactuar con la base de datos.
     */
    private FacturaDAO modelo = new FacturaDAO();

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

            case "PdfProductosFactura":
                PdfProductosFactura(request, response);
                break;
                
            default:
                throw new AssertionError();
        }
    }

    /**
     * Registra una nueva venta en el sistema.
     * Este método extrae los parámetros de la solicitud, crea un objeto Factura
     * y lo envía al modelo para ser registrado en la base de datos.
     * 
     * @param request  El objeto HttpServletRequest que contiene la solicitud del cliente.
     * @param response El objeto HttpServletResponse que contiene la respuesta del servlet.
     * @throws ServletException Si ocurre un error específico del servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    private void NuevaVenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    /**
     * Agrega productos a una factura existente.
     * Este método recibe los parámetros de la solicitud, crea un objeto Factura 
     * y lo envía al modelo para agregar los productos a la factura.
     * 
     * @param request  El objeto HttpServletRequest que contiene la solicitud del cliente.
     * @param response El objeto HttpServletResponse que contiene la respuesta del servlet.
     * @throws ServletException Si ocurre un error específico del servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    private void AgregarProdcutosFactura(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    /**
     * Lista todas las facturas registradas en el sistema.
     * Este método consulta al modelo para obtener una lista de facturas,
     * y luego construye una respuesta en formato JSON que contiene los datos de todas las facturas.
     * 
     * @param request  El objeto HttpServletRequest que contiene la solicitud del cliente.
     * @param response El objeto HttpServletResponse que contiene la respuesta del servlet.
     * @throws ServletException Si ocurre un error específico del servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    private void ListarFacturas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    /**
     * Lista los productos asociados a una factura específica.
     * Este método consulta al modelo para obtener una lista de productos 
     * relacionados con una factura y construye una respuesta en formato JSON.
     * 
     * @param request  El objeto HttpServletRequest que contiene la solicitud del cliente.
     * @param response El objeto HttpServletResponse que contiene la respuesta del servlet.
     * @throws ServletException Si ocurre un error específico del servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    private void ListarProductosFactura(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    /**
     * Lista las compras realizadas por un cliente específico.
     * Este método recibe un documento de cliente como parámetro y consulta al modelo 
     * para obtener una lista de compras, devolviendo una respuesta en formato JSON.
     * 
     * @param request  El objeto HttpServletRequest que contiene la solicitud del cliente.
     * @param response El objeto HttpServletResponse que contiene la respuesta del servlet.
     * @throws ServletException Si ocurre un error específico del servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    private void ClientesCompras(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    /**
     * Genera un archivo PDF con los detalles de los productos de una factura.
     * Este método recibe el ID de la factura como parámetro y consulta al modelo
     * para obtener los productos relacionados, generando un documento PDF con esta información.
     * 
     * @param request  El objeto HttpServletRequest que contiene la solicitud del cliente.
     * @param response El objeto HttpServletResponse que contiene la respuesta del servlet.
     * @throws ServletException Si ocurre un error específico del servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    private void PdfProductosFactura(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id_factura = request.getParameter("id_factura");
        List<Factura> productos = modelo.PdfProductoFactura(id_factura);
        
        response.setContentType("application/pdf");
        try {
            OutputStream out = response.getOutputStream();
            Document documento = new Document();
            PdfWriter.getInstance(documento, out);
            documento.open();
            
            // Añade detalles de la factura al documento PDF
            Paragraph paragraph = new Paragraph("DETALLES DE FACTURA");
            paragraph.setAlignment(Element.ALIGN_CENTER);
            documento.add(paragraph);
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph("PRODUCTOS"));
            documento.add(new Paragraph(" "));
            
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
                documento.add(new Paragraph(" "));
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
