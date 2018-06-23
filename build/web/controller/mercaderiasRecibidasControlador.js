$(".mer-rec").on('click', function () {
    $("#inicio").load("views/mercaderiasrecibidas.jsp", {}, function () {
        listarMercaderiasRecibidas();
        editarMR();
        $("#load").on('click', function () {
            nuevaMercaderiaRecibida();
        });
    });
});

function listarMercaderiasRecibidas() {
    $("#table-mr").DataTable({
        destroy: true,
        ajax: {
            url: "mercaderiarecControlador",
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
            {data: "nrofactura_mr"},
            {data: "nombre_pro"},
            {data: "fecha_mr"},
            {data: "comentario_mr"},
            {data: null,
                render: function (data, type, full, meta) {
                    var btn = '<div class="btn-group">' +
                            '<a class="btn btn-info btn-xs edit" data-id="' + data.id_mercaderia_recibida + '">Editar</a>' +
                            '<a class="btn btn-warning btn-xs clo" data-id="' + data.id_mercaderia_recibida + '">Anular</a><a></a>' +
                            '</div>';
                    return btn;
                }
            }
        ]
    });
}

function nuevaMercaderiaRecibida() {
    $("#inicio").load("views/insertMercaderiaRec.jsp", {}, function () {
        limpiar();
        autocompleteSuppliers();
        $("tfoot").remove();
        agregarMR(0, 0);
        validarNfac();
        $("#fecha").datepicker({
            format: "dd/mm/yyyy",
            autoclose: true
        });
        $(".mas").on('click', function () {
            $("#modal").load("views/modalpro.jsp", {}, function () {
                $("#MyModal").modal();
                ruc();
                ListarCiudades("select");
                AgregarProveedores();
            });
        });
    });
}

function agregarMR(id, accion) {
    var detalle = [];
    $("#form-orden").submit(function (e) {
        e.preventDefault();
        $("tbody tr").each(function (i) {
            if ($(this).find("td input.cod").attr("data-id")) {
                detalle[i] = {
                    id_articulo: $(this).find("td input.cod").attr("data-id"),
                    precio_unitario_mr: $(this).find("td input.precio").val(),
                    talle_mr: $(this).find("select.talle").val(),
                    cantidad_mr: $(this).find("td input.cant").val(),
                    iva_mr: $(this).find("td select.iva").val(),
                    codigo_ar_mr: $(this).find("td input.cod").val(),
                    subtotal_mr: $(this).find("td input.total").val()
                };
            }
        });
        $.post("mercaderiarecControlador",
                {
                    accion: accion,
                    id_mercaderia_recibida: id,
                    nrofactura_mr: $("#nfac").val(),
                    id_proveedor: $("#ruc_pro").attr("data-id"),
                    fecha_mr: $("#fecha").val(),
                    comentario_mr: $("#comentario").val(),
                    total_iva_mr: $("#ti").val(),
                    total_mr: $("#total").val(),
                    detalle_mr: JSON.stringify(detalle)
                },
                function (response) {
                    if (response == 1) {
                        toastr.success("GUARDADO!");
                        nuevaMercaderiaRecibida();
                    } else {
                        toastr.error("Error Inesperado!");
                    }
                }
        );
        if (id != 0 && accion == 0) {
            RecibirOrden(id);
        }
    });
}

function editarMR() {
    $("#table-mr tbody").on('click', '.edit', function () {
        var id = $(this).attr("data-id");
        $("#inicio").load("views/insertMercaderiaRec.jsp", {}, function () {
            limpiar();
            autocompleteSuppliers();
            $("tfoot").remove();
            agregarMR(1, id);
            validarNfac();
            $("#fecha").datepicker({
                format: "dd/mm/yyyy",
                autoclose: true
            });
            $(".mas").on('click', function () {
                $("#modal").load("views/modalpro.jsp", {}, function () {
                    $("#MyModal").modal();
                    ruc();
                    ListarCiudades("select");
                    AgregarProveedores();
                });
            });
            completarCabMr(id);
            completarDetMr(id);
        });
    });
}

function completarCabMr(id) {
    $.post("mercaderiarecControlador",
            {
                accion: "buscarCab",
                id_mercaderia_recibida: id
            })
            .done(function (response) {
                $("#nfac").val(response.nrofactura_mr);
                $("#proveedor").val(response.nombre_pro);
                $("#ruc_pro").val(response.ruc_pro + "-" + response.dv_pro).attr("data-id", response.id_proveedor);
                $("#fecha").val(response.fecha_mr);
                $("#comentario").val(response.comentario_mr);
            })
            .fail(function (response, jqxhr, error) {
                toastr.error(error);
            });
}

function completarDetMr(id) {
    $.post("mercaderiarecControlador",
            {
                accion: "buscarDet",
                id_mercaderia_recibida: id
            })
            .done(function (response) {
                for (var i = 0; i <= response.length; i++) {
                    $("#cuerpo").append(compra_rows);
                }
                $("#cuerpo tr").each(function (i) {
                    var ele = this;
                    autocompletarArticulos();
                    calcularTotal();
                    calcularTotales();
                    addRow();
                    if (i < response.length) {
                        $(this).find("td input.cod").val(response[i].codigo_ar).removeAttr("data-row").attr("data-id", response[i].id_articulo);
                        $(this).find("td input.desc").val(response[i].descripcion_ar);
                        $(this).find("td input.cant").val(response[i].cantidad_rec);
                        $(this).find("td input.precio").val(response[i].precio_unitario_rec);
                        $(this).find("td select.iva").val(response[i].iva_rec);
                        $(this).find("td input.total").val(response[i].precio_unitario_rec * response[i].cantidad_rec);
                        $(this).find("td button.eliminar").removeClass("hide");
                    }

                    $.post("tallesControlador", {accion: "select"}, function (data) {
                        for (var x = 0; x < data.length; x++) {
                            var option = document.createElement("option");
                            option.setAttribute("value", data[x].id_talle);
                            option.innerHTML = data[x].nombre_t;
                            $(ele).find("select.select-talle").append(option);
                            if (i < response.length) {
                                if (response[i].id_talle == data[x].id_talle) {
                                    option.setAttribute("selected", "selected");
                                }
                            }
                        }
                        $(ele).find("select.select-talle").removeClass("select-talle");
                    });
                });
            })
            .fail(function (response, jqxhr, error) {
                toastr.error(error);
            });
}

