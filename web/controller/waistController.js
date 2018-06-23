function talles() {
    $("#inicio").load("views/talles.jsp", {}, function () {
        listarTalles();
        editarTalle();
        $(".add").on("click", function () {
            $("#modal").load("views/modalta.jsp", {}, function () {
                $("#MyModal").modal();
                agregarTalle(0, 0);
            });
        });
    });
}

function listarTalles(accion) {
    if (accion == "select") {
        $.post("tallesControlador", {accion: accion}, function (response) {
            for (var i = 0; i < response.length; i++) {
                var option = document.createElement("option");
                var talles = $(".select-talle");
                option.setAttribute("value", response[i].id_talle);
                option.innerHTML = response[i].nombre_t;
                talles.append(option);
            }
        });
    } else {
        $("#table-talle").DataTable({
            destroy: true,
            ajax: {
                url: "tallesControlador",
                type: "post",
                data: {accion: "listar"}
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
                {data: "id_talle"},
                {data: "nombre_t"},
                {data: "descripcion_t"},
                {data: null,
                    render: function (data, type, full, meta) {
                        return '<div class="btn-group"><a class="btn btn-info btn-xs edit" data-id="' + data.id_talle + '">Editar</a><a class="btn btn-primary btn-xs clo" data-id="' + data.id_talle + '">Inhabilitar</a></div>';
                    }
                }
            ]
        });
    }
}

function agregarTalle(id, accion) {
    $("#form").submit(function (e) {
        e.preventDefault();
        $.ajax({
            url: "tallesControlador",
            type: "post",
            data: {
                accion: accion,
                id_talle: id,
                nombre_t: $("#talle").val(),
                descripcion_t: $("#descripcion").val()
            },
            success: function (response) {
                if (response) {
                    toastr.success("GUARDADO!");
                    $("#MyModal").modal("hide");
                    listarTalles();
                } else {
                    toastr.error("Ha sucedido un error!");
                }
            }
        });
    });
}

function  editarTalle() {
    $("#table-talle tbody").on('click', '.edit', function () {
        var id = $(this).attr("data-id");
        $("#modal").load("views/modalta.jsp", {}, function () {
            $("#MyModal").modal();
            $.post("tallesControlador",
                    {
                        accion: "buscar",
                        id_talle: id
                    })
                    .done(function (response) {
                        $("#talle").val(response.nombre_t);
                        $("#descripcion").val(response.descripcion_t);
                    })
                    .fail(function (response, ajaxOptions, error) {
                        toastr.error(error);
                    });
            agregarTalle(id, 1);
        });
    });
}