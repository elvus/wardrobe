<!-- Modal -->
<div class="modal fade" id="MyModal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title"><strong>Agregar Ciudad</strong></h2>
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
                        <label class="control-label col-sm-3" for="departamento">Departamento</label>
                        <div class="col-sm-8" id="parent">
                            <select id="departamento" class="form-control" data-width="100%">
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
