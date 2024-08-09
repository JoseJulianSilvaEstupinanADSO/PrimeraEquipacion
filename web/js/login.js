/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */
import Ajax from "./Modulos/ajax.js";
import Mensaje from "./Modulos/msjerror.js";

const $dom = document;
const servlet = "Usuarios";

const error = $dom.querySelector(".container__modal--error");
const $titleError = $dom.querySelector(".title_error");
const $paragrahp = $dom.querySelector(".paragrahp__error");

const $cambiar = document.querySelector(".Ingresar");
const $input = document.querySelectorAll("div > input.login__input");

const $usuario = document.getElementById("usuario");
const $contraseña = document.getElementById("contraseña");

document.addEventListener('DOMContentLoaded', function() {
    
    $cambiar.addEventListener("click", async function() {
        let num = 0;
        $input.forEach(x => {
            let p = x.nextElementSibling;
            if (x.value.length === 0) {
                x.classList.add("alert"); 
                p.classList.add("vacio--ver")  ;      
            } else {
                x.classList.remove("alert");
                p.classList.remove("vacio--ver") ;  
                num += 1;
            }
        });

        if (num === $input.length) {

            let datos = {
                "usuario": $usuario.value,
                "contrasena" :$contraseña.value
            };
            
            let respuesta = await Ajax(servlet ,datos,"POST", "ValidarLogin");
            if (respuesta.error){
                    Mensaje($titleError, $paragrahp, error, "Error en credenciales","Usuaro o contraseña incorrectos"
                );
            }
            else{
                localStorage.setItem("idUsuario", respuesta.idUsuario);
                localStorage.setItem("session", true);

                switch (respuesta.rol){
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
        }
        else{
            Mensaje($titleError, $paragrahp, error, "Error campos vacios","Por favor llene todos los campos");
        }
    });
});

