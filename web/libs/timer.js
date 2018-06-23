function Hora() {
    var f = new Date();
    var h, m, s;
    h = verificarInstancia(f.getHours());
    m = verificarInstancia(f.getMinutes());
    s = verificarInstancia(f.getSeconds());


    var cad = h + ":" + m + ":" + s;
    $(".hour").html(cad);


    setTimeout("Hora()", 1000);
}
function fecha() {
    var f = new Date();
    var d, M, y;
    d = verificarInstancia(f.getDate());
    M = verificarInstancia(f.getMonth() + 1);
    y = f.getFullYear();
    var dat = d + "/" + M + "/" + y;
    $(".date").html(dat);
}

function verificarInstancia(v) {
    if (v < 10) {
        v = "0" + v;
    }
    return v;
}
