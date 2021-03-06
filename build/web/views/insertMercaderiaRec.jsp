<section class="content-header">
    <h1>
        Mercaderias Recibidas
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Inicio</a></li>
        <li><a href="#"><i class="fa fa-shopping-cart"></i> Mercaderia Recibida</a></li>
        <li class="active">Agregar</li>
    </ol>
</section>
<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Nueva Mercaderia Recibida</h3>
                    <div class="btn-group pull-right">
                        <button type="submit" class="save btn btn-primary btn-xs">Guardar</button>
                        <button class="clr btn btn-success btn-xs">Limpiar</button>
                        <button class="btn btn-default btn-xs">Cancelar</button>
                    </div>                    
                </div>
                <div class="box-body">
                    <form id="form-orden" class="form-horizontal">
                        <div class="col-lg-4">
                            <label for="nfac">Factura N�</label>
                            <input type="text" id="nfac" class="form-control"  autocomplete="off" required>
                        </div>
                        <div class="col-lg-4">
                            <label for="proveedor">Proveedor</label>
                            <div class="input-group">
                                <input type="text" id="proveedor" class="form-control" autocomplete="off">
                                <div class="input-group-btn">
                                    <button type="button" class="mas btn btn-info"><i class="fa fa-plus"></i></button>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <label for="fecha">R.U.C.</label>
                            <input type="text" id="ruc_pro" class="form-control" autocomplete="off">
                        </div>
                        <div class="col-lg-4">
                            <label for="fecha">Fecha</label>
                            <input type="text" id="fecha" class="form-control"  autocomplete="off">
                        </div>
                        <div class="col-lg-4">
                            <label for="comentario">Comentario</label>
                            <textarea class="form-control" id=comentario"></textarea>
                        </div>
                        <div class="clearfix"></div>
                        <table class="table table-condensed">
                            <thead>
                                <tr>
                                    <th>Codigo</th>      
                                    <th>Descripcion</th>
                                    <th>Tama�o</th>
                                    <th>Cantidad</th>
                                    <th>Precio Unitario</th>
                                    <th>IVA</th>
                                    <th>Total</th>
                                </tr>
                            </thead>
                            <tbody id="cuerpo">
                            </tbody>
                            <tfoot>
                                <tr>
                                    <th></th>      
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                    <th class="la"></th>
                                    <th class="in"></th>
                                </tr>
                            </tfoot>
                        </table>
                        <div class="col-lg-offset-9">
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
                        <br>
                        <div class="pull-right">
                            <button type="submit" class="save btn btn-primary">Guardar</button>
                            <button type="button" class="clr btn btn-success">Limpiar</button>
                            <button class="btn btn-default">Cancelar</button>
                        </div>                            
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<div id="modal"></div>