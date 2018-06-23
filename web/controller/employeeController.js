function funcionarios() {
    $("#inicio").load('views/funcionarios.jsp', {}, function () {
        $(".active").removeClass("active");
        $("#tree2").addClass("active");
        ListarEmpleados();
        editarFuncionario();
        $("#loadfunc").on('click', function () {
            $("#modal").load('views/modalfunc.jsp', {}, function () {
                $("#Modal-func").modal();
                selectCargo();
                ListarCiudades("select");
                AgregarEmpleado(0, 0);
            });
        });
    });
}

function ListarEmpleados() {
    $("#table-func").DataTable({
        destroy: true,
        "ajax": "empleadoControlador?accion=listar",
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
            {"data": "nombre_emp"},
            {"data": "ndocumento_emp"},
            {"data": "correo_cont"},
            {"data": "celular_cont"},
            {"data": "cargo_emp"},
            {"data": "estado_emp",
                "render": function (data, type, full, meta) {
                    return '<label class="label label-success">' + data + '</label>';
                }
            },
            {data: null,
                render: function (data, type, full, meta) {
                    return '<div class="btn-group"><a class="btn btn-info btn-xs edit" data-id="' + data.id_empleado + '">Editar</a><a class="btn btn-primary btn-xs cerrar" data-id="' + data.id_empleado + '">Inhabilitar</a></div>'
                }
            }
        ]
    });
}

function AgregarEmpleado(accion, id) {
    $("#form").submit(function (e) {
        e.preventDefault();
        var data = {
            "accion": accion,
            "id_empleado": id,
            "nombre_emp": $("#nombre").val(),
            "apellido_emp": $("#apellido").val(),
            "ndocumento_emp": $("#ndoc").val(),
            "fnac_emp": $("#fnac").val(),
            "fecha_ingreso_emp": $("#fing").val(),
            "id_cargo": $("#cargo").val(),
            "telefono_cont": $("#telefono").val(),
            "celular_cont": $("#celular").val(),
            "correo_cont": $("#correo").val(),
            "id_nacionalidad": $("#nacionalidad").val(),
            "id_ciudad": $("#city").val(),
            "principal_cont": 1
        };
        $.ajax({
            url: 'empleadoControlador',
            type: 'post',
            data: data,
            success: function (response) {
                if (response == 1) {
                    toastr.success("Guardado");
                    $("#Modal-func").modal('hide');
                    ListarEmpleados();
                } else {
                    toastr.error("Ha ocurrido un error");
                }
            }
        });
    });
}

function editarFuncionario() {
    $("#table-func tbody").on('click', '.edit', function () {
        var id = $(this).attr("data-id");
        $("#modal").load("views/modalfunc.jsp", {}, function () {
            selectCargo();
            ListarCiudades("select");
            $("#Modal-func").modal();
            $.get("empleadoControlador",
                    {
                        accion: "buscar",
                        id_empleado: id
                    })
                    .done(function (response) {
                        $("#nombre").val(response.nombre_emp);
                        $("#apellido").val(response.apellido_emp);
                        $("#ndoc").val(response.ndocumento_emp);
                        $("#fnac").val(response.fnac_emp);
                        $("#fing").val(response.fecha_ingreso_emp);
                        $("#cargo").val(response.id_cargo).trigger("change");
                        $("#telefono").val(response.telefono_cont);
                        $("#celular").val(response.celular_cont);
                        $("#correo").val(response.correo_cont);
                        $("#city").val(response.id_ciudad).trigger("change");
                    })
                    .fail(function (response, jqxhr, error) {
                        toastr.error(error);
                    });
            AgregarEmpleado(1, id);
        });
    });
}
