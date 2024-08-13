/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */


export default Mensaje

function Mensaje($titleError,$paragrahp,error, title_msj,paragrahp_msj) {
    $titleError.innerText = title_msj;
    $paragrahp.innerText = paragrahp_msj;
    error.style.display = "block";
    setTimeout(() => {
        error.style.display = "none";
    }, 2000);
}
