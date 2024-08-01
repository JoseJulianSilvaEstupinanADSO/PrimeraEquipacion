<%-- 
    Document   : factura_usuario
    Created on : 6/07/2024, 6:52:51 p. m.
    Author     : Julian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="../../Css/style.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
  <title>Facturas</title>
</head>
<body>
  <header class="nav__header">
    <nav class="nav__container">
      <div class="nav__items">
        <ul class="nav__list">
          <li class="nav__logo">
            <img class="logo" src="../../img/logo.png" alt="">
          </li>
          <li class="nav__item">
            <a href="../inicio_vendedor.jsp" class="nav__link nav__link--titulo">PIMERA EQUIPACION</a>
          </li>
        </ul>
      </div>
      <div class="menu__toggle">
        <span class="menu__toggle-icon"><i class="fa-solid fa-bars"></i></span>
      </div>
    </nav>
  </header>
  <main class="container container--size">
    <section class="menu">
      <h1 class="menu__title">M E N U</h1>
      <ul class="menu__lista">
        <li class="menu__item">
          <a href="../inicio_vendedor.jsp" class="menu__link" ><span class="icono__span"><i class="fa-solid fa-house"></i></span>Home</a>
        </li>
        <li class="menu__item">
            <a href="../datos/datos_vendedor.jsp" class="menu__link" ><span class="icono__span"><i class="fa-solid fa-user-gear"></i></span>Mis Datos</a>
        </li>
        <li class="menu__item">
          <a href="../ventas/ventas_vendedor.jsp" class="menu__link"><span class="icono__span"><i class="fa-solid fa-cart-shopping"></i></span>Ventas</a>
        </li>
        <li class="menu__item menu__item--selec">
            <a href="factura_usuario.jsp" class="menu__link menu__link--selec"><span class="icono__span"><i class="fa-solid fa-file-invoice"></i></span>Facturas</a>
        </li>
      </ul>
      <div class="menu__salir">
        <a href="../../index.jsp" class="menu__link menu__link--salir Sign-out" ><span class="icono__span"><i class="fa-solid fa-right-from-bracket"></i></span>Sign out</a>
      </div>
    </section>
    <section class="facturas">
      <h1 class="tabla__title">Gestion de Facturas</h1>
      <div class="container__tabla">
        <table class="tabla--factura">
          <thead class="tabla__header">
            <th class="th__item">Id Factura</th>
            <th class="th__item">Fecha Facturacion</th>
            <th class="th__item">Documento Cliente</th>
            <th class="th__item">Id Venta</th>
            <th class="th__item">Id Usuario</th>
            <th class="th__item">Total</th>
            <th class="th__item">Acciones</th>
          </thead>
          <tbody class="body__tabla">
          </tbody>
        </table>
      </div>
      <div class="container__buscar">
        <div>
            <label class="label__buscar" for="">Buscar Factura:</label>
            <input class="input__buscar" type="text">
            <button class="button__buscar">Buscar</button>
        </div>
      </div>
    </section>
    <div id="ventanaModal" class="container__modal container__modal--facturas">
      <div class="contenido__modal contenido__modal--facturas">
          <div class="cerrar__factura">
              <p class="cerrar__x">X</p>
          </div>
          <div class="factura__header">
              <img class="img__modal" src="../../img/logo.png" alt="">
              <h2>Detalles de Factura</h2>
              <div class="numero__factura">
                  <p class="Numero_factura"></p>
                  <p class="fecha__factura"></p>
              </div>
          </div>
          <div class="container__tabla container__tabla--modal tabla ">
              <table class="tabla__profact">
                <thead class="tabla__header">
                  <th class="th__item">Id Producto</th>
                  <th class="th__item">Nombre</th>
                  <th class="th__item">Precio</th>
                  <th class="th__item">Talla</th>
                  <th class="th__item">Cantidad</th>
                </thead>
                <tbody class="body__tabla body__tabla--modal">           
                </tbody>
              </table>
          </div>
          <div class="personas">
              <h2 class="total total--facturas"></h2>
          </div>
      </div>
  </div>
    <div class="container__modal--error">
      <div class="contenido__modal--error">
          <h1 class="title_error"></h1>
          <p class="paragrahp__error"></p>
      </div>
     </div>
  </main>
  <script src="../../js/factura.js"></script>
  <script src="../../js/menu.js"></script>
</body>
</html>
