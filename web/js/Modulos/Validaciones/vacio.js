/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */


export default validations;

function validations(event, elements, select) {

    event.preventDefault();

    let num = 0;

    elements.forEach(element => {

        const $fnode = element.parentElement;

        const $span1 = document.createElement("span");
        $span1.classList.add("alert");
        $span1.classList.add("spam");

        let $vacio = $fnode.querySelector(".vacio");

        if ($fnode.contains($vacio)) {
            $fnode.removeChild($vacio);
        }

        let valor = element.value;
        if (valor.length > 0) {
            element.classList.remove("alert");

            num += 1;
        } else {
            element.classList.add("alert");
            $span1.innerText = "Campo vacio";
            $fnode.appendChild($span1);
        }
    });


    if (num === elements.length) {
        elements.forEach((x) => {

            const $fnode = x.parentElement;

            const $span1 = document.createElement("span");
            $span1.classList.add("vacio");
            $span1.classList.add("spam");

            let $vacio = $fnode.querySelector(".vacio");

            if ($fnode.contains($vacio)) {
                $fnode.removeChild($vacio);
            }

            let valor = x.value;

            if (x.classList.contains("input__documento") && valor.length >= 10 && valor.length <= 15) {
                x.classList.remove("alert");
            } else {
                x.classList.add("alert");
                $span1.innerText = "El documento debe tener de 10 a 15 caracteres";
                $fnode.appendChild($span1);
            }

            if (x.classList.contains("input__telefono") && valor.length === 10) {
                x.classList.remove("alert");
            } else {
                x.classList.add("alert");
                $span1.innerText = "El Telefono debe tener de 10";
                $fnode.appendChild($span1);
            }
        });
    }
}