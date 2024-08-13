/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

export default validarLength;

function validarLength(event, value) {
    if (value.classList.contains("form_Tel")) {
        if ((value.value).length > 9 && (value.value).length < 16) {
            value.classList.remove("alert");
            return true;
        } else {
            value.classList.add("alert");
            return false;
        }
    }
    if (value.classList.contains("form_Doc")) {
        if ((value.value).length === 10) {
            value.classList.remove("alert");
            return true;
        } else {
            value.classList.add("alert");
            return false;
        }
    }
}

  