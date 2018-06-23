$(".cobros").on('click', function () {
    $("#inicio").load("views/cobros.html", function () {
        VentasPorCobrar();
    });
});

function VentasPorCobrar() {
    $("#table-cobros").DataTable({
        destroy: true,
        ajax: {
            type: "post",
            url: "ventasControlador",
            data: {accion: "cobros"}
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
                    return '<button class="btn btn-primary btn-xs cob" data-id="' + data.id_venta + '">Cobrar</button>';
                }
            }
        ]
    });
}

function CobrarVenta() {
    $("#table-cobros tbody").on('click', '.cob', function () {
        $("#modal").load("views/modalCobro.html", {}, function () {
            $("#Modal-cobro").modal();
        });
    });
}

function AgregarCobro(accion, id) {
    $.post("recibosControlador",
            {
                accion: accion,
                id_recibo: id,
                id_venta: $("#nfac").attr("data-id"),
                monto_rec: $("#monto_rec").val()
            })
            .done(function (response) {
                if (response == 1) {
                    toastr.success("LISTO!");
                } else {
                    toastr.error("Error Inesperado");
                }
            })
            .fail(function (response, jqxhr, error) {
                toastr.error(error);
            });
}

