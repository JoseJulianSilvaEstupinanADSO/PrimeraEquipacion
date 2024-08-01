<%-- 
    Document   : datos_inventario
    Created on : 6/07/2024, 10:48:01 p. m.
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
  <title>Mis Datos</title>
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
              <a href="../inicio_inventario.jsp" class="nav__link nav__link--titulo">PIMERA EQUIPACION</a>
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
          <li class="menu__item">
              <a href="../inicio_inventario.jsp" class="menu__link" ><span class="icono__span"><i class="fa-solid fa-house"></i></span>Home</a>
          </li>
          <li class="menu__item menu__item--selec">
              <a href="datos_inventario.jsp" class="menu__link menu__link--selec"><span class="icono__span"><i class="fa-solid fa-user-gear"></i></span>Mis Datos</a>
          </li>
          <li class="menu__item">
              <a href="../inventario/inventario_usuario.jsp" class="menu__link" ><span class="icono__span"><i class="fa-solid fa-boxes-stacked"></i></span>Inventario</a>
          </li>
        </ul>
        <div class="menu__salir">
          <a href="../../index.jsp" class="menu__link menu__link--salir Sign-out" ><span class="icono__span"><i class="fa-solid fa-right-from-bracket"></i></span>Sign out</a>
        </div>
      </section>
      <section class="container--datos">
        <div class="container__datos">
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
                                <input class="input__modal form_Id" type="number" readonly>
                            </div>
                            <div>
                                <label class="label__modal" for="">Nombre:</label>
                                <input class="input__modal form_Nombre" type="text">
                            </div>
                        </div>
                        <div class="form__content">
                            <div>
                                <label class="label__modal" for="">Documento:</label>
                                <input class="input__modal form_Doc" type="text" readonly>
                            </div>
                            <div id="selec">
                                <label class="label__modal" for="">Rol:</label>
                                <select   class="input__modal form_Rol" name="" id="">
                                  <option disabled value="sleccionar">Seleccionar Rol</option  >
                                  <option disabled value="1">1</option >
                                  <option disabled value="2">2</option >
                                  <option disabled value="3">3</option>
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
                            <button type="submit" class="button__modal button__modal--inventario  GuardarCambios">Guardar Cambios</button>
                            <button type="button" class="cambiar__contraseña">Cambiar Contraseña</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
      </section>
      <div id="ventanaModal" class="container__modal container__modal--datos">
        <div class="contenido__modal">
          <h2 class="modal__title--ventas">Cambiar Contraseña</h2>
          <form action="" class="form__modal">
              <div class="div__form">
                  <label for="" class="">Contraseña Actual: </label>
                  <input type="password" class="modal__input password_actual">
              </div>
              <div class="div__form">
                  <label for="" class="">Nueva Contraseña: </label>
                  <input type="password" class="modal__input password_nueva">
              </div>
              <div class="div__form">
                  <label for="" class="">Confirme Contraseña: </label>
                  <input type="password" class="modal__input password_confrm">
              </div>
              <div class="div__buttons">
                  <button type="button" class="button button__modal Cambiar">Cambiar</button>
                  <button type="button" id="Cerrar__Modal" class="button--alert button__modal button__modal--alert">Cancelar</button>
              </div>
          </form>
        </div>
      </div>
      <div class="container__modal--error">
        <div class="contenido__modal--error">
            <h1 class="title_error"></h1>
            <p class="paragrahp__error"></p>

        </div>
       </div>
  </main>
    <script src="../../js/menu.js"></script>
  <script src="../../js/datos.js" type="module"></script>
</body>
</html>