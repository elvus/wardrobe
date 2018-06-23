$(".inventario").on('click', function () {
    $("#inicio").load("views/inventario.jsp", function () {
        listarInventario();
    });
});

function listarInventario() {
    $("#table-stock").DataTable({
        destroy: true,
        ajax: {
            type: "post",
            url: "stockControlador",
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
            {data: "id_stock"},
            {data: "descripcion_ar"},
            {data: "cantidad"}
        ]
    });
}