/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function ruc() {
    $(".dv").prop("disabled", true);
    $(".ci").on('blur', function () {
        var ci = $(".ci").val();
        var n = 0;
        var s = 0;
        var k = 2;
        var res = 0;
        var dv = 0;
        for (var i = ci.length - 1; i >= 0; i--) {
            n = parseInt(ci.charAt(i));
            s = s + (n * k);
            k = k + 1;
        }
        res = s % 11;
        if (res > 1) {
            dv = 11 - res;
        } else {
            dv = 0;
        }
        $(".dv").val(dv);
    });
}


