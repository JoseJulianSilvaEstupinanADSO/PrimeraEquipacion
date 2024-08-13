/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

const $dom = document;

const error = $dom.querySelector(".container__modal--error");
const $titleError = $dom.querySelector(".title_error");
const $paragrahp = $dom.querySelector(".paragrahp__error");

const $modal = document.getElementById("ventanaModal");
const $boton = document.querySelector(".cambiar__contraseña");
const $cerrar = document.getElementById("Cerrar__Modal");

const $actual = $dom.querySelector(".password_actual");
const $nueva = $dom.querySelector(".password_nueva");
const $confirm = $dom.querySelector(".password_confrm");

function CloseSession(){
        let $session = localStorage.getItem("session");
        if ($session === "false" || $session === null) {
            console.log("cerrado");
            window.location.href = '../../index.jsp';
        };
    
}
CloseSession();

setInterval(() => {
    CloseSession();
}, 1000);


//MODAL-----------------------------------------------------------------------



$boton.addEventListener("click",function() {
  $modal.style.display = "block";
});

$cerrar.addEventListener("click", function () {
    $modal.classList.add("cerrando");
    setTimeout(function() {
        $modal.style.display = "none";
        $modal.classList.remove("cerrando");
    }, 500);
});
window.addEventListener("click",function(event) {
  if (event.target === $modal) {
    $modal.classList.add("cerrando");
    setTimeout(function() {
        $modal.style.display = "none";
        $modal.classList.remove("cerrando");
    }, 500);
  }
});


/* Validar Inputs del modal cambair contraseña que no esten vacios */ 

const $cambiar = document.querySelector(".Cambiar");

$cambiar.addEventListener("click", async function () {
    const $input = document.querySelectorAll("div.div__form > input.modal__input");
    let num = 0;
    $input.forEach(x => {
        if (x.value.length === 0) {
            x.classList.add("alert");
            $titleError.innerText = "Error campos vacios";
            $paragrahp.innerText = "Por favor llene todos los campos";
            error.style.display = "block";
            setTimeout(() => {
                error.style.display = "none";
            }, 2000);
        }
        else{
            x.classList.remove("alert");
            num+=1;
        }
    });
    
    if (num === $input.length) {
        
        let actual = $actual.value;
        let confirm = $confirm.value;
        let nueva = $nueva.value;
        
        if (nueva === confirm) {
            let idUsuario = localStorage.getItem("idUsuario");
            let ope = new XMLHttpRequest();
            ope.open("POST", "../../Usuarios?action=ModificarContraseña", true);
            ope.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            ope.onload = function() {
                if (ope.status === 200) {
                    let respuesta = JSON.parse(ope.responseText);
                    if (respuesta.resultado) {
                        let cambio = localStorage.getItem("session");
                        cambio = false;
                        localStorage.setItem("session", cambio);
                    }
                    else{
                        $titleError.innerText = "Error al cambiar la contraseña";
                        $paragrahp.innerText = "La contraseña actual no coincide con la registrada";
                        error.style.display = "block";
                        setTimeout(() => {
                            error.style.display = "none";
                        }, 2000);
                    }
                }
            };
            ope.send("id_usuario=" + idUsuario + "&contrasena=" + actual + "&nueva=" + nueva);
        }
        else{
            $titleError.innerText = "Error al confirmar la nueva contraseña";
            $paragrahp.innerText = "Por favor asegurese de que las dos contraseñas sean identicas";
            error.style.display = "block";
            setTimeout(() => {
                error.style.display = "none";
            }, 2000);
        }
        
    }
});

//----------------------------------------------------------------------------


document.addEventListener("DOMContentLoaded", function() {
    let idUsuario = localStorage.getItem("idUsuario");
    if (idUsuario) {
        
        let ope = new XMLHttpRequest();

        ope.open("POST", "../../Usuarios?action=BuscarUsuario", true);
        ope.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        ope.onload = function() {
            if(ope.status === 200){
                let respuesta = JSON.parse(ope.responseText);
                
                
                const $dom = document;
                
                const $id_user = $dom.querySelector('.form_Id');
                const $nombre_user = $dom.querySelector(".form_Nombre");
                const $doc_user = $dom.querySelector(".form_Doc");
                const $rol_user = $dom.querySelector(".form_Rol");
                const $user_user = $dom.querySelector(".form_User");
                const $tel_user = $dom.querySelector(".form_Tel");
                const $direc_user = $dom.querySelector(".form_Direc");
                const $email_user = $dom.querySelector(".form_Email");

                $id_user.value = respuesta.idUsuario;
                $nombre_user.value = respuesta.nombre;
                $doc_user.value = respuesta.documento;
                

                if(respuesta.rol === "null"){ 
                    $rol_user.value = "sleccionar";
                }
                else{
                    $rol_user.value = String(respuesta.rol);
                }

                $user_user.value = respuesta.usuario;
                $tel_user.value = respuesta.telefono;
                $direc_user.value = respuesta.direccion;
                $email_user.value = respuesta.correo;

            }

        };
        ope.send("id=" + idUsuario);
            
        
        
        // Ahora puedes usar el idUsuario para cualquier operación que necesites
    } else {
        console.log("No se encontró el ID del usuario en localStorage.");
    }
});



function Modificar(event){
    event.preventDefault();
     const $input = document.querySelectorAll("div > input.input__modal");
        let num = 0;
        $input.forEach(x => {
            let p = x.nextElementSibling;
            if (x.value.length === 0) {
                x.classList.add("alert");     
            } else {
                x.classList.remove("alert");
                num += 1;
            }
        });
        
        if(num === $input.length){
            

            const $id = $dom.querySelector(".form_Id").value;
            const $nombre = $dom.querySelector(".form_Nombre").value;
            const $documento = $dom.querySelector(".form_Doc").value;
            const $rol = $dom.querySelector(".form_Rol").value;
            const $usuario = $dom.querySelector(".form_User").value;
            const $telefono = $dom.querySelector(".form_Tel").value;
            const $direccion = $dom.querySelector(".form_Direc").value;
            const $correo = $dom.querySelector(".form_Email").value;


           let ope = new XMLHttpRequest();
           ope.open("POST", "../../Usuarios?action=ModificarUsuario", true);
           ope.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
           ope.onload = function () {
             if(ope.status === 200){
                 let respuesta = JSON.parse(ope.responseText);
                 
                 if(respuesta.resultado){
                     
                    $titleError.innerText = "Exito";
                    $paragrahp.innerText = "Usuario Modificado";
                    error.style.display = "block";
                    setTimeout(() => {
                        error.style.display = "none";
                    }, 2000);

                 }
                 else{
                    $titleError.innerText = "Error al modificar el usuario";
                    $paragrahp.innerText = "Hubo algun conflicto con los datos, Intentelo denuevo";
                    error.style.display = "block";
                    setTimeout(() => {
                        error.style.display = "none";
                    }, 2000);
                 }
             }  
           };
           ope.send("id=" + $id + "&id_rol=" + $rol + "&nombre=" + $nombre + "&documento=" + $documento + "&usuario=" + $usuario + "&telefono=" + $telefono + "&direccion=" + $direccion + "&correo=" + $correo + "&contrasena=" + null);

        }
        else{
            $titleError.innerText = "Capos vacios";
            $paragrahp.innerText = "Favor llenar todos los campos";
            error.style.display = "block";
            setTimeout(() => {
                error.style.display = "none";
            }, 2000);
        }

}

const $BtnModificar = $dom.querySelector(".GuardarCambios");

$BtnModificar.addEventListener("click", Modificar);