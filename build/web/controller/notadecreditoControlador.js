$(".notacredito").on('click', function () {
    $("#inicio").load("views/notadecredito.jsp", function () {
        listarNotas();
    });
});

function listarNotas() {
    $("#table-nc").DataTable({
        destroy: true,
        ajax: {
            type: "post",
            url: "notacreditoControlador",
            data: {accion: "listar"},
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
            {data: "id_venta"},
            {data: "fecha_ven"},
            {data: "nombre_cli"},
            {data: "total_ven"},
            {data: null,
                render: function (data, type, full, meta) {
                    return '<div class="btn-group"><a class="btn btn-info btn-xs see" data-id="' + data.id_venta + '">Ver</a><a class="btn btn-primary btn-xs clo" data-id="' + data.id_venta + '">Anular</a></div>';
                }
            }
        ]
    });
}

function agregarNotaCredito(accion, id) {
    $("#form-nc").submit(function (e) {
        e.preventDefault();
        var detalle = [];
        $("#cuerpo tr").each(function (i) {
            if ($(this).find("input.cod").attr("data-id")) {
                detalle[i] = {
                    id_articulo: $(this).find("input.cod").attr("data-id"),
                    precio_ven: $(this).find("input.pv").val(),
                    talle_ven: $(this).find("select.talle").val(),
                    cantidad_ven: $(this).find("input.cant").val(),
                    iva_ven: $(this).find("select.iva").val(),
                    subtotal_ven: $(this).find("input.tv").val()
                };
            }
        });

        $.post("notacreditoControlador",
                {
                    accion: accion,
                    id_venta: id,
                    id_cliente: $("#cliente").attr("data-id"),
                    total_ven: $("#total").val(),
                    total_iva_ven: $("#ti").val(),
                    detalle: JSON.stringify(detalle)
                })
                .done(function (response) {
                    if (response == 1) {
                        toastr.success("GUARDADO!");
                        nuevaVenta();
                    } else {
                        toastr.error("Error Inesperado");
                    }
                })
                .fail(function (response, jqrxml, error) {
                    toastr.error(error);
                });
    });
}