function color() {
    $("#inicio").load("views/colores.jsp", {}, function () {
        ListarColor();
        $("#load").on('click', function () {
            $("#modal").load("views/modalcol.jsp", {}, function () {
                $("#MyModal").modal();
                AgregarColor(0, 0);
            });
        });
    });
}
function ListarColor() {
    $("#example").DataTable({
        destroy: true,
        ajax: "coloresControlador?accion=listar",
        columnDefs: [
            {
                "targets": [2],
                "data": null,
                "render": function (data, full, type, meta) {
                    return "<a class='btn btn-info btn-xs'>Detalles</a>"
                }
            }
        ],
        columns: [
            {"data": "id_color"},
            {"data": "nombre_color"}
        ]
    });
}

function AgregarColor(accion, id) {
    $("#form").submit(function (e) {
        e.preventDefault();
        $.ajax({
            url: "coloresControlador",
            type: "post",
            data: {"accion": 0, "nombre_color": $("#nombre").val()},
            success: function (response) {
                if (response == 1) {
                    toastr.success("Guardado!");
                    $("#MyModal").modal("hide");
                    ListarColor();
                } else {
                    toastr.error("Ha ocurrido un error");
                }
            }
        });
    });
}

function EditarColor(id) {
    $.ajax({
        url: "coloresControlador",
        type: "get",
        data: {"accion": "buscar"},
        success: function (response) {
            $("#nombre").val(response.nombre_color);
        }
    });
    $("#MyModal").modal();
    AgregarColor(1, id);
}




