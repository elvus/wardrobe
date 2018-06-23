<!-- Modal -->
<div class="modal fade" id="MyModal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title"><strong>Agregar Talle</strong></h2>
            </div>
            <form class="form-horizontal" id="form">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="control-label col-sm-3" for="talle">Talle: </label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="talle" placeholder="Talle" name="talle" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" for="nombre">Descripcion: </label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="descripcion" placeholder="Descripcion" name="descripcion">
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
