/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.89
 * Generated at: 2024-08-01 17:46:23 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.Vistas.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class usuarios_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("  <meta charset=\"UTF-8\">\r\n");
      out.write("  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("  <link rel=\"stylesheet\" href=\"../../Css/style.css\">\r\n");
      out.write("  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css\" integrity=\"sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==\" crossorigin=\"anonymous\" referrerpolicy=\"no-referrer\" />\r\n");
      out.write("  <title>Usuarios</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("  <header class=\"nav__header\">\r\n");
      out.write("    <nav class=\"nav__container\">\r\n");
      out.write("      <div class=\"nav__items\">\r\n");
      out.write("        <ul class=\"nav__list\">\r\n");
      out.write("          <li class=\"nav__logo\">\r\n");
      out.write("            <img class=\"logo\" src=\"../../img/logo.png\" alt=\"\">\r\n");
      out.write("          </li>\r\n");
      out.write("          <li class=\"nav__item\">\r\n");
      out.write("            <a href=\"../inicio.jsp\" class=\"nav__link nav__link--titulo\">PIMERA EQUIPACION</a>\r\n");
      out.write("          </li>\r\n");
      out.write("        </ul>\r\n");
      out.write("      </div>\r\n");
      out.write("      <div class=\"menu__toggle\">\r\n");
      out.write("        <span class=\"menu__toggle-icon\"><i class=\"fa-solid fa-bars\"></i></span>\r\n");
      out.write("      </div>\r\n");
      out.write("    </nav>\r\n");
      out.write("  </header>\r\n");
      out.write("  <main class=\"container container--size\">\r\n");
      out.write("    <section class=\"menu\">\r\n");
      out.write("      <h1 class=\"menu__title\">M E N U</h1>\r\n");
      out.write("      <ul class=\"menu__lista\">\r\n");
      out.write("        <li class=\"menu__item\">\r\n");
      out.write("          <a href=\"../inicio.jsp\" class=\"menu__link\" ><span class=\"icono__span\"><i class=\"fa-solid fa-house\"></i></span>Home</a>\r\n");
      out.write("        </li>\r\n");
      out.write("        <li class=\"menu__item\">\r\n");
      out.write("            <a href=\"../datos/datos.jsp\" class=\"menu__link\" ><span class=\"icono__span\"><i class=\"fa-solid fa-user-gear\"></i></span>Mis Datos</a>\r\n");
      out.write("        </li>\r\n");
      out.write("        <li class=\"menu__item\">\r\n");
      out.write("          <a href=\"../ventas/ventas.jsp\" class=\"menu__link\"><span class=\"icono__span\"><i class=\"fa-solid fa-cart-shopping\"></i></span>Ventas</a>\r\n");
      out.write("        </li>\r\n");
      out.write("        <li class=\"menu__item\">\r\n");
      out.write("          <a href=\"../facturas/facturas.jsp\" class=\"menu__link\"><span class=\"icono__span\"><i class=\"fa-solid fa-file-invoice\"></i></span>Facturas</a>\r\n");
      out.write("        </li>\r\n");
      out.write("        <li class=\"menu__item\">\r\n");
      out.write("          <a href=\"../inventario/inventario.jsp\" class=\"menu__link \" ><span class=\"icono__span\"><i class=\"fa-solid fa-boxes-stacked\"></i></span>Inventario</a>\r\n");
      out.write("        </li>\r\n");
      out.write("        <li class=\"menu__item menu__item--selec\">\r\n");
      out.write("          <a href=\"usuarios.jsp\" class=\"menu__link menu__link--selec\" ><span class=\"icono__span\"><i class=\"fa-solid fa-user\"></i></span>Usuarios</a>\r\n");
      out.write("        </li>\r\n");
      out.write("        <li class=\"menu__item\">\r\n");
      out.write("          <a href=\"clientes.jsp\" class=\"menu__link\" ><span class=\"icono__span\"><i class=\"fa-solid fa-users-between-lines\"></i></span>Clientes</a>\r\n");
      out.write("        </li>\r\n");
      out.write("      </ul>\r\n");
      out.write("      <div class=\"menu__salir\">\r\n");
      out.write("        <a href=\"../../index.jsp\" class=\"menu__link menu__link--salir Sign-out\" ><span class=\"icono__span\"><i class=\"fa-solid fa-right-from-bracket\"></i></span>Sign out</a>\r\n");
      out.write("      </div>\r\n");
      out.write("    </section>\r\n");
      out.write("    <section class=\"facturas\">\r\n");
      out.write("        <div class=\"header__productos\">\r\n");
      out.write("            <h1 class=\"producto__title\">Gestion de Usuarios</h1>\r\n");
      out.write("            <button type=\"button\" class=\"agregar__producto  Listar\">Listar</button>\r\n");
      out.write("        </div>\r\n");
      out.write("      <div class=\"container__tabla\">\r\n");
      out.write("        <table class=\"tabla--factura\">\r\n");
      out.write("          <thead class=\"tabla__header\">\r\n");
      out.write("            <th class=\"th__item\">Id Usuario</th>\r\n");
      out.write("            <th class=\"th__item\">Nombre</th>\r\n");
      out.write("            <th class=\"th__item\">Documento</th>\r\n");
      out.write("            <th class=\"th__item\">Rol</th>\r\n");
      out.write("            <th class=\"th__item\">Usuario</th>\r\n");
      out.write("            <th class=\"th__item\">Telefono</th>\r\n");
      out.write("            <th class=\"th__item\">Direccion</th>\r\n");
      out.write("            <th class=\"th__item\">Correo</th>\r\n");
      out.write("            <th class=\"th__item\">Acciones</th>\r\n");
      out.write("          </thead>\r\n");
      out.write("          <tbody class=\"body__tabla\">\r\n");
      out.write("              \r\n");
      out.write("          </tbody>\r\n");
      out.write("        </table>\r\n");
      out.write("      </div>\r\n");
      out.write("      <div class=\"container__buscar\">\r\n");
      out.write("        <div class=\"buscar--usuarios\">\r\n");
      out.write("            <label class=\"label__buscar\" for=\"\">Buscar Usuario:</label>\r\n");
      out.write("            <input class=\"input__buscar BuscarUsuario\" type=\"text\">\r\n");
      out.write("            <button class=\"button__buscar Buscar\">Buscar</button>\r\n");
      out.write("        </div>\r\n");
      out.write("      </div>\r\n");
      out.write("    </section>\r\n");
      out.write("    <div id=\"ventanaModal\" class=\"container__modal container__modal--facturas\">\r\n");
      out.write("        <div class=\"contenido__modal contenido__modal--facturas contenido__modal--inventario contenido__modal--usuers\">\r\n");
      out.write("            <div class=\"cerrar__factura\">\r\n");
      out.write("                <p class=\"cerrar__x\">X</p>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"factura__header\">\r\n");
      out.write("                <img class=\"img__modal\" src=\"../../img/logo.png\" alt=\"\">\r\n");
      out.write("                <h2 class=\"modal__title\">Detalles del Usuario</h2>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"modal__body modal__body--users\">\r\n");
      out.write("                <form action=\"\" class=\"form__modal--inventario\">\r\n");
      out.write("                    <div class=\"form__items\">\r\n");
      out.write("                        <div class=\"form__content\">\r\n");
      out.write("                            <div>\r\n");
      out.write("                                <label class=\"label__modal\" for=\"\">Id Usuario:</label>\r\n");
      out.write("                                <input class=\"input__modal form_Id\" type=\"number\">\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <div>\r\n");
      out.write("                                <label class=\"label__modal\" for=\"\">Nombre:</label>\r\n");
      out.write("                                <input class=\"input__modal form_Nombre\" type=\"text\">\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form__content\">\r\n");
      out.write("                            <div>\r\n");
      out.write("                                <label class=\"label__modal\" for=\"\">Documento:</label>\r\n");
      out.write("                                <input class=\"input__modal form_Doc\" type=\"text\">\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <div id=\"selec\">\r\n");
      out.write("                                <label class=\"label__modal\" for=\"\">Rol:</label>\r\n");
      out.write("                                <select   class=\"input__modal form_Rol\" name=\"\" id=\"\">\r\n");
      out.write("                                  <option value=\"sleccionar\">Seleccionar Rol</option>\r\n");
      out.write("                                  <option value=\"1\">1</option>\r\n");
      out.write("                                  <option value=\"2\">2</option>\r\n");
      out.write("                                  <option value=\"3\">3</option>\r\n");
      out.write("                                </select>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form__content\">\r\n");
      out.write("                            <div>\r\n");
      out.write("                                <label class=\"label__modal\" for=\"\">Usuario:</label>\r\n");
      out.write("                                <input class=\"input__modal form_User\" type=\"text\">\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <div>\r\n");
      out.write("                                <label class=\"label__modal\" for=\"\">Telefono:</label>\r\n");
      out.write("                                <input class=\"input__modal form_Tel\" type=\"text\">\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form__content\">\r\n");
      out.write("                          <div>\r\n");
      out.write("                              <label class=\"label__modal\" for=\"\">Direccion:</label>\r\n");
      out.write("                              <input class=\"input__modal form_Direc\" type=\"text\">\r\n");
      out.write("                          </div>\r\n");
      out.write("                          <div>\r\n");
      out.write("                              <label class=\"label__modal\" for=\"\">Correo:</label>\r\n");
      out.write("                              <input class=\"input__modal form_Email\" type=\"text\">\r\n");
      out.write("                          </div>\r\n");
      out.write("                      </div>\r\n");
      out.write("                        <div class=\"form__content--buttons\">\r\n");
      out.write("                            <button type=\"button\" class=\"button__modal button__modal--inventario ModificarUser\">Guardar Cambios</button>\r\n");
      out.write("                            <button type=\"button\" class=\"button__modal  button--alert modal__cancelar\">Cancelar</button>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </form>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"container__modal--error\">\r\n");
      out.write("        <div class=\"contenido__modal--error\">\r\n");
      out.write("            <h1 class=\"title_error\"></h1>\r\n");
      out.write("            <p class=\"paragrahp__error\"></p>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("  </main>\r\n");
      out.write("  <script src=\"../../js/users.js\"></script>\r\n");
      out.write("  <script src=\"../../js/menu.js\"></script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
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
