<%-- 
    Document   : inicio_vendedor
    Created on : 6/07/2024, 6:42:21 p. m.
    Author     : Julian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="../Css/style.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
  <title>Inicio</title>
</head>
<body>
  <header class="nav__header">
    <nav class="nav__container">
      <div class="nav__items">
        <ul class="nav__list">
          <li class="nav__logo">
            <img class="logo" src="../img/logo.png" alt="">
          </li>
          <li class="nav__item">
            <a href="inicio_vendedor.jsp" class="nav__link nav__link--titulo">PIMERA EQUIPACION</a>
          </li>
        </ul>
      </div>
      <div class="menu__toggle">
        <span class="menu__toggle-icon"><i class="fa-solid fa-bars"></i></span>
      </div>
    </nav>
  </header>
  <main class="container">
      <section class="menu">
        <h1 class="menu__title">M E N U</h1>
        <ul class="menu__lista">
          <li class="menu__item menu__item--selec">
            <a href="inicio_vendedor.jsp" class="menu__link menu__link--selec" ><span class="icono__span"><i class="fa-solid fa-house"></i></span>Home</a>
          </li>
          <li class="menu__item">
              <a href="datos/datos_vendedor.jsp" class="menu__link" ><span class="icono__span"><i class="fa-solid fa-user-gear"></i></span>Mis Datos</a>
          </li>
          <li class="menu__item">
            <a href="ventas/ventas_vendedor.jsp" class="menu__link" ><span class="icono__span"><i class="fa-solid fa-cart-shopping"></i></span>Ventas</a>
          </li>
          <li class="menu__item">
              <a href="facturas/factura_usuario.jsp" class="menu__link" ><span class="icono__span"><i class="fa-solid fa-file-invoice"></i></span>Facturas</a>
          </li>
        </ul>
        <div class="menu__salir">
          <a href="../index.jsp" class="menu__link menu__link--salir Sign-out" ><span class="icono__span"><i class="fa-solid fa-right-from-bracket"></i></span>Sign out</a>
        </div>
      </section>
      <section class="logo__inicio">
        <div class="container__img">
          <img class="logo__imagen" src="../img/logo.png" alt="">
        </div>
      </section>
  </main>
  <script src="../js/menu.js"></script>
  <script src="../js/inicio.js"></script>
</body>
</html>
