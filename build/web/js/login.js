/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

const $dom = document;
const error = $dom.querySelector(".container__modal--error");
const $titleError = $dom.querySelector(".title_error");
const $paragrahp = $dom.querySelector(".paragrahp__error");
document.addEventListener('DOMContentLoaded', function() {
    const $cambiar = document.querySelector(".Ingresar");
    
    $cambiar.addEventListener("click", function() {
        const $input = document.querySelectorAll("div > input.login__input");
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
            const $usuario = document.getElementById("usuario").value;
            const $contraseña = document.getElementById("contraseña").value;
            
            let ope = new XMLHttpRequest();
            ope.open("POST", "Usuarios?action=ValidarLogin", true);
            ope.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            ope.onload = function(){
                if (ope.status === 200){
                    let respuesta = JSON.parse(ope.responseText);
                    if (respuesta.error){
                        $titleError.innerText = "Error en credenciales";
                        $paragrahp.innerText = "Usuaro o contraseña incorrectos";
                        error.style.display = "block";
                        setTimeout(() => {
                            error.style.display = "none";
                        }, 2000)
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
            };
            ope.send("usuario=" + $usuario + "&contrasena=" + $contraseña);
        }
        else{
            $titleError.innerText = "Error campos vacios";
            $paragrahp.innerText = "Por favor llene todos los campos";
            error.style.display = "block";
            setTimeout(() => {
                error.style.display = "none";
            }, 2000)
        }
    });
});

