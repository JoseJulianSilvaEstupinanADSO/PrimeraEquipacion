/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */


export default validarCorreo

function validarCorreo(event, value) {

    const regex = /^[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{2,5}/;
    if (regex.test(value.value)) {
        value.classList.remove("alert");
        return true;
    }
    else{
        value.classList.add("alert");
        return false;
    }
}