<!-- Modal -->
<div class="modal fade" id="Modal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title"><strong>Agregar Usuario</strong></h2>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-3" for="nombre">Usuario:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="nombre" placeholder="Usuario" name="usuario">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" for="pwd">Contraseña:</label>
                        <div class="col-sm-8">          
                            <input type="password" class="form-control" id="pwd" placeholder="Contraseña" name="pass">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" for="confirmar">Confirmar Contraseña:</label>
                        <div class="col-sm-8">          
                            <input type="password" class="form-control" id="confirmar" placeholder="Confirmar Contraseña" name="pass">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" for="funcionario">Funcionario</label>
                        <div class="col-sm-8" id="select">          
                            <select type="text" class="form-control" id="funcionario" data-width="100%" name="funcionario">
                                <option></option>
                            </select>
                        </div>
                    </div>                    
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <button type="button" id="save" data-dismiss="modal" class="btn btn-primary">Guardar</button>
            </div>
        </div>
    </div>
</div>
