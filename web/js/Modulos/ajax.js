/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

export default Ajax

async function Ajax(servlet, datos, tipo, action) {
    const url = `${servlet}?action=${action}`;

    const options = {
        method: tipo,
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    };

    // Si no es GET, agregar el cuerpo a las opciones
    if (tipo.toUpperCase() !== 'GET') {
        options.body = new URLSearchParams(datos);
    }

    // Si es GET, agregar los datos directamente a la URL
    const finalUrl = tipo.toUpperCase() === 'GET' 
        ? `${url}&${new URLSearchParams(datos)}` 
        : url;

    const request = await fetch(finalUrl, options);
    const response = await request.json();
    return response;
}











//async function Ajax(servlet, datos, tipo, action) {
//    let request = await fetch(`${servlet}?action=${action}`, {
//        method: tipo,
//        header: {
//            'Content-Type': 'application/x-www-form-urlencoded'
//        },
//        body: new URLSearchParams(datos)
//    });
//    let response = await request.json();
//    return response;
//}

