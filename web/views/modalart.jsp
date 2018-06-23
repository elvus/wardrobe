<!-- Modal -->
<div class="modal fade" id="MyModal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title"><strong>Agregar Articulos</strong></h2>
            </div>
            <form class="form-horizontal" id="form">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="control-label col-sm-3" for="cod">Codigo:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="cod" placeholder="Codigo" name="codigo" required="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" for="grupo">Codigo de Barras:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="cb" placeholder="Codigo de Barras" name="cb">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" for="descripcion">Descripcion:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="descripcion" placeholder="Descripcion" name="descripcion" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" for="marca">Marca:</label>
                        <div class="col-sm-8" id="parent">
                            <select class="form-control" id="select-marca" data-width="100%">
                                <option></option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" for="precio">Precio:</label>
                        <div class="col-sm-8">
                            <input type="text" class="ci form-control" id="precio" placeholder="Precio" name="precio">
                        </div>                
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" for="precio">Precio Compra:</label>
                        <div class="col-sm-8">
                            <input type="text" class="ci form-control" id="precioc" placeholder="Precio Compra" name="precioc">
                        </div>                
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" for="iva">I.V.A.:</label>
                        <div class="col-sm-8">
                            <select class="form-control" id="iva" data-width="100%" required>
                                <option value="10">10%</option>
                                <option value="5">5%</option>
                                <option value="E">EXENTO</option>
                            </select>
                        </div>
                    </div>                      
                    <div class="form-group">
                        <label class="control-label col-sm-3" for="talle">Talle</label>
                        <div class="col-sm-8" id="ta">
                            <select class="form-control select-talle" id="select-talle" data-width="100%">
                                <option></option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" for="categoria">Categoria: </label>
                        <div class="col-sm-8" id="cat">
                            <select class="form-control" id="select-categoria" data-width="100%">
                                <option></option>
                            </select>
                        </div>
                    </div>  
                    <div class="form-group">
                        <label class="control-label col-sm-3" for="temporada">Temporada:</label>
                        <div class="col-sm-8" id="temp">
                            <select class="form-control" id="temporada" data-width="100%">
                                <option></option>
                                <option value="1">Otoño/Invierno</option>
                                <option value="2">Primavera/Verano</option>
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
