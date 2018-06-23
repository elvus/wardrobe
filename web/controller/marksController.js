function marcas() {
    $("#inicio").load("views/marcas.jsp", {}, function () {
        ListarMarcas();
        $("#load").on('click', function () {
            $("#modal").load("views/modalmar.jsp", {}, function () {
                $("#MyModal").modal();
                AgregarMarca(0, 0);
                autocompleteSuppliers();
            });
        });
    });
}

function ListarMarcas(accion) {
    if (accion == "select") {
        $.get("marcasControlador", {"accion": accion}, function (response) {
            for (var i = 0; i < response.length; i++) {
                var option = document.createElement("option");
                var marca = document.getElementById("select-marca");
                option.setAttribute("value", response[i].id_marca);
                option.innerHTML = response[i].nombre_marca;
                marca.appendChild(option);
            }
        });
    } else {
        $("#example").DataTable({
            destroy: true,
            ajax: "marcasControlador?accion=listar",
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
                {"data": "id_marca"},
                {"data": "nombre_marca"},
                {"data": null,
                    "render": function (data, type, full, meta) {
                        return "<div class='btn-group'><a onclick='editarMarcas(" + data.id_marca + ")' class='btn btn-info btn-xs'>Editar</a><a class='btn btn-danger btn-xs' onclick='eliminarMarca(" + data.id_marca + ")'>Eliminar<a></div>";
                    }
                }
            ]
        });
    }
}

function AgregarMarca(accion, id) {
    $("#form").submit(function (e) {
        e.preventDefault();
        $.ajax({
            url: "marcasControlador",
            type: "post",
            data: {
                "accion": accion,
                "id_marca": id,
                "nombre_marca": $("#nombre").val(),
            },
            success: function (response) {
                if (response == 1) {
                    toastr.success("Guardado!");
                    $("#MyModal").modal("hide");
                    ListarMarcas();
                } else {
                    toastr.error("Ha ocurrido un error");
                }
            }
        });
    });
}

function editarMarcas(id) {
    $("#modal").load("views/modalmar.jsp", {}, function () {
        $.get("marcasControlador", {"accion": "buscar", "id": id}, function (response) {
            $("#nombre").val(response.nombre_marca);
            $("#proveedor").val(response.nombre_pro);
            $("#ruc_pro").val(response.ruc_pro + "-" + response.dv_pro);
            document.getElementById("ruc_pro").setAttribute("data-id", response.id_proveedor);
            document.getElementById("ruc_pro").readOnly = true;
        });
        $("#MyModal").modal();
        autocompleteSuppliers();
        AgregarMarca(1, id);
        ListarMarcas();
    });
}

function eliminarMarca(id) {
    $.get("marcasControlador", {"accion": "eliminar", "id": id}, function (response) {
        if (response == 1) {
            toastr.success("Eliminado!");
            ListarMarca();
        } else {
            toastr.error("Ha ocurrido un error");
        }
    });
}


