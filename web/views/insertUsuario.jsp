<section class="content-header">
    <h1>
        Usuarios
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Inicio</a></li>
        <li><a href="#"><i class="fa fa-key"></i> Usuarios</a></li>
        <li class="active">Agregar</li>
    </ol>
</section>

<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Lista de Usuarios</h3>
                    <div class="btn btn-group pull-right">
                        <button class="save btn btn-primary btn-xs">Guardar</button>
                        <button class="btn-default btn-xs">Cancelar</button>
                    </div>                    
                </div>
                <div class="box-body">
                    <form class="form-horizontal">
                        <div class="form-group has-feedback">
                            <label class="control-label col-sm-3" for="nombre">Usuario:</label>
                            <div class="nombre col-sm-8">
                                <input type="text" class="form-control" id="nombre" placeholder="Usuario" name="usuario">
                            </div>
                            <div class="name col-sm-8 col-lg-offset-3 hide">
                                <label class="error text text-danger"></label>
                            </div>
                        </div>
                        <div class="form-group has-feedback">
                            <label class="control-label col-sm-3" for="pwd">Contraseña:</label>
                            <div class="pwd col-sm-8">          
                                <input type="password" class="form-control" id="pwd" placeholder="Contraseña" name="pass">
                            </div>
                            <div class="length col-sm-8 col-lg-offset-3 hide">
                                <label class="lengtherror text text-danger">Debe contener por lo menos 4 caracteres</label>
                            </div>
                        </div>
                        <div class="form-group has-feedback">
                            <label class="control-label col-sm-3" for="confirmar">Confirmar Contraseña:</label>
                            <div class="confirm col-sm-8">          
                                <input type="password" class="form-control" id="confirmar" placeholder="Confirmar Contraseña" name="pass">
                            </div>
                            <div class="pass col-sm-8 col-lg-offset-3 hide">
                                <label class="passerror text text-danger">Los campos no coinciden</label>
                            </div>
                        </div>
                        <div class="form-group has-feedback">
                            <label class="control-label col-sm-3" for="funcionario">Funcionario</label>
                            <div class="col-sm-8">          
                                <select type="text" class="form-control" id="funcionario" data-width="100%" name="funcionario">
                                    <option></option>
                                </select>
                            </div>
                            <div class="emp col-sm-8 col-lg-offset-3 hide">
                                <label class="emperror text text-danger">Debe seleccionar un empleado <i class="glyphicon glyphicon-exclamation-sign"></i></label>
                            </div>
                        </div>                    
                    </form>
                </div>
            </div>
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Permisos</h3>
                </div>
                <div class="box-body">
                    <table class="table table-bordered" id="table-permisos">
                        <tbody>
                            <tr>
                                <th>Modulo</th>
                                <th>Ver</th>
                                <th>Agregar</th>
                                <th>Editar</th>
                            </tr>
                            <tr>
                                <th>Compras</th>
                                <td><input type="checkbox" class="ver"></td>
                                <td><input type="checkbox" class="agregar"></td>
                                <td><input type="checkbox" class="editar"></td>
                            </tr>
                            <tr>
                                <th>Ventas</th>
                                <td><input type="checkbox" class="ver"></td>
                                <td><input type="checkbox" class="agregar"></td>
                                <td><input type="checkbox" class="editar"></td>
                            </tr>
                            <tr>
                                <th>Stock</th>
                                <td><input type="checkbox" class="ver"></td>
                                <td><input type="checkbox" class="agregar"></td>
                                <td><input type="checkbox" class="editar"></td>
                            </tr>
                            <tr>
                                <th>Clientes</th>
                                <td><input type="checkbox" class="ver"></td>
                                <td><input type="checkbox" class="agregar"></td>
                                <td><input type="checkbox" class="editar"></td>
                            </tr>
                            <tr>
                                <th>Proveedores</th>
                                <td><input type="checkbox" class="ver"></td>
                                <td><input type="checkbox" class="agregar"></td>
                                <td><input type="checkbox" class="editar"></td>
                            </tr>
                            <tr>
                                <th>Funcionarios</th>
                                <td><input type="checkbox" class="ver"></td>
                                <td><input type="checkbox" class="agregar"></td>
                                <td><input type="checkbox" class="editar"></td>
                            </tr>
                            <tr>
                                <th>Fondos</th>
                                <td><input type="checkbox" class="ver"></td>
                                <td><input type="checkbox" class="agregar"></td>
                                <td><input type="checkbox" class="editar"></td>
                            </tr>
                            <tr>
                                <th>Mercaderias</th>
                                <td><input type="checkbox" class="ver"></td>
                                <td><input type="checkbox" class="agregar"></td>
                                <td><input type="checkbox" class="editar"></td>
                            </tr>
                            <tr>
                                <th>Ubicacion</th>
                                <td><input type="checkbox" class="ver"></td>
                                <td><input type="checkbox" class="agregar"></td>
                                <td><input type="checkbox" class="editar"></td>
                            </tr>
                            <tr>
                                <th>Sistema</th>
                                <td><input type="checkbox" class="ver"></td>
                                <td><input type="checkbox" class="agregar"></td>
                                <td><input type="checkbox" class="editar"></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>