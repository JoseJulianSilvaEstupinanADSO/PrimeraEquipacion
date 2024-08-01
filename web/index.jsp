<%-- 
    Document   : index
    Created on : 6/07/2024, 6:22:54 p. m.
    Author     : Julian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="Css/style.css">
        <Title>Login</Title>
    </head>
    <body>
        <div class="login__container">
            <div class="login__logo">
                <img class="logo__img" src="img/logo.png" alt="">
            </div>
            <form class="login__form">
                <h1 class="login__title">
                    Login
                </h1>
                <div class="mb-3">
                    <label for="formGroupExampleInput" class="form-label login__label">Usuario</label>
                    <input type="text" class="form-control login__input" id="usuario" placeholder="Usuario">
                    <p class="vacio">Campo Vacio</p>
                </div>
                <div class="mb-3">
                    <label for="formGroupExampleInput2" class="form-label login__label">Contraseña</label>
                    <input type="password" class="form-control login__input" id="contraseña" placeholder="Contraseña">
                    <p class="vacio">Campo Vacio</p>
                </div>
                <div class="btns__form">
                    <button class="button Ingresar " type="button"><a class="btn__link">Ingresar</a></button>
                    <button class="button button--color" type="button"><a class="btn__link btn__link--color" href="Vistas/registro.jsp">Registrarse</a></button>
                </div>
            </form>
        </div>
        <div class="container__modal--error">
          <div class="contenido__modal--error">
              <h1 class="title_error"></h1>
              <p class="paragrahp__error"></p>
              
          </div>
         </div>
 
    <script type="module" src="js/login.js"></script>
    </body>
</html>
