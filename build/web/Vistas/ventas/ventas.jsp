<%-- 
    Document   : ventas
    Created on : 6/07/2024, 6:46:50 p. m.
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
  <title>Ventas</title>
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
          <li class="menu__item menu__item--selec">
            <a href="ventas.jsp" class="menu__link menu__link--selec"><span class="icono__span"><i class="fa-solid fa-cart-shopping"></i></span>Ventas</a>
          </li>
          <li class="menu__item">
            <a href="../facturas/facturas.jsp" class="menu__link" ><span class="icono__span"><i class="fa-solid fa-file-invoice"></i></span>Facturas</a>
          </li>
          <li class="menu__item">
            <a href="../inventario/inventario.jsp" class="menu__link" ><span class="icono__span"><i class="fa-solid fa-boxes-stacked"></i></span>Inventario</a>
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
      </section>
      <section class="container__factura">
        <div class="fatura__buscar">
          <h1 class="title">REALIZAR VENTA</h1>
          <form action="" class="form__datos">
            <div class="buscar">
              <h2 class="cliente__title">Cliente</h2>
              <div class="id__cliente">
                <input type="number" id="cdCliente" class="codigo__cliente login__input documento" placeholder="Codigo Cliente">
                <button type="button" class="button buscar__cliente  Btn_busacar_cliente">Buscar</button>
                <input type="text" class="codigo__cliente cliente__nombre insertDocumento" readonly>
              </div>
              <h2 class="cliente__title">Buscar Producto</h2>
              <div class="id__cliente">
                <input type="number" id="cdProducto" class="codigo__cliente login__input" placeholder="Codigo Producto" >
                <button type="button" class="button buscar__cliente">Buscar</button>
                <input placeholder="Nombre" type="text" class="codigo__cliente cliente__nombre" readonly>
                <div class="datos__producto">
                  <input placeholder="Precio" type="text" class="codigo__cliente cliente__nombre margin" readonly>
                  <input placeholder="Cantidad" type="number" class="codigo__cliente cliente__nombre ">
                  <select name="select" class="codigo__cliente cliente__nombre margin margin--top">
                    <option value="value1">Seleccionar Talla</option>
                  </select>
                </div>
              </div>
              <div class="buscar__btn">
                <button type="button" class="button button__agregar">Agregar Producto</button>
                <button type="button" id="abrirModal" class="button button__agregar  button__agregar--color">Agragar Cliente</button>
              </div>
            </div>
            <div class="factura__tabla">
              <div class="tabla">
                <table class="tabla__factura">
                    <thead class="tabla__th">
                        <th>Id Producto</th>
                        <th>Nombre</th>
                        <th>Talla</th>
                        <th>Precio</th>
                        <th>Cantidad</th>
                        <th>Acciones</th>
                    </thead>
                    <tbody class="tabla__tb">
                        <tr class="tabla__fila">
                            <td class="id__producto tabla__td">1</td>
                            <td class="nombre__producto tabla__td">Camisa Real Madrid</td>
                            <td class="talla__producto tabla__td">M</td>
                            <td class="precio__producto tabla__td">120000</td>
                            <td class="cantidad__producto tabla__td">1</td>
                            <td class="tabla__td">
                              <button type="button" class=" button__link BtnEliminar">Eliminar</button>
                            </td>
                        </tr>
                        <tr class="tabla__fila">
                          <td class="id__producto tabla__td">1</td>
                          <td class="nombre__producto tabla__td">Camisa Real Madrid</td>
                          <td class="talla__producto tabla__td">M</td>
                          <td class="precio__producto tabla__td">120000</td>
                          <td class="cantidad__producto tabla__td">2</td>
                          <td class="tabla__td">
                            <button type="button" class=" button__link BtnEliminar">Eliminar</button>
                          </td>
                        </tr>
                        <tr class="tabla__fila">
                          <td class="id__producto tabla__td">1</td>
                          <td class="nombre__producto tabla__td">Camisa Real Madrid</td>
                          <td class="talla__producto tabla__td">M</td>
                          <td class="precio__producto tabla__td">120000</td>
                          <td class="cantidad__producto tabla__td">3</td>
                          <td class="tabla__td">
                            <button type="button" class=" button__link BtnEliminar">Eliminar</button>
                          </td>
                        </tr>
                    </tbody>
                </table>
              </div>
              <div class="generar__factura">
                <div class="buttons__facturas">
                  <button type="submit" class="button button__form btn__ventas">Generar Factura</button>
                  <button type="button" class="button__link button__link--size btn__ventas">Cancelar</button>
                </div>
                <h2 class="total">TOTAL: 1000000</h2>
              </div>
            </div>
          </form>
        </div>        
      </section>
      <div id="ventanaModal" class="container__modal">
        <div class="contenido__modal">
          <h2 class="modal__title--ventas">REGISTRAR CLIENTE</h2>
          <form action="" class="form__modal  registar__clientes">
              <div class="div__form">
                  <label for="" class="">Documento: </label>
                  <input type="text" class="modal__input documento_cliente">
              </div>
              <div class="div__form">
                  <label for="" class="">Nombre: </label>
                  <input type="text" class="modal__input nombre_cliente">
              </div>
              <div class="div__form">
                  <label for="" class="">Telefono: </label>
                  <input type="text" class="modal__input tefelono_cliente">
              </div>
              <div class="div__buttons">
                  <button type="submit" class="button button__modal registrar_cliente">Registrar</button>
                  <button type="button" id="Cerrar__Modal" class="button--alert button__modal button__modal--alert">Cancelar</button>
              </div>
          </form>
        </div>
      </div>
  
  </main>
  <script src="../../js/ventas.js"></script>
  <script src="../../js/menu.js"></script>
</body>
</html>
