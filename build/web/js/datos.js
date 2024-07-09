/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */




//MODAL-----------------------------------------------------------------------

const $modal = document.getElementById("ventanaModal");


const $boton = document.querySelector(".cambiar__contraseña");


const $cerrar = document.getElementById("Cerrar__Modal");


$boton.addEventListener("click",function() {
  $modal.style.display = "block";
});

$cerrar.addEventListener("click", function () {
    $modal.style.display = "none";
});

window.addEventListener("click",function(event) {
  if (event.target == $modal) {
    $modal.style.display = "none";
  }
});



/* Validar Inputs del modal cambair contraseña que no esten vacios */ 

const $cambiar = document.querySelector(".Cambiar");

$cambiar.addEventListener("click", async function () {
    const $input = document.querySelectorAll("div.div__form > input.modal__input");
    $input.forEach(x => {
        if (x.value.length == 0) {
            x.classList.add("alert");
        }
        else{
            x.classList.remove("alert");
        }
    });
});

//----------------------------------------------------------------------------


document.addEventListener("DOMContentLoaded", function() {
    var idUsuario = localStorage.getItem("idUsuario");
    if (idUsuario) {
        console.log("ID del Usuario:", idUsuario);
        // Ahora puedes usar el idUsuario para cualquier operación que necesites
    } else {
        console.log("No se encontró el ID del usuario en localStorage.");
    }
});