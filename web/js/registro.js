/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

import Ajax from "./Modulos/ajax.js";
import Mensaje from "./Modulos/msjerror.js";
import validations from "./Modulos/Validaciones/vacio.js";
import validarCorreo from "./Modulos/Validaciones/correo.js";
import validarString from "./Modulos/Validaciones/letras.js";
import validarNumber from "./Modulos/Validaciones/numeros.js"
import validarLength from "./Modulos/Validaciones/length.js";


const $dom = document;
const servlet = "../Usuarios";
const $input = $dom.querySelectorAll(".login__form [required]");
const $cambiar = document.querySelector(".login__form");
const error = $dom.querySelector(".container__modal--error");
const $titleError = $dom.querySelector(".title_error");
const $paragrahp = $dom.querySelector(".paragrahp__error");

const $usuario = document.querySelector("#Ruser");
const $contrasena = document.querySelector("#Rcontra");
const $documento = document.querySelector("#Rdoc");
const $nombre = document.querySelector("#Rnom");
const $telefono = document.querySelector("#Rtel");
const $direccion = document.querySelector("#Rdirec");
const $email = document.querySelector("#Remail");
const $edad = document.querySelector("#Redad");
        
$cambiar.addEventListener("submit", async function () {
    const $input = document.querySelectorAll("div > input.login__input");
    let num = validations(event, $input);
    if (num === $input.length) {
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
        let respuesta = await Ajax(servlet,datos,"POST","RegistrarUsuario");   
        if (respuesta.resultado){
            Mensaje($titleError, $paragrahp, error, "Exito","Usuario creado exitosamente");
            setInterval(() => {
                window.location.href = '../index.jsp';         
            },2000);
        }
        else{
            Mensaje($titleError, $paragrahp, error, "Error","El usuario no pudo ser Registrado UserName ya registrado");
        }
    }
    else{
        Mensaje($titleError, $paragrahp, error, "Error campos vacios","Por favor llene todos los campos");
    }
});


$nombre.addEventListener("keypress", (event) => {
    validarString(event);
});
$documento.addEventListener("keypress",(event) => {
    validarNumber(event);
});
$telefono.addEventListener("keypress",(event) => {
    validarNumber(event);
});
$edad.addEventListener("keypress", (event) => {
    validarNumber(event);
});
$telefono.addEventListener("blur", (event) => {
    validarLength(event, $telefono);
});
$documento.addEventListener("blur", (event) => {
    validarLength(event, $documento);
});
$email.addEventListener("blur", (event) => {
    validarCorreo(event, $email);
});