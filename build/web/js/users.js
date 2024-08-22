/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

import Ajax from "./Modulos/ajax.js";
import Mensaje from "./Modulos/msjerror.js";

const $dom = document;
const servlet = "../../Usuarios";
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

const $modal = $dom.getElementById("ventanaModal");

const $cerrar = $dom.querySelector(".cerrar__x");

const $cancelar = $dom.querySelector(".modal__cancelar");

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




// Listar Toddos los usuarios Registrados en la base de datos//
async function CargarDatos() {
    const $BtnBusacar = $dom.querySelector("#BuscarUser");
    const  $filas = $dom.querySelectorAll("tbody.body__tabla > tr.fila__tabla");
    $filas.forEach((x) =>{
        x.remove();
    });
    //Operacion AJAX//
    let respuesta = await Ajax(servlet,"","GET","ListaUsuarios"); 
    const $tbody = $dom.querySelector(".body__tabla");
    const $frag = $dom.createDocumentFragment();
    respuesta.forEach((x) => {

        //tomando el resultado de la operacion AJAX y recorriendolo
        //Se crea la fila donde se almacenaran los datos del respectivo usuario
        const $fila = $dom.createElement("tr");
        $fila.classList.add("fila__tabla");

        const $colId = $dom.createElement("td");
        $colId.classList.add("td__tabla");
        $colId.classList.add("IdUsuario");

        $colId.innerText = x.idUsuario;

        const $colNom = $dom.createElement("td");
        $colNom.classList.add("td__tabla");
        $colNom.classList.add("NombreUsuario");

        $colNom.innerText = x.nombre;

        const $colDoc = $dom.createElement("td");
        $colDoc.classList.add("td__tabla");
        $colDoc.classList.add("DocumentoUsuario");

        $colDoc.innerText = x.documento;

        const $colRol = $dom.createElement("td");
        $colRol.classList.add("td__tabla");
        $colRol.classList.add("Rol");

        $colRol.innerText = x.rol;

        const $colUser = $dom.createElement("td");
        $colUser.classList.add("td__tabla");
        $colUser.classList.add("UsuarioUser");

        $colUser.innerText = x.usuario;

        const $colTel = $dom.createElement("td");
        $colTel.classList.add("td__tabla");
        $colTel.classList.add("TelUser");

        $colTel.innerText = x.telefono;

        const $colDirec = $dom.createElement("td");
        $colDirec.classList.add("td__tabla");
        $colDirec.classList.add("DireccionUser");

        $colDirec.innerText = x.direccion;

        const $colEmail = $dom.createElement("td");
        $colEmail.classList.add("td__tabla");
        $colEmail.classList.add("EmailUser");

        $colEmail.innerText = x.email;

        const $colBtn = $dom.createElement("td");
        $colBtn.classList.add("td__tabla");


        const $BtnEdit = $dom.createElement("button");
        $BtnEdit.classList.add("button__tabla");
        $BtnEdit.innerText = "Editar";


        $colBtn.appendChild($BtnEdit);
        $fila.appendChild($colId);
        $fila.appendChild($colNom);
        $fila.appendChild($colDoc);
        $fila.appendChild($colRol);
        $fila.appendChild($colUser);
        $fila.appendChild($colTel);
        $fila.appendChild($colDirec);
        $fila.appendChild($colEmail);
        $fila.appendChild($colBtn);

        $frag.appendChild($fila);

    });

    $tbody.appendChild($frag);
    filas();
   
}

CargarDatos();


/*Se abra el modal con toda la imformacion de la fila de la tabla
en la que se encontraba el boton editar*/ 

const table = $dom.querySelector('.tabla--factura');

