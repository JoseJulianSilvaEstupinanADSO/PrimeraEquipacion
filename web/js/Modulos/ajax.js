/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

export default Ajax

async function Ajax(servlet, datos, tipo, action) {
    let request = await fetch(`${servlet}?action=${action}`, {
        method: tipo,
        header: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams(datos)
    });
    let response = await request.json();
    return response;
}
