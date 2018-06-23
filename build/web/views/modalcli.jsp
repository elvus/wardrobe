<!-- Modal -->
<div class="modal fade" id="modal-cli" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title"><strong>Agregar Cliente</strong></h2>
            </div>
            <form class="form-horizontal" id="form">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="control-label col-sm-3" for="nombre">Nombre:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="nombre" placeholder="Nombre" name="nombre" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" for="ndoc">Nro de Documento</label>
                        <div class="col-sm-6">
                            <input type="text" class="ci form-control" id="ndoc" placeholder="Nro de Documento" name="ndoc">
                        </div>     
                        <div class="col-sm-2">
                            <input type="text" class="dv form-control" id="dv" placeholder="dv" name="dv">
                        </div>
                    </div>                   
                    <div class="form-group">
                        <label class="control-label col-sm-3" for="telefono">Telefono:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="telefono" placeholder="Telefono" name="telefono">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" for="celular">Celular</label>
                        <div class="col-sm-8">
                            <input type="tel" class="form-control" id="celular" placeholder="Celular" name="celular">
                        </div>
                    </div>   
                    <div class="form-group">
                        <label class="control-label col-sm-3" for="correo">Correo</label>
                        <div class="col-sm-8">
                            <input type="email" class="form-control" id="correo" placeholder="Correo" name="correo">
                        </div>
                    </div>  
                    <div class="form-group">
                        <label class="control-label col-sm-3" for="direccion">Direccion</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="direccion" placeholder="Direccion" name="direccion">
                        </div>
                    </div>  
                    <div class="form-group">
                        <label class="control-label col-sm-3" for="city">Ciudad</label>
                        <div class="col-sm-8" id="ciudad">
                            <select class="form-control" id="city" data-width="100%">
                                <option></option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                    <button type="submit" id="save" class="btn btn-primary">Guardar</button>
                </div>
            </form>
        </div>
    </div>
</div>
