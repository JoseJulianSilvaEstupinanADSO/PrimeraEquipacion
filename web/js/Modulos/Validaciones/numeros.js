/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */



export default validarNumber

function validarNumber(event) {

    if (!(event.charCode >= 48 && event.charCode <= 57)) {
        event.preventDefault();
    }
}