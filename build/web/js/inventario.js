/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

import Ajax from "./Modulos/ajax.js";
import Mensaje from "./Modulos/msjerror.js";
import validations from "./Modulos/Validaciones/vacio.js";
import validarCorreo from "./Modulos/Validaciones/correo.js";
import validarString from "./Modulos/Validaciones/letras.js";
import validarNumber from "./Modulos/Validaciones/numeros.js"
import validarLength from "./Modulos/Validaciones/length.js";

const $dom = document;
const servlet = "../../Productos";

const error = $dom.querySelector(".container__modal--error");
const $titleError = $dom.querySelector(".title_error");
const $paragrahp = $dom.querySelector(".paragrahp__error");

const $cerrar = $dom.querySelector(".cerrar__x");
const $cancelar = $dom.querySelector(".modal__cancelar");

const $modal = $dom.getElementById("ventanaModal");
const $agregar = $dom.querySelector(".agregar__producto");
const $btn = $dom.querySelector(".button__modal--inventario");

const $id_producto = $dom.querySelector('.form_Id');
const $nombre_producto = $dom.querySelector(".form_Nombre");
const $precio_producto = $dom.querySelector(".form_Precio");
const $stock_producto = $dom.querySelector(".form_Stock");
const $tela_producto = $dom.querySelector(".form_Tela");
const $estado = $dom.querySelector("#estado");

const table = $dom.querySelector('.tabla--factura');
const $inputs = $dom.querySelectorAll("form.form__modal--inventario [required]");

const $tbody = $dom.querySelector(".body__tabla");
const $BtnBuscar = $dom.querySelector(".button__buscar");

    
function CloseSession(){
        let $session = localStorage.getItem("session");
        if ($session === "false" || $session === null) {
            console.log("cerrado");
            window.location.href = '../../index.jsp';
        };
    
}
setInterval(() => {
    CloseSession();
}, 1000);

function CloseModal() {   
    $modal.classList.add("cerrando");
    setTimeout(function() {
        $modal.style.display = "none";
        $modal.classList.remove("cerrando");
    }, 500);
}


/**
 * Se crean los eventos para cerrar el modal
 * 
 */

