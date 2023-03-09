

window.addEventListener('DOMContentLoaded', (event) => {
    let formulario = document.getElementById('fechaFormInput');
    let fechaMax = new Date();
    fechaMax.setFullYear(fechaMax.getFullYear()+5)

    formulario.value = new Date().toISOString().split("T")[0];
    formulario.min = new Date().toISOString().split("T")[0];
    formulario.max = fechaMax.toISOString().split("T")[0];
});



