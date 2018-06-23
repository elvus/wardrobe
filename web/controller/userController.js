function usuarios() {
    $("#inicio").load("views/usuarios.jsp", {}, function () {
        $(".active").removeClass("active");
        $("#tree1").addClass("active");
        $("#example").DataTable({
            destroy: true,
            "ajax": "usuarioControlador?accion=listar",
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
                {"data": "alias"},
                {"data": "correo_cont"},
                {"data": "cargo_emp"},
                {data: null,
                    render: function (data, type, full, meta) {
                        return '<div class="btn-group"><a class="btn btn-info btn-xs">Editar</a><a class="btn btn-primary btn-xs">Inhabilitar</a></div>';
                    }
                }
            ]
        });
        $("#loadusu").on('click', function () {
            get();
            function get() {
                $("#inicio").load("views/insertUsuario.jsp", {}, function () {

                    $.get("empleadoControlador?accion=sinusuario", function (data) {
                        for (var i = 0; i < data.length; i++) {
                            $("#funcionario").append("<option value='" + data[i].id_empleado + "'>" + data[i].nombre_emp + "</option>");
                        }
                    });

                    $("#funcionario").select2({
                        placeholder: "Seleccione un empleado",
                        allowClear: true
                    });

                    $("#nombre").on('blur', function () {
                        validarNombre($("#nombre").val());
                    });

                    $("#confirmar").on('blur', function () {
                        validarPass($("#pwd").val(), $("#confirmar").val());
                    });

                    $(".save").on('click', function () {
                        alert(validarNombre($("#nombre").val()));
                        alert(validarPass($("#pwd").val(), $("#confirmar").val()));
                        alert(validarSelect($("#funcionario").val()));

                        if (validarNombre($("#nombre").val()) && validarPass($("#pwd").val(), $("#confirmar").val()) && validarSelect($("#funcionario").val())) {
                            AgregarUsuarios(get);
                        }
                    });

                });
            }
        });
    });
}

function AgregarUsuarios(get) {
    $.ajax({
        url: 'usuarioControlador',
        type: 'post',
        data: {
            'accion': "0",
            'usuario': $("#nombre").val(),
            'password': $("#pwd").val(),
            'id_empleado': $("#funcionario").val()
        },
        success: function (response) {
            if (response == 1) {
                toastr.success("Guardado");
                get();
            } else {
                toastr.error("Ha ocurrido un error");
            }
        }
    });
}

function agregarPermisos() {
    var detalles = [];
    $("#table-permisos tbody").each(function () {
        
    });
}