$cerrar.addEventListener("click", function () {
    $modal.classList.add("cerrando");
    setTimeout(function() {
        $modal.style.display = "none";
        $modal.classList.remove("cerrando");
    }, 500);
});
$cancelar.addEventListener("click", function () {
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



/*Se cambia el color de las filas inpares */
function filas(){
    let $filas = $dom.querySelectorAll("tbody.body__tabla > tr.fila__tabla");
        $filas.forEach((fila,index) =>{
            if (index % 2 !== 0) {
                fila.style.background = "#DBDADA";
            }
        });  
}

$agregar.addEventListener("click", function () {
    
    const $talla = $dom.querySelector(".form_talla");
    
    $modal.style.display = "block";
    $btn.classList.add("agregar__producto");
    $btn.classList.remove("modificar__producto");
    $btn.innerText = "Agregar Producto";
    $talla.remove();

    const $cambio = $dom.querySelector("#selec");
    const $selec = $dom.createElement("select");
    const $option = $dom.createElement("option");

    $selec.classList.add("input__modal");
    $selec.classList.add("form_talla");
    $option.innerText = "Seleccione la talla";

    $selec.appendChild($option);
    $cambio.appendChild($selec);
    
    $tela_producto.removeAttribute("readonly", "");
    
    $id_producto.value = "";
    $nombre_producto.value = "";
    $precio_producto.value = "";
    $stock_producto.value = "";
    $tela_producto.value = "";
    $estado.value = "Seleccionar";
    
});







/* Se crea un evento que al dar clicl en la tabla al boton editar se abro el modal 
y en cada input del formulario se llena con la informacion del producto de dicha fila*/ 


table.addEventListener('click', function(event) {
   if (event.target.classList.contains('button__tabla')) {
    $btn.innerText = "Guardar Cambios";
    $btn.classList.remove("agregar__producto");
    $btn.classList.add("modificar__producto");


    $modal.style.display = "block";
    const row = event.target.closest('tr');
    const $id = row.querySelector('td.IdProducto');
    const $nombre = row.querySelector("td.NombreProducto");
    const $precio = row.querySelector("td.PrecioProducto");
    const $talla = row.querySelector("td.TallaProducto");
    const $stock = row.querySelector("td.StockProducto");
    const $tela = row.querySelector("td.TelaProducto");
    const $estado_t = row.querySelector("td.pro_estado");
    
    let id = Number($id.innerText);
    let nombre = $nombre.innerText;
    let precio = Number($precio.innerText);
    let talla = $talla.innerText;
    let stock = Number($stock.innerText);
    let tela = $tela.innerText;
    let estado = $estado_t.innerText;
    
    const $talla_producto = $dom.querySelector(".form_talla");

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
    $estado.value = Number(estado);

    $id_producto.setAttribute("readonly","");
    $input.setAttribute("readonly","");
    $tela_producto.setAttribute("readonly","");

   }
 });
//Listar Las Tallas en el slec//

async function ListarTallas() {
    const $tallas = $dom.querySelector("select.form_talla");  
    let respuesta = await Ajax(servlet,"","GET","ListarTallas");          
    respuesta.forEach((x) => {
       let option = $dom.createElement("option") ;
       option.innerText = x.talla;
       $tallas.appendChild(option);
    });
}

//Agregar un Producto // 

const $bnt_modal = $dom.querySelector(".button__modal--inventario");

async function AgregarModificarProducto() {
    
    const $id_producto = $dom.querySelector('.form_Id').value;
    
    const $talla_producto = $dom.querySelector(".form_talla").value;
    const $estado = $dom.querySelector("#estado").value;
    console.log($talla_producto)
    let num = validations(event, $inputs);
    console.log(num)
    if($bnt_modal.classList.contains('agregar__producto')){
        console.log($inputs + " " + num);
        if(num === $inputs.length && $estado != "Seleccionar" && $talla_producto != "Seleccione la talla"){
            
            let datos = {
                "nombre": $nombre_producto.value,
                "precio": $precio_producto.value,
                "talla": $talla_producto,
                "stock": $stock_producto.value,
                "tela": $tela_producto.value,
                "estado": $estado
            };
            let respuesta = await Ajax(servlet,datos,"POST","RegistrarProducto");   
            
            if(respuesta.resultado){
                Mensaje($titleError, $paragrahp, error, "Exito","Producto agregado");
                CloseModal();
                ListarProductos();
            }
            else{
                Mensaje($titleError, $paragrahp, error, "Error al agregar","Verifique que los campos coincidan con el tipo de dato");
            }


        }
        else{
            Mensaje($titleError, $paragrahp, error, "Error campos vacios","Por favor llene todos los campos");
        }

    }
    if($bnt_modal.classList.contains('modificar__producto')){
        const $id_producto = $dom.querySelector('.form_Id').value;
        const $talla_producto = $dom.querySelector(".form_talla").value;
        const $estado = $dom.querySelector("#estado").value;
        let datos = {
            "id_producto": $id_producto,
            "nombre": $nombre_producto.value,
            "precio": $precio_producto.value,
            "talla": $talla_producto,
            "stock": $stock_producto.value,
            "tela": $tela_producto.value,
            "estado": $estado
        };
        let respuesta = await Ajax(servlet,datos,"POST","ModificarProducto");
        if(respuesta.resultado){
            Mensaje($titleError, $paragrahp, error, "Exito","Producto Modificado");
            CloseModal();
            ListarProductos();
        }
        else{
            Mensaje($titleError, $paragrahp, error, "Error al Modificar","Verifique que los campos coincidan con el tipo de datos");
        }

    }    
}

// Listar Produectos //

async function ListarProductos() {
    
    const  $filas = $dom.querySelectorAll("tbody.body__tabla > tr.fila__tabla");
    $filas.forEach((x) =>{
        x.remove();
    });
    
    let respuesta = await Ajax(servlet,"","GET","ListarProductos");
    const $frag = $dom.createDocumentFragment();

    respuesta.forEach((x) => {

        const $fila = $dom.createElement("tr");
        $fila.classList.add("fila__tabla"); 

        const $colId = $dom.createElement("td");
        $colId.classList.add("td__tabla");
        $colId.classList.add("IdProducto");

        $colId.innerText = x.id_producto;

        const $colNom = $dom.createElement("td");
        $colNom.classList.add("td__tabla");
        $colNom.classList.add("NombreProducto");

        $colNom.innerText = x.nombre;

        const $colPre = $dom.createElement("td");
        $colPre.classList.add("td__tabla");
        $colPre.classList.add("PrecioProducto");

        $colPre.innerText = x.precio;

        const $colTalla = $dom.createElement("td");
        $colTalla.classList.add("td__tabla");
        $colTalla.classList.add("TallaProducto");

        $colTalla.innerText = x.talla;

        const $colStock = $dom.createElement("td");
        $colStock.classList.add("td__tabla");
        $colStock.classList.add("StockProducto");

        $colStock.innerText = x.stock;

        const $colTela = $dom.createElement("td");
        $colTela.classList.add("td__tabla");
        $colTela.classList.add("TelaProducto");

        $colTela.innerText = x.tela;

        const $colEs = $dom.createElement("td");
        $colEs.classList.add("td__tabla");
        $colEs.classList.add("pro_estado");

        $colEs.innerText = x.estado;

        const $colBtn = $dom.createElement("td");
        $colBtn.classList.add("td__tabla");


        const $BtnEdit = $dom.createElement("button");
        $BtnEdit.classList.add("button__tabla");

        $BtnEdit.innerText = "Editar";

        $colBtn.appendChild($BtnEdit);
        $fila.appendChild($colId);
        $fila.appendChild($colNom);
        $fila.appendChild($colPre);
        $fila.appendChild($colTalla);
        $fila.appendChild($colStock);
        $fila.appendChild($colTela);
        $fila.appendChild($colEs);
        $fila.appendChild($colBtn);

        $frag.appendChild($fila);
    });
    $tbody.appendChild($frag);
    filas();
}
ListarProductos();


//Buscar Cliente en especifico//

function Buscar(){
    const $label = $dom.querySelector(".input__buscar").value;
    const $filas = document.querySelectorAll("tbody.body__tabla > tr.fila__tabla");
    let num = 0;
    
    $filas.forEach((fila) => {
        let id = fila.querySelector(".IdProducto").innerText;
        let nombre = fila.querySelector(".NombreProducto").innerText;
        
        if (id !== $label){
            fila.style.display = "none";
            num = num+1;
        }
        else{
            fila.style.display = "";
        }
    });
    
    if (num === $filas.length){
        ListarProductos();
        $titleError.innerText = "Producto no encontrado";
        $paragrahp.innerText = "Verifique el id del producto";
        error.style.display = "block";
        setTimeout(() => {
            error.style.display = "none";
        }, 2000);
    }
}

$BtnBuscar.addEventListener("click", Buscar);


$agregar.addEventListener("click", ListarTallas);
$bnt_modal.addEventListener("click",AgregarModificarProducto);