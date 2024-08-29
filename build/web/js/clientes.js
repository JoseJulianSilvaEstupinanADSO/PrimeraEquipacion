/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

import Ajax from "./Modulos/ajax.js";
import Mensaje from "./Modulos/msjerror.js";

// Definición de constantes para los elementos del DOM y las rutas de los servlets
const $dom = document;
const servlet_f = "../../Facturas";
const servlet_c = "../../Clientes";

// Elementos relacionados con el modal de error y la tabla de clientes
const error = $dom.querySelector(".container__modal--error");
const $titleError = $dom.querySelector(".title_error");
const $paragrahp = $dom.querySelector(".paragrahp__error");
const $modal = $dom.getElementById("ventanaModal");
const $id_form = $dom.querySelector('p.Id_cliente > b');
const $tbody = $dom.querySelector("tbody.body__tabla--modal");
const $tbody_c = $dom.querySelector("tbody.body__tabla");
const $frag = $dom.createDocumentFragment();

// Función para cerrar sesión y redirigir a la página de inicio si la sesión está cerrada
function CloseSession(){
    let $session = localStorage.getItem("session");
    if ($session === "false" || $session === null) {
        console.log("cerrado");
        window.location.href = '../../index.jsp';
    }
}
CloseSession();

// Verificar el estado de la sesión cada segundo
setInterval(() => {
    CloseSession();
}, 1000);

// Cerrar el modal al hacer clic en el botón de cerrar o fuera del modal
const $cerrar = $dom.querySelector(".cerrar__x");

$cerrar.addEventListener("click", function () {
    $modal.classList.add("cerrando");
    setTimeout(function() {
        $modal.style.display = "none";
        $modal.classList.remove("cerrando");
    }, 500);
});

window.addEventListener("click", function(event) {
    if (event.target == $modal) {
        $modal.classList.add("cerrando");
        setTimeout(function() {
            $modal.style.display = "none";
            $modal.classList.remove("cerrando");
        }, 500);
    }
});

// Cambiar el color de fondo de las filas impares en las tablas
function filas(){
    let $filas = $dom.querySelectorAll("tbody.body__tabla > tr.fila__tabla");
    $filas.forEach((fila, index) => {
        if (index % 2 !== 0) {
            fila.style.background = "#DBDADA";
        }
    });
}

function filasModal(){
    let $filas = $dom.querySelectorAll("tbody.body__tabla--modal > tr");
    $filas.forEach((fila, index) => {
        if (index % 2 !== 0) {
            fila.style.background = "#DBDADA";
        }
    });
}

// Abrir el modal y cargar la información de facturación correspondiente
const table = $dom.querySelector('.tabla--factura');

table.addEventListener('click', async function(event) {
    if (event.target.classList.contains('button__tabla')) {
        $modal.style.display = "block";

        let $filas = $dom.querySelectorAll("tbody.body__tabla > tr.fila__tabla--modal");
        $filas.forEach((x) => {
            x.remove();
        });

        const row = event.target.closest('tr');
        const $id = row.querySelector('td.IdCliente');
        let id = Number($id.innerText);

        $id_form.innerText = "Numero: " + id;

        let $doc = row.querySelector(".documento__cliente");
        let datos = {
            "doc_cliente": $doc.innerText
        };
        let respuesta = await Ajax(servlet_f, datos, "POST", "ClientesCompras");

        respuesta.forEach((x) => {
            const $fila = $dom.createElement("tr");
            $fila.classList.add("fila__tabla--modal");

            const $colId_venta = $dom.createElement("td");
            $colId_venta.classList.add("td__tabla", "idventa");
            $colId_venta.innerText = x.id_venta;

            const $colidfac = $dom.createElement("td");
            $colidfac.classList.add("td__tabla", "idfactura");
            $colidfac.innerText = x.id_factura;

            const $coldate = $dom.createElement("td");
            $coldate.classList.add("td__tabla", "date");
            $coldate.innerText = x.fecha_facturacion;

            const $colidven = $dom.createElement("td");
            $colidven.classList.add("td__tabla", "idvendedor");
            $colidven.innerText = x.id_usuario;

            $fila.appendChild($colId_venta);
            $fila.appendChild($colidfac);
            $fila.appendChild($coldate);
            $fila.appendChild($colidven);

            $tbody.appendChild($fila);
        });

        filasModal();
    }
});

// Listar todos los clientes en la tabla
async function ListarClientes() {
    const $filas = $dom.querySelectorAll("tbody.body__tabla > tr.fila__tabla");
    $filas.forEach((x) => {
        x.remove();
    });

    let respuesta = await Ajax(servlet_c, "", "GET", "ListarUsuario");

    respuesta.forEach((x) => {
        const $fila = $dom.createElement("tr");
        $fila.classList.add("fila__tabla");

        const $colId = $dom.createElement("td");
        $colId.classList.add("td__tabla", "IdCliente");
        $colId.innerText = x.id_cliente;

        const $colDoc = $dom.createElement("td");
        $colDoc.classList.add("td__tabla", "documento__cliente");
        $colDoc.innerText = x.documento;

        const $colNom = $dom.createElement("td");
        $colNom.classList.add("td__tabla", "nombre__cliente");
        $colNom.innerText = x.nombre;

        const $colTel = $dom.createElement("td");
        $colTel.classList.add("td__tabla", "tel__cliente");
        $colTel.innerText = x.telefono;

        const $colBtn = $dom.createElement("td");
        $colBtn.classList.add("td__tabla");

        const $BtnEdit = $dom.createElement("button");
        $BtnEdit.classList.add("button__tabla");
        $BtnEdit.innerText = "Historial";

        $colBtn.appendChild($BtnEdit);
        $fila.appendChild($colId);
        $fila.appendChild($colDoc);
        $fila.appendChild($colNom);
        $fila.appendChild($colTel);
        $fila.appendChild($colBtn);

        $frag.appendChild($fila);
    });

    $tbody_c.appendChild($frag);
    filas();
}
ListarClientes();

// Buscar un cliente específico en la tabla
function Buscar() {
    const $label = $dom.querySelector(".bucar__cliente").value;
    const $filas = document.querySelectorAll("tbody.body__tabla > tr.fila__tabla");
    let num = 0;

    $filas.forEach((fila) => {
        let documento = fila.querySelector(".documento__cliente").innerText;

        if (documento !== $label) {
            fila.style.display = "none";
            num++;
        } else {
            fila.style.display = "";
        }
    });

    if (num === $filas.length) {
        Mensaje($titleError, $paragrahp, error, "Cliente no encontrado", "Verifique el documento del Cliente");
        ListarClientes();
    }
}

// Evento para buscar cliente cuando se hace clic en el botón de buscar
const $BtnBuscar = $dom.querySelector(".Btn__buscar");
$BtnBuscar.addEventListener("click", Buscar);

// Evento para listar todos los clientes cuando se hace clic en el botón de listar
const $Listar = $dom.querySelector(".Listar");
$Listar.addEventListener("click", () => {
    const $filas = document.querySelectorAll("tbody.body__tabla > tr.fila__tabla");
    $filas.forEach((fila) => {
        fila.remove();
    });
    ListarClientes();
});
