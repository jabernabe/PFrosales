
function nuevoAlbaran(){
	
	$.ajax({
        url: "dameClientes"   
    }).then(function(data) {
    		
    	if (data.existClientes){
    		
    		muestraRegistraAlbaran(data);
    	}
    	else{
    		mensajeErrorRegistraAlbaran(data);
    	}
    });	
}

function muestraRegistraAlbaran(data){
	
	var textoHTML='<div class="panel panel-danger" style="margin-left:auto; margin-right:auto; padding:10; max-width:700px">'
		textoHTML+= '<div class="panel-heading" style="text-align:center; height:45px; text-align:center; background-color:#222; color:#FFF"><h4>Seleccione un cliente</h4></div>'
		textoHTML+= '<form  method="post" id="formCreaAlbaran" class="form-horizontal" style="margin:20px">'
		      
		textoHTML+='<div class="form-group"><label for="idCliente" class="col-sm-2 control-label">Cliente</label>'
		textoHTML+='<div class="col-sm-10"><select class="form-control" id="idCliente" name="idCliente">' 
				
		for (i=0;i<data.listaClientes.length;i++){
			
			textoHTML+='<option value="'+data.listaClientes[i].idCliente+'">'+data.listaClientes[i].nombreCliente+'</option>'
		}	  
		textoHTML+='</select></div></div>'		
		  	
	    textoHTML+= '</form>'
		   
		textoHTML+='<div class="alert alert-danger" role="alert" id="createRosalMessage" style="text-align:center; font-size:20px; visibility: hidden"></div>'
		textoHTML+='<div style="text-align:right"><button onclick="creaNuevoAlbaran()" '
		textoHTML+='class="btn btn-success" style="width:150px; margin:10px">Crear albaran</button></div></div>'
		document.getElementById("albaranesContent").innerHTML=textoHTML;
	
}


// Metodo que crea un nuevo albaran del cliente seleccionado.
function creaNuevoAlbaran() {
	
	var formData = new FormData(document.getElementById("formCreaAlbaran"));

	$.ajax({
        url: "creaAlbaran",
        type: "POST",
        data: formData,
        processData: false,
        contentType: false,
        success:function(data){
        	
        	if (data.existAlbaran){
        		
        		muestraAlbaran(data);
        	}
        	else{
        		muestraErrorCreaAlbaran(data);
        		alert("error al crear el albaran")
        	}
        }
    })
}

