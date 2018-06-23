function ciudades() {
    $("#inicio").load("views/ciudades.jsp", {}, function () {
        ListarCiudades("listar");
        $("#load").on('click', function () {
            $("#modal").load("views/modalciu.jsp", {}, function () {
                $("#MyModal").modal();
                ListarDepartamentos("select");
                AgregarCiudades(0, 0);
            });
        });
    });
}

function ListarCiudades(accion) {
    if (accion == "select") {
        $.get('ciudadControlador?accion=' + accion, function (data) {
            for (var i = 0; i < data.length; i++) {
                $("#city").append('<option value="' + data[i].id_ciudad + '">' + data[i].nombre_ciudad + '</option>');
            }
        });

        $("#city").select2({
            dropdownParent: $("#ciudad"),
            placeholder: "Seleccione la ciudad",
            allowCrear: true
        });
    } else {
        $("#example").DataTable({
            destroy: true,
            ajax: "ciudadControlador?accion=listar",
            columnDefs: [
                {
                    "targets": [3],
                    "data": null,
                    "render": function (data, full, type, meta) {
                        return "<div class='btn-group'><a onclick='editarCiudad(" + data.id_ciudad + ")' class='btn btn-info btn-xs'>Editar</a><a class='btn btn-danger btn-xs' onclick='eliminarCiudad(" + data.id_ciudad + ")'>Eliminar<a></div>";
                    }
                }
            ],
            dom: '<"html5buttons"B>lTfgitp',
        buttons: [
            {extend: 'copy'},
            {extend: 'csv'},
            {extend: 'excel', title: 'ExampleFile'},
            {extend: 'pdf', title: 'ExampleFile'},
            {extend: 'print',
                customize: function (win) {
                    $(win.document.body).addClass('white-bg');
                    $(win.document.body).css('font-size', '10px');

                    $(win.document.body).find('table')
                            .addClass('compact')
                            .css('font-size', 'inherit');
                }
            }],
            columns: [
                {"data": "id_ciudad"},
                {"data": "nombre_ciudad"},
                {"data": "nombre_dep"}
            ]
        });
    }
}

function AgregarCiudades(accion, id) {
    $("#form").submit(function (e) {
        e.preventDefault();
        $.ajax({
            url: "ciudadControlador",
            type: "post",
            data: {
                "accion": accion,
                "id": id,
                "nombre_ciudad": $("#nombre").val(),
                "id_departamento": $("#departamento").val()
            },
            success: function (response) {
                if (response == 1) {
                    toastr.success("Guardado!");
                    $("#MyModal").modal("hide");
                    ListarCiudades();
                } else {
                    toastr.error("Ha ocurrido un error");
                }
            }
        });
    });
}

function editarCiudad(id) {
    $("#modal").load("views/modalciu.jsp", {}, function () {
        ListarDepartamentos("select");
        $("#MyModal").modal();
        $.ajax({
            url: "ciudadControlador",
            type: "get",
            data: {"accion": "buscar", "id": id},
            success: function (response) {
                $("#nombre").val(response.nombre_ciudad);
                $("#departamento").val(response.id_departamento).trigger("change");
            }
        });
        AgregarCiudades(1, id);
    });
}

function eliminarCiudad(id) {
    $.get("ciudadControlador", {"accion": "eliminar", "id": id}, function (response) {
        if (response == 1) {
            toastr.success("Eliminado!");
            ListarCiudades("listar");
        } else {
            toastr.error("Ha ocurrido un error");
        }
    });
}

