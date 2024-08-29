/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

import Ajax from "./Modulos/ajax.js";
import Mensaje from "./Modulos/msjerror.js";
import validations from "./Modulos/Validaciones/vacio.js";
import validarCorreo from "./Modulos/Validaciones/correo.js";
import validarString from "./Modulos/Validaciones/letras.js";
import validarNumber from "./Modulos/Validaciones/numeros.js";
import validarLength from "./Modulos/Validaciones/length.js";

const $dom = document;
const servlet = "../Usuarios";
const $input = $dom.querySelectorAll(".login__form [required]");
const $cambiar = $dom.querySelector(".login__form");

// Elementos relacionados con el modal de error
const error = $dom.querySelector(".container__modal--error");
const $titleError = $dom.querySelector(".title_error");
const $paragrahp = $dom.querySelector(".paragrahp__error");

// Campos del formulario de registro
const $usuario = $dom.querySelector("#Ruser");
const $contrasena = $dom.querySelector("#Rcontra");
const $documento = $dom.querySelector("#Rdoc");
const $nombre = $dom.querySelector("#Rnom");
const $telefono = $dom.querySelector("#Rtel");
const $direccion = $dom.querySelector("#Rdirec");
const $email = $dom.querySelector("#Remail");
const $edad = $dom.querySelector("#Redad");

// Evento que se dispara cuando se envía el formulario de registro
$cambiar.addEventListener("submit", async function (event) {
    // Evita que el formulario se envíe de la manera tradicional
    event.preventDefault();
    
    // Validación de los campos requeridos en el formulario
    let num = validations(event, $input);

    // Si todos los campos están completos
    if (num === $input.length) {
        // Datos a enviar al servidor para registrar el nuevo usuario
        let datos = {
            "usuario": $usuario.value,
            "contrasena": $contrasena.value,
            "documento": $documento.value,
            "nombre": $nombre.value,
            "telefono": $telefono.value,
            "direccion": $direccion.value,
            "email": $email.value,
            "edad": $edad.value
        };

        // Realiza una solicitud AJAX para registrar el nuevo usuario
        let respuesta = await Ajax(servlet, datos, "POST", "RegistrarUsuario");

        // Si la respuesta es exitosa
        if (respuesta.resultado) {
            Mensaje($titleError, $paragrahp, error, "Éxito", "Usuario creado exitosamente");
            setTimeout(() => {
                window.location.href = '../index.jsp';         
            }, 2000);
        } else {
            // Si hubo un error en el registro
            Mensaje($titleError, $paragrahp, error, "Error", "El usuario no pudo ser registrado. Nombre de usuario ya registrado.");
        }
    } else {
        // Mensaje de error si hay campos vacíos
        Mensaje($titleError, $paragrahp, error, "Error campos vacíos", "Por favor llene todos los campos");
    }
});

// Validaciones de campos en tiempo real
$nombre.addEventListener("keypress", (event) => {
    validarString(event);
});
$documento.addEventListener("keypress", (event) => {
    validarNumber(event);
});
$telefono.addEventListener("keypress", (event) => {
    validarNumber(event);
});
$edad.addEventListener("keypress", (event) => {
    validarNumber(event);
});

// Validaciones en el desenfoque (blur) de los campos
$telefono.addEventListener("blur", (event) => {
    validarLength(event, $telefono);
});
$documento.addEventListener("blur", (event) => {
    validarLength(event, $documento);
});
$email.addEventListener("blur", (event) => {
    validarCorreo(event, $email);
});
