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

function filas(){
    $filas = $dom.querySelectorAll("tbody.body__tabla > tr.fila__tabla");
        $filas.forEach((fila,index) =>{
            if (index % 2 !== 0) {
                fila.style.background = "#DBDADA";
            }
        });  
}

function filasModal(){
    $filas = $dom.querySelectorAll("tbody.body__tabla > tr.fila__tabla--modal");
        $filas.forEach((fila,index) =>{
            if (index % 2 !== 0) {
                fila.style.background = "#DBDADA";
            }
        });  
}



//Abrir modal y poner el id y fecha de facturachion el en modal//------------

const table = $dom.querySelector('.tabla--factura');

table.addEventListener('click', function(event) {
   if (event.target.classList.contains('button__tabla')) {
    const $modal = $dom.getElementById("ventanaModal");
    $modal.style.display = "block";

    const row = event.target.closest('tr');
    const $id = row.querySelector('td.IdCliente');



    let id = Number($id.innerText);


    const $id_form = $dom.querySelector('.Id_cliente');


    $id_form.innerText = "Numero: " + id;
     
    filasModal();

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


// Listar Clientes //

function ListarClientes() {
    
    
     const  $filas = $dom.querySelectorAll("tbody.body__tabla > tr.fila__tabla");
    $filas.forEach((x) =>{
        x.remove();
    });
    
    
    let ope = new XMLHttpRequest();
    ope.open("GET","../../Clientes?action=ListarUsuario", true );
    ope.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    ope.onload = function() {
        if (ope.status === 200 ) {
            let respuesta = JSON.parse(ope.responseText);
            const $tbody = $dom.querySelector(".body__tabla");
            const $frag = $dom.createDocumentFragment();
            
            respuesta.forEach((x) => {
               
                const $fila = $dom.createElement("tr");
                $fila.classList.add("fila__tabla"); 
                
                const $colId = $dom.createElement("td");
                $colId.classList.add("td__tabla");
                $colId.classList.add("IdCliente");
                
                $colId.innerText = x.id_cliente;
                
                const $colDoc = $dom.createElement("td");
                $colDoc.classList.add("td__tabla");
                $colDoc.classList.add("documento__cliente");
                
                $colDoc.innerText = x.documento;
                
                const $colNom = $dom.createElement("td");
                $colNom.classList.add("td__tabla");
                $colNom.classList.add("nombre__cliente");
                
                $colNom.innerText = x.nombre;
                
                const $colTel = $dom.createElement("td");
                $colTel.classList.add("td__tabla");
                $colTel.classList.add("tel__cliente");
                
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
            $tbody.appendChild($frag);
            filas();
        }
    };
    ope.send();
}
ListarClientes();


//Buscar Cliente en especifico//

function Buscar(){
    const $label = $dom.querySelector(".bucar__cliente").value;
    const $filas = document.querySelectorAll("tbody.body__tabla > tr.fila__tabla");
    let num = 0;
    
    $filas.forEach((fila) => {
        let documento = fila.querySelector(".documento__cliente").innerText;
        
        if (documento !== $label){
            fila.style.display = "none";
            num = num+1;
        }
        else{
            fila.style.display = "";
        }
    });
    
    if (num === $filas.length){
        ListarClientes();
    }
}

const $BtnBuscar = $dom.querySelector(".Btn__buscar");
$BtnBuscar.addEventListener("click", Buscar);