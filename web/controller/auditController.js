function listarAuditoria() {
    $("#inicio").load("views/auditoria.jsp", {}, function () {
        $("#example").DataTable({
            destroy: true,
            "ajax": "auditoriaControlador?accion=listar",
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
            "columns": [
                {"data": "id_auditoria"},
                {"data": "fecha"},
                {"data": "descripcion"},
                {"data": "usuario"},
                {"data": "nombre_emp"}
            ]
        });
    });
}


