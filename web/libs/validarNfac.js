function validarNfac() {
    $("#nfac").on("keypress", function (e) {
        tecla = (document.all) ? e.keyCode : e.which;
        if (tecla == 8)
            return true; // 3
        patron = /[0-9]/;
        if ($(this).val().length == 3 || $(this).val().length == 7) {
            var n = $(this).val();
            $(this).val(n + "-");
        }
        te = String.fromCharCode(tecla);
        if ($(this).val().length == 15) {
            return false;
        } else {
            return patron.test(te);
        }
    });
}

