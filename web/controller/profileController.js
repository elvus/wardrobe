function perfil() {
    $("#inicio").load('views/miperfil.jsp', {}, function () {
        userData(function (data) {
            document.getElementById("nombre").innerHTML = data.nombre_emp + " " + data.apellido_emp;
            document.getElementById("ndoc").innerHTML = data.ndocumento_emp;
            document.getElementById("nac").innerHTML = data.nacionalidad_pais;
            document.getElementById("fnac").innerHTML = data.fnac_emp;
            document.getElementById("alias").innerHTML = data.alias;
            document.getElementById("correo").innerHTML = data.correo_cont;
            document.getElementById("telefono").innerHTML = data.telefono_cont;
            document.getElementById("celular").innerHTML = data.celular_cont;
            document.getElementById("fing").innerHTML = data.fecha_ingreso_emp;
            document.getElementById("est").innerHTML = data.estado_emp;
            document.getElementById("cargo").innerHTML = data.cargo_emp;
            document.getElementById("dir").innerHTML = data.nombre_ciudad + " - " + data.nombre_dep;
        });
    });
}
