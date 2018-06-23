$(document).ready(function () {
    $("#modal-aux").load("views/aperturaCaja.html", {}, function () {
        modalCaja();
    });
});

function modalCaja() {
    $("#Modal-caja").modal();
    abrirCaja();
    $.post("cajaControlador",
            {
                accion: "apertura"
            })
            .done(function (response) {
                console.log(response);
                $("#apertura").val(response.saldo_disponible);
            })
            .fail(function (response, jqxhr, error) {
                toastr.error(error);
            });
}

$(".caja").on('click', function () {
    $("#modal-aux").load("views/caja.html", {}, function () {
        $("#Modal-scaja").modal();

        $(".mod").on('click', function () {
            $("#modal").load("views/aperturaCaja.html", {}, function () {
                modalCaja();
            });
        });

        $(".informe").on('click', function () {
            $("#Modal-scaja").modal("hide");
            $("#inicio").load("views/reporteCaja.html", {}, function () {
                $("#table-cja").DataTable({
                    destroy: true,
                    ajax: {
                        type: "post",
                        url: "cajaControlador",
                        data: {
                            accion: "reporte"
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
                        {data: "id_caja"},
                        {data: "fecha_caja"},
                        {data: "ingreso_caja"},
                        {data: "egreso_caja"},
                        {data: "saldo_disponible"},
                        {data: "saldo_anterior"}
                    ]
                })
            });
        });

        $.post("cajaControlador",
                {
                    accion: "apertura"
                })
                .done(function (response) {
                    console.log(response);
                    $(".sa").html(response.saldo_anterior);
                    $(".sd").html(response.saldo_disponible);
                })
                .fail(function (response, jqxhr, error) {
                    toastr.error(error);
                });

    });
});
function abrirCaja() {
    $("#form-caja").submit(function (e) {
        e.preventDefault();
        $.post("cajaControlador",
                {
                    accion: "abrir",
                    saldo_anterior: $("#apertura").val(),
                    saldo_disponible: $("#apertura").val()
                })
                .done(function (response) {
                    if (response == 1) {
                        toastr.success("LISTO ☺");
                    } else {
                        toastr.success("Error inesperado ☻");
                    }
                })
                .fail(function (response, jqxhr, error) {
                    toastr.error(error);
                });
    });
}