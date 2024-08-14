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

const servlet_c = "../../Clientes";
const servlet_p = "../../Productos";
const servlet_f = "../../Facturas";

const error = $dom.querySelector(".container__modal--error");
const $titleError = $dom.querySelector(".title_error");
const $paragrahp = $dom.querySelector(".paragrahp__error");

const $pago_c = $dom.querySelector("#pago_cliente");
const $vuelta_c = $dom.querySelector("#vueltos_cliente");

const $modal = $dom.getElementById("ventanaModal");
const $boton = $dom.getElementById("abrirModal");
const $cerrar = $dom.getElementById("Cerrar__Modal");

const documento = $dom.querySelector(".documento_cliente");
const nombre = $dom.querySelector(".nombre_cliente");
const telefono = $dom.querySelector(".tefelono_cliente");

const $label = $dom.querySelector("input#cdProducto");
const $selec = $dom.querySelector("select#tallas");
const $tbody = $dom.querySelector("tbody.tabla__tb");


const $BtnRegistrar = $dom.querySelector(".registar__clientes");
const Btn_buscar_cliente = $dom.querySelector(".Btn_busacar_cliente");
const $buscarp = $dom.querySelector("button#buscar_producto");

const $documento = $dom.querySelector(".documento");
const $nom_clie = $dom.querySelector(".insertDocumento");
const $nombre = $dom.querySelector("#nombre_produc");
const $precio = $dom.querySelector("#precio_produc");
const $pro_items = $dom.querySelectorAll("input[required]");
const $btn_agregar_pro = $dom.querySelector("#agregar_producto");
const $cant = $dom.querySelector("#cant_produc");

const $venta = $dom.querySelector(".btn__ventas");

const $canelar = $dom.querySelector(".btn__cancelar");

console.log($canelar)

let stock = 0;
let estado = 0;
let iva = 19;

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


//MODAL-----------------------------------------------------------------------



function CloseModal() {   
    $modal.classList.add("cerrando");
    setTimeout(function() {
        $modal.style.display = "none";
        $modal.classList.remove("cerrando");
    }, 500);
}



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

$boton.addEventListener("click",function() {
  $modal.style.display = "block";
    documento.value = "";
    nombre.value = "";
    telefono.value = "";
});
//----------------------------------------------------------------------------

