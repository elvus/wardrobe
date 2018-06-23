<section class="content-header">
    <h1>
        Nueva Venta
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Inicio</a></li>
        <li><a href="#"><i class="fa fa-shopping-cart"></i> Ventas</a></li>
        <li class="active">Agregar</li>
    </ol>
</section>
<!-- Main content -->
<section class="content">
    <form id="form-venta">   
        <div class="row">
            <div class="col-xs-4">
                <div class="box box-warning">
                    <div class="box-body">
                        <h4>Usuario: </h4>
                        <p  class="user">Elvis Segovia</p>
                        <h4>Fecha: </h4>
                        <p class="date"></p>
                        <h4 >Hora: </h4>
                        <p class="hour"></p>
                    </div>
                </div>
            </div>
            <div class="col-xs-4">
                <div class="box box-warning">
                    <div class="box-body">
                        <div class="form-group">
                            <label for="Cliente">Cliente</label>
                            <input type="text" class="form-control nom" id="cliente" required>
                        </div>
                        <div class="form-group">
                            <label for="cliente">Ruc</label>
                            <input type="text" class="form-control ruc" id="ruc">
                        </div>
                        <div class="form-group">
                            <div class="radio">
                                <label>
                                    <input type="radio" name="radio"  value="0" checked="">
                                    Contado
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="radio" value="1">
                                    Crédito
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-4">
                <div class="box box-warning">
                    <div class="box-body">
                        <table>
                            <tr>
                                <td><b>IVA 10: </b></td>
                                <td><input id="i10" type="text" class="form-control" value="0" readonly></td>
                            </tr>
                            <tr>
                                <td><b>IVA 5: </b></td>
                                <td><input id="i5" type="text" class="form-control" value="0" readonly></td>
                            </tr>
                            <tr>
                                <td><b>EXENTA: </b></td>
                                <td><input id="exenta" type="text" class="form-control" value="0" readonly></td>
                            </tr>
                            <tr>
                                <td><b>TOTAL IVA: </b></td>
                                <td><input id="ti" type="text" class="form-control" value="0" readonly></td>
                            </tr>
                            <tr>
                                <td><b>TOTAL: </b></td>
                                <td><input id="total" type="text" class="form-control" value="0" readonly></td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <div class="box box-primary">
                    <div class="box-body">
                        <table class="table table-condensed"> 
                            <thead>
                                <tr>
                                    <th>Codigo</th>      
                                    <th>Descripcion</th>
                                    <th>Tamaño</th>
                                    <th>Cantidad</th>
                                    <th>Precio Unitario</th>
                                    <th>IVA</th>
                                    <th>Total</th>
                                </tr>
                            </thead>
                            <tbody id="cuerpo">
                            </tbody>
                        </table>               
                        <div class="pull-right">
                            <button type="submit" class="save btn btn-primary">Guardar</button>
                            <button type="button" class="clr btn btn-success">Limpiar</button>
                            <button class="btn btn-default">Cancelar</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</section>
<div id="modal"></div>