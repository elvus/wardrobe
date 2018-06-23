function ordencompra() {
    $("#inicio").load("views/ordendecompra.jsp", {}, function () {
        ListarOrdenes();
        AprobarOrden();
        RecibidoOrd();
        editarOrden();
        $("#load").on('click', function () {
            nuevaOrden();
        });
    });
}

function nuevaOrden() {
    $("#inicio").load("views/insertOrden.jsp", {}, function () {
        limpiar();
        autocompleteSuppliers();
        agregarOrdendeCompra(0, 0);
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

function ListarOrdenes() {
    $("#table-ord").DataTable({
        destroy: true,
        ajax: "ordencompraControlador?accion=listar",
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
            {"data": "id_orden_compra"},
            {"data": "nombre_pro"},
            {"data": "fecha_ord"},
            {"data": "aprobado_ord",
                render: function (data, type, full, meta) {
                    if (data == 0) {
                        return '<span class="label label-warning cam">NO</span>';
                    } else if (data == 1) {
                        return '<span class="label label-warning">SI</span>';
                    }
                }
            },
            {"data": "recibido_ord",
                render: function (data, type, full, meta) {
                    if (data == 0) {
                        return '<span class="label label-danger">Pendiente</span>';
                    } else {
                        return '<span class="label label-primary">Recibido</span>';
                    }
                }
            },
            {"data": "total_ord"},
            {"data": null,
                render: function (data, type, full, meta) {
                    if (data.aprobado_ord == 0) {
                        return '<div class="btn-group cmb-btn"><a class="btn btn-primary btn-xs edit" data-id="' + data.id_orden_compra + '">Editar</a><a class="btn btn-info btn-xs apb" data-id="' + data.id_orden_compra + '">Aprobar</a><a class="btn btn-primary btn-xs drop anl" data-id="' + data.id_orden_compra + '">Anular</a></div>';
                    } else if (data.aprobado_ord == 1) {
                        return '<div class="btn-group cmb-btn"><a class="btn btn-primary btn-xs res" data-id="' + data.id_orden_compra + '">Restablecer</a><a class="btn btn-info btn-xs rec" data-id="' + data.id_orden_compra + '">Recibido</a><a class="btn btn-primary btn-xs drop anl" data-id="' + data.id_orden_compra + '">Anular</a></div>';
                    }
                }
            }
        ]
    });
}

function addRow() {
    $(".add").on('change', function () {
        if ($(this).val() != "") {
            var x = $("#cuerpo tr").length;
            var u = this.getAttribute("data-row");

            this.removeAttribute("data-row");
            if (u == "u") {
                if (x > 0) {
                    $(".eliminar").removeClass('hide');
                }
                $("#cuerpo").append(compra_rows);
                autocompletarArticulos();
                listarTalles("select");
                $(this).parent().parent().find("td input.desc").removeAttr("data-row");
                $(this).parent().parent().find("td input.add").removeClass("add");
                $(this).parent().parent().find("td select.select-talle").removeClass("select-talle");
                calcularTotales();
                calcularTotal();
                addRow();
                $(".cod").change(function () {
                    calcularTotales();
                });
                $(".desc").change(function () {
                    calcularTotales();
                });
            }
        } else {
            toastr.error("Primero debe ingresar el Proveedor");
        }
        $(document).on('click', ".eliminar", function (e) {
            e.preventDefault();
            $(this).closest("tr").remove();
            calcularTotales();
            return false;
        });
    });
}

function completarProveedores(c) {
    var np = [];
    var rp = [];
    $.ajax({
        url: "proveedoresControlador",
        type: "get",
        data: {"accion": "completar"},
        success: function (response) {
            for (var i = 0; i < response.length; i++) {
                np[i] = {
                    "id": response[i].ruc_pro,
                    "name": response[i].nombre_pro,
                    "data": response[i].id_proveedor
                };
                rp[i] = {
                    "id": response[i].nombre_pro,
                    "name": response[i].ruc_pro,
                    "data": response[i].id_proveedor
                };
            }
            c(np, rp);
        }
    });
}

function autocompleteSuppliers() {
    var id_pro = 0;
    $("#proveedor").typeahead("destroy");
    completarProveedores(function (np, rp) {
        $("#proveedor").typeahead("destroy");
        $("#ruc_pro").typeahead("destroy");
        $("#proveedor").typeahead({
            source: np,
            autoSelect: true
        });
        $("#ruc_pro").typeahead({
            source: rp,
            autoSelect: true
        });

        $("#ruc_pro").change(function () {
            var current = $("#ruc_pro").typeahead("getActive");
            if (current != null) {
                $("#ruc_pro").attr("data-id", current.data);
                $("#proveedor").val(current.id);
                document.getElementById("proveedor").readOnly = true;

                if (id_pro != document.getElementById("ruc_pro").getAttribute("data-id")) {
                    if ($("#cuerpo tr").length <= 0) {
                        $("#cuerpo").html(compra_rows);
                    }
                    autocompletarArticulos();
                    listarTalles("select");
                    $(".la").html("Total:");
                    $(".in").html('<input type="text" id="total" class="form-control" value="0" readonly>');
                    calcularTotal();
                    calcularTotales();
                    addRow();
                }
            } else {
                $("#ruc_pro").val("");
            }
        });

        $("#proveedor").change(function () {
            var current = $("#proveedor").typeahead("getActive");
            if (current != null) {
                $("#ruc_pro").attr("data-id", current.data);
                $("#ruc_pro").val(current.id);
                document.getElementById("ruc_pro").readOnly = true;
                if (id_pro != document.getElementById("ruc_pro").getAttribute("data-id")) {
                    if ($("#cuerpo tr").length <= 0) {
                        $("#cuerpo").html(compra_rows);
                    }
                    autocompletarArticulos();
                    listarTalles("select");
                    $(".la").html("Total:");
                    $(".in").html('<input type="text" id="total" class="form-control" value="0" readonly>');
                    calcularTotal();
                    calcularTotales();
                    addRow();
                }
            } else {
                $("#proveedor").val("");
            }
        });

        $("#ruc_pro").on('blur', function () {
            if ($("#ruc_pro").val() == "" || $("#ruc_pro").val() == null) {
                $("#proveedor").val("");
                document.getElementById("ruc_pro").readOnly = false;
            }
        });

        $("#proveedor").on('blur', function () {
            if ($("#proveedor").val() == "" || $("#proveedor").val() == null) {
                $("#ruc_pro").val("");
                document.getElementById("ruc_pro").readOnly = false;
            }
        });
    });
}

function limpiar() {
    $(".clr").on('click', function (e) {
        e.preventDefault();
        var x = $("tbody tr").length - 1;
        $("tbody tr").each(function (i) {
            if (i < x) {
                $(this).remove();
            }
        });
    });
}

function autocompletarArticulos() {
    completarArticulos(function (na, ca) {
        $(".cod").typeahead("destroy");
        $(".desc").typeahead("destroy");
        $(".cod").typeahead({
            source: ca,
            autoSelect: true
        });
        $(".desc").typeahead({
            source: na,
            autoSelect: true
        });

        $(".cod").change(function () {
            verificarCodigo_ar(this.value, $(this).parent().parent());
        });

        $(".desc").change(function () {
            var response = $(this).typeahead("getActive");
            var tr = $(this).parent().parent();
            $(tr).find("td input.cod").val(response.codigo_ar);
            $(tr).find("td input.cod").attr("data-id", response.id_articulo);
            $(tr).find("td input.precio").val(response.precio_compra_ar);
            $(tr).find("td input.pv").val(response.precio_ar);
            $(tr).find("td select.talle").val(response.id_talle);
            $(tr).find("td input.iva").val(response.iva_ar);
            $(tr).find("td input.total").val(response.precio_compra_ar * $(tr).find("td input.cant").val());
            $(tr).find("td input.tv").val(response.precio_ar);
        });
    });
}

function verificarCodigo_ar(cod, tr) {
    $.get("articuloControlador", {accion: "verificar", cod: cod}, function (response) {
        $(tr).find("td input.cod").attr("data-id", response.id_articulo);
        $(tr).find("td input.desc").val(response.descripcion_ar);
        $(tr).find("td input.precio").val(response.precio_compra_ar);
        $(tr).find("td input.pv").val(response.precio_ar);
        $(tr).find("td select.talle").val(response.id_talle);
        $(tr).find("td input.iva").val(response.iva_ar);
        $(tr).find("td input.total").val(response.precio_compra_ar * $(tr).find("td input.cant").val());
        $(tr).find("td input.tv").val(response.precio_ar);
    });
}

function calcularTotal() {
    $(".cant").on('change', function () {
        var price = 0;
        var cant = this.value;
        if ($(this).parent().parent().find("td input.precio").val()) {
            price = $(this).parent().parent().find("td input.precio").val();
        } else if ($(this).parent().parent().find("td input.pv").val()) {
            price = $(this).parent().parent().find("td input.pv").val();
        }
        if ($(this).parent().parent().find("td input.total").val()) {
            $(this).parent().parent().find("td input.total").val(price * cant);
            calcularTotales();
        } else if ($(this).parent().parent().find("td input.tv").val()) {
            $(this).parent().parent().find("td input.tv").val(price * cant);
            calcularTotalVenta();
        }
    });

    $(".precio").on('change', function () {
        var cant = $(this).parent().parent().find("td input.cant").val();
        var price = this.value;
        $(this).parent().parent().find("td input.total").val(price * cant);
        calcularTotales();
    });
}

function calcularTotales() {
    var t10 = 0, t5 = 0, tE = 0, total = 0, ti;
    $("#cuerpo tr").each(function (i) {
        if ($(this).find("select.iva").val() == "5") {
            t5 += parseInt($(this).find("input.total").val()) / 21;
        } else if ($(this).find("select.iva").val() == "10") {
            t10 += parseInt($(this).find("input.total").val()) / 11;
        } else if ($(this).find("select.iva").val() == "E") {
            tE += parseInt($(this).find("input.total").val());
        }
        if ($(this).find("td input.total").val() != 0) {
            total += parseInt($(this).find("td input.total").val());
        }
    });
    ti = t10 + t5;
    $("#i10").val(Math.round(t10));
    $("#i5").val(Math.round(t5));
    $("#exenta").val(tE);
    $("#ti").val(Math.round(ti));
    $("#total").val(total);
}

function agregarOrdendeCompra(accion, id) {
    $("#form-orden").submit(function (e) {
        e.preventDefault();
        var detalle = [];
        $("#cuerpo tr").each(function (i) {
            if ($(this).find("td input.cod").attr("data-id")) {
                detalle[i] = {
                    id_articulo: $(this).find("td input.cod").attr("data-id"),
                    precio_ord: $(this).find("td input.precio").val(),
                    cantidad_ord: $(this).find("td input.cant").val(),
                    iva_ord: $(this).find("td select.iva").val(),
                    codigo_ar_ord: $(this).find("td input.cod").val()
                };
            }
        });
        $.post("ordencompraControlador",
                {
                    accion: accion,
                    id_orden: id,
                    comentario_ord: $("#comentario").val(),
                    fecha_ord: $("#fecha").val(),
                    id_proveedor: $("#ruc_pro").attr("data-id"),
                    total_ord: $("#total").val(),
                    detalle_ord: JSON.stringify(detalle)
                },
                function (response) {
                    if (response == 1) {
                        toastr.success("Guardado!");
                        nuevaOrden();
                    } else {
                        toastr.error("Error Inesperado");
                    }
                });
    });
}

function AprobarOrden() {
    $("#table-ord tbody").on('click', '.apb', function () {
        var id = $(this).attr("data-id");
        var ele = this;
        $.get("ordencompraControlador",
                {
                    accion: "aprobar",
                    id_orden: id
                })
                .done(function (response) {
                    if (response == 1) {
                        toastr.success("Aprobado!");
                        console.log($(ele).parent());
                        $(ele).parent().html('<a class="btn btn-primary btn-xs res" data-id="' + id + '">Restablecer</a><a class="btn btn-info btn-xs rec" data-id="' + id + '">Recibido</a><a class="btn btn-primary btn-xs drop anl" data-id="' + id + '">Anular</a>');
                    } else if (response == 0) {
                        toastr.error("Error inesperado");
                    }
                })
                .fail(function (response, jqrxhm, error) {
                    toastr.error(error);
                });
    });
}

function RecibidoOrd() {
    $("#table-ord tbody").on('click', '.rec', function () {
        var id = $(this).attr("data-id");
        $("#inicio").load("views/insertMercaderiaRec.jsp", {}, function () {
            validarNfac();
            generarCabMR(id);
            generarDetMR(id);
            agregarMR(id, 0);
        });
    });
}

function generarCabMR(id) {
    $.get("ordencompraControlador",
            {
                accion: "buscarCab",
                id_orden: id
            })
            .done(function (response) {
                $("#proveedor").val(response.nombre_pro).prop("readOnly", true);
                $("#ruc_pro").val(response.ruc_pro + "-" + response.dv_pro).attr("data-id", response.id_proveedor).prop("readOnly", true);
                $("#fecha").val(response.fecha_ord).prop("readOnly", true);
            })
            .fail(function (response, ajaxOptions, error) {
                toastr.error(error);
            });
}

function generarDetMR(id) {
    $.get("ordencompraControlador",
            {
                accion: "buscarDet",
                id_orden: id
            })
            .done(function (response) {
                for (var i = 0; i <= response.length; i++) {
                    $("#cuerpo").append(compra_rows);
                    $("#cuerpo tr").each(function (e) {
                        autocompletarArticulos();
                        addRow();
                        var ele = this;
                        if (e < response.length) {
                            $(this).find("input.cod").val(response[e].codigo_ar_ord).attr("data-id", response[e].id_articulo).prop("readOnly", true).removeAttr("data-row");
                            $(this).find("input.desc").val(response[e].descripcion_ar).prop("readOnly", true).removeAttr("data-row");
                            $(this).find("input.cant").val(response[e].cantidad_ord);
                            $(this).find("input.precio").val(response[e].precio_ord);
                            $(this).find("input.total").val(response[e].precio_ord * response[e].cantidad_ord);
                            calcularTotal();
                            calcularTotales();
                        }
                        $.post("tallesControlador", {accion: "select"}, function (data) {
                            for (var i = 0; i < data.length; i++) {
                                var option = document.createElement("option");
                                option.setAttribute("value", data[i].id_talle);
                                option.innerHTML = data[i].nombre_t;
                                $(ele).find("select.select-talle").append(option);
                                if (e < response.length) {
                                    if (response[e].id_talle == data[i].id_talle) {
                                        option.setAttribute("selected", "selected");
                                    }
                                }
                            }
                            $(ele).find("select.select-talle").removeClass("select-talle");
                        });
                    });
                }
            })
            .fail(function (response, ajaxOptions, error) {
                toastr.error(error);
            });
}

function generarCabOrd(id) {
    $.get("ordencompraControlador",
            {
                accion: "buscarCab",
                id_orden: id
            })
            .done(function (response) {
                $("#proveedor").val(response.nombre_pro);
                $("#ruc_pro").val(response.ruc_pro + "-" + response.dv_pro).attr("data-id", response.id_proveedor).prop("readOnly", true);
                $("#fecha").val(response.fecha_ord);
            })
            .fail(function (response, ajaxOptions, error) {
                toastr.error(error);
            });
}

function generarDetOrd(id) {
    $.get("ordencompraControlador",
            {
                accion: "buscarDet",
                id_orden: id
            })
            .done(function (response) {
                for (var i = 0; i <= response.length; i++) {
                    $("#cuerpo").append(compra_rows);
                    $("#cuerpo tr").each(function (e) {
                        autocompletarArticulos();
                        addRow();
                        var ele = this;
                        if (e < response.length) {
                            $(this).find("input.cod").val(response[e].codigo_ar_ord).attr("data-id", response[e].id_articulo).removeAttr("data-row");
                            $(this).find("input.desc").val(response[e].descripcion_ar).removeAttr("data-row");
                            $(this).find("input.cant").val(response[e].cantidad_ord);
                            $(this).find("input.precio").val(response[e].precio_ord);
                            $(this).find("input.total").val(response[e].precio_ord * response[e].cantidad_ord);
                            calcularTotal();
                            calcularTotales();
                        }
                        $.post("tallesControlador", {accion: "select"}, function (data) {
                            for (var i = 0; i < data.length; i++) {
                                var option = document.createElement("option");
                                option.setAttribute("value", data[i].id_talle);
                                option.innerHTML = data[i].nombre_t;
                                $(ele).find("select.select-talle").append(option);
                                if (e < response.length) {
                                    if (response[e].id_talle == data[i].id_talle) {
                                        option.setAttribute("selected", "selected");
                                    }
                                }
                            }
                            $(ele).find("select.select-talle").removeClass("select-talle");
                        });
                    });
                }
            })
            .fail(function (response, ajaxOptions, error) {
                toastr.error(error);
            });
}

function RecibirOrden(id) {
    $.get("ordencompraControlador",
            {
                accion: "recibir",
                id_orden: id
            })
            .done(function (response) {
                if (response == 1) {
                    toastr.success("Listo!");
                } else {
                    toastr.error("Error Inesperado");
                }
            })
            .fail(function (response, jqrxhm, error) {
                toastr.error(error);
            });
}

function editarOrden() {
    $("#table-ord tbody").on('click', '.edit', function () {
        var id = $(this).attr("data-id");
        $("#inicio").load("views/insertOrden.jsp", {}, function () {
            limpiar();
            autocompleteSuppliers();
            $("#fecha").datepicker({
                format: "dd/mm/yyyy",
                autoclose: true
            });
            $(".la").html("Total:");
            $(".in").html('<input type="text" id="total" class="form-control" value="0" readonly>');
            generarCabOrd(id);
            generarDetOrd(id);
            agregarOrdendeCompra(1, id);
        });
    });
}