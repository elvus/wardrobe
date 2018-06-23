<table class="table table-condensed">
    <thead>
        <tr>
            <th>Codigo</th>      
            <th>Descripcion</th>
            <th>Cantidad</th>
            <th>Precio Unitario</th>
            <th>Precio Total</th>
        </tr>
    </thead>
    <tbody id="tabla">
        <tr>
            <td><input type="text" data-row="u" class="con clear form-control" onclick="addRow(this)" onblur="addRow(this)"></td>
            <td><select class="form-control select2" id="0" data-width="100%"><option></option></select></td>
            <td><input type="text" data-row="u" id="0" class="con clear form-control"></td>
            <td><input type="text" data-row="u" id="0" class="imp clear form-control"></td>
            <td><input type="text" data-row="u" id="0" class="con clear form-control"></td>
            <td><button class="eliminar btn btn-danger hide"><i class="fa fa-trash"></i></button></td>
        </tr>
    </tbody>
</table>