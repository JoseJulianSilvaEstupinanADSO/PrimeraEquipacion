/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */


document.addEventListener('DOMContentLoaded', function() {
    const menuToggle = document.querySelector('.menu__toggle');
    const menuList = document.querySelector('.menu');

    menuToggle.addEventListener('click', function() {
        menuList.classList.toggle('menu__lista--active');
    });
});


const $BtnSalir = document.querySelector(".Sign-out");



$BtnSalir.addEventListener("click", function CloseSession(){
    let cambio = localStorage.getItem("session");
    cambio = false;
    localStorage.setItem("session", cambio);
    
});