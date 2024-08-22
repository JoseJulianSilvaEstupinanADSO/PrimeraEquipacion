<%-- 
    Document   : inventario
    Created on : 6/07/2024, 6:49:27 p. m.
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
        <title>Inventario</title>
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
            <aside class="menu">
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
                    <li class="menu__item menu__item--selec">
                        <a href="inventario.jsp" class="menu__link menu__link--selec" ><span class="icono__span"><i class="fa-solid fa-boxes-stacked"></i></span>Inventario</a>
                    </li>
                    <li class="menu__item">
                        <a href="../admin/usuarios.jsp" class="menu__link" ><span class="icono__span"><i class="fa-solid fa-user"></i></span>Usuarios</a>
                    </li>
                    <li class="menu__item">
                        <a href="../admin/clientes.jsp" class="menu__link" ><span class="icono__span"><i class="fa-solid fa-users-between-lines"></i></span>Clientes</a>
                    </li>
                </ul>
                <div class="menu__salir">
                    <a href="../../index.jsp" class="menu__link menu__link--salir Sign-out" ><span class="icono__span"><i class="fa-solid fa-right-from-bracket"></i></span>Sign out</a>
                </div>
            </aside>
            <section class="facturas">
                <div class="header__productos">
                    <h1 class="producto__title">Gestion de Inventario</h1>
                    <button type="button" class="agregar__producto">Agragar Producto</button>
                </div>
                <div class="container__tabla">
                    <table class="tabla--factura">
                        <thead class="tabla__header">
                        <th class="th__item">Id Producto</th>
                        <th class="th__item">Nombre</th>
                        <th class="th__item">Precio</th>
                        <th class="th__item">Talla</th>
                        <th class="th__item">Stock</th>
                        <th class="th__item">Tela</th>
                        <th class="th__item">Estado</th>
                        <th class="th__item">Acciones</th>
                        </thead>
                        <tbody class="body__tabla">

                        </tbody>
                    </table>
                </div>
                <div class="container__buscar">
                    <div class="buscar--cliente">
                        <label class="label__buscar" for="">Buscar Producto:</label>
                        <input class="input__buscar bucar__cliente" type="text">
                        <button class="button__buscar Btn__buscar">Buscar</button>
                    </div>
                </div>
            </section>
            <div id="ventanaModal" class="container__modal container__modal--facturas">
                <div class="contenido__modal contenido__modal--facturas contenido__modal--inventario">
                    <div class="cerrar__factura">
                        <p class="cerrar__x">X</p>
                    </div>
                    <div class="factura__header">
                        <img class="img__modal" src="../../img/logo.png" alt="">
                        <h2 class="modal__title">Detalles del Producto</h2>
                    </div>
                    <div class="modal__body">
                        <form action="" class="form__modal--inventario" novalidate>
                            <div class="form__items">
                                <div class="form__content">
                                    <div>
                                        <label class="label__modal" for="">Id:</label>
                                        <input disabled="" class="input__modal llenar form_Id" type="number" >
                                    </div>
                                    <div>
                                        <label class="label__modal" for="">Nombre:</label>
                                        <input class="input__modal llenar form_Nombre" type="text" required>
                                    </div>
                                </div>
                                <div class="form__content">
                                    <div>
                                        <label class="label__modal" for="">Precio:</label>
                                        <input class="input__modal llenar form_Precio" type="number" required>
                                    </div>
                                    <div id="selec">
                                        <label class="label__modal" for="">Talla:</label>
                                        <input class="input__modal form_talla" type="text">
                                    </div>
                                </div>
                                <div class="form__content">
                                    <div>
                                        <label class="label__modal" for="">Stock:</label>
                                        <input class="input__modal llenar form_Stock" type="number" required>
                                    </div>
                                    <div>
                                        <label class="label__modal" for="tela">Tela:</label>
                                        <input class="input__modal llenar form_Tela" type="text" required>
                                    </div>
                                </div>     
                                <div class="form__content">
                                    <div>
                                        <label class="label__modal" for="">Estado:</label>
                                        <select id="estado" class="input__modal">
                                            <option value="Seleccionar">Seleccionar</option>
                                            <option value="1">Habilitado</option>
                                            <option value="0">Desabilitado</option>
                                        </select>
                                    </div>
                                </div>     
                                <div class="form__content--buttons">
                                    <button type="button" class="button__modal button__modal--inventario">Guardar Cambios</button>
                                    <button type="button" class="button__modal  button--alert modal__cancelar">Cancelar</button>
                                </div>
                            </div>
                        </form>
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
        <script src="../../js/inventario.js" type="module"></script>
        <script src="../../js/menu.js"></script>
    </body>
</html>
