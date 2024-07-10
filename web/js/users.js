/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */



const $dom = document;


const $modal = $dom.getElementById("ventanaModal");

/*Se cambia el color de las filas inpares */





function filas(){
    $filas = $dom.querySelectorAll("tbody.body__tabla > tr.fila__tabla");
        $filas.forEach((fila,index) =>{
            if (index % 2 !== 0) {
                fila.style.background = "#DBDADA";
            }
        });  
}



function CargarDatos() {
    const $BtnBusacar = $dom.querySelector("#BuscarUser");
    
    let ope = new XMLHttpRequest();
        ope.open("GET", "../../Usuarios?action=ListaUsuarios", true);
        ope.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        ope.onload = function(){
            if (ope.status === 200){
                let respuesta = JSON.parse(ope.responseText);
                console.log("la respuesta es " + respuesta);
                console.log(respuesta.idUsuario);
                const $tbody = $dom.querySelector(".body__tabla");
                const $frag = $dom.createDocumentFragment();
                respuesta.forEach((x) => {
                    
                    console.log(x);

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
        };
        ope.send();
        
    
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
    let rol = Number($rol.innerText);
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
    if (rol === 1) {
        $rol_user.value = "valor1";
    }
    else{
        if (rol === 2) {
            $rol_user.value = "valor2";
        }
        else{
            $rol_user.value = "sleccionar";
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