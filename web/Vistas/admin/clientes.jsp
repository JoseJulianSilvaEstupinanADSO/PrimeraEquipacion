<%-- 
    Document   : cliente
    Created on : 6/07/2024, 6:53:38 p. m.
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
        <title>Clientes</title>
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
                    <li class="menu__item ">
                        <a href="../inventario/inventario.jsp" class="menu__link" ><span class="icono__span"><i class="fa-solid fa-boxes-stacked"></i></span>Inventario</a>
                    </li>
                    <li class="menu__item">
                        <a href="usuarios.jsp" class="menu__link" ><span class="icono__span"><i class="fa-solid fa-user"></i></span>Usuarios</a>
                    </li>
                    <li class="menu__item menu__item--selec">
                        <a href="clientes.jsp " class="menu__link menu__link--selec" ><span class="icono__span"><i class="fa-solid fa-users-between-lines"></i></span>Clientes</a>
                    </li>
                </ul>
                <div class="menu__salir">
                    <a href="../../index.jsp" class="menu__link menu__link--salir Sign-out" ><span class="icono__span"><i class="fa-solid fa-right-from-bracket"></i></span>Sign out</a>
                </div>
            </aside>
            <section class="facturas">
                <div class="header__productos">
                    <h1 class="producto__title">Gestion de Usuarios</h1>
                    <button type="button" class="agregar__producto  Listar">Listar</button>
                </div>
                <div class="container__tabla">
                    <table class="tabla--factura">
                        <thead class="tabla__header">
                        <th class="th__item">Id Cliente</th>
                        <th class="th__item">Documento</th>
                        <th class="th__item">Nombre</th>
                        <th class="th__item">Telefono</th>
                        <th class="th__item">Acciones</th>
                        </thead>
                        <tbody class="body__tabla">
                        </tbody>
                    </table>
                </div>
                <div class="container__buscar">
                    <div class="buscar--cliente">
                        <label class="label__buscar" for="">Buscar Cliente:</label>
                        <input class="input__buscar bucar__cliente" type="text">
                        <button class="button__buscar Btn__buscar">Buscar</button>
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
                        <h2>Historial de cliente</h2>
                        <div class="numero__factura">
                            <p class="Numero_factura Id_cliente"><b></b></p>
                        </div>
                    </div>
                    <h2 class="title__tabla--clientes">COMPRAS REALIZADAS</h2>
                    <div class="container__tabla container__tabla--modal ">
                        <table class="tabla--factura">
                            <thead class="tabla__header">
                            <th class="th__item">Id Venta</th>
                            <th class="th__item">Id Factura</th>
                            <th class="th__item">Fecha</th>
                            <th class="th__item">Id Vendedor</th>
                            </thead>
                            <tbody class="body__tabla body__tabla--modal">
                                <tr class="fila__tabla--modal" >
                                    <td class="td__tabla idventa">12</td>
                                    <td class="td__tabla idfactura">45</td>
                                    <td class="td__tabla date">02/04/2024</td>
                                    <td class="td__tabla idvendedor">1</td>
                                </tr>
                            </tbody>
                        </table>
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
        <script src="../../js/clientes.js" type="module"></script>
        <script src="../../js/menu.js"></script>
    </body>
</html>
