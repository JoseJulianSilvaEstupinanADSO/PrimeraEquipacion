/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */


console.log(localStorage.getItem("session"));


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
                        alert(respuesta.error);
                    }
                    else{
                        alert("Bienvenido " + respuesta.nombre);
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
    });
});