//function eliminar(){
//    return  botonesEliminar = document.querySelectorAll('.BtnEliminar');    
//}


 document.addEventListener('DOMContentLoaded', function() {   
 setInterval(() => {
    const botonesEliminar = $dom.querySelectorAll('.BtnEliminar');    
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

async function RegistarCliente() {
    
    event.preventDefault();
    

    let num = 0;
    
    if ((documento.value).length === 10 && (nombre.value).length > 0 && (telefono.value).length === 10) {
        num = 1;
    }    
    if (num === 1) {
        
        let datos = {
                "nombre": nombre.value,
                "documento": documento.value,
                "telefono": telefono.value
        };
        
        let respuesta = await Ajax(servlet_c,datos,"POST","RegistrarUsuario");
        
        if (respuesta.resultado) {
            Mensaje($titleError, $paragrahp, error, "Exito","Cliente registrado con exito");
            CloseModal();

        }
    }
    else{
        Mensaje($titleError, $paragrahp, error, "Error al registrar","Verifique que no hayan campos vacios o que el telefono y documento tengan 10 caracteres");
    }
    
    

}

// Bucar Cliente   //

async function BuscarCliente() {
    let valor = $documento.value;
    $nom_clie.value = "";
    if(valor !== ""){ 
        $documento.classList.remove("alert");
        let datos = {
                "documento": valor
        };   
        let respuesta = await Ajax(servlet_c,datos,"POST","BuscarCliente");

        if (respuesta.error){
            Mensaje($titleError, $paragrahp, error, "Cliente no encontrado","Verifique el documento del cliente");
            $documento.classList.add("alert");
        }
        else{
            $nom_clie.value = respuesta.nombre;
        }
    }  
    else{
        $documento.classList.add("alert");
        Mensaje($titleError, $paragrahp, error, "Error campo vacio","Por favor llene todos los campos");
    }
}

//Listar las tallas en la vista ventas//

async function ListarTallas() {    
    let respuesta = await Ajax(servlet_p,"","GET","ListarTallas");     
    respuesta.forEach((x) => {
       let option = $dom.createElement("option") ;
       option.innerText = x.talla;
       $selec.appendChild(option);
    });
}
ListarTallas();

//Buscar Producto para agregarlo a la factura//

async function BuscarProducto() {
    let num = 0;
    
    if ($label.value !== ""){
        $label.classList.remove("alert");
        num+=1;
    }
    else{
        $label.classList.add("alert");
        Mensaje($titleError, $paragrahp, error, "Error campos vacios","Por favor llene todos los campos");
    }
    if ($selec.value !== "Seleccionar Talla"){
        $selec.classList.remove("alert"); 
        num+=1;
    }
    else{
        Mensaje($titleError, $paragrahp, error, "Error talla no seleccionada","Seleccione una talla");
    }
    
    let $filas = $dom.querySelectorAll("tbody.tabla__tb > tr.tabla__fila");  
    $filas.forEach((fila) => {
        let id = fila.querySelector(".id__producto").innerText;
        if (id === $label.value) {
            num-=1;
            Mensaje($titleError, $paragrahp, error, "Error producto ya registrado","El producto ya se encuantra listado");
        }
    });
    
    if (num === 2){
         let datos = {
                "id_producto": $label.value,
                "talla": $selec.value
        };
        
        let respuesta = await Ajax(servlet_p,datos,"POST","BuscarProducto");
        if(respuesta.error){
            Mensaje($titleError, $paragrahp, error, "Error Producto no encontrado","Producto en estado inhabilitado o Error en datos ingresados");
        }
        else{
            $nombre.value = respuesta.nombre;
            $precio.value = respuesta.precio;
            stock = Number(respuesta.stock);
            estado = Number(respuesta.estado);   
        }
        if (respuesta.stock <= 10) {
            Mensaje($titleError, $paragrahp, error, "Estock del producto bajo","El estock actual del producto es: " + respuesta.stock);
        }             
    }
}
//Agregar Productos a la tabla Factura//

function AgragarProdutoTabla() {
    let num = validations(event, $pro_items);
    if (num === $pro_items.length){
        if (stock >= Number($cant.value)) {
            
            const $tr = $dom.createElement("tr");
            $tr.classList.add("tabla__fila");

            const $td1 = $dom.createElement("td");
            $td1.classList.add("id__producto");
            $td1.classList.add("tabla__td");
            $td1.innerText = $label.value;

            const $td2 = $dom.createElement("td");
            $td2.classList.add("nombre__producto");
            $td2.classList.add("tabla__td");
            $td2.innerText = $nombre.value;

            const $td3 = $dom.createElement("td");
            $td3.classList.add("talla__producto");
            $td3.classList.add("tabla__td");
            $td3.innerText = $selec.value;
            
            const $tdiva = $dom.createElement("td");
            $tdiva.classList.add("iva__producto");
            $tdiva.classList.add("tabla__td");
            $tdiva.innerText = iva + "%";
            
            
            const $subTotal = $dom.createElement("td");
            $subTotal.classList.add("subTotal__producto");
            $subTotal.classList.add("tabla__td");
            $subTotal.innerText = Number($precio.value) * Number($cant.value);

            let iva_p = Number($precio.value) * Number(iva/100);
            
            const $td4 = $dom.createElement("td");
            $td4.classList.add("precio__producto");
            $td4.classList.add("tabla__td");
            $td4.innerText = (Number($precio.value) + Number(iva_p))*Number($cant.value);

            const $td5 = $dom.createElement("td");
            $td5.classList.add("cantidad__producto");
            $td5.classList.add("tabla__td");
            $td5.innerText = $cant.value;

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
            $tr.appendChild($tdiva);
            $tr.appendChild($subTotal);
            $tr.appendChild($td4);
            $tr.appendChild($td5);
            $tr.appendChild($td6);

            $tbody.appendChild($tr);

            $pro_items.forEach((x) => {
                x.value = "";
            });
            $selec.value = "Seleccionar Talla";
        }
        else{
            Mensaje($titleError, $paragrahp, error, "Stock insuficiente","El stock del produdco no es suficiente para realizar la venta");
        }
    }
    else{
        Mensaje($titleError, $paragrahp, error, "Campos Vacios","Favor llene todos los campos");
    }
    total();
}

//Calcular el tottal de la factura //
function total() {
    event.preventDefault();
    let $total = $dom.querySelector(".total__factura");
    let $filas = $dom.querySelectorAll("tbody.tabla__tb > tr.tabla__fila");
    let total = 0;
    $filas.forEach((fila) =>{
        let $precio = fila.querySelector(".precio__producto");
        let precio = Number($precio.innerText);
        total+= precio;
    });
    $total.innerText = total;
}

//Generrar Una Nueva Venta, Agregando los datos a las tablas venta,factura,factura_producto//

async function NuevaVenta() {
    let id_usuario = localStorage.getItem("idUsuario");
    let fecha = new Date;
    let fecha_factura = fecha.getFullYear()+"-"+(fecha.getMonth() + 1)+"-"+fecha.getDate();
    let $filas = $dom.querySelectorAll("tbody.tabla__tb > tr.tabla__fila");  
    let $total = $dom.querySelector(".total__factura").innerText; 
    let pago = $pago_c.value;
    if ($filas.length > 0) {
        if (Number($total) <= Number(pago)){
            $vuelta_c.value = Number(pago) - Number($total);
            let datos_1 = {
                "id_usuario": id_usuario,
                "fecha": fecha_factura,
                "doc_cliente": $documento.value,
                "total": $total
            };
            let respuesta = await Ajax(servlet_f,datos_1,"POST","NuevaVenta");
            let id_factura = respuesta.resultado;
            if (respuesta.resultado) {
                $filas.forEach(async (fila) => {
                    let $id_producto = fila.querySelector(".id__producto");
                    let $cantidad = fila.querySelector(".cantidad__producto ");
                    let $precio = fila.querySelector(".precio__producto ");
                    
                    let datos_2 = {
                        "id_factura": id_factura,
                        "id_producto": $id_producto.innerText,
                        "cantidad": $cantidad.innerText,
                        "precion": $precio.innerText
                    };
                    let respuesta = await Ajax(servlet_f,datos_2,"POST","AgregarProdcutosFactura");
                    fila.remove();

                });
            } 
            $nom_clie.value = "";
            $documento.value = "";
            setTimeout(() => {
                Descargar(id_factura);
            },2000);
        }
        else{
            Mensaje($titleError, $paragrahp, error, "Pago Insuficiente","El monto total de la compra es mayor al pago del cliente");
        }
    }
    
    else{
        Mensaje($titleError, $paragrahp, error, "No hay datos para añadir","Agrege productos a la factura");
    }
    
}

function cancelar() {
    let $filas = $dom.querySelectorAll("tbody.tabla__tb > tr.tabla__fila"); 
    $filas.forEach((x) => {
        x.remove();
    });
}

function Descargar(id) {
    let ope = new XMLHttpRequest();
    ope.open("GET", "../../Facturas?action=PdfProductosFactura&id_factura=" + encodeURIComponent(id), true);
    ope.responseType = 'blob'; // Configura el tipo de respuesta como blob para manejar archivos binarios

    ope.onload = function () {
        if (ope.status === 200) {
            // Crea un enlace para descargar el PDF
            let url = window.URL.createObjectURL(new Blob([ope.response], { type: 'application/pdf' }));
            let a = document.createElement('a');
            a.href = url;
            a.download = 'factura.pdf'; // Nombre del archivo PDF
            document.body.appendChild(a);
            a.click();
            document.body.removeChild(a);
        } else {
            console.error('Error al generar el PDF:', ope.statusText);
        }
    };

    ope.onerror = function () {
        console.error('Error de red.');
    };

    ope.send();
}

$BtnRegistrar.addEventListener("submit", RegistarCliente);

Btn_buscar_cliente.addEventListener("click", BuscarCliente);

$buscarp.addEventListener("click", BuscarProducto);

$canelar.addEventListener("click", cancelar);

$btn_agregar_pro.addEventListener("click", () => {
    AgragarProdutoTabla();
});

$venta.addEventListener("click", NuevaVenta);

$documento.addEventListener("keypress", (event) => {
    validarNumber(event);
});
$label.addEventListener("keypress", (event) => {
    validarNumber(event);
});
$cant.addEventListener("keypress", (event) => {
    validarNumber(event);
});
$pago_c.addEventListener("keypress", (event) => {
    validarNumber(event);
});
documento.addEventListener("keypress", (event) => {
    validarNumber(event);
});
telefono.addEventListener("keypress", (event) => {
    validarNumber(event);
});
nombre.addEventListener("keypress", (event) => {
    validarString(event);
});
documento.addEventListener("blur", (event) => {
    validarLength(event, documento);
});
telefono.addEventListener("blur", (event) => {
    validarLength(event, telefono);
});