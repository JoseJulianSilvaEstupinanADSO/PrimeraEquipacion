<%-- 
    Document   : usuarios
    Created on : 6/07/2024, 6:54:40 p. m.
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
  <title>Usuarios</title>
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
            <a href="../inicio.jsp" class="nav__link nav__link--titulo">PIMERA EQUIPACION</a>
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
          <a href="../inicio.jsp" class="menu__link" ><span class="icono__span"><i class="fa-solid fa-house"></i></span>Home</a>
        </li>
        <li class="menu__item">
            <a href="../datos/datos.jsp" class="menu__link" ><span class="icono__span"><i class="fa-solid fa-user-gear"></i></span>Mis Datos</a>
        </li>
        <li class="menu__item">
          <a href="../ventas/ventas.jsp" class="menu__link"><span class="icono__span"><i class="fa-solid fa-cart-shopping"></i></span>Ventas</a>
        </li>
        <li class="menu__item">
          <a href="../facturas/facturas.jsp" class="menu__link"><span class="icono__span"><i class="fa-solid fa-file-invoice"></i></span>Facturas</a>
        </li>
        <li class="menu__item">
          <a href="../inventario/inventario.jsp" class="menu__link " ><span class="icono__span"><i class="fa-solid fa-boxes-stacked"></i></span>Inventario</a>
        </li>
        <li class="menu__item menu__item--selec">
          <a href="usuarios.jsp" class="menu__link menu__link--selec" ><span class="icono__span"><i class="fa-solid fa-user"></i></span>Usuarios</a>
        </li>
        <li class="menu__item">
          <a href="clientes.jsp" class="menu__link" ><span class="icono__span"><i class="fa-solid fa-users-between-lines"></i></span>Clientes</a>
        </li>
      </ul>
      <div class="menu__salir">
        <a href="../../index.jsp" class="menu__link menu__link--salir" ><span class="icono__span"><i class="fa-solid fa-right-from-bracket"></i></span>Sign out</a>
      </div>
    </section>
    <section class="facturas">
        <div class="header__productos">
            <h1 class="producto__title">Gestion de Usuarios</h1>
            <button type="button" class="agregar__producto  Listar">Listar</button>
        </div>
      <div class="container__tabla">
        <table class="tabla--factura">
          <thead class="tabla__header">
            <th class="th__item">Id Usuario</th>
            <th class="th__item">Nombre</th>
            <th class="th__item">Documento</th>
            <th class="th__item">Rol</th>
            <th class="th__item">Usuario</th>
            <th class="th__item">Telefono</th>
            <th class="th__item">Direccion</th>
            <th class="th__item">Correo</th>
            <th class="th__item">Acciones</th>
          </thead>
          <tbody class="body__tabla">
              
          </tbody>
        </table>
      </div>
      <div class="container__buscar">
        <div class="buscar--usuarios">
            <label class="label__buscar" for="">Buscar Usuario:</label>
            <input class="input__buscar BuscarUsuario" type="text">
            <button class="button__buscar Buscar">Buscar</button>
        </div>
      </div>
    </section>
    <div id="ventanaModal" class="container__modal container__modal--facturas">
        <div class="contenido__modal contenido__modal--facturas contenido__modal--inventario contenido__modal--usuers">
            <div class="cerrar__factura">
                <p class="cerrar__x">X</p>
            </div>
            <div class="factura__header">
                <img class="img__modal" src="../../img/logo.png" alt="">
                <h2 class="modal__title">Detalles del Usuario</h2>
            </div>
            <div class="modal__body modal__body--users">
                <form action="" class="form__modal--inventario">
                    <div class="form__items">
                        <div class="form__content">
                            <div>
                                <label class="label__modal" for="">Id Usuario:</label>
                                <input class="input__modal form_Id" type="number">
                            </div>
                            <div>
                                <label class="label__modal" for="">Nombre:</label>
                                <input class="input__modal form_Nombre" type="text">
                            </div>
                        </div>
                        <div class="form__content">
                            <div>
                                <label class="label__modal" for="">Documento:</label>
                                <input class="input__modal form_Doc" type="text">
                            </div>
                            <div id="selec">
                                <label class="label__modal" for="">Rol:</label>
                                <select   class="input__modal form_Rol" name="" id="">
                                  <option value="sleccionar">Seleccionar Rol</option>
                                  <option value="1">1</option>
                                  <option value="2">2</option>
                                  <option value="3">3</option>
                                </select>
                            </div>
                        </div>
                        <div class="form__content">
                            <div>
                                <label class="label__modal" for="">Usuario:</label>
                                <input class="input__modal form_User" type="text">
                            </div>
                            <div>
                                <label class="label__modal" for="">Telefono:</label>
                                <input class="input__modal form_Tel" type="text">
                            </div>
                        </div>
                        <div class="form__content">
                          <div>
                              <label class="label__modal" for="">Direccion:</label>
                              <input class="input__modal form_Direc" type="text">
                          </div>
                          <div>
                              <label class="label__modal" for="">Correo:</label>
                              <input class="input__modal form_Email" type="text">
                          </div>
                      </div>
                        <div class="form__content--buttons">
                            <button type="button" class="button__modal button__modal--inventario ModificarUser">Guardar Cambios</button>
                            <button type="button" class="button__modal  button--alert modal__cancelar">Cancelar</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
  </main>
  <script src="../../js/users.js"></script>
  <script src="../../js/menu.js"></script>
</body>
</html>
