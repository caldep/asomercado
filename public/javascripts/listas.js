$(function(){
	//enfocamos el campo para digitar nombres (cuestion de usabilidad)
	$('#txtNombre').focus();
	
	//evento al hacer clic en el boton agregar
	$('#btnAgregar').on('click',function(){
		//obtenemos el nombre digitado por el usuario, y el limite establecido
		//con la funcion parseInt() convertimos de texto a numero
		var $txtNombre=$('#txtNombre'), iLimite=0;//parseInt($('#selLimite').val());
		
		//verificamos que el campo nombre no este vacio
		if($.trim($(txtNombreLista).val())!=''){
			if($.trim($txtNombre.val())!=''){
				//variable para contener la lista html
				var $ulLista;
				var descripOrig = $.trim($txtNombre.val());
				//si la lista html no existe entonces la agregamos al dom
				if(!$('#divLista').find('ul').length) $('#divLista').append('<ul/>');

				//obtenemos una instancia de la lista
				$ulLista=$('#divLista').find('ul');

				//verificamos el limite de elementos
				if($ulLista.find('li').length<iLimite || iLimite==0){
					$.ajax({
							type: 'POST',
							data: {
								idArticulo  : 0,
								descripcion : $.trim($txtNombre.val()),
								idLista     : $("#idListaHdn").val()
							},
							url: '/articulo/guardar',
							success: function(e){
								//creamos el item que va a contener el nombre y el boton eliminar
								var idList = $("#idListaHdn").val()+'-'+e;
								var $liNuevoNombre=$('<li/>',
								{
									id:idList
								}
								).html('<a class="clsEliminarElemento">&nbsp;</a>'+'<label contentEditable="true" '+
								'id="'+idList+'" '
								+
								'onkeyup="postTecla(event,this,'+"'articulo');"+'"'
								+'>'+descripOrig+'</label>');

								//agregamos el elemento al final de la lista (con append)
								$ulLista.append($liNuevoNombre);

							}
							});

				//no se pueden agregar mas elementos, debido al limite establecido
				}else{
					alert('No es posible agregar el elemento. Se permiten solamente '+iLimite+'.');
				}
			//el campo nombre esta vacio
			}else{
				alert('Por favor, ingrese la descripción del artículo.')
			}
		}else{
			alert('Por favor, ingrese el nombre de la lista.')
		}
		//limpiamos el campo nombre y lo enfocamos
		$txtNombre.val('').focus();
	});
	
	//evento al hacer clic en el boton eliminar de cada item de la lista
	//se debe usar "live", ya que son elementos generados donamicamente
	$('.clsEliminarElemento').live('click',function(){
		//buscamos la lista
		var $ulLista=$('#divLista').find('ul');
		//buscamos el padre del boton (el tag li en el que se encuentra)
		var $liPadre=$($(this).parents().get(0));
		
		//eliminamos el elemento
		$liPadre.remove();
		//si la listaesta vacia entonces la eliminamos del dom
		if($ulLista.find('li').length==0) $ulLista.remove();
	});
	
	//eliminamos los elementos impares en la lista (odd)
	$('#btnEliminarPares').on('click',function(){
		$('#divLista ul li:odd').remove();
	});
	
	//eliminamos los elementos pares en la lista (even)
	$('#btnEliminarImpares').on('click',function(){
		$('#divLista ul li:even').remove();
	});
	
	//eliminamos la lista del dom
	$('#btnEliminarTodo').on('click',function(){
		$('#divLista ul').remove();
	});
	
	//al presionar <ENTER> sobre el campo txtNombre llamamos al boton (usabilidad otra vez)
	$('#txtNombre').on('keypress',function(eEvento){
		if(eEvento.which==13) $('#btnAgregar').trigger('click');
	});	
});