function muestraAlbaran(data){
	
	var textoHTML='<div class="panel panel-danger" style="margin-left:auto; margin-right:auto; margin-bottom:; padding:10">'
		textoHTML+= '<div class="panel-heading" style="text-align:center; height:45px; text-align:center; background-color:#222; color:#FFF"><h4>Nuevo albaran</h4></div>'
		textoHTML+= '<form  method="post" id="formAlbaran" class="form-horizontal" style="margin:20px">'
		 
		textoHTML+='<div class="form-group"><label for="nombreCliente" class="col-sm-2 control-label">Cliente</label>'
    	textoHTML+='<div class="col-sm-10"><input type="text" class="form-control" id="nombreCliente" name="nombreCliente" value="'+data.albaran.cliente.nombreCliente+'" maxlength="40" readonly></div></div>'        
		
    	textoHTML+='<div class="form-group"><label for="idAlbaran" class="col-sm-2 control-label">Nº Albaran</label>'
        textoHTML+='<div class="col-sm-10"><input type="text" class="form-control" id="idAlbaran" name="idAlbaran" value="'+data.albaran.idAlbaran+'" maxlength="40" readonly></div></div>'        
			
        textoHTML+='<div class="form-group"><label for="fecha" class="col-sm-2 control-label">Fecha</label>'
        textoHTML+='<div class="col-sm-10"><input type="date" class="form-control" id="fecha" name="fecha" value="'+parseaFecha(data.albaran.fecha)+'" maxlength="40" readonly></div></div>'        
		  	
	    textoHTML+= '</form>'
	    
	    textoHTML += "<div id='detalleAlbaran' style='margin:20px'>";
    	textoHTML += "<table id=\"mitabla\" class=\"table  table-hover table-striped table-condensed ";
    	textoHTML += "table-responsive\" cellspacing=\"0\" width=\"100%\"  >"
    	textoHTML += ""
    	textoHTML += "	<thead>";
    	textoHTML += "		<tr style='text-align:center; background-color:#222; color:#FFF;'>";
    	textoHTML += "			<th>Descripcion<\/th>";
    	textoHTML += "			<th>Variedad<\/th>";
    	textoHTML += "			<th>Cantidad<\/th>";
    	textoHTML += "			<th>Eliminar<\/th>";
    	textoHTML += "		<\/tr>";
    	textoHTML += "	<\/thead>";
    	textoHTML += "	<tbody>";
    	
    	if (data.listaDetalle.length>0){
    		
    		for(var elm = 0;elm < data.listaDetalle.length;elm++){
        		
        		textoHTML += "		<tr>";
        		textoHTML += "			<td>"+data.listaDetalle[elm].rosal.nombreRosal+"<\/td>";
        		textoHTML += "			<td>"+data.listaDetalle[elm].rosal.variedad.nombreVariedad+"<\/td>";
        		textoHTML += "			<td>"+data.listaDetalle[elm].cantidad+"<\/td>";
        		
        		textoHTML += "			<td><button type='button' onclick='confirmaEliminaDetalle("
        		textoHTML += 			+data.listaDetalle[elm].rosal.idRosal+", "+data.listaDetalle[elm].cantidad+", "+data.listaDetalle[elm].idDetalleAlbaran+")' "
        		textoHTML += "			class='btn btn-danger btn-xs' style='width:40px; height:30px'>"
        		textoHTML += "			<span class='glyphicon glyphicon-trash'></span></button><\/td>"	
        		textoHTML += "		<\/tr>";
        		
        		}	
    	}
    	else{
    		textoHTML += "		<tr>";
        	textoHTML += "			<td><\/td>";
        	textoHTML += "			<td>Por favor. Inserte lineas al pedido<\/td>";
        	textoHTML += "			<td><\/td>";
        	textoHTML += ""
        	textoHTML += "		<\/tr>";
    		
    	}
    	textoHTML += "	<\/tbody>";
    	textoHTML += "<\/table>";   	
    	textoHTML += "</div>";
    	
    	textoHTML += "<form  class='form-inline' id='formInsertRosal'>";
  
    	textoHTML+='<div class="form-group"><label for="idRosal" style="margin-left:20px; margin-right:20px">Producto</label>'
        textoHTML+='<select class="form-control" id="idRosal" name="idRosal">'
        		
        	for (i=0;i<data.listaRosales.length;i++){
        					
        		textoHTML+='<option value="'+data.listaRosales[i].idRosal+'">'+data.listaRosales[i].nombreRosal+'</option>'			
        	}
        textoHTML+='</select></div>'
        	
        textoHTML+='<div class="form-group"><label for="cantidad" style="margin-left:20px; margin-right:20px">Cantidad</label>'
        textoHTML+='<input type="text" class="form-control" id="cantidad" name="cantidad" maxlength="10"></div>' 
        	
        textoHTML+='<div class="form-group"><label for="idAlbaran" style="margin-left:20px; margin-right:20px"></label>'
        textoHTML+='<input type="hidden" class="form-control" value="'+data.albaran.idAlbaran+'" id="idAlbaran" name="idAlbaran"></div>' 
    	
        
        textoHTML+='<button type="button" onclick="insertaLinea()" class="btn btn-success" style="margin-left:20px; margin-right:20px"><span class="glyphicon glyphicon-shopping-cart"></span>&ensp;Añadir</button>'
        	
    	textoHTML +='</form>'
    	
	    textoHTML+='<div style="text-align:right"><button onclick="nuevoAlbaran()" '
	    textoHTML+='class="btn btn-success" style="width:150px; margin:10px">Terminar</button></div>'
	        	
	    textoHTML+='<div style="text-align:right"><button onclick="eliminaAlbaran('+data.albaran.idAlbaran+')" '
	    textoHTML+='class="btn btn-danger" style="width:150px; margin:10px">Cancelar</button></div>'
	   
	    textoHTML+='</div>'
	    
	    document.getElementById("albaranesContent").innerHTML=textoHTML;
        
        $('#mitabla').DataTable( {
	        "language": {
	           
	        	"url": "bootstrap/js/spanish.json"
	        	// "url": "//cdn.datatables.net/plug-ins/1.10.12/i18n/Spanish.json"
	        }
	    } );
	
}


//Metodo que crea un nuevo albaran del cliente seleccionado.
function insertaLinea() {
	
	var formData = new FormData(document.getElementById("formInsertRosal"));	

	$.ajax({
        url: "insertaLinea",
        type: "POST",
        data: formData,
        processData: false,
        contentType: false,
        success:function(data){
        	
        	if (data.existAlbaran){
        		refrescaAlbaran(data);
        	}
        	else{
        		mensajeStock(data);
        	}
        	
        		
        }
    })
}


// Metodo que refresca el albaran.
function refrescaAlbaran(data){
	
	var textoHTML = "<table id=\"mitabla\" class=\"table  table-hover table-striped table-condensed ";
	textoHTML += "table-responsive\" cellspacing=\"0\" width=\"100%\" >"
	textoHTML += ""
	textoHTML += "	<thead>";
	textoHTML += "		<tr style='text-align:center; background-color:#222; color:#FFF;'>";
	textoHTML += "			<th>Descripcion<\/th>";
	textoHTML += "			<th>Variedad<\/th>";
	textoHTML += "			<th>Cantidad<\/th>";
	textoHTML += "			<th>Eliminar<\/th>";
	textoHTML += "		<\/tr>";
	textoHTML += "	<\/thead>";
	textoHTML += "	<tbody>";
	
	for(var elm = 0;elm < data.listaDetalle.length;elm++){
	
	textoHTML += "		<tr>";
	textoHTML += "			<td>"+data.listaDetalle[elm].rosal.nombreRosal+"<\/td>";
	textoHTML += "			<td>"+data.listaDetalle[elm].rosal.variedad.nombreVariedad+"<\/td>";
	textoHTML += "			<td>"+data.listaDetalle[elm].cantidad+"<\/td>";
	
	textoHTML += "			<td><button type='button' onclick='confirmaEliminaDetalle("
	textoHTML += 			+data.listaDetalle[elm].rosal.idRosal+", "+data.listaDetalle[elm].cantidad+", "+data.listaDetalle[elm].idDetalleAlbaran+")' "
	textoHTML += "			class='btn btn-danger btn-xs' style='width:40px; height:30px'>"
	textoHTML += "			<span class='glyphicon glyphicon-trash'></span></button><\/td>"
		
		
	textoHTML += "		<\/tr>";
	
	}
	
	textoHTML += "	<\/tbody>";
	textoHTML += "<\/table>";
	
	
	
	document.getElementById("detalleAlbaran").innerHTML=textoHTML;
	
	$('#mitabla').DataTable( {
        "language": {
           
        	"url": "bootstrap/js/spanish.json"
        	// "url": "//cdn.datatables.net/plug-ins/1.10.12/i18n/Spanish.json"
        }
    } );
		
}

// Funcion que muestra el stock altual de un rosal.
function mensajeStock(data){
	
	var textoHTML ='<div class="modal fade" id="mostrarmodal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">'
		textoHTML+='	<div class="modal-dialog"> '
		textoHTML+='		<div class="modal-content">'
		textoHTML+='			<div class="modal-body">'	
		textoHTML+='				<div class="alert alert-danger" role="alert" id="alertMessage" '
		textoHTML+='				style="text-align:center; font-size:20px">'+data.message+'</div>'
		textoHTML+='			</div>'
		textoHTML+='			<div class="modal-footer">'
		textoHTML+='				<button id="enviar" type="button" onclick="" class="btn btn-success" data-dismiss="modal">Aceptar</button>'    
		textoHTML+='			</div>'  
		textoHTML+='		</div>'
		textoHTML+='	</div>'
		textoHTML+='</div>'
			
		document.getElementById("modalDatos").innerHTML=textoHTML;
		$("#mostrarmodal").modal("show");
}
// Funcion que muestra advertencia antes de eliminar una linea del pedido.
function confirmaEliminaDetalle(idRosal, cantidad, idDetalle){
	var textoHTML ='<div class="modal fade" id="mostrarmodal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">'
		textoHTML+='	<div class="modal-dialog"> '
		textoHTML+='		<div class="modal-content">'
		textoHTML+='			<div class="modal-body">'	
		textoHTML+='		<div class="alert alert-danger" role="alert" id="alertMessage" '	
		textoHTML+='				style="text-align:center; font-size:20px">¿Desea eliminar el articulo del pedido?</div>'
		textoHTML+='			</div>'
		textoHTML+='			<div class="modal-footer">'
		textoHTML+='				<button id="enviar" type="button" onclick="" class="btn btn-danger" data-dismiss="modal">cancelar</button>'	
		textoHTML+='				<button id="enviar" type="button" onclick="eliminaLineaDetalle('+idRosal+', '+cantidad+', '+idDetalle+')" class="btn btn-success" data-dismiss="modal">Aceptar</button>'   
		textoHTML+='			</div>'  
		textoHTML+='		</div>'
		textoHTML+='	</div>'
		textoHTML+='</div>'
			
		document.getElementById("modalDatos").innerHTML=textoHTML;
		$("#mostrarmodal").modal("show");
}

