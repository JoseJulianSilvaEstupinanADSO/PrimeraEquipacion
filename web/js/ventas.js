/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

const $dom = document;

const $pago_c = $dom.querySelector("#pago_cliente");
const $vuelta_c = $dom.querySelector("#vueltos_cliente");



const error = $dom.querySelector(".container__modal--error");
const $titleError = $dom.querySelector(".title_error");
const $paragrahp = $dom.querySelector(".paragrahp__error");

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
    let documento = document.querySelector(".documento_cliente");
    let nombre = document.querySelector(".nombre_cliente");
    let telefono = document.querySelector(".tefelono_cliente");
    documento.value = "";
    nombre.value = "";
    telefono.value = "";
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
    
    let documento = document.querySelector(".documento_cliente");
    let nombre = document.querySelector(".nombre_cliente");
    let telefono = document.querySelector(".tefelono_cliente");
   
   $documento = documento.value;
   $nombre = nombre.value;
   $telefono = telefono.value;
    
    let num = 0;
    
    if ($documento.length === 10 && $nombre.length > 0 && $telefono.length === 10) {
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
                    $titleError.innerText = "Exito";
                    $paragrahp.innerText = "Cliente registrado con exito";
                    error.style.display = "block";
                    setTimeout(() => {
                        error.style.display = "none";
                    }, 2000);
                    
                    
                    CloseModal();
                    
                }
            }  
        };
        ope.send("nombre=" + $nombre + "&documento=" + $documento + "&telefono=" + $telefono);
    }
    else{
        $titleError.innerText = "Error al registrar";
        $paragrahp.innerText = "Verifique que no hayan campos vacios o que el telefono y documento tengan 10 caracteres";
        error.style.display = "block";
        setTimeout(() => {
            error.style.display = "none";
        }, 2000);
    }
    
    

}


const $BtnRegistrar = document.querySelector(".registar__clientes");

$BtnRegistrar.addEventListener("submit", RegistarCliente);



// Bucar Cliente   //

function BuscarCliente() {
    const $documento = $dom.querySelector(".documento");
    let valor = $documento.value;
    if(valor !== ""){
        $documento.classList.remove("alert");
        let ope = new XMLHttpRequest();
        ope.open("POST", "../../Clientes?action=BuscarCliente", true);
        ope.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        ope.onload = function() {
            if (ope.status === 200){
                let respuesta = JSON.parse(ope.responseText);
                if (respuesta.error){
                    $documento.classList.add("alert");
                    $titleError.innerText = "Cliente no encontrado";
                    $paragrahp.innerText = "Verifique el documento del cliente";
                    error.style.display = "block";
                    setTimeout(() => {
                        error.style.display = "none";
                    }, 2000);
                }
                else{
                    const $label = $dom.querySelector(".insertDocumento");
                    $label.value = respuesta.nombre;
                }
            }  
        };
        
    ope.send("documento=" + valor);
    }
    else{
        $documento.classList.add("alert");
        $titleError.innerText = "Error campo vacio";
        $paragrahp.innerText = "Por favor llene todos los campos";
        error.style.display = "block";
        setTimeout(() => {
            error.style.display = "none";
        }, 2000);
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
let stock = 0;
let estado = 0;
let iva = 19;
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
        $titleError.innerText = "Error campos vacios";
        $paragrahp.innerText = "Por favor llene todos los campos";
        error.style.display = "block";
        setTimeout(() => {
            error.style.display = "none";
        }, 2000);
    }
    if (talla !== "Seleccionar Talla"){
        $selec.classList.remove("alert"); 
        num+=1;
    }
    else{
        $selec.classList.add("alert");
        $titleError.innerText = "Error talla no seleccionada";
        $paragrahp.innerText = "Seleccione una talla";
        error.style.display = "block";
        setTimeout(() => {
            error.style.display = "none";
        }, 2000);
    }
    
    let $filas = $dom.querySelectorAll("tbody.tabla__tb > tr.tabla__fila");  
        $filas.forEach((fila) => {
            let id = fila.querySelector(".id__producto").innerText;
            if (id === $label.value) {
                num-=1;
                $titleError.innerText = "Error producto ya registrado";
                $paragrahp.innerText = "El producto ya se encuantra listado";
                error.style.display = "block";
                setTimeout(() => {
                    error.style.display = "none";
                }, 2000);
            }
        });
    
    if (num === 2){
        
        let ope = new XMLHttpRequest();
        ope.open("POST", "../../Productos?action=BuscarProducto", true);
        ope.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        ope.onload = function() {
            if (ope.status === 200){
                let respuesta = JSON.parse(ope.responseText);
                if(respuesta.error){
                    $titleError.innerText = "Error Producto no encontrado";
                    $paragrahp.innerText = "Producto en estado inhabilitado o Error en datos ingresados";
                    error.style.display = "block";
                    setTimeout(() => {
                        error.style.display = "none";
                    }, 2000);
                }
                else{
                    let $nombre = $dom.querySelector("#nombre_produc");
                    let $precio = $dom.querySelector("#precio_produc");
                    $nombre.value = respuesta.nombre;
                    $precio.value = respuesta.precio;
                    stock = Number(respuesta.stock);
                    estado = Number(respuesta.estado);   
                }
                if (respuesta.stock <= 10) {
                    console.log("si")
                    $titleError.innerText = "Estock del producto bajo";
                    $paragrahp.innerText = "El estock actual del producto es: " + respuesta.stock;
                    error.style.display = "block";
                    setTimeout(() => {
                        error.style.display = "none";
                    }, 2000);
                }
                else{
                    console.log("no")
                }
                
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
            $titleError.innerText = "Error campos vacios";
            $paragrahp.innerText = "Por favor llene todos los campos";
            error.style.display = "block";
            setTimeout(() => {
                error.style.display = "none";
            }, 2000);
        }
    });   
    const $cant = $dom.querySelector("#cant_produc").value;
    let cant = Number($cant);
    if (num === items.length){
        if (stock >= cant) {
            
            const $tbody = $dom.querySelector("tbody.tabla__tb");

            const $id = $dom.querySelector("#cdProducto").value;
            const $nombre = $dom.querySelector("#nombre_produc").value;
            const $talla = $dom.querySelector("#tallas");
            const $precio = $dom.querySelector("#precio_produc").value;

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
            
            const $tdiva = $dom.createElement("td");
            $tdiva.classList.add("iva__producto");
            $tdiva.classList.add("tabla__td");
            $tdiva.innerText = iva + "%";
            
            
            const $subTotal = $dom.createElement("td");
            $subTotal.classList.add("subTotal__producto");
            $subTotal.classList.add("tabla__td");
            $subTotal.innerText = Number($precio);

            let iva_p = Number($precio) * Number(iva/100);
            
            const $td4 = $dom.createElement("td");
            $td4.classList.add("precio__producto");
            $td4.classList.add("tabla__td");
            $td4.innerText = (Number($precio) + Number(iva_p))*Number($cant);

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
            $tr.appendChild($tdiva);
            $tr.appendChild($subTotal);
            $tr.appendChild($td4);
            $tr.appendChild($td5);
            $tr.appendChild($td6);

            $tbody.appendChild($tr);

            items.forEach((x) => {
                x.value = "";
            });
            $talla.value = "Seleccionar Talla";
        
        }
        
        else{
            $titleError.innerText = "Stock insuficiente";
            $paragrahp.innerText = "El stock del produdco no es suficiente para realizar la venta";
            error.style.display = "block";
            setTimeout(() => {
                error.style.display = "none";
            }, 2000);
        }
    }
    total();
}


