/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */


const $dom = document;
const error = $dom.querySelector(".container__modal--error");
const $titleError = $dom.querySelector(".title_error");
const $paragrahp = $dom.querySelector(".paragrahp__error");
const $descargar = $dom.querySelector(".btn__descargar");
let $id_factura = 0;
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



//Cambiar colores de las filas impares De las tablas //

function filas(){
    $filas = $dom.querySelectorAll("tbody.body__tabla > tr.fila__tabla");
        $filas.forEach((fila,index) =>{
            if (index % 2 !== 0) {
                fila.style.background = "#DBDADA";
            }
        });  
}


//Abrir modal y poner el id y fecha de facturachion el en modal//------------

const table = $dom.querySelector('.tabla--factura');

table.addEventListener('click', function(event) {
   if (event.target.classList.contains('FacturaDetalles')) {
       
       
        const $modal = $dom.getElementById("ventanaModal");
        $modal.style.display = "block";

        const  $filas = $dom.querySelectorAll("tbody.body__tabla--modal > tr.fila__tabla");
        $filas.forEach((x) =>{
            x.remove();
        });
        
        const row = event.target.closest('tr');
        const $id = row.querySelector('td.IdFactura');
        const $fecha = row.querySelector("td.Fecha");
        const $total = row.querySelector("td.Total");
        id_factura = $id.innerText;
        let total = Number($total.innerText);
        let id = Number($id.innerText);
        let fecha = $fecha.innerText;

        const $id_form = $dom.querySelector('.Numero_factura');
        const $fecha_factura = $dom.querySelector(".fecha__factura");
        const $total_modal = $dom.querySelector(".total--facturas");

        $id_form.innerText = "Numero: " + id;
        $fecha_factura.innerText = "Fecha: " + fecha;
        $total_modal.innerText = "TOTAL: " + total;
        
        let ope = new XMLHttpRequest();
        ope.open("POST","../../Facturas?action=ListarProductosFactura", true );
        ope.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        ope.onload = function() {
            if (ope.status === 200) {
                let respuesta = JSON.parse(ope.responseText);
                const $tbody = $dom.querySelector("tbody.body__tabla--modal");
                respuesta.forEach((x) => {
                    const $fila = $dom.createElement("tr");
                    $fila.classList.add("fila__tabla"); 
                    $fila.classList.add("fila__tabla--tamaño"); 

                    const $colId = $dom.createElement("td");
                    $colId.classList.add("tabla__td");
                    $colId.classList.add("modal_idpro");

                    $colId.innerText = x.id_producto;

                    const $colNom = $dom.createElement("td");
                    $colNom.classList.add("tabla__td");
                    $colNom.classList.add("modal_nompro");

                    $colNom.innerText = x.nombre;

                    const $colPre = $dom.createElement("td");
                    $colPre.classList.add("tabla__td");
                    $colPre.classList.add("modal_precio");

                    $colPre.innerText = x.precio;

                    const $colTalla = $dom.createElement("td");
                    $colTalla.classList.add("tabla__td");
                    $colTalla.classList.add("modal_talla");

                    $colTalla.innerText = x.talla;

                    const $colCant = $dom.createElement("td");
                    $colCant.classList.add("tabla__td");
                    $colCant.classList.add("modal_cant");

                    $colCant.innerText = x.cantidad;

                    $fila.appendChild($colId);
                    $fila.appendChild($colNom);
                    $fila.appendChild($colPre);
                    $fila.appendChild($colTalla);
                    $fila.appendChild($colCant);

                    $tbody.appendChild($fila);

                });
                filas();
                
            }
        };
        ope.send("id_factura=" + id);
     
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

function  ListarFacturas() {
    
    const  $filas = $dom.querySelectorAll("tbody.body__tabla > tr.fila__tabla");
    $filas.forEach((x) =>{
        x.remove();
    });
    
    let ope = new XMLHttpRequest();
    ope.open("GET", "../../Facturas?action=ListarFacturas", true );
    ope.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    ope.onload = function() {
        if (ope.status === 200) {
            let respuesta = JSON.parse(ope.responseText);
            const $tbody = $dom.querySelector("tbody.body__tabla");
            respuesta.forEach((x) => {
                const $fila = $dom.createElement("tr");
                $fila.classList.add("fila__tabla"); 
                
                const $colId_fac = $dom.createElement("td");
                $colId_fac.classList.add("td__tabla");
                $colId_fac.classList.add("IdFactura");
                
                $colId_fac.innerText = x.id_factura;
                
                const $colFecha = $dom.createElement("td");
                $colFecha.classList.add("td__tabla");
                $colFecha.classList.add("Fecha");
                
                $colFecha.innerText = x.fecha_facturacion;
                
                const $colclie = $dom.createElement("td");
                $colclie.classList.add("td__tabla");
                $colclie.classList.add("doc_clie");
                
                $colclie.innerText = x.doc_cliente;
                
                const $colId_venta = $dom.createElement("td");
                $colId_venta.classList.add("td__tabla");
                $colId_venta.classList.add("id_venta");
                
                $colId_venta.innerText = x.id_venta;
                
                const $colId_user = $dom.createElement("td");
                $colId_user.classList.add("td__tabla");
                $colId_user.classList.add("id_usuario");
                
                $colId_user.innerText = x.id_usuario;
                
                const $colTotal = $dom.createElement("td");
                $colTotal.classList.add("td__tabla");
                $colTotal.classList.add("Total");
                
                $colTotal.innerText = x.total;
                
                const $colBtn = $dom.createElement("td");
                $colBtn.classList.add("td__tabla");
                
                const $BtnEdit = $dom.createElement("button");
                $BtnEdit.classList.add("button__tabla");
                $BtnEdit.classList.add("FacturaDetalles");
                
                $BtnEdit.innerText = "Detalles";
                
                $colBtn.appendChild($BtnEdit);
                $fila.appendChild($colId_fac);
                $fila.appendChild($colFecha);
                $fila.appendChild($colclie);
                $fila.appendChild($colId_venta);
                $fila.appendChild($colId_user);
                $fila.appendChild($colTotal);
                $fila.appendChild($colBtn);
                
                $tbody.appendChild($fila);
                
            });
            filas();
        }  
    };
    ope.send();
}
ListarFacturas();


function Buscar(){
    const $label = $dom.querySelector(".input__buscar").value;
    const $filas = document.querySelectorAll("tbody.body__tabla > tr.fila__tabla");
    let num = 0;
    
    $filas.forEach((fila) => {
        let otra = fila.querySelector(".Fecha").innerText;
        let id = fila.querySelector(".IdFactura").innerText;
        
        if (id === $label || otra === $label){
            fila.style.display = "";
        }
        else{
            num = num+1;
            fila.style.display = "none";
        }
    });
    
    if (num === $filas.length){
        ListarFacturas();
        $titleError.innerText = "Factura no encontrada";
        $paragrahp.innerText = "Verifique el id de la factura";
        error.style.display = "block";
        setTimeout(() => {
            error.style.display = "none";
        }, 2000)
    }
}

const $BtnBuscar = $dom.querySelector(".button__buscar");
$BtnBuscar.addEventListener("click", Buscar);

const $Listar = $dom.querySelector(".Listar");
$Listar.addEventListener("click",() => {
    const $filas = document.querySelectorAll("tbody.body__tabla > tr.fila__tabla");
    $filas.forEach((fila) => {
           fila.remove();
    });
    ListarFacturas();
});



function Descargar() {

    let ope = new XMLHttpRequest();
    ope.open("GET", "../../Facturas?action=PdfProductosFactura&id_factura=" + encodeURIComponent(id_factura), true);
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

$descargar.addEventListener("click", Descargar);