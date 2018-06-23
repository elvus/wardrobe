function articulos() {
    $("#inicio").load("views/articulos.jsp", {}, function () {
        $(".active").removeClass("active");
        $("#tree5").addClass("active");
        ListarArticulos("listar");
        editarArticulo();
        $("#load").on('click', function () {
            $("#modal").load("views/modalart.jsp", {}, function () {
                $("#MyModal").modal();
                generarCodigo();
                agregarArticulos(0, 0);
                ListarMarcas("select");
                listarTalles("select");
                listarCategorias("select");

                $("#select-categoria").select2({
                    dropdownParent: $("#cat"),
                    placeholder: "Seleccione una categoria",
                    allowClear: true
                });

                $("#select-marca").select2({
                    dropdownParent: $("#parent"),
                    placeholder: "Seleccione la marca",
                    allowCrear: true
                });

                $("#select-talle").select2({
                    placeholder: "Seleccione el talle",
                    dropdownParent: $("#ta"),
                    allowClear: true
                });

                $("#temporada").select2({
                    dropdownParent: $("#temp"),
                    placeholder: "Seleccione la temporada",
                    allowCrear: true
                });
            });
        });
    });
}

function ListarArticulos(accion) {
    if (accion == "listar") {
        $("#table-art").DataTable({
            destroy: true,
            ajax: "articuloControlador?accion=listar",
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
                {"data": "codigo_ar"},
                {"data": "descripcion_ar"},
                {"data": "nombre_marca"},
                {"data": "precio_ar"},
                {"data": null,
                    render: function (data, type, full, meta) {
                        return '<div class="btn-group"><a class="btn btn-info btn-xs edit" data-id="' + data.id_articulo + '">Editar</a><a class="btn btn-primary btn-xs cerrar" data-id="' + data.id_articulo + '">Inhabilitar</a><a></a></div>';
                    }
                }
            ]
        });
    }
}

function agregarArticulos(accion, id) {
    $("#form").submit(function (e) {
        e.preventDefault();
        $.ajax({
            url: "articuloControlador",
            type: "post",
            data: {
                "accion": accion,
                "id_articulo": id,
                "codigo_ar": $("#cod").val(),
                "barcode_ar": $("#cb").val(),
                "descripcion_ar": $("#descripcion").val(),
                "id_marca": $("#select-marca").val(),
                "precio_ar": $("#precio").val(),
                "precio_compra_ar": $("#precioc").val(),
                "iva_ar": $("#iva").val(),
                "talle_ar": $("#select-talle").val(),
                "id_categoria": $("#select-categoria").val(),
                "temporada_ar": $("#temporada").val()
            },
            success: function (response) {
                if (response == 1) {
                    toastr.success("Guardado!");
                    ListarArticulos("listar");
                    $("#MyModal").modal('hide');
                } else {
                    toastr.error("Ha ocurrido un error");
                }
            }
        });
    });
}

function editarArticulo() {
    $("#table-art tbody").on('click', '.edit', function () {
        var id = $(this).attr("data-id");
        $("#modal").load("views/modalart.jsp", {}, function () {
            $("#MyModal").modal();
            ListarMarcas("select");
            listarTalles("select");
            listarCategorias("select");

            $("#select-categoria").select2({
                dropdownParent: $("#cat"),
                placeholder: "Seleccione una categoria",
                allowClear: true
            });

            $("#select-marca").select2({
                dropdownParent: $("#parent"),
                placeholder: "Seleccione la marca",
                allowCrear: true
            });

            $("#select-talle").select2({
                placeholder: "Seleccione el talle",
                dropdownParent: $("#ta"),
                allowClear: true
            });

            $("#temporada").select2({
                dropdownParent: $("#temp"),
                placeholder: "Seleccione la temporada",
                allowCrear: true
            });
            $.get("articuloControlador", {"accion": "buscar", "id_articulo": id}, function (response) {
                console.log(response);
                $("#cod").val(response.codigo_ar);
                $("#cb").val(response.barcode_ar);
                $("#descripcion").val(response.descripcion_ar);
                $("#select-marca").val(response.id_marca).trigger("change");
                $("#precioc").val(response.precio_compra_ar);
                $("#iva").val(response.iva_ar);
                $("#select-talle").val(response.id_talle).trigger("change");
                $("#select-categoria").val(response.id_categoria).trigger("change");
                $("#precio").val(response.precio_ar);
                $("#temporada").val(response.temporada_ar).trigger("change");
            });
            agregarArticulos(1, id);
        });
    });
}

function eliminarArticulo(id) {
    $.get("articuloControlador", {"accion": "eliminar", "id": id}, function (response) {
        if (response == 1) {
            toastr.success("Eliminado!");
            ListarArticulos("listar");
        } else {
            toastr.error("Ha ocurrido un error");
        }
    });
}

function generarCodigo() {
    $.get("articuloControlador", {accion: "generar"}, function (response) {
        $("#cod").val(response);
    });
}

function completarArticulos(callback) {
    var x = [];
    var y = [];
    $.get("articuloControlador", {accion: "completar"}, function (response) {
        for (var i = 0; i < response.length; i++) {
            x[i] = {
                name: response[i].descripcion_ar,
                id_articulo: response[i].id_articulo,
                codigo_ar: response[i].codigo_ar,
                precio_compra_ar: response[i].precio_compra_ar,
                precio_ar: response[i].precio_ar,
                iva_ar: response[i].iva_ar,
                id_talle: response[i].id_talle
            };
            y[i] = {
                name: response[i].codigo_ar
            };
        }
    });
    callback(x, y);
}