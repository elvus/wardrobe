function proveedores() {
    $("#inicio").load("views/proveedores.jsp", {}, function () {
        $(".active").removeClass("active");
        $("#tree4").addClass("active");
        ListarProveedores();
        editarProveedores();
        $("#load").on('click', function () {
            $("#modal").load("views/modalpro.jsp", {}, function () {
                $("#modal-pro").modal();
                ruc();
                ListarCiudades("select");
                AgregarProveedores(0, 0);
            });
        });
    });
}

function ListarProveedores(accion) {
    if (accion == "select") {
        $.get("proveedoresControlador", {"accion": "completar"}, function (response) {
            for (var i = 0; i < response.length; i++) {
                var option = document.createElement("option");
                var supplier = document.getElementById("supplier");
                option.setAttribute("value", response[i].id_proveedor);
                option.innerHTML = response[i].nombre_pro;
                supplier.appendChild(option);
            }
        });
    } else {
        $("#table-pro").DataTable({
            destroy: true,
            ajax: "proveedoresControlador?accion=listar",
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
                {"data": "id_proveedor"},
                {"data": "nombre_pro"},
                {"data": "ruc_pro"},
                {"data": "nombre_ciudad"},
                {"data": "direccion_pro"},
                {"data": "celular_pro"},
                {"data": "telefono_pro"},
                {"data": "correo_pro"},
                {data: null,
                    render: function (data, type, full, meta) {
                        return '<div class="btn-group"><a class="btn btn-info btn-xs edit" data-id="' + data.id_proveedor + '">Editar</a><a class="btn btn-primary btn-xs cerrar" data-id="' + data.id_proveedor + '">Inhabilitar</a></div>';
                    }
                }
            ]
        });
    }
}

function AgregarProveedores(accion, id) {
    $("#form").submit(function (e) {
        e.preventDefault();
        var data = {
            "accion": accion,
            "id_proveedor": id,
            "representante_pro": $("#representante").val(),
            "nombre_pro": $("#nombref").val(),
            "ruc_pro": $("#ruc").val(),
            "dv_pro": $("#dv").val(),
            "telefono_pro": $("#telefono").val(),
            "celular_pro": $("#celular").val(),
            "correo_pro": $("#correo").val(),
            "direccion_pro": $("#direccion").val(),
            "id_ciudad": $("#city").val()
        };
        $.ajax({
            url: 'proveedoresControlador',
            type: 'post',
            data: data,
            success: function (response) {
                if (response == 1) {
                    toastr.success("Guardado");
                    $("#modal-pro").modal('hide');
                    ListarProveedores();
                    autocompleteSuppliers();
                } else {
                    toastr.error("Ha ocurrido un error");
                }
            }
        });
    });
}

function editarProveedores() {
    $("#table-pro tbody").on('click', '.edit', function () {
        var id = $(this).attr("data-id");
        $("#modal").load("views/modalpro.jsp", {}, function () {
            ruc();
            ListarCiudades("select");
            AgregarProveedores(1, id);
            $("#modal-pro").modal();
            $.get('proveedoresControlador',
                    {
                        accion: "buscar",
                        id_proveedor: id
                    })
                    .done(function (response) {
                        $("#representante").val(response.representante_pro);
                        $("#nombref").val(response.nombre_pro);
                        $("#ruc").val(response.ruc_pro);
                        $("#dv").val(response.dv_pro);
                        $("#telefono").val(response.telefono_pro);
                        $("#celular").val(response.celular_pro);
                        $("#correo").val(response.correo_pro);
                        $("#direccion").val(response.direccion_pro);
                        $("#city").val(response.id_ciudad).trigger("change");
                    })
                    .fail(function (response, jqxhr, error) {
                        toastr.error(error);
                    });
        });
    });
}




