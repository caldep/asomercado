//--------------------------------------------------------------------------------
function getId(strId,tipo)
{
    var idItem = strId.id.split('-');
    return idItem[tipo];
}
//--------------------------------------------------------------------------------
function getIdArticulo(strId)
{
    return getId(strId,1);
}
//--------------------------------------------------------------------------------
function getIdLista(strId)
{
    return getId(strId,0);
}
//--------------------------------------------------------------------------------

function preTecla(tecla,itemLista,tipo)
{
    var idItem = getIdArticulo(itemLista);
    var idLista = getIdLista(itemLista);
    switch(tecla.keyCode){
                case 27:{ //Escape
                    document.execCommand('undo');
                    $("#title").html("Escape");
                    break;
                }

                case 46:{ //Suprimir
                    tecla.preventDefault();
                    var mensaje;
                    mensaje = (tipo == 'articulo')?'el artículo de la lista?':'la lista?';
                    if (confirm('¿Confirma eliminar '+mensaje)){
                        if(tipo == 'articulo'){
                            $.ajax({
                                type: 'POST',
                                data: {
                                    idArticulo  : idItem
                                },
                                url: '/articulo/eliminar',
                                success: function(e){
                                    $(itemLista).fadeOut('slow').remove();
                                }
                                });
                        }
                        if(tipo == 'listado'){
                            $.ajax({
                                    type: 'POST',
                                    data: {
                                        idListado     : idLista
                                    },
                                    url: '/listado/eliminar',
                                    success: function(e){
                                        $(itemLista).fadeOut('slow').remove();
                                    }
                                    });
                        }

                    }
                    else
                        document.execCommand('undo');
                    break;
                }

                case 13:{ //Enter
                    $("#title").html("Agregar otra linea");
                    tecla.preventDefault();
                   /*
                    var texto = punto.text();
                    punto.blur();
                    $.ajax({
                        type: 'POST',
                        data: {
                            accion: 'editar',
                            id: idPunto,
                            nombre: texto
                        },
                        url: 'controlador.php'
                    });*/
                    break;
                }
            }
}
//--------------------------------------------------------------------------------

//--------------------------------------------------------------------------------
function postTecla(tecla,itemLista,tipo) {
    var idItem = getIdArticulo(itemLista);
    var idListado = getIdLista(itemLista);
    if(tecla.keyCode == 8 || tecla.keyCode > 31) {
        if(tipo == 'articulo'){
            $.ajax({
                    type: 'POST',
                    data: {
                        idArticulo  : idItem,
                        descripcion : itemLista.textContent,
                        idLista     : idListado
                    },
                    url: '/articulo/guardar',
                    success: function(e){
                    }
                    });
        }
        if(tipo == 'listado'){
            $.ajax({
                    type: 'POST',
                    data: {
                        idLista     : idListado,
                        nombre      : itemLista.textContent,
                    },
                    url: '/listado/guardar',
                    success: function(e){
                        e = e;
                    }
                    });
        }

    }
}
//--------------------------------------------------------------------------------