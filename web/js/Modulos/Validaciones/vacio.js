/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

export default validations;

function validations(event, elements, select) {

    event.preventDefault();

    let num = 0;

    elements.forEach(element => {
        let valor = element.value;
        
        if (valor.length > 0) {
            element.classList.remove("alert");

            num += 1;
        } else {
            element.classList.add("alert");
        }
    });
    return num;
}