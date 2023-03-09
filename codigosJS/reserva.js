window.addEventListener('DOMContentLoaded', (event) => {
    let formulario = document.getElementById('fechaFormInput');
    let fechaMax = new Date();
    fechaMax.setFullYear(fechaMax.getFullYear()+5)


    formulario.min = new Date().toISOString().split("T")[0];
    formulario.max = fechaMax.toISOString().split("T")[0];

    let inputRange = document.getElementById('numPersonasInput')
    inputRange.addEventListener("input", (event) => {
        let rangeOutput = document.getElementById('outputNumPersonas');
        rangeOutput.style.display = "block";
    });

    let horIniInput = document.getElementById('horaInicioInput');
    let HorFinInput = document.getElementById('horaFinInput');

    horIniInput.addEventListener("input", (event) => {
        let horaInicio = new Date('2000-01-01T' + horIniInput.value + ':00');
        let horaFin = new Date('2000-01-01T' + HorFinInput.value + ':00');

        if (horIniInput.value && HorFinInput.value && horaInicio > horaFin)
        {
            alert("No puedes elegir una hora de inicio anterior a la de fin");
        }
    });

    HorFinInput.addEventListener("input", (event) => {
        let horaInicio = new Date('2000-01-01T' + horIniInput.value + ':00');
        let horaFin = new Date('2000-01-01T' + HorFinInput.value + ':00');

        if (horIniInput.value && HorFinInput.value && horaInicio > horaFin)
        {
            alert("No puedes elegir una hora de inicio anterior a la de fin");
        }
    });

});
