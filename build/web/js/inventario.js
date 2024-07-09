/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */



const $dom = document;

/*Se cambia el color de las filas inpares */
(() =>{
    const $filas = $dom.querySelectorAll("tbody.body__tabla > tr.fila__tabla");
    $filas.forEach((fila,index) =>{
        if (index % 2 !== 0) {
            fila.style.background = "#DBDADA";
        }
    });
})
();

const $modal = $dom.getElementById("ventanaModal");

const $agregar = $dom.querySelector(".agregar__producto");


$agregar.addEventListener("click", function () {
    $modal.style.display = "block";
    const $btn = $dom.querySelector(".button__modal--inventario");
    $btn.innerText = "Agregar Producto";
    const $talla = $dom.querySelector(".form_talla");
    $talla.remove();

    const $cambio = $dom.querySelector("#selec");
    const $selec = $dom.createElement("select");
    const $option = $dom.createElement("option");

    $selec.classList.add("input__modal");
    $selec.classList.add("form_talla");
    $option.innerText = "Seleccione la talla";

    $selec.appendChild($option);
    $cambio.appendChild($selec);

    const $id_producto = $dom.querySelector('.form_Id');
    const $nombre_producto = $dom.querySelector(".form_Nombre");
    const $precio_producto = $dom.querySelector(".form_Precio");
    const $stock_producto = $dom.querySelector(".form_Stock");
    const $tela_producto = $dom.querySelector(".form_Tela");

    $id_producto.value = "";
    $nombre_producto.value = "";
    $precio_producto.value = "";
    $stock_producto.value = "";
    $tela_producto.value = "";
});







/* Se crea un evento que al dar clicl en la tabla al boton editar se abro el modal 
y en cada input del formulario se llena con la informacion del producto de dicha fila*/ 

const table = $dom.querySelector('.tabla--factura');

table.addEventListener('click', function(event) {
   if (event.target.classList.contains('button__tabla')) {
    const $modal = $dom.getElementById("ventanaModal");
    const $btn = $dom.querySelector(".button__modal--inventario");
    $btn.innerText = "Guardar Cambios";


    $modal.style.display = "block";
    const row = event.target.closest('tr');
    const $id = row.querySelector('td.IdProducto');
    const $nombre = row.querySelector("td.NombreProducto");
    const $precio = row.querySelector("td.PrecioProducto");
    const $talla = row.querySelector("td.TallaProducto");
    const $stock = row.querySelector("td.StockProducto");
    const $tela = row.querySelector("td.TelaProducto");

    

    let id = Number($id.innerText);
    let nombre = $nombre.innerText;
    let precio = Number($precio.innerText);
    let talla = $talla.innerText;
    let stock = Number($stock.innerText);
    let tela = $tela.innerText;

    const $id_producto = $dom.querySelector('.form_Id');
    const $nombre_producto = $dom.querySelector(".form_Nombre");
    const $precio_producto = $dom.querySelector(".form_Precio");
    const $talla_producto = $dom.querySelector(".form_talla");
    const $stock_producto = $dom.querySelector(".form_Stock");
    const $tela_producto = $dom.querySelector(".form_Tela");

    $talla_producto.remove();

    const $cambio = $dom.querySelector("#selec");
    const $input = $dom.createElement("input");


    $input.classList.add("input__modal");
    $input.classList.add("form_talla");

    $cambio.appendChild($input);

    $id_producto.value = id;
    $nombre_producto.value = nombre;
    $precio_producto.value = precio;
    $input.value = talla;
    $stock_producto.value = stock;
    $tela_producto.value = tela;

    $id_producto.setAttribute("readonly","");
    $input.setAttribute("readonly","");
    $tela_producto.setAttribute("readonly","");

   }
 });




/**
 * Se crean los eventos para cerrar el modal
 * 
 */

const $cerrar = $dom.querySelector(".cerrar__x");

const $cancelar = $dom.querySelector(".modal__cancelar");

$cerrar.addEventListener("click", function () {
    $modal.style.display = "none";
});
$cancelar.addEventListener("click", function () {
    $modal.style.display = "none";
});
window.addEventListener("click",function(event) {
  if (event.target == $modal) {
    $modal.style.display = "none";
  }
});