$(".facturas").on('click', function () {
    $("#inicio").load("views/ventas.jsp", {}, function () {
        listarVentas();
        GenerarNotadeCredito();
        $(".add").on('click', function () {
            nuevaVenta();
        });
    });
});

function nuevaVenta() {
    $("#inicio").load("views/insertVenta.jsp", {}, function () {
        Hora();
        fecha();
        completarClientes(function (nom, ruc) {
            $(".nom").typeahead("destroy");
            $(".nom").typeahead({
                source: nom,
                autoSelect: true
            });
            $(".ruc").typeahead("destroy");
            $(".ruc").typeahead({
                source: ruc,
                autoSelect: true
            });
        });
        $(".nom").on('change', function () {
            if ($(this).val() != "") {
                var current = $(this).typeahead("getActive");
                $(".ruc").val(current.ruc_cli).prop("disabled", true);
                $(".nom").attr("data-id", current.id);
            } else {
                $(".ruc").prop("disabled", false);
            }
        });
        $(".ruc").on('change', function () {
            if ($(this).val() != "") {
                var current = $(this).typeahead("getActive");
                $(".nom").val(current.nombre_cli).prop("disabled", true);
                $(".nom").attr("data-id", current.id);
            } else {
                $(".nom").prop("disabled", false);
            }
        });
        $("#cuerpo").html(venta_rows);
        listarTalles("select");
        autocompletarArticulos();
        addVentaRow();
        agregarVentas(0, 0);
    });
}

function listarVentas() {
    $("#table-ventas").DataTable({
        destroy: true,
        ajax: {
            type: "post",
            url: "ventasControlador",
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
            {data: "id_venta"},
            {data: "fecha_ven"},
            {data: "nombre_cli"},
            {data: "tipo_ven",
                render: function (data, type, full, meta) {
                    if (data == 0) {
                        return '<label class="label label-primary">Contado</label>';
                    } else if (data == 1) {
                        return '<label class="label label-danger">Credito</label>';
                    }
                }
            },
            {data: "total_ven"},
            {data: null,
                render: function (data, type, full, meta) {
                    return '<div class="btn-group"><a class="btn btn-info btn-xs nc" data-id="' + data.id_venta + '">Nota de Credito</a><a class="btn btn-primary btn-xs clo" data-id="' + data.id_venta + '">Anular</a></div>';
                }
            }
        ]
    });
}

function addVentaRow() {
    $(".add").on('change', function () {
        if ($(this).val() != "") {
            var x = $("#cuerpo tr").length;
            var u = this.getAttribute("data-row");

            this.removeAttribute("data-row");
            if (u == "u") {
                if (x > 0) {
                    $(".eliminar").removeClass('hide');
                }
                $("#cuerpo").append(venta_rows);
                autocompletarArticulos();
                listarTalles("select");
                $(this).parent().parent().find("td input.desc").removeAttr("data-row");
                $(this).parent().parent().find("td input.add").removeClass("add");
                $(this).parent().parent().find("td select.select-talle").removeClass("select-talle");
                addVentaRow();
                calcularTotal();
                $(".cod").change(function () {
                    calcularTotalVenta();
                });
                $(".desc").change(function () {
                    calcularTotalVenta();
                });
            }
        } else {
            toastr.error("Primero debe ingresar el Proveedor");
        }
    });
}

function calcularTotalVenta() {
    var t10 = 0, t5 = 0, tE = 0, total = 0, ti;
    $("#cuerpo tr").each(function () {
        if ($(this).find("select.iva").val() == "5") {
            t5 += parseInt($(this).find("input.tv").val()) / 21;
        } else if ($(this).find("select.iva").val() == "10") {
            t10 += parseInt($(this).find("input.tv").val()) / 11;
        } else if ($(this).find("select.iva").val() == "E") {
            tE += parseInt($(this).find("input.tv").val());
        }
        total += parseInt($(this).find("input.tv").val());
    });
    ti = t10 + t5;
    $("#i10").val(Math.round(t10));
    $("#i5").val(Math.round(t5));
    $("#exenta").val(tE);
    $("#ti").val(Math.round(ti));
    $("#total").val(total);
}

function agregarVentas(accion, id) {
    $("#form-venta").submit(function (e) {
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

        $.post("ventasControlador",
                {
                    accion: accion,
                    id_venta: id,
                    id_cliente: $("#cliente").attr("data-id"),
                    total_ven: $("#total").val(),
                    total_iva_ven: $("#ti").val(),
                    tipo_ven: $("input[name='radio']:checked").val(),
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

function GenerarNotadeCredito() {
    $("#table-ventas tbody").on('click', '.nc', function () {
        var id = $(this).attr("data-id");
        $("#inicio").load("views/insertNotaCredito.html", {}, function () {
            generarCabNc(id);
            generarDetNc(id);
            agregarNotaCredito(0, id);
        });
    });
}

function generarCabNc(id) {
    $.post("ventasControlador",
            {
                id_venta: id,
                accion: "buscarCab"
            })
            .done(function (response) {
                $("#nfac").val(response.id_venta).prop("disabled", true);
                $("#cliente").val(response.nombre_cli).prop("disabled", true).attr("data-id", response.id_cliente);
                $("#ruc_cli").val(response.documento_cli + "-" + response.dv_cli).prop("disabled", true);
            })
            .fail(function (response, jqxhr, error) {
                toastr.error(error);
            });
}

function generarDetNc(id) {
    $.post("ventasControlador",
            {
                id_venta: id,
                accion: "buscarDet"
            })
            .done(function (response) {
                for (var i = 0; i <= response.length; i++) {
                    $("#cuerpo").append(venta_rows);
                }
                $("#cuerpo tr").each(function (e) {
                    var ele = this;
                    if (e < response.length) {
                        $(this).find("td input.cod").val(response[e].codigo_ar).prop("disabled", true);
                        $(this).find("td input.cod").attr("data-id", response[e].id_articulo);
                        $(this).find("td input.desc").val(response[e].descripcion_ar).removeAttr("data-row").prop("disabled", true);
                        $(this).find("td input.pv").val(response[e].precio_ven).prop("disabled", true);
                        $(this).find("td select.iva").val(response[e].iva);
                        $(this).find("td input.tv").val(response[e].subtotal_ven);
                        calcularTotalVenta();
                        calcularTotal();
                    }
                    $.post("tallesControlador", {accion: "select"}, function (data) {
                        for (var x = 0; x < data.length; x++) {
                            var option = document.createElement("option");
                            option.setAttribute("value", data[x].id_talle);
                            option.innerHTML = data[x].nombre_t;
                            $(ele).find("select.select-talle").append(option);
                            if (e < response.length) {
                                if (response[e].id_talle == data[x].id_talle) {
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

