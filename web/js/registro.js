/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */


const $cambiar = document.querySelector(".Registrar");

$cambiar.addEventListener("click", function () {
    const $input = document.querySelectorAll("div > input.login__input");
    let num = 0;
    $input.forEach(x => {
        let p = x.nextElementSibling;
        if (x.value.length === 0) {
            x.classList.add("alert"); 
            p.classList.add("vacio--ver");        
        } else {
            x.classList.remove("alert");
            p.classList.remove("vacio--ver");   
            num += 1;
        }
    });
    if (num === $input.length) {
        
        const $usuario = document.querySelector("#Ruser").value;
        const $contrasena = document.querySelector("#Rcontra").value;
        const $documento = document.querySelector("#Rdoc").value;
        const $nombre = document.querySelector("#Rnom").value;
        const $telefono = document.querySelector("#Rtel").value;
        const $direccion = document.querySelector("#Rdirec").value;
        const $email = document.querySelector("#Remail").value;
        const $edad = document.querySelector("#Redad").value;
        
        let ope = new XMLHttpRequest();
        ope.open("POST","../Usuarios?action=RegistrarUsuario",true);
        ope.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        ope.onload = function(){
            if (ope.status === 200){
                let respuesta = JSON.parse(ope.responseText);
                if (respuesta.resultado){
                    alert(respuesta.resultado);
                    alert("Usuario Creado");
                    window.location.href = '../index.jsp';
                }
                else{
                    alert("El usuario no pudo ser Registrado UserName ya registrado");
                }
            }
        };
        ope.send("usuario=" + $usuario + "&contrasena=" + $contrasena + "&documento=" + $documento + "&nombre=" + $nombre + "&telefono=" + $telefono + "&direccion=" + $direccion + "&email=" + $email + "&edad=" + $edad);

    };
});
