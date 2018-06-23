function departamentos() {
    $("#inicio").load("views/departamentos.jsp", {}, function () {
        ListarDepartamentos();
        $("#load").on('click', function () {
            $("#modal").load("views/modaldep.jsp", {}, function () {
                $("#MyModal").modal();
                AgregarDepartamentos(0, 0);
            });
        });
    });
}

function ListarDepartamentos(accion) {
    if (accion == "select") {
        $.get("departamentosControlador", {"accion": accion}, function (response) {
            for (var i = 0; i < response.length; i++) {
                var option = document.createElement("option");
                var dpto = document.getElementById("departamento");
                option.setAttribute("value", response[i].id_departamento);
                option.innerHTML = response[i].nombre_dep;
                dpto.appendChild(option);
            }
        });
        $("#departamento").select2({
            dropdownParent: $("#parent"),
            placeholder: "Seleccione el departamento",
            allowCrear: true
        });
    } else {
        $("#example").DataTable({
            destroy: true,
            ajax: "departamentosControlador?accion=listar",
            columnDefs: [
                {
                    "targets": [2],
                    "data": null,
                    "render": function (data, type, full, meta) {
                        return "<div class='btn-group'><a onclick='editarDepartamentos(" + data.id_departamento + ")' class='btn btn-info btn-xs'>Editar</a><a class='btn btn-danger btn-xs' onclick='eliminarDpto(" + data.id_departamento + ")'>Eliminar<a></div>";
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
                {"data": "id_departamento"},
                {"data": "nombre_dep"}
            ]
        });
    }
}

function AgregarDepartamentos(accion, id) {
    $("#form").submit(function (e) {
        e.preventDefault();
        $.ajax({
            url: "departamentosControlador",
            type: "post",
            data: {
                "accion": accion,
                "id_departamento": id,
                "nombre_dep": $("#nombre").val()
            },
            success: function (response) {
                if (response == 1) {
                    toastr.success("Guardado!");
                    $("#MyModal").modal("hide");
                    ListarDepartamentos();
                } else {
                    toastr.error("Ha ocurrido un error");
                }
            }
        });
    });
}

function editarDepartamentos(id) {
    $("#modal").load("views/modaldep.jsp", {}, function () {
        $.get("departamentosControlador", {"accion": "buscar", "id": id}, function (response) {
            $("#nombre").val(response.nombre_dep);
        });
        $("#MyModal").modal();
        AgregarDepartamentos(1, id);
        ListarDepartamentos();
    });
}

function eliminarDpto(id) {
    $.get("departamentosControlador", {"accion": "eliminar", "id": id}, function (response) {
        if (response == 1) {
            toastr.success("Eliminado!");
            ListarDepartamentos();
        } else {
            toastr.error("Ha ocurrido un error");
        }
    });
}