table.addEventListener('click', function(event) {
   if (event.target.classList.contains('button__tabla')) {
    const $modal = $dom.getElementById("ventanaModal");
    const $btn = $dom.querySelector(".button__modal--inventario");
    $btn.innerText = "Guardar Cambios";


    $modal.style.display = "block";
    const row = event.target.closest('tr');
    const $id = row.querySelector('td.IdUsuario');
    const $nombre = row.querySelector("td.NombreUsuario");
    const $documento = row.querySelector("td.DocumentoUsuario");
    const $rol = row.querySelector("td.Rol");
    const $user = row.querySelector("td.UsuarioUser");
    const $tel = row.querySelector("td.TelUser");
    const $direccion = row.querySelector("td.DireccionUser");
    const $email = row.querySelector("td.EmailUser");

    

    let id = Number($id.innerText);
    let nombre = $nombre.innerText;
    let documento = $documento.innerText;
    let rol = ($rol.innerText);
    let user = $user.innerText;
    let tel = $tel.innerText;
    let direccion = $direccion.innerText;
    let email = $email.innerText;

    const $id_user = $dom.querySelector('.form_Id');
    const $nombre_user = $dom.querySelector(".form_Nombre");
    const $doc_user = $dom.querySelector(".form_Doc");
    const $rol_user = $dom.querySelector(".form_Rol");
    const $user_user = $dom.querySelector(".form_User");
    const $tel_user = $dom.querySelector(".form_Tel");
    const $direc_user = $dom.querySelector(".form_Direc");
    const $email_user = $dom.querySelector(".form_Email");


    $id_user.value = id;
    $nombre_user.value = nombre;
    $doc_user.value = documento;
    

    if(rol === "null"){ 
        $rol_user.value = "sleccionar";
    }
    else{
        switch (rol) {
            case "admin":
                $rol_user.value = 1;                  
                break;                
            case "ventas":
                $rol_user.value = 2;               
                break;
            case "inventario":
                $rol_user.value = 3;                    
                break;
            case "cliente":
                $rol_user.value = 4;     
                break;             
            default:
                break;
        }
    }

    $user_user.value = user;
    $tel_user.value = tel;
    $direc_user.value = direccion;
    $email_user.value = email;

    $id_user.setAttribute("readonly","");
    $nombre_user.setAttribute("readonly","");
    $doc_user.setAttribute("readonly","");
    $user_user.setAttribute("readonly","");
    $tel_user.setAttribute("readonly","");
    $direc_user.setAttribute("readonly","");
    $email_user.setAttribute("readonly","");

   }
 });

async function Modificar() {
    
    const $id = $dom.querySelector(".form_Id").value;
    const $nombre = $dom.querySelector(".form_Nombre").value;
    const $documento = $dom.querySelector(".form_Doc").value;
    const $rol = $dom.querySelector(".form_Rol").value;
    const $usuario = $dom.querySelector(".form_User").value;
    const $telefono = $dom.querySelector(".form_Tel").value;
    const $direccion = $dom.querySelector(".form_Direc").value;
    const $correo = $dom.querySelector(".form_Email").value;
    let datos = {
        "id": $id,
        "id_rol": $rol,
        "nombre": $nombre,
        "documento": $documento,
        "usuario": $usuario,
        "telefono": $telefono,
        "direccion": $direccion,
        "correo": $correo,
        "contrasena": null
    };
    let respuesta = await Ajax(servlet,datos,"POST","ModificarUsuario");   
    if(respuesta.resultado){
        Mensaje($titleError, $paragrahp, error, "Exito","Usuario Modificado");
         CloseModal();
         CargarDatos();


    }
};

const $BtnModificar = $dom.querySelector(".ModificarUser");

$BtnModificar.addEventListener("click",Modificar);

function Buscar() {

    const $Label = document.querySelector(".BuscarUsuario").value;
    const $filas = document.querySelectorAll("tbody.body__tabla > tr.fila__tabla");
    let num = 0;
    $filas.forEach((fila) => {
       let otra = fila.querySelector(".DocumentoUsuario").innerText;
       let documento = fila.querySelector(".IdUsuario").innerText;
       
        if (documento === $Label || otra === $Label){
            fila.style.display = "";
        }
        else{
            num = num+1;
            fila.style.display = "none";
        }
    });
    
    if(num === $filas.length){
        Mensaje($titleError, $paragrahp, error, "suario no encontrado","Verifique el id del Usuario");
        CargarDatos();
    }
}

const $BtnBuscar = $dom.querySelector(".Buscar");
$BtnBuscar.addEventListener("click",Buscar);

const $Listar = $dom.querySelector(".Listar");
$Listar.addEventListener("click",() => {
    const $filas = document.querySelectorAll("tbody.body__tabla > tr.fila__tabla");
    $filas.forEach((fila) => {
           fila.remove();
    });
    CargarDatos();
});

