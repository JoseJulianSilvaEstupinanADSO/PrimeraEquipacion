/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

export default validarString

function validarString(event ) {

    const regex = /^[a-zA-Zá-ÿ\s]$/;
        if (!regex.test(event.key)) {
            event.preventDefault();
        }
}
