function categorias() {
    $("#inicio").load("views/categoria.jsp", {}, function () {
        listarCategorias();
        $("#load").on("click", function () {
            $("#modal").load("views/modalCategoria.jsp", {}, function () {
                $("#MyModal").modal();
                agregarCategorias(0, 0);
            });
        });
    });
}

function listarCategorias(accion) {
    if (accion == "select") {
        $.get("categoriaControlador", {"accion": accion}, function (response) {
            for (var i = 0; i < response.length; i++) {
                var option = document.createElement("option");
                var categoria = document.getElementById("select-categoria");
                option.setAttribute("value", response[i].id_categoria);
                option.innerHTML = response[i].nombre_cat;
                categoria.appendChild(option);
            }
        });
    } else {
        $("#example").DataTable({
            destroy: true,
            "ajax": "categoriaControlador?accion=listar",
            columnDefs: [
                {
                    targets: [3],
                    data: null,
                    render: function (data, full, type, meta) {
                        var btn = '<div class="btn-group">';
                        btn += '<a class="edit btn btn-info btn-xs" onclick="editarCategoria(this)" data-id=' + data.id_categoria + '>Editar<a>';
                        btn += '<a class="det btn btn-danger btn-xs" onclick="borrarCategoria(this)" data-id=' + data.id_categoria + '>Eliminar</a>';
                        btn += '</div>';
                        return btn;
                    }
                }
            ],
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
                }
            ],
            "columns": [
                {"data": "id_categoria"},
                {"data": "nombre_cat"},
                {"data": "descripcion_cat"}
            ]
        });
    }
}

function agregarCategorias(accion, id) {
    $("#form").submit(function (e) {
        e.preventDefault();
        $.ajax({
            url: "categoriaControlador",
            type: "POST",
            data: {"accion": accion, "id_cat": id, "nombre_cat": $("#nombre_cat").val(), "descripcion_cat": $("#descripcion").val()},
            success: function (response) {
                if (response == 1) {
                    toastr.success("Guardado!");
                    $("#MyModal").modal("hide");
                    listarCategorias();
                } else {
                    toastr.error("Ha ocurrido un error");
                }
            }
        });
    });
}

function editarCategoria(cat) {
    $("#modal").load("views/modalCategoria.jsp", {}, function () {
        $("#MyModal").modal();
        $.get("categoriaControlador", {"accion": "buscar", "id_cat": cat.getAttribute("data-id")}, function (response) {
            $("#nombre_cat").val(response.nombre_cat);
            $("#descripcion").val(response.descripcion_cat);
        });
        agregarCategorias(1, cat.getAttribute("data-id"));
    });
}

function borrarCategoria(cat) {
    $.post("categoriaControlador", {"accion": "2", "id_cat": cat.getAttribute("data-id")}, function (response) {
        if (response) {
            toastr.success("ELIMINADO!");
            listarCategorias();
        } else {
            toastr.error("Ha ocurrido un error");
        }
    });
}