// Metodo que elimina una linea del detalle del albaran.
function eliminaLineaDetalle(idRosal, cantidad, idDetalle){
	
	var idAlbaran = $("#idAlbaran").val();
	
	$.ajax({
        url: "eliminaLinea?idRosal="+idRosal+"&cantidad="+cantidad+"&idDetalleAlbaran="+idDetalle+"&idAlbaran="+idAlbaran,
        type: "POST",
        processData: false,
        contentType: false,
        success:function(data){
        	
        	if (data.existAlbaran){
        		refrescaAlbaran(data);
        	}
        	else{
        		mensajeAlbaranEliminado(data);
        		refrescaAlbaran(data);
        	}
        	
        }
    })
	
}

// Metodo que elimina un albaran
function eliminaAlbaran(numAlbaran){
	
	var idAlbaran = numAlbaran;
	
	$.ajax({
        url: "eliminaAlbaran?idAlbaran="+idAlbaran,
        type: "POST",
        processData: false,
        contentType: false,
        success:function(data){
        	
        	if (data.existAlbaran){
        		mensajeAlbaranEliminado(data)
        		nuevoAlbaran();
        	}
        	else{
        		mensajeAlbaranEliminado(data)
        		nuevoAlbaran();
        	}
        	
        }
    })
	
}



// Metodo que muestra el mensaje de albaran eliminado.
function mensajeAlbaranEliminado(data){
	var textoHTML ='<div class="modal fade" id="mostrarmodal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">'
		textoHTML+='	<div class="modal-dialog"> '
		textoHTML+='		<div class="modal-content">'
		textoHTML+='			<div class="modal-body">'	
		
		if (data.existAlbaran){
			textoHTML+='		<div class="alert alert-success" role="alert" id="alertMessage" '	
		}
		else{
			textoHTML+='		<div class="alert alert-danger" role="alert" id="alertMessage" '
		}
			
		textoHTML+='				style="text-align:center; font-size:20px">'+data.message+'</div>'
		textoHTML+='			</div>'
		textoHTML+='			<div class="modal-footer">'
		textoHTML+='				<button id="enviar" type="button" onclick="" class="btn btn-success" data-dismiss="modal">Aceptar</button>'    
		textoHTML+='			</div>'  
		textoHTML+='		</div>'
		textoHTML+='	</div>'
		textoHTML+='</div>'
			
		document.getElementById("modalDatos").innerHTML=textoHTML;
		$("#mostrarmodal").modal("show");
	
}

// Funcion que consulta los pedidos de la base de datos.
function listaPedidos(){
	
	$.ajax({
        url: "damePedidos"   
    }).then(function(data) {
    		
    	if (data.existAlbaran){
    		
    		muestraListaPedidos(data);
    	}
    	else{
    		errorListaPedidos(data);
    	}
    });	
}

// Funcion que muestra la lista de pedidos.
function muestraListaPedidos(data){
	
	var textoHTML='<div class="panel panel-danger" style="margin-left:auto; margin-right:auto; margin-bottom:; padding:5">'
		textoHTML+= '<div class="panel-heading" style="text-align:center; height:45px; text-align:center; background-color:#222; color:#FFF"><h4>Gestión de albaranes</h4></div>'
			    
	    textoHTML += "<div id='listaPedidos' style='margin:20px'>";
    	textoHTML += "<table id=\"mitabla\" class=\"table  table-hover table-striped table-condensed ";
    	textoHTML += "table-responsive\" cellspacing=\"0\" width=\"100%\"  >"
    	textoHTML += ""
    	textoHTML += "	<thead>";
    	textoHTML += "		<tr style='text-align:center; background-color:#222; color:#FFF;'>";
    	textoHTML += "			<th>Numero<\/th>";
    	textoHTML += "			<th>Fecha<\/th>";
    	textoHTML += "			<th>Cliente<\/th>";
    	textoHTML += "			<th>Eliminar<\/th>";
    	textoHTML += "			<th>Editar<\/th>";
    	textoHTML += "			<th>Imprimir<\/th>";
    	textoHTML += "		<\/tr>";
    	textoHTML += "	<\/thead>";
    	textoHTML += "	<tbody>";
    	
   	for(var elm = 0;elm < data.listaAlbaranes.length;elm++){
    		
    		textoHTML += "		<tr>";
    		textoHTML += "			<td>"+data.listaAlbaranes[elm].idAlbaran+"<\/td>";
    		
    		textoHTML += "			<td>"+parseaFecha(data.listaAlbaranes[elm].fecha)+"<\/td>";
    		textoHTML += "			<td>"+data.listaAlbaranes[elm].cliente.nombreCliente+"<\/td>";
    		
    		textoHTML += "			<td><button type='button' onclick='confirmaEliminaPedido("
    		textoHTML += 			+data.listaAlbaranes[elm].idAlbaran+")' "
    		textoHTML += "			class='btn btn-danger btn-xs' style='width:40px; height:30px'>"
    		textoHTML += "			<span class='glyphicon glyphicon-trash'></span></button><\/td>"
    		
    		textoHTML += "			<td><button type='button' onclick='editaPedido("
    	    textoHTML += 			+data.listaAlbaranes[elm].idAlbaran+")' "
    	    textoHTML += "			class='btn btn-primary btn-xs' style='width:40px; height:30px'>"
    	    textoHTML += "			<span class='glyphicon glyphicon-refresh'></span></button><\/td>"
    		
    	    textoHTML += "			<td><button type='button' onclick='imprimePedido("
    	    textoHTML += 			+data.listaAlbaranes[elm].idAlbaran+")' "
    	    textoHTML += "			class='btn btn-danger btn-xs' style='width:40px; height:30px; horizontal-align:middle'>"
    	    textoHTML += '			<span>pdf</span></button><\/td>'
    			
    		textoHTML += "		<\/tr>";
    		
    		}
    	textoHTML += "	<\/tbody>";
    	textoHTML += "<\/table>";   	
    	textoHTML += "</div>";
	    
	    
	    document.getElementById("albaranesContent").innerHTML=textoHTML;
        
        $('#mitabla').DataTable( {
	        "language": {
	           
	        	"url": "bootstrap/js/spanish.json"
	        	// "url": "//cdn.datatables.net/plug-ins/1.10.12/i18n/Spanish.json"
	        },
        	"order": [[ 0, "desc" ]]
               
	    } );
}

function imprimePedido(idPedido){
	
	$.ajax({
        url: "generaPdf?idAlbaran="+idPedido,
        type: "POST",
        processData: false,
        contentType: false,
        success:function(data){
        	
        	if (data.created){
                   
                var pdfWin= window.open("data:application/pdf;base64, " + data.filePdf, 'title='+data.pdfName, 'height=900,width=800');
                pdfWin.document.name = data.pdfName;
        	}
        	else{
        		alert("pdf generado")
        	}
        	
        }
    })
	
}

function confirmaEliminaPedido(numAlbaran){
	var textoHTML ='<div class="modal fade" id="mostrarmodal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">'
		textoHTML+='	<div class="modal-dialog"> '
		textoHTML+='		<div class="modal-content">'
		textoHTML+='			<div class="modal-body">'	
		textoHTML+='		<div class="alert alert-danger" role="alert" id="alertMessage" '	
		textoHTML+='				style="text-align:center; font-size:20px">¿Desea eliminar el Pedido?</div>'
		textoHTML+='			</div>'
		textoHTML+='			<div class="modal-footer">'
		textoHTML+='				<button id="enviar" type="button" onclick="" class="btn btn-danger" data-dismiss="modal">cancelar</button>'	
		textoHTML+='				<button id="enviar" type="button" onclick="eliminaAlbaranLista('+numAlbaran+')" class="btn btn-success" data-dismiss="modal">Aceptar</button>'   
		textoHTML+='			</div>'  
		textoHTML+='		</div>'
		textoHTML+='	</div>'
		textoHTML+='</div>'
			
		document.getElementById("modalDatos").innerHTML=textoHTML;
		$("#mostrarmodal").modal("show");
}



//Metodo que elimina un albaran de la lista albaranes.
function eliminaAlbaranLista(numAlbaran){
	
	var idAlbaran = numAlbaran;
	
	$.ajax({
        url: "eliminaAlbaran?idAlbaran="+idAlbaran,
        type: "POST",
        processData: false,
        contentType: false,
        success:function(data){
        	
        	if (data.existAlbaran){
        		mensajeAlbaranEliminado(data)
        		listaPedidos();
        	}
        	else{
        		mensajeAlbaranEliminado(data)
        		listaPedidos();
        	}
        	
        }
    })
	
}

// Funcion que edita un albaran.
function editaPedido(numPedido){
	

	$.ajax({
        url: "editaAlbaran?idAlbaran="+numPedido,
        type: "POST",
        processData: false,
        contentType: false,
        success:function(data){
        	
        	if (data.existAlbaran){
        		
        		editaAlbaran(data);
        	}
        	else{
        		muestraErrorCreaAlbaran(data);
        		alert("error al crear el albaran")
        	}
        }
    })
}

// Funcion que muestra un albaran editado.
function editaAlbaran(data){
	
	var textoHTML='<div class="panel panel-danger" style="margin-left:auto; margin-right:auto; margin-bottom:; padding:10">'
		textoHTML+= '<div class="panel-heading" style="text-align:center; height:45px; text-align:center; background-color:#222; color:#FFF"><h4>Edición de Albaranes</h4></div>'
		textoHTML+= '<form  method="post" id="formAlbaran" class="form-horizontal" style="margin:20px">'
		 
		textoHTML+='<div class="form-group"><label for="nombreCliente" class="col-sm-2 control-label">Cliente</label>'
    	textoHTML+='<div class="col-sm-10"><input type="text" class="form-control" id="nombreCliente" name="nombreCliente" value="'+data.albaran.cliente.nombreCliente+'" maxlength="40" readonly></div></div>'        
		
    	textoHTML+='<div class="form-group"><label for="idAlbaran" class="col-sm-2 control-label">Nº Albaran</label>'
        textoHTML+='<div class="col-sm-10"><input type="text" class="form-control" id="idAlbaran" name="idAlbaran" value="'+data.albaran.idAlbaran+'" maxlength="40" readonly></div></div>'        
			
        textoHTML+='<div class="form-group"><label for="fecha" class="col-sm-2 control-label">Fecha</label>'
        textoHTML+='<div class="col-sm-10"><input type="date" class="form-control" id="fecha" name="fecha" value="'+parseaFecha(data.albaran.fecha)+'" maxlength="40" readonly></div></div>'        
		  	
	    textoHTML+= '</form>'
	    
	    textoHTML += "<div id='detalleAlbaran' style='margin:20px'>";
    	textoHTML += "<table id=\"mitabla\" class=\"table  table-hover table-striped table-condensed ";
    	textoHTML += "table-responsive\" cellspacing=\"0\" width=\"100%\"  >"
    	textoHTML += ""
    	textoHTML += "	<thead>";
    	textoHTML += "		<tr style='text-align:center; background-color:#222; color:#FFF;'>";
    	textoHTML += "			<th>Descripcion<\/th>";
    	textoHTML += "			<th>Variedad<\/th>";
    	textoHTML += "			<th>Cantidad<\/th>";
    	textoHTML += "			<th>Eliminar<\/th>";
    	textoHTML += "		<\/tr>";
    	textoHTML += "	<\/thead>";
    	
    	textoHTML += "	<tbody>";
    	
    	if (data.listaDetalle.length>0){
    		
    		for(var elm = 0;elm < data.listaDetalle.length;elm++){
        		
        		textoHTML += "		<tr>";
        		textoHTML += "			<td>"+data.listaDetalle[elm].rosal.nombreRosal+"<\/td>";
        		textoHTML += "			<td>"+data.listaDetalle[elm].rosal.variedad.nombreVariedad+"<\/td>";
        		textoHTML += "			<td>"+data.listaDetalle[elm].cantidad+"<\/td>";
        		
        		textoHTML += "			<td><button type='button' onclick='confirmaEliminaDetalle("
        		textoHTML += 			+data.listaDetalle[elm].rosal.idRosal+", "+data.listaDetalle[elm].cantidad+", "+data.listaDetalle[elm].idDetalleAlbaran+")' "
        		textoHTML += "			class='btn btn-danger btn-xs' style='width:40px; height:30px'>"
        		textoHTML += "			<span class='glyphicon glyphicon-trash'></span></button><\/td>"	
        		textoHTML += "		<\/tr>";
        		
        		}	
    	}
    	else{
    		textoHTML += "		<tr>";
        	textoHTML += "			<td><\/td>";
        	textoHTML += "			<td>Por favor. Inserte lineas al pedido<\/td>";
        	textoHTML += "			<td><\/td>";
        	textoHTML += ""
        	textoHTML += "		<\/tr>";
    		
    	}
    	textoHTML += "	<\/tbody>";
    	textoHTML += "<\/table>";   	
    	textoHTML += "</div>";
    	
    	textoHTML += "<form  class='form-inline' id='formInsertRosal'>";
  
    	textoHTML+='<div class="form-group"><label for="idRosal" style="margin-left:20px; margin-right:20px">Producto</label>'
        textoHTML+='<select class="form-control" id="idRosal" name="idRosal">'
        		
        	for (i=0;i<data.listaRosales.length;i++){
        					
        		textoHTML+='<option value="'+data.listaRosales[i].idRosal+'">'+data.listaRosales[i].nombreRosal+'</option>'			
        	}
        textoHTML+='</select></div>'
        	
        textoHTML+='<div class="form-group"><label for="cantidad" style="margin-left:20px; margin-right:20px">Cantidad</label>'
        textoHTML+='<input type="text" class="form-control" id="cantidad" name="cantidad" maxlength="10"></div>' 
        	
        textoHTML+='<div class="form-group"><label for="idAlbaran" style="margin-left:20px; margin-right:20px"></label>'
        textoHTML+='<input type="hidden" class="form-control" value="'+data.albaran.idAlbaran+'" id="idAlbaran" name="idAlbaran"></div>' 
    	
        
        textoHTML+='<button type="button" onclick="insertaLinea()" class="btn btn-success" style="margin-left:20px; margin-right:20px"><span class="glyphicon glyphicon-shopping-cart"></span>&ensp;Añadir</button>'
        	
    	textoHTML +='</form>'
    	
	    textoHTML+='<div style="text-align:right"><button onclick="listaPedidos()" '
	    textoHTML+='class="btn btn-success" style="width:150px; margin:10px">Terminar</button></div>'
	        	
	   // textoHTML+='<div style="text-align:right"><button onclick="eliminaAlbaran()" '
	   // textoHTML+='class="btn btn-danger" style="width:150px; margin:10px">Cancelar</button></div>'
	   
	    textoHTML+='</div>'
	    
	    document.getElementById("albaranesContent").innerHTML=textoHTML;
        
        $('#mitabla').DataTable( {
	        "language": {
	           
	        	"url": "bootstrap/js/spanish.json"
	        	// "url": "//cdn.datatables.net/plug-ins/1.10.12/i18n/Spanish.json"
	        }
	    } );
	
}


// Funcion que convierte una fecha json al formato dd-mm-yyyy
function parseaFecha(fechaJson){
	
	var fec = new Date(fechaJson);
	 
	dia=fec.getDate(); 
	if (dia<10){
		dia='0'+dia; 
	}
	else{
		dia=0+dia;
	}
	mes=fec.getMonth(); 
	
	if (mes<10) {
		mimes = mes+1;
		mes='0'+ mimes; 
	}
	else{
		mes=1+mes; 
	}
	anio=fec.getFullYear();  
	fecha=dia+'-'+mes+'-'+anio;
	
	return fecha;
}