<%-- 
    Document   : registro
    Created on : 6/07/2024, 6:40:23 p. m.
    Author     : Julian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="../Css/style.css">
        <title>Registro</title>
    </head>
    <body class="body--registro">
        <div class="login__container login__container--registro">
            <div class="login__logo">
                <img class="logo__img" src="../img/logo.png" alt="">
            </div>
            <form class="login__form">
                <h1 class="login__title">
                    Registro
                </h1>
                <div class="mb-3">
                    <label for="formGroupExampleInput" class="form-labe login__label">Nombre:</label>
                    <input type="text" class="form-control login__input" id="Rnom" placeholder="Nombre">
                    <p class="vacio">Campo Vacio</p>
                </div>
                <div class="mb-3">
                    <label for="formGroupExampleInput" class="form-label login__label" >Documento:</label>
                    <input type="text" class="form-control login__input" id="Rdoc" placeholder="Documento">
                    <p class="vacio">Campo Vacio</p>
                </div>
                <div class="mb-3">
                    <label for="formGroupExampleInput" class="form-label login__label " >Telefono:</label>
                    <input type="text" class="form-control login__input" id="Rtel" placeholder="Telefono">
                    <p class="vacio">Campo Vacio</p>
                </div>
                <div class="mb-3">
                    <label for="formGroupExampleInput" class="form-label login__label ">Direccion:</label>
                    <input type="text" class="form-control login__input" id="Rdirec" placeholder="Direccion">
                    <p class="vacio">Campo Vacio</p>
                </div>
                <div class="mb-3">
                    <label for="formGroupExampleInput" class="form-label login__label">E-mail:</label>
                    <input type="email" class="form-control login__input" id="Remail" placeholder="E-mail">
                    <p class="vacio">Campo Vacio</p>
                </div>
                <div class="mb-3">
                    <label for="formGroupExampleInput" class="form-label login__label ">Edad:</label>
                    <input type="text" class="form-control login__input" id="Redad" placeholder="Edad">
                    <p class="vacio">Campo Vacio</p>
                </div>
                <div class="mb-3">
                    <label for="formGroupExampleInput" class="form-label login__label">Usuario:</label>
                    <input type="text" class="form-control login__input" id="Ruser" placeholder="Usuario">
                    <p class="vacio">Campo Vacio</p>
                </div>
                <div class="mb-3">
                    <label for="formGroupExampleInput2" class="form-label login__label">Contraseña:</label>
                    <input type="password" class="form-control login__input" id="Rcontra" placeholder="Contraseña">
                    <p class="vacio">Campo Vacio</p>
                </div>
                <div class="btns__form">
                    <button class="button" type="button"><a class="btn__link Registrar">Registrarse</a></button>
                    <button class="button button--color" type="button"><a class="btn__link btn__link--color" href="../index.jsp">Cancelar</a></button>
                </div>
            </form>
        </div>
        <div class="container__modal--error">
            <div class="contenido__modal--error">
                <h1 class="title_error"></h1>
                <p class="paragrahp__error"></p>
            </div>
        </div>
 
    </body>
    <script src="../js/registro.js"></script>
</html>
