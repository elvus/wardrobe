function clientes() {
    $("#inicio").load("views/clientes.jsp", {}, function () {
        $(".active").removeClass("active");
        $("#tree3").addClass("active");

        ListarClientes();
        editarClientes();

        $("#loadcli").on('click', function () {
            $("#modal").load("views/modalcli.jsp", {}, function () {
                $("#modal-cli").modal();
                ListarCiudades("select");
                ruc();
                AgregarClientes(0, 0);
            });
        });
    });
}

function ListarClientes() {
    $("#table-cli").DataTable({
        destroy: true,
        "ajax": "clientesControlador?accion=listar",
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
        "columns": [
            {"data": "id_cliente"},
            {"data": "documento_cli"},
            {"data": "nombre_cli"},
            {"data": "celular_cli"},
            {"data": "nombre_ciudad"},
            {"data": "direccion_cli"},
            {data: null,
                render: function (data, type, full, meta) {
                    return '<div class="btn-group"><a class="btn btn-info btn-xs edit" data-id="' + data.id_cliente + '">Editar</a><a class="btn btn-primary btn-xs" data-id="' + data.id_cliente + '">Inhabilitar</a></div>'
                }
            }
        ]
    });
}

function AgregarClientes(accion, id) {
    $("#form").submit(function (e) {
        e.preventDefault();
        var data = {
            "accion": accion,
            "id_cliente": id,
            "nombre_cli": $("#nombre").val(),
            "ndocumento_cli": $("#ndoc").val(),
            "dv_cli": $("#dv").val(),
            "telefono_cli": $("#telefono").val(),
            "celular_cli": $("#celular").val(),
            "correo_cli": $("#correo").val(),
            "direccion_cli": $("#direccion").val(),
            "id_ciudad": $("#city").val()
        };
        $.ajax({
            url: 'clientesControlador',
            type: 'post',
            data: data,
            success: function (response) {
                if (response == 1) {
                    toastr.success("Guardado");
                    $("#modal-cli").modal('hide');
                    ListarClientes();
                } else {
                    toastr.error("Ha ocurrido un error");
                }
            }
        });
    });
}

function completarClientes(callback) {
    var x = [];
    var y = [];
    $.get("clientesControlador",
            {accion: "completar"})
            .done(function (response) {
                for (var i = 0; i < response.length; i++) {
                    x[i] = {
                        name: response[i].nombre_cli,
                        id: response[i].id_cliente,
                        ruc_cli: response[i].documento_cli
                    };
                    y[i] = {
                        name: response[i].documento_cli,
                        id: response[i].id_cliente,
                        nombre_cli: response[i].nombre_cli
                    };
                }
                callback(x, y);
            })
            .fail(function (response) {
                toastr.error(response);
            });
}

function editarClientes() {
    $("#table-cli tbody").on('click', '.edit', function () {
        var id = $(this).attr("data-id");
        $("#modal").load("views/modalcli.jsp", {}, function () {
            $("#modal-cli").modal();
            ListarCiudades("select");
            ruc();
            AgregarClientes(1, id);
            $("#modal-cli").modal();
            $.get("clientesControlador",
                    {
                        accion: "buscar",
                        id_cliente: id
                    })
                    .done(function (response) {
                        $("#nombre").val(response.nombre_cli);
                        $("#ndoc").val(response.documento_cli);
                        $("#dv").val(response.dv_cli);
                        $("#telefono").val(response.telefono_cli);
                        $("#celular").val(response.celular_cli);
                        $("#correo").val(response.correo_cli);
                        $("#direccion").val(response.direccion_cli);
                        $("#city").val(response.id_ciudad).trigger("change");
                    })
                    .fail(function (response, jqxhr, error) {
                        toastr.error(error);
                    });
        });
    });
}

