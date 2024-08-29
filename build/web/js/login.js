/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

import Ajax from "./Modulos/ajax.js";
import Mensaje from "./Modulos/msjerror.js";
import validations from "./Modulos/Validaciones/vacio.js";

const $dom = document;
const servlet = "Usuarios";

// Elementos relacionados con el modal de error
const error = $dom.querySelector(".container__modal--error");
const $titleError = $dom.querySelector(".title_error");
const $paragrahp = $dom.querySelector(".paragrahp__error");

// Elementos del formulario de login
const $cambiar = $dom.querySelector(".Ingresar");
const $input = $dom.querySelectorAll(".login__form [required]");

// Campos de usuario y contraseña
const $usuario = $dom.getElementById("usuario");
const $contraseña = $dom.getElementById("contraseña");

// Evento que se dispara cuando el contenido del documento está completamente cargado
document.addEventListener('DOMContentLoaded', function() {
    // Evento para el botón de ingreso
    $cambiar.addEventListener("click", async function() {
        // Validación de los campos requeridos en el formulario
        let num = validations(event, $input);

        // Si todos los campos están completos
        if (num === $input.length) {
            // Datos a enviar al servidor para validar el login
            let datos = {
                "usuario": $usuario.value,
                "contrasena": $contraseña.value
            };
            
            // Realiza una solicitud AJAX para validar las credenciales del usuario
            let respuesta = await Ajax(servlet, datos, "POST", "ValidarLogin");
            
            // Si hay un error en las credenciales
            if (respuesta.error) {
                Mensaje($titleError, $paragrahp, error, "Error en credenciales", "Usuario o contraseña incorrectos");
            } else {
                // Si el login es exitoso, almacena la información de la sesión en localStorage
                localStorage.setItem("idUsuario", respuesta.idUsuario);
                localStorage.setItem("session", true);

                // Redirige al usuario según su rol
                switch (respuesta.rol) {
                    case "1":
                        window.location.href = 'Vistas/inicio.jsp';
                        break;
                    case "2":
                        window.location.href = 'Vistas/inicio_vendedor.jsp';
                        break;
                    case "3":
                        window.location.href = 'Vistas/inicio_inventario.jsp';
                        break;
                }
            }
        } else {
            // Mensaje de error si hay campos vacíos
            Mensaje($titleError, $paragrahp, error, "Error campos vacíos", "Por favor llene todos los campos");
        }
    });
});
