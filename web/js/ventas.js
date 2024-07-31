/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */



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

const $dom = document;

//MODAL-----------------------------------------------------------------------

const $modal = document.getElementById("ventanaModal");


const $boton = document.getElementById("abrirModal");


const $cerrar = document.getElementById("Cerrar__Modal");


function CloseModal() {   
    $modal.classList.add("cerrando");
    setTimeout(function() {
        $modal.style.display = "none";
        $modal.classList.remove("cerrando");
    }, 500);
}


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
  if (event.target == $modal) {
    $modal.classList.add("cerrando");
    setTimeout(function() {
        $modal.style.display = "none";
        $modal.classList.remove("cerrando");
    }, 500);
  }
});

//----------------------------------------------------------------------------

function eliminar(){
    
    return  botonesEliminar = document.querySelectorAll('.BtnEliminar');    
}


 document.addEventListener('DOMContentLoaded', function() {
     
     
  setInterval(() => {
    const botonesEliminar = eliminar();    
    botonesEliminar.forEach(boton => {
        boton.addEventListener('click', function() {
            const fila = this.closest('tr'); // Selecciona la fila más cercana al botón clicado
            fila.remove(); // Elimina la fila
            total();
        });
    }); 
}, 1000);   
});


// Registrar cliente //

function RegistarCliente() {
    
    event.preventDefault();
    
    let $documento = document.querySelector(".documento_cliente").value;
    let $nombre = document.querySelector(".nombre_cliente").value;
    let $telefono = document.querySelector(".tefelono_cliente").value;
   
    
    let num = 0;
    
    if ($documento.length > 0 && $nombre.length > 0 && $telefono.length > 0) {
        num = 1;
    }
    
    if (num === 1) {
        
        let ope = new XMLHttpRequest();
        ope.open("POST", "../../Clientes?action=RegistrarUsuario", true);
        ope.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        ope.onload = function(){
            if (ope.status === 200) {
                let respuesta = JSON.parse(ope.responseText);
                console.log(respuesta);
                if (respuesta.resultado) {
                    const $modal = document.getElementById("ventanaModal");
                    alert("Cliente Registrado");
                    CloseModal();
                    
                }
                else{
                    alert("Error al Registrar el cliente");
                }
            }  
        };
        ope.send("nombre=" + $nombre + "&documento=" + $documento + "&telefono=" + $telefono);
    }
    else{
        aler("Campos vacion");
    }
    
    

}


const $BtnRegistrar = document.querySelector(".registar__clientes");

$BtnRegistrar.addEventListener("submit", RegistarCliente);



// Bucar Cliente   //

function BuscarCliente() {
    const $documento = $dom.querySelector(".documento").value;
    if($documento !== ""){
        
        let ope = new XMLHttpRequest();
        ope.open("POST", "../../Clientes?action=BuscarCliente", true);
        ope.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        ope.onload = function() {
            if (ope.status === 200){
                let respuesta = JSON.parse(ope.responseText);
                if (respuesta.error){
                    alert("Usuario no encontrado");
                }
                else{
                    console.log(respuesta.nombre);
                    const $label = $dom.querySelector(".insertDocumento");
                    $label.value = respuesta.nombre;
                }
            }  
        };
        
    ope.send("documento=" + $documento);
    }
}

const Btn_buscar_cliente = document.querySelector(".Btn_busacar_cliente");

Btn_buscar_cliente.addEventListener("click", BuscarCliente);


//Listar las tallas en la vista ventas//

function ListarTallas() {
    const $tallas = $dom.querySelector("select.tallas");    
    let ope = new XMLHttpRequest();
    ope.open("GET", "../../Productos?action=ListarTallas", true);
    ope.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    ope.onload = function () {
        if (ope.status === 200){
            let respuesta = JSON.parse(ope.responseText);        
            respuesta.forEach((x) => {
               let option = $dom.createElement("option") ;
               option.innerText = x.talla;
               $tallas.appendChild(option);
            });
            
        }
    };
    ope.send();
}
ListarTallas();

//Buscar Producto para agregarlo a la factura//

const $buscarp = $dom.querySelector("button#buscar_producto");

