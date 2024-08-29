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
const servlet = "../../Usuarios";

// Elementos relacionados con el modal de error y la actualización de datos del usuario
const error = $dom.querySelector(".container__modal--error");
const $titleError = $dom.querySelector(".title_error");
const $paragrahp = $dom.querySelector(".paragrahp__error");

const $modal = $dom.getElementById("ventanaModal");
const $boton = $dom.querySelector(".cambiar__contraseña");
const $cerrar = $dom.getElementById("Cerrar__Modal");

// Elementos del formulario para modificar los datos del usuario
const $id_user = $dom.querySelector('.form_Id');
const $nombre_user = $dom.querySelector(".form_Nombre");
const $doc_user = $dom.querySelector(".form_Doc");
const $rol_user = $dom.querySelector(".form_Rol");
const $user_user = $dom.querySelector(".form_User");
const $tel_user = $dom.querySelector(".form_Tel");
const $direc_user = $dom.querySelector(".form_Direc");
const $email_user = $dom.querySelector(".form_Email");

// Elementos del formulario para cambiar la contraseña
const $actual = $dom.querySelector(".password_actual");
const $nueva = $dom.querySelector(".password_nueva");
const $confirm = $dom.querySelector(".password_confrm");

const $cambiar = $dom.querySelector(".Cambiar");

// Selección de campos obligatorios para validación
const $input = $dom.querySelectorAll(".form__user [required]");
const $input_password = $dom.querySelectorAll(".form__password [required]");

// Función para verificar el estado de la sesión y redirigir si la sesión está cerrada
function CloseSession(){
    let $session = localStorage.getItem("session");
    if ($session === "false" || $session === null) {
        console.log("cerrado");
        window.location.href = '../../index.jsp';
    }
}
CloseSession();

// Verificar el estado de la sesión cada segundo
setInterval(() => {
    CloseSession();
}, 1000);

// Mostrar el modal para cambiar la contraseña al hacer clic en el botón correspondiente
$boton.addEventListener("click", function() {
    $modal.style.display = "block";
});

// Cerrar el modal al hacer clic en el botón de cerrar o fuera del modal
$cerrar.addEventListener("click", function () {
    $modal.classList.add("cerrando");
    setTimeout(function() {
        $modal.style.display = "none";
        $modal.classList.remove("cerrando");
    }, 500);
});

window.addEventListener("click", function(event) {
    if (event.target === $modal) {
        $modal.classList.add("cerrando");
        setTimeout(function() {
            $modal.style.display = "none";
            $modal.classList.remove("cerrando");
        }, 500);
    }
});

/* Validar campos del modal para cambiar la contraseña */

$cambiar.addEventListener("click", async function () {
    let num = validations(event, $input_password);

    if (num === $input_password.length) {
        let idUsuario = localStorage.getItem("idUsuario");

        let datos = {
            "id_usuario": idUsuario,
            "contrasena": $actual.value,
            "nueva": $nueva.value
        };

        if ($nueva.value === $confirm.value) {
            let respuesta = await Ajax(servlet, datos, "POST", "ModificarContraseña");

            if (respuesta.resultado) {
                let cambio = localStorage.getItem("session");
                cambio = false;
                localStorage.setItem("session", cambio);
            } else {
                Mensaje($titleError, $paragrahp, error, "Error al cambiar la contraseña", "La contraseña actual no coincide con la registrada");
            }
        } else {
            Mensaje($titleError, $paragrahp, error, "Error al confirmar la nueva contraseña", "Por favor asegurese de que las dos contraseñas sean identicas");
        }
    } else {
        Mensaje($titleError, $paragrahp, error, "Error campos vacios", "Por favor asegurese de llenar todos los campos");
    }
});

// Cargar y mostrar los datos del usuario cuando el documento esté completamente cargado
document.addEventListener("DOMContentLoaded", async function() {
    let idUsuario = localStorage.getItem("idUsuario");
    if (idUsuario) {
        let datos = {
            "id": idUsuario
        };

        let respuesta = await Ajax(servlet, datos, "POST", "BuscarUsuario");

        $id_user.value = respuesta.idUsuario;
        $nombre_user.value = respuesta.nombre;
        $doc_user.value = respuesta.documento;

        if (respuesta.rol === "null") { 
            $rol_user.value = "seleccionar";
        } else {
            $rol_user.value = String(respuesta.rol);
        }

        $user_user.value = respuesta.usuario;
        $tel_user.value = respuesta.telefono;
        $direc_user.value = respuesta.direccion;
        $email_user.value = respuesta.correo;
    } else {
        console.log("No se encontró el ID del usuario en localStorage.");
    }
});

// Función para modificar los datos del usuario
async function Modificar(event) {
    event.preventDefault();
    let num = validations(event, $input, $rol_user.value);

    if (num === $input.length) {
        let correo = validarCorreo(event, $email_user);
        let tel = validarLength(event, $tel_user);
        
        if (correo && tel) {
            let datos = {
                "id": $id_user.value,
                "id_rol": $rol_user.value,
                "nombre": $nombre_user.value,
                "documento": $doc_user.value,
                "usuario": $user_user.value,
                "telefono": $tel_user.value,
                "direccion": $direc_user.value,
                "correo": $email_user.value,
                "contrasena": null
            };

            let respuesta = await Ajax(servlet, datos, "POST", "ModificarUsuario");

            if (respuesta.resultado) {
                Mensaje($titleError, $paragrahp, error, "Éxito", "Usuario Modificado");
            } else {
                Mensaje($titleError, $paragrahp, error, "Error al modificar el usuario", "Hubo algún conflicto con los datos, inténtelo de nuevo");
            }
        } else {
            Mensaje($titleError, $paragrahp, error, "Correo no válido", "Favor revise que el correo sea correcto");
        }
    } else {
        Mensaje($titleError, $paragrahp, error, "Campos vacíos", "Favor llenar todos los campos");
    }
}

// Evento para guardar cambios en el usuario al hacer clic en el botón correspondiente
const $BtnModificar = $dom.querySelector(".GuardarCambios");
$BtnModificar.addEventListener("click", Modificar);

// Validaciones en tiempo real para los campos del formulario
$nombre_user.addEventListener("keypress", (event) => {
    validarString(event);
});

$tel_user.addEventListener("keypress", (event) => {
    validarNumber(event);
});

$tel_user.addEventListener("blur", (event) => {
    validarLength(event, $tel_user);
});

$email_user.addEventListener("blur", (event) => {
    validarCorreo(event, $email_user);
});
