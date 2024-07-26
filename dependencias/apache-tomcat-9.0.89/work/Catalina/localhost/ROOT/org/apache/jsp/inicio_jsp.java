/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.89
 * Generated at: 2024-07-26 13:27:01 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import modelo.objetos.Perfil;
import modelo.objetos.Usuario;

public final class inicio_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/WEB-INF/lib/jstl-impl.jar", Long.valueOf(1721999511403L));
    _jspx_dependants.put("jar:file:/C:/Users/Propietario/Documents/NetBeansProjects/mdaSenaAnt/build/web/WEB-INF/lib/jstl-impl.jar!/META-INF/c.tld", Long.valueOf(1343837818000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.LinkedHashSet<>(3);
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.LinkedHashSet<>(2);
    _jspx_imports_classes.add("modelo.objetos.Perfil");
    _jspx_imports_classes.add("modelo.objetos.Usuario");
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
      out.write("\r\n");

    HttpSession sesion = request.getSession(false);
    HttpSession sesionPerfil = request.getSession(false);
    if (sesion == null || sesion.getAttribute("dataUser") == null) {
            response.sendRedirect("login.jsp");
            return;
    }
    Perfil perfil = (Perfil)  sesionPerfil.getAttribute("dataPerfil");
    Usuario user = (Usuario) sesion.getAttribute("dataUser");

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("  <meta charset=\"UTF-8\" />\r\n");
      out.write("  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\r\n");
      out.write("  <!-- Enlace para estilos personalizados -->\r\n");
      out.write("  <link rel=\"stylesheet\" href=\"styles/style.css\" />\r\n");
      out.write("  <!-- Enlace con la librería Tailwind -->\r\n");
      out.write("  <script src=\"https://cdn.tailwindcss.com\"></script>\r\n");
      out.write("  <!-- Enlace para personalización de colores en Tailwind -->\r\n");
      out.write("  <script src=\"scripts/tailwind.js\"></script>\r\n");
      out.write("  <!-- Enlace con la libería DaisyUI -->\r\n");
      out.write("  <link href=\"https://cdn.jsdelivr.net/npm/daisyui@4.12.2/dist/full.min.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("  <!-- Enlace para iconos de Font Awesome -->\r\n");
      out.write("  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css\"\r\n");
      out.write("    integrity=\"sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==\"\r\n");
      out.write("    crossorigin=\"anonymous\" referrerpolicy=\"no-referrer\" />\r\n");
      out.write("  <title>MDA Sena - Inicio</title>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body class=\"flex bg-mdaWhite bg-gradient-to-t from-mdaGreen_400 to-mdaWhite\">\r\n");
      out.write("  <!-- Ingrese aquí la estrucuta de la página -->\r\n");
      out.write("\r\n");
      out.write("  <!-- Barra lateral izquierda -->\r\n");
      out.write("  <nav class=\"bg-white p-7 shadow-md sticky top-0 h-screen z-10\">\r\n");
      out.write("    <div class=\"grid gap-y-5\">\r\n");
      out.write("      <!-- Logo Sena y nombre del proyecto -->\r\n");
      out.write("      <div class=\"flex flex-row w-full h-32\">\r\n");
      out.write("        <!-- Logo -->\r\n");
      out.write("        <div class=\"grid flex-grow place-items-center w-full\">\r\n");
      out.write("          <img src=\"images/LogoNegro.svg\" alt=\"\" />\r\n");
      out.write("        </div>\r\n");
      out.write("        <!-- HR -->\r\n");
      out.write("        <div class=\"divider divider-horizontal\"></div>\r\n");
      out.write("        <!-- Nombre -->\r\n");
      out.write("        <div class=\"grid flex-grow place-items-center w-full\">\r\n");
      out.write("          <h1 class=\"text-4xl text-mdaBlack leading-none\">\r\n");
      out.write("            <span class=\"text-3xl text-mdaGreen\">MDA</span> <br />\r\n");
      out.write("            Sena\r\n");
      out.write("          </h1>\r\n");
      out.write("        </div>\r\n");
      out.write("      </div>\r\n");
      out.write("      <!-- HR -->\r\n");
      out.write("      <div class=\"flex flex-col w-full\">\r\n");
      out.write("        <div class=\"divider m-0 h-0\"></div>\r\n");
      out.write("      </div>\r\n");
      out.write("      <!-- Input de búsqueda -->\r\n");
      out.write("      <form action=\"\">\r\n");
      out.write("        <label class=\"input input-bordered flex items-center gap-2 bg-white\">\r\n");
      out.write("          <i class=\"fa-solid fa-magnifying-glass text-gray-400\"></i>\r\n");
      out.write("          <input type=\"text\" class=\"grow text-mdaBlack\" placeholder=\"Search\" />\r\n");
      out.write("        </label>\r\n");
      out.write("      </form>\r\n");
      out.write("      <!-- HR -->\r\n");
      out.write("      <div class=\"flex flex-col w-full\">\r\n");
      out.write("        <div class=\"divider m-0 h-0\"></div>\r\n");
      out.write("      </div>\r\n");
      out.write("      \r\n");
      out.write("      ");

          if ("Aprendiz".equals(user.getId_rol_fk().getNombre_rol())) {
      
      out.write("\r\n");
      out.write("      \r\n");
      out.write("      <!-- Botones navegación -->\r\n");
      out.write("      <div>\r\n");
      out.write("        <!-- Inicio -->\r\n");
      out.write("        <a href=\"inicio.jsp\">\r\n");
      out.write("          <button\r\n");
      out.write("            class=\"btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start\">\r\n");
      out.write("            <i class=\"fa-solid fa-house\"></i>\r\n");
      out.write("            Inicio\r\n");
      out.write("          </button>\r\n");
      out.write("        </a>\r\n");
      out.write("        <a href=\"editarPerfil.jsp\">\r\n");
      out.write("          <button\r\n");
      out.write("            class=\"btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start\">\r\n");
      out.write("            <i class=\"fa-regular fa-address-card\"></i>\r\n");
      out.write("            Perfil\r\n");
      out.write("          </button>\r\n");
      out.write("        </a>\r\n");
      out.write("      </div>\r\n");
      out.write("    </div>\r\n");
      out.write("      \r\n");
      out.write("      ");

          } else if ("Instructor".equals(user.getId_rol_fk().getNombre_rol())) {
      
      out.write("\r\n");
      out.write("      \r\n");
      out.write("      <!-- Botones navegación -->\r\n");
      out.write("      <div>\r\n");
      out.write("        <!-- Inicio -->\r\n");
      out.write("        <a href=\"inicio.jsp\">\r\n");
      out.write("          <button\r\n");
      out.write("            class=\"btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start\">\r\n");
      out.write("            <i class=\"fa-solid fa-house\"></i>\r\n");
      out.write("            Inicio\r\n");
      out.write("          </button>\r\n");
      out.write("        </a>\r\n");
      out.write("        <!-- Asignar monitor -->\r\n");
      out.write("        <a href=\"views/instructor/asignarMonitor.jsp\">\r\n");
      out.write("          <button\r\n");
      out.write("            class=\"btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start\">\r\n");
      out.write("            <i class=\"fa-solid fa-plus-minus\"></i>\r\n");
      out.write("            Asignar monitor\r\n");
      out.write("          </button>\r\n");
      out.write("        </a>\r\n");
      out.write("        <a href=\"#\">\r\n");
      out.write("          <button id=\"showModal-2\"\r\n");
      out.write("            class=\"btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start\">\r\n");
      out.write("            <i class=\"fa-solid fa-bell\"></i>\r\n");
      out.write("            Notificaciones\r\n");
      out.write("          </button>\r\n");
      out.write("        </a>\r\n");
      out.write("        <a href=\"editarPerfil.jsp\">\r\n");
      out.write("          <button\r\n");
      out.write("            class=\"btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start\">\r\n");
      out.write("            <i class=\"fa-regular fa-address-card\"></i>\r\n");
      out.write("            Perfil\r\n");
      out.write("          </button>\r\n");
      out.write("        </a>\r\n");
      out.write("      </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    \r\n");
      out.write("    ");

      } else if ("Monitor".equals(user.getId_rol_fk().getNombre_rol())) {
    
      out.write("\r\n");
      out.write("    \r\n");
      out.write("     <!-- Botones navegación -->\r\n");
      out.write("      <div>\r\n");
      out.write("        <!-- Inicio -->\r\n");
      out.write("        <a href=\"inicio.jsp\">\r\n");
      out.write("          <button\r\n");
      out.write("            class=\"btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start\">\r\n");
      out.write("            <i class=\"fa-solid fa-house\"></i>\r\n");
      out.write("            Inicio\r\n");
      out.write("          </button>\r\n");
      out.write("        </a>\r\n");
      out.write("        <!-- Crear post -->\r\n");
      out.write("        <a href=\"views/instructor/asignarMonitor.jsp\">\r\n");
      out.write("          <button\r\n");
      out.write("            class=\"btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start\">\r\n");
      out.write("            <i class=\"fa-solid fa-user-plus\"></i>\r\n");
      out.write("            Crear post\r\n");
      out.write("          </button>\r\n");
      out.write("        </a>\r\n");
      out.write("        <a href=\"#\">\r\n");
      out.write("          <button id=\"showModal-2\"\r\n");
      out.write("            class=\"btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start\">\r\n");
      out.write("            <i class=\"fa-solid fa-bell\"></i>\r\n");
      out.write("            Notificaciones\r\n");
      out.write("          </button>\r\n");
      out.write("        </a>\r\n");
      out.write("        <a href=\"editarPerfil.jsp\">\r\n");
      out.write("          <button\r\n");
      out.write("            class=\"btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start\">\r\n");
      out.write("            <i class=\"fa-regular fa-address-card\"></i>\r\n");
      out.write("            Perfil\r\n");
      out.write("          </button>\r\n");
      out.write("        </a>\r\n");
      out.write("      </div>\r\n");
      out.write("    </div>\r\n");
      out.write("        \r\n");
      out.write("    ");

        }
    
      out.write("\r\n");
      out.write("    \r\n");
      out.write("    <!-- Barra notificaciones -->\r\n");
      out.write("    <nav id=\"modal-2\" class=\"hidden bg-white p-7 shadow-md absolute top-0 left-full h-screen w-full\">\r\n");
      out.write("      <div class=\"grid gap-y-5\">\r\n");
      out.write("        <button id=\"closeModal-2\" class=\"btn btn-sm btn-circle btn-ghost text-mdaGreen\">\r\n");
      out.write("          <i class=\"fa-solid fa-chevron-left\"></i>\r\n");
      out.write("        </button>\r\n");
      out.write("        <!-- HR -->\r\n");
      out.write("        <div class=\"flex flex-col w-full\">\r\n");
      out.write("          <div class=\"divider m-0 h-0\"></div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div>\r\n");
      out.write("          <p class=\"text-mdaBlack text-sm\">\r\n");
      out.write("            Daniel Acetaminofén, ha cargado una evidencia\r\n");
      out.write("          </p>\r\n");
      out.write("          <button class=\"btn btn-sm bg-mdaGreen border-none text-white mt-2 hover:bg-mdaGreen w-full\">\r\n");
      out.write("            Ver evidencia\r\n");
      out.write("          </button>\r\n");
      out.write("        </div>\r\n");
      out.write("      </div>\r\n");
      out.write("    </nav>\r\n");
      out.write("  </nav>\r\n");
      out.write("  <div id=\"modal-2__background\" class=\"hidden bg-mdaBlack_400 w-full min-h-screen absolute\"></div>\r\n");
      out.write("  \r\n");
      out.write("  <!-- Contenedor para los artículos -->\r\n");
      out.write("  <section class=\"m-auto flex w-full max-w-screen-2xl min-h-screen justify-center p-5 gap-5 flex-wrap content-start\">\r\n");
      out.write("    <!-- Artículo -->\r\n");
      out.write("    <article class=\"bg-white w-full max-w-2xl h-40 shadow-md rounded-lg p-5 flex flex-col justify-between\">\r\n");
      out.write("      <div class=\"text-mdaBlack text-sm\">\r\n");
      out.write("        <p><b>Nombre aprendiz</b></p>\r\n");
      out.write("      </div>\r\n");
      out.write("      <div class=\"text-mdaGreen\">\r\n");
      out.write("        <h2 class=\"text-4xl mb-2 truncate\" title=\"TITULO DEL POST 1\">\r\n");
      out.write("          <b>TITULO DEL POST 1</b>\r\n");
      out.write("        </h2>\r\n");
      out.write("        <a class=\"text-base hover:underline\" href=\"#\">Nombre del documento.docx\r\n");
      out.write("          <i class=\"fa-solid fa-arrow-down\"></i>\r\n");
      out.write("        </a>\r\n");
      out.write("      </div>\r\n");
      out.write("    </article>\r\n");
      out.write("  </section>\r\n");
      out.write("  <!-- Indicador de rol -->\r\n");
      out.write("  <button class=\"bg-white btn btn-sm border-none text-mdaBlack absolute top-0 right-0 m-2.5 hover:bg-white\">\r\n");
      out.write("    <i class=\"fa-solid fa-user\"></i> ");
      out.print( user.getId_rol_fk().getNombre_rol() );
      out.write("\r\n");
      out.write("  </button>\r\n");
      out.write("\r\n");
      out.write("  <!-- Enlace para manejo del DOM -->\r\n");
      out.write("  <script src=\"scripts/inicio.js\"></script>\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      out.write("</html>");
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