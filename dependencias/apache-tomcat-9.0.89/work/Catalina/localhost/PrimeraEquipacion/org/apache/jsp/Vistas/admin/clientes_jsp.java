/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.89
 * Generated at: 2024-07-07 00:07:51 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.Vistas.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class clientes_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.LinkedHashSet<>(3);
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("  <meta charset=\"UTF-8\">\n");
      out.write("  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("  <link rel=\"stylesheet\" href=\"../../Css/style.css\">\n");
      out.write("  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css\" integrity=\"sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==\" crossorigin=\"anonymous\" referrerpolicy=\"no-referrer\" />\n");
      out.write("  <title>Clientes</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("  <header class=\"nav__header\">\n");
      out.write("    <nav class=\"nav__container\">\n");
      out.write("      <div class=\"nav__items\">\n");
      out.write("        <ul class=\"nav__list\">\n");
      out.write("          <li class=\"nav__logo\">\n");
      out.write("            <img class=\"logo\" src=\"../../img/logo.png\" alt=\"\">\n");
      out.write("          </li>\n");
      out.write("          <li class=\"nav__item\">\n");
      out.write("            <a href=\"../inicio.jsp\" class=\"nav__link nav__link--titulo\">PIMERA EQUIPACION</a>\n");
      out.write("          </li>\n");
      out.write("        </ul>\n");
      out.write("      </div>\n");
      out.write("      <div class=\"menu__toggle\">\n");
      out.write("        <span class=\"menu__toggle-icon\"><i class=\"fa-solid fa-bars\"></i></span>\n");
      out.write("      </div>\n");
      out.write("    </nav>\n");
      out.write("  </header>\n");
      out.write("  <main class=\"container container--size\">\n");
      out.write("    <section class=\"menu\">\n");
      out.write("      <h1 class=\"menu__title\">M E N U</h1>\n");
      out.write("      <ul class=\"menu__lista\">\n");
      out.write("        <li class=\"menu__item\">\n");
      out.write("          <a href=\"../inicio.jsp\" class=\"menu__link\" ><span class=\"icono__span\"><i class=\"fa-solid fa-house\"></i></span>Home</a>\n");
      out.write("        </li>\n");
      out.write("        <li class=\"menu__item\">\n");
      out.write("          <a href=\"../datos.jsp\" class=\"menu__link\" ><span class=\"icono__span\"><i class=\"fa-solid fa-user-gear\"></i></span>Mis Datos</a>\n");
      out.write("        </li>\n");
      out.write("        <li class=\"menu__item\">\n");
      out.write("          <a href=\"../ventas/ventas.jsp\" class=\"menu__link\"><span class=\"icono__span\"><i class=\"fa-solid fa-cart-shopping\"></i></span>Ventas</a>\n");
      out.write("        </li>\n");
      out.write("        <li class=\"menu__item\">\n");
      out.write("          <a href=\"../facturas/facturas.jsp\" class=\"menu__link\"><span class=\"icono__span\"><i class=\"fa-solid fa-file-invoice\"></i></span>Facturas</a>\n");
      out.write("        </li>\n");
      out.write("        <li class=\"menu__item \">\n");
      out.write("          <a href=\"../inventario/inventario.jsp\" class=\"menu__link\" ><span class=\"icono__span\"><i class=\"fa-solid fa-boxes-stacked\"></i></span>Inventario</a>\n");
      out.write("        </li>\n");
      out.write("        <li class=\"menu__item\">\n");
      out.write("          <a href=\"usuarios.jsp\" class=\"menu__link\" ><span class=\"icono__span\"><i class=\"fa-solid fa-user\"></i></span>Usuarios</a>\n");
      out.write("        </li>\n");
      out.write("        <li class=\"menu__item menu__item--selec\">\n");
      out.write("          <a href=\"clientes.jsp \" class=\"menu__link menu__link--selec\" ><span class=\"icono__span\"><i class=\"fa-solid fa-users-between-lines\"></i></span>Clientes</a>\n");
      out.write("        </li>\n");
      out.write("      </ul>\n");
      out.write("      <div class=\"menu__salir\">\n");
      out.write("        <a href=\"../../index.jsp\" class=\"menu__link menu__link--salir\" ><span class=\"icono__span\"><i class=\"fa-solid fa-right-from-bracket\"></i></span>Sign out</a>\n");
      out.write("      </div>\n");
      out.write("    </section>\n");
      out.write("    <section class=\"facturas\">\n");
      out.write("        <div class=\"header__productos\">\n");
      out.write("            <h1 class=\"producto__title\">Gestion de Clientes</h1>\n");
      out.write("        </div>\n");
      out.write("      <div class=\"container__tabla\">\n");
      out.write("        <table class=\"tabla--factura\">\n");
      out.write("          <thead class=\"tabla__header\">\n");
      out.write("            <th class=\"th__item\">Id Cliente</th>\n");
      out.write("            <th class=\"th__item\">Documento</th>\n");
      out.write("            <th class=\"th__item\">Nombre</th>\n");
      out.write("            <th class=\"th__item\">Telefono</th>\n");
      out.write("            <th class=\"th__item\">Acciones</th>\n");
      out.write("          </thead>\n");
      out.write("          <tbody class=\"body__tabla\">\n");
      out.write("            <tr class=\"fila__tabla\">\n");
      out.write("                <td class=\"td__tabla IdProducto\">1</td>\n");
      out.write("                <td class=\"td__tabla NombreProducto\">1106544620</td>\n");
      out.write("                <td class=\"td__tabla PrecioProducto\">Ronaldo Messi</td>\n");
      out.write("                <td class=\"td__tabla TallaProducto\">123546351</td>\n");
      out.write("                <td class=\"td__tabla \">\n");
      out.write("                  <button type=\"button\" class=\"button__tabla\">Historial</button>\n");
      out.write("                </td>\n");
      out.write("            </tr>\n");
      out.write("            <tr class=\"fila__tabla\">\n");
      out.write("                <td class=\"td__tabla IdProducto\">1</td>\n");
      out.write("                <td class=\"td__tabla NombreProducto\">Casima Real Madrid</td>\n");
      out.write("                <td class=\"td__tabla PrecioProducto\">123000</td>\n");
      out.write("                <td class=\"td__tabla TallaProducto\">M</td>\n");
      out.write("                <td class=\"td__tabla \">\n");
      out.write("                  <button type=\"button\" class=\"button__tabla\">Historial</button>\n");
      out.write("                </td>\n");
      out.write("            </tr>\n");
      out.write("            <tr class=\"fila__tabla\">\n");
      out.write("                <td class=\"td__tabla IdProducto\">1</td>\n");
      out.write("                <td class=\"td__tabla NombreProducto\">Casima Real Madrid</td>\n");
      out.write("                <td class=\"td__tabla PrecioProducto\">124000</td>\n");
      out.write("                <td class=\"td__tabla TallaProducto\">L</td>\n");
      out.write("                <td class=\"td__tabla \">\n");
      out.write("                  <button type=\"button\" class=\"button__tabla\">Historial</button>\n");
      out.write("                </td>\n");
      out.write("            </tr>\n");
      out.write("            <tr class=\"fila__tabla\">\n");
      out.write("                <td class=\"td__tabla IdProducto\">1</td>\n");
      out.write("                <td class=\"td__tabla NombreProducto\">Casima Real Madrid</td>\n");
      out.write("                <td class=\"td__tabla PrecioProducto\">125000</td>\n");
      out.write("                <td class=\"td__tabla TallaProducto\">X</td>\n");
      out.write("                <td class=\"td__tabla \">\n");
      out.write("                  <button type=\"button\" class=\"button__tabla\">Historial</button>\n");
      out.write("                </td>\n");
      out.write("            </tr>\n");
      out.write("            <tr class=\"fila__tabla\">\n");
      out.write("                <td class=\"td__tabla IdProducto\">1</td>\n");
      out.write("                <td class=\"td__tabla NombreProducto\">Casima Real Madrid</td>\n");
      out.write("                <td class=\"td__tabla PrecioProducto\">126000</td>\n");
      out.write("                <td class=\"td__tabla TallaProducto\">XL</td>\n");
      out.write("                <td class=\"td__tabla \">\n");
      out.write("                  <button type=\"button\" class=\"button__tabla\">Historial</button>\n");
      out.write("                </td>\n");
      out.write("            </tr>\n");
      out.write("          </tbody>\n");
      out.write("        </table>\n");
      out.write("      </div>\n");
      out.write("      <div class=\"container__buscar\">\n");
      out.write("        <div class=\"buscar--cliente\">\n");
      out.write("            <label class=\"label__buscar\" for=\"\">Buscar Cliente:</label>\n");
      out.write("            <input class=\"input__buscar\" type=\"text\">\n");
      out.write("            <button class=\"button__buscar\">Buscar</button>\n");
      out.write("        </div>\n");
      out.write("      </div>\n");
      out.write("    </section>\n");
      out.write("    <div id=\"ventanaModal\" class=\"container__modal container__modal--facturas\">\n");
      out.write("        <div class=\"contenido__modal contenido__modal--facturas\">\n");
      out.write("            <div class=\"cerrar__factura\">\n");
      out.write("                <p class=\"cerrar__x\">X</p>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"factura__header\">\n");
      out.write("                <img class=\"img__modal\" src=\"../../img/logo.png\" alt=\"\">\n");
      out.write("                <h2>Historial de cliente</h2>\n");
      out.write("                <div class=\"numero__factura\">\n");
      out.write("                    <p class=\"Numero_factura\"><b>Id Cliente: 25</b></p>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <h2 class=\"title__tabla--clientes\">COMPRAS REALIZADAS</h2>\n");
      out.write("            <div class=\"container__tabla container__tabla--modal \">\n");
      out.write("                <table class=\"tabla--factura\">\n");
      out.write("                  <thead class=\"tabla__header\">\n");
      out.write("                    <th class=\"th__item\">Id Venta</th>\n");
      out.write("                    <th class=\"th__item\">Id Factura</th>\n");
      out.write("                    <th class=\"th__item\">Fecha</th>\n");
      out.write("                    <th class=\"th__item\">Id Vendedor</th>\n");
      out.write("                  </thead>\n");
      out.write("                  <tbody class=\"body__tabla\">\n");
      out.write("                    <tr class=\"fila__tabla fila__tabla--modal\" >\n");
      out.write("                        <td class=\"td__tabla \">12</td>\n");
      out.write("                        <td class=\"td__tabla \">45</td>\n");
      out.write("                        <td class=\"td__tabla \">02/04/2024</td>\n");
      out.write("                        <td class=\"td__tabla \">1</td>\n");
      out.write("                    </tr>\n");
      out.write("                    <tr class=\"fila__tabla fila__tabla--modal\" >\n");
      out.write("                        <td class=\"td__tabla \">12</td>\n");
      out.write("                        <td class=\"td__tabla \">45</td>\n");
      out.write("                        <td class=\"td__tabla \">02/04/2024</td>\n");
      out.write("                        <td class=\"td__tabla \">1</td>\n");
      out.write("                    </tr>\n");
      out.write("                    <tr class=\"fila__tabla fila__tabla--modal\" >\n");
      out.write("                        <td class=\"td__tabla \">12</td>\n");
      out.write("                        <td class=\"td__tabla \">45</td>\n");
      out.write("                        <td class=\"td__tabla \">02/04/2024</td>\n");
      out.write("                        <td class=\"td__tabla \">1</td>\n");
      out.write("                    </tr>\n");
      out.write("                    <tr class=\"fila__tabla fila__tabla--modal\" >\n");
      out.write("                        <td class=\"td__tabla \">12</td>\n");
      out.write("                        <td class=\"td__tabla \">45</td>\n");
      out.write("                        <td class=\"td__tabla \">02/04/2024</td>\n");
      out.write("                        <td class=\"td__tabla \">1</td>\n");
      out.write("                    </tr>\n");
      out.write("                    <tr class=\"fila__tabla fila__tabla--modal\" >\n");
      out.write("                        <td class=\"td__tabla \">12</td>\n");
      out.write("                        <td class=\"td__tabla \">45</td>\n");
      out.write("                        <td class=\"td__tabla \">02/04/2024</td>\n");
      out.write("                        <td class=\"td__tabla \">1</td>\n");
      out.write("                    </tr>\n");
      out.write("                    <tr class=\"fila__tabla fila__tabla--modal\" >\n");
      out.write("                        <td class=\"td__tabla \">12</td>\n");
      out.write("                        <td class=\"td__tabla \">45</td>\n");
      out.write("                        <td class=\"td__tabla \">02/04/2024</td>\n");
      out.write("                        <td class=\"td__tabla \">1</td>\n");
      out.write("                    </tr>\n");
      out.write("                  </tbody>\n");
      out.write("                </table>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("  </main>\n");
      out.write("  <script src=\"../../js/clientes.js\"></script>\n");
      out.write("  <script src=\"../../js/menu.js\"></script>\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