$btn_agregar_pro.addEventListener("click", (() => {
    AgragarProdutoTabla($pro_items);
}));

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

const $venta = $dom.querySelector(".btn__ventas");
function NuevaVenta() {
    
    let id_usuario = localStorage.getItem("idUsuario");
    
    let fecha = new Date;
    let fecha_factura = fecha.getFullYear()+"-"+(fecha.getMonth() + 1)+"-"+fecha.getDate();
    
    let $doc_cliente = $dom.querySelector("#cdCliente");
    
    let doc_cliente = $doc_cliente.value;
    
    let $filas = $dom.querySelectorAll("tbody.tabla__tb > tr.tabla__fila");  
    
    let $total = $dom.querySelector(".total__factura");
    
    let total = $total.innerText;
    
    let pago = $pago_c.value;
    
    if ($filas.length > 0) {
        if (Number(total) <= Number(pago)){
            $vuelta_c.value = Number(pago) - Number(total);
            let ope = new XMLHttpRequest();
            ope.open("POST", "../../Facturas?action=NuevaVenta", true);
            ope.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            ope.onload = function(){
                if (ope.status === 200) {
                    let respuesta = JSON.parse(ope.responseText);
                    id = respuesta.resultado;
                    if (respuesta.resultado) {
                        $filas.forEach((fila) => {
                            let id_factura = respuesta.resultado;
                            let $id_producto = fila.querySelector(".id__producto");
                            let $cantidad = fila.querySelector(".cantidad__producto ");
                            let $precio = fila.querySelector(".precio__producto ");

                            let id_producto = $id_producto.innerText;
                            let cantidad = $cantidad.innerText;
                            let precio = $precio.innerText;

                            let ope2 = new XMLHttpRequest();
                            ope2.open("POST", "../../Facturas?action=AgregarProdcutosFactura", true);
                            ope2.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                            ope2.onload = function(){
                                if (ope.status === 200){
                                    let respuesta2 = JSON.parse(ope2.responseText);
                                    
                                }
                            };
                            ope2.send("id_factura=" + id_factura + "&id_producto=" + id_producto + "&cantidad=" + cantidad + "&precion="+ precio);

                            fila.remove();

                        });

                    } 
                    let cliente = $dom.querySelector(".cliente__nombre");
                    cliente.value = "";
                    $doc_cliente.value = "";
                    setTimeout(() => {
                        Descargar(respuesta.resultado);
                    },2000);



                }
            };
            ope.send("id_usuario=" + id_usuario + "&fecha=" + fecha_factura + "&doc_cliente=" + doc_cliente + "&total=" + total);
        }
        else{
            $titleError.innerText = "Pago Insuficiente";
            $paragrahp.innerText = "El monto total de la compra es mayor al pago del cliente";
            error.style.display = "block";
            setTimeout(() => {
                error.style.display = "none";
            }, 2000);
        }
    }
    
    else{
        $titleError.innerText = "No hay datos para añadir";
        $paragrahp.innerText = "Agrege productos a la factura";
        error.style.display = "block";
        setTimeout(() => {
            error.style.display = "none";
        }, 2000);
    }
    
}
$venta.addEventListener("click", NuevaVenta);

function Descargar() {
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