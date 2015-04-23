//--------------------------------------------------------------------------------
function getId(strId)
{
    var idItem = strId.id.split('-');
    return idItem[1];
}
//--------------------------------------------------------------------------------
function preTecla(tecla,itemLista)
{
    var idItem = getId(itemLista);

    switch(tecla.keyCode){
                case 27:{ //Escape
                    document.execCommand('undo');
                    break;
                }
                /*
                case 46:{ //Suprimir
                    tecla.preventDefault();
                    if (confirm('¿Confirma eliminar el artículo de la lista?')){
                        $("#title").html("Eliminar Articulo: "+idItem);
                        /*
                        $.ajax({
                            type: 'POST',
                            data: {
                                accion: 'eliminar',
                                orden: punto.index(),
                                id: idPunto
                            },
                            url: 'controlador.php',
                            success: function(e){
                                punto.fadeOut('slow').remove();
                            }
                        });

                        }
                    else
                        document.execCommand('undo');
                    break;
                }
                */
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
function postTecla(tecla,itemLista) {
    var idItem = getId(itemLista);

    if(tecla.keyCode == 8 || tecla.keyCode > 31) {
        $("#title").html("Guardar Artículo: "+idItem+" Contenido: "+itemLista.textContent+" Tecla: "+tecla.keyCode);
        /*
        $.ajax({
            type: 'POST',
            data: {
                accion: 'eliminar',
                orden: punto.index(),
                id: idPunto
            },
            url: 'controlador.php',
            success: function(e){
                punto.fadeOut('slow').remove();
            }
        });
        */
    }
    else
        $("#title").html("Pendientes");

}
//--------------------------------------------------------------------------------