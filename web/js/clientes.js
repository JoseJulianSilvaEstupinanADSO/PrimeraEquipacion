/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */


const $dom = document;


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


//Cambiar colores de las filas impares De las tablas //

(() =>{
    const $filas = $dom.querySelectorAll("tbody.body__tabla > tr.fila__tabla");
    $filas.forEach((fila,index) =>{
        if (index % 2 !== 0) {
            fila.style.background = "#DBDADA";
        }
    });
})
();


//Abrir modal y poner el id y fecha de facturachion el en modal//------------

const table = $dom.querySelector('.tabla--factura');

table.addEventListener('click', function(event) {
   if (event.target.classList.contains('button__tabla')) {
    const $modal = $dom.getElementById("ventanaModal");
    $modal.style.display = "block";

     const row = event.target.closest('tr');
     const $id = row.querySelector('td.IdCliente');



     let id = Number($id.innerText);


     const $id_form = $dom.querySelector('.Numero_factura');


     $id_form.innerText = "Numero: " + id;

   }
 });


 //CERRAR MODAL

 const $modal = $dom.getElementById("ventanaModal");

 const $cerrar = $dom.querySelector(".cerrar__x");
 
 
$cerrar.addEventListener("click", function () {
    $modal.classList.add("cerrando");
    setTimeout(function() {
        $modal.style.display = "none";
        $modal.classList.remove("cerrando");
    }, 500);
});
window.addEventListener("click",function(event) {
  if (event.target == $modal) {
    $modal.classList.add("cerrando");
    setTimeout(function() {
        $modal.style.display = "none";
        $modal.classList.remove("cerrando");
    }, 500);
  }
});
//----------------------------------------------------------------------------
