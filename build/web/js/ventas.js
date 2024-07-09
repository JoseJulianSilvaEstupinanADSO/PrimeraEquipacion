/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */




const $dom = document;

//MODAL-----------------------------------------------------------------------

const $modal = document.getElementById("ventanaModal");


const $boton = document.getElementById("abrirModal");


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


//----------------------------------------------------------------------------


 document.addEventListener('DOMContentLoaded', function() {
  const botonesEliminar = document.querySelectorAll('.BtnEliminar');

  botonesEliminar.forEach(boton => {
      boton.addEventListener('click', function() {
          const fila = this.closest('tr'); // Selecciona la fila más cercana al botón clicado
          fila.remove(); // Elimina la fila
      });
  });
});