function BuscarProducto() {
    $label = $dom.querySelector("input#cdProducto");
    $selec = $dom.querySelector("select#tallas");
    
    let codigo = $label.value;
    let talla = $selec.value;
    
    let num = 0;
    
    if (codigo !== ""){
        $label.classList.remove("alert");
        num+=1;
    }
    else{
        $label.classList.add("alert");
    }
    if (talla !== "Seleccionar Talla"){
        $selec.classList.remove("alert"); 
        num+=1;
    }
    else{
        $selec.classList.add("alert");
    }
    if (num === 2){
        
        let ope = new XMLHttpRequest();
        ope.open("POST", "../../Productos?action=BuscarProducto", true);
        ope.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        ope.onload = function() {
            if (ope.status === 200){
                let respuesta = JSON.parse(ope.responseText);
                let $nombre = $dom.querySelector("#nombre_produc");
                let $precio = $dom.querySelector("#precio_produc");
                $nombre.value = respuesta.nombre;
                $precio.value = respuesta.precio;
                
            }
        };
        ope.send("id_producto=" + codigo + "&talla=" + talla );
    }
}

$buscarp.addEventListener("click", BuscarProducto);


//Agregar Productos a la tabla Factura//

const $pro_items = $dom.querySelectorAll("input[required]");
const $btn_agregar_pro = $dom.querySelector("#agregar_producto");

function AgragarProdutoTabla(items) {
    let num = 0;
    items.forEach((x) => {
        if (x.value !== "") {
            num+=1;
            x.classList.remove("alert");
        }
        else{
            x.classList.add("alert");
        }
    });
    if (num === items.length){
        const $tbody = $dom.querySelector("tbody.tabla__tb");
        
        const $id = $dom.querySelector("#cdProducto").value;
        const $nombre = $dom.querySelector("#nombre_produc").value;
        const $talla = $dom.querySelector("#tallas");
        const $precio = $dom.querySelector("#precio_produc").value;
        const $cant = $dom.querySelector("#cant_produc").value;
        
        talla = $talla.value;
        
        const $tr = $dom.createElement("tr");
        $tr.classList.add("tabla__fila");
        
        const $td1 = $dom.createElement("td");
        $td1.classList.add("id__producto");
        $td1.classList.add("tabla__td");
        $td1.innerText = $id;
        
        const $td2 = $dom.createElement("td");
        $td2.classList.add("nombre__producto");
        $td2.classList.add("tabla__td");
        $td2.innerText = $nombre;
        
        const $td3 = $dom.createElement("td");
        $td3.classList.add("talla__producto");
        $td3.classList.add("tabla__td");
        $td3.innerText = talla;
        
        
        const $td4 = $dom.createElement("td");
        $td4.classList.add("precio__producto");
        $td4.classList.add("tabla__td");
        $td4.innerText = Number($precio) * Number($cant);
        
        const $td5 = $dom.createElement("td");
        $td5.classList.add("cantidad__producto");
        $td5.classList.add("tabla__td");
        $td5.innerText = $cant;
        
        const $td6 = $dom.createElement("td");
        $td6.classList.add("tabla__td");
        
        const $btn = $dom.createElement("button");
        $btn.classList.add("button__link");
        $btn.classList.add("BtnEliminar");
        $btn.setAttribute("type", "button");
        $btn.innerText = "Eliminar";
        
        $td6.appendChild($btn);
        
        $tr.appendChild($td1);
        $tr.appendChild($td2);
        $tr.appendChild($td3);
        $tr.appendChild($td4);
        $tr.appendChild($td5);
        $tr.appendChild($td6);
        
        $tbody.appendChild($tr);
        
        items.forEach((x) => {
            x.value = "";
        });
        $talla.value = "Seleccionar Talla";
        
    }  
    total();
}


$btn_agregar_pro.addEventListener("click", (() => {
    AgragarProdutoTabla($pro_items);
}));

//Calcular el tottal de la factura //
function total() {
    let $total = $dom.querySelector(".total__factura");
    let $filas = $dom.querySelectorAll("tbody.tabla__tb > tr.tabla__fila");
    let total = 0;
    $filas.forEach((fila) =>{
        let $precio = fila.querySelector(".precio__producto");
        let precio = Number($precio.innerText);
        total+= precio;
    })
    $total.innerText = "TOTAL: " + total;
}