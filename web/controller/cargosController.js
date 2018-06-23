$(".cargos").on('click', function () {
    $("#inicio").load("views/cargos.jsp", {}, function () {
        listarCargos();
        editarCargos();
        $("#load").on('click', function () {
            $("#modal").load("views/modalcargo.jsp", {}, function () {
                $("#Modal-cargo").modal();
                agregarCargos(0, 0);
            });
        });
    });
});

function listarCargos() {
    $("#table-cargo").DataTable({
        destroy: true,
        ajax: {
            type: "post",
            url: "cargosControlador",
            data: {
                accion: "listar"
            }
        },
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
            {data: "id_cargo"},
            {data: "nombre_cargo"},
            {data: null,
                render: function (data, type, full, meta) {
                    return '<div class="btn-group"><a class="btn btn-info btn-xs edit" data-id="' + data.id_cargo + '">Editar</a><a class="btn btn-primary btn-xs cerrar" data-id="' + data.id_cargo + '">Inhabilitar</a></div>'
                }
            }
        ]
    });
}

function agregarCargos(accion, id) {
    $("#form-cargo").submit(function (e) {
        e.preventDefault();
        $.post("cargosControlador",
                {
                    id_cargo: id,
                    accion: accion,
                    nombre_cargo: $("#nombre").val()
                })
                .done(function (response) {
                    if (response == 1) {
                        toastr.success("GUARDADO!");
                        $("#Modal-cargo").modal("hide");
                        listarCargos();
                    } else {
                        toastr.error("Error Inesperado");
                    }
                })
                .fail(function (response, ajaxOptions, error) {
                    toastr.error(error);
                });
    });
}

function editarCargos() {
    $("#table-cargo tbody").on('click', '.edit', function () {
        var id = $(this).attr("data-id");
        $("#modal").load("views/modalcargo.jsp", {}, function () {
            $("#Modal-cargo").modal();
            $.post("cargosControlador",
                    {
                        id_cargo: id,
                        accion: "buscar"
                    })
                    .done(function (response) {
                        $("#nombre").val(response.nombre_cargo);
                    })
                    .fail(function (response, jqrxhl, error) {
                        toastr.error(error);
                    });
            agregarCargos(1, id);
        });
    });
}

function selectCargo() {
    $(".select-cargo").select2({
        placeholder: "Seleccione el cargo",
        dropdownParent: $("#parent-cargo"),
        allowClear: true
    });
    $.post("cargosControlador",
            {
                accion: "select"
            })
            .done(function (response) {
                for (var i = 0; i < response.length; i++) {
                    var option = document.createElement("option");
                    var select = $(".select-cargo");
                    option.setAttribute("value", response[i].id_cargo);
                    option.innerHTML = response[i].nombre_cargo;
                    select.append(option);
                }
            })
            .fail(function (response, ajaxOptions, error) {
                toastr.error(error);
            });
}