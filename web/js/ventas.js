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


 document.addEventListener('DOMContentLoaded', function() {
  const botonesEliminar = document.querySelectorAll('.BtnEliminar');

  botonesEliminar.forEach(boton => {
      boton.addEventListener('click', function() {
          const fila = this.closest('tr'); // Selecciona la fila más cercana al botón clicado
          fila.remove(); // Elimina la fila
      });
  });
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
        ope.send("nombre=" + $nombre + "&documento=" + $documento + "&telefono=" + $telefono)
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