/**
 * 
 */

// Metodo que muestra tabla con las variedades registradas.
function listaVariedades(){
	
	$.ajax({
        url: "dameVariedades"
    }).then(function(data) {
    	
    	
    	if (data.variedadExist){
				
    		var textoHTML="";
    		textoHTML += "<div>";
    		textoHTML += "";
    		textoHTML += "<table id=\"mitabla\" class=\"table  table-hover table-striped table-condensed ";
    		textoHTML += "table-responsive\" cellspacing=\"0\" width=\"100%\">"
    		
    		textoHTML += "	<thead>";
    		textoHTML += "		<tr style='text-align:center; background-color:#222; color:#FFF;'>";
    		textoHTML += "			<th>Nombre<\/th>";
    		textoHTML += "			<th>Plantacion<\/th>";
    		textoHTML += "			<th>Descripcion<\/th>";
    		textoHTML += "			<th>Eliminar<\/th>";
    		textoHTML += "			<th>Editar<\/th>";
    		textoHTML += "		<\/tr>";
    		textoHTML += "	<\/thead>";
    		 		
    		textoHTML += "	<tbody>";
    		
    		for(var elm = 0;elm < data.listaVariedades.length;elm++){
    		
    		textoHTML += "		<tr>";
    		textoHTML += "			<td>"+data.listaVariedades[elm].nombreVariedad+"<\/td>";
    		textoHTML += "			<td>"+data.listaVariedades[elm].distPlantacion+"<\/td>";
    		textoHTML += "			<td>"+data.listaVariedades[elm].descripcion+"<\/td>";
    			
    		textoHTML += "			<td><button type='button' onclick='eliminaVariedad(\""+data.listaVariedades[elm].nombreVariedad+"\", "+data.listaVariedades[elm].idVariedad+")' ";
    		textoHTML += "			class='btn btn-danger btn-xs' style='width:40px; height:30px'>"
    		textoHTML += "			<span class='glyphicon glyphicon-trash'></span></button><\/td>"
    			
    		textoHTML += "			<td><button type='button' onclick='modificaVariedad("+data.listaVariedades[elm].idVariedad+", "
        	textoHTML += "			\""+data.listaVariedades[elm].nombreVariedad+"\", \""+data.listaVariedades[elm].distPlantacion+"\",  "
        	textoHTML += "			\""+data.listaVariedades[elm].descripcion+"\")' class='btn btn-primary btn-xs' style='width:40px; height:30px' >";
        	textoHTML += "			<span class='glyphicon glyphicon-refresh'></span></button><\/td>"
    			
    		textoHTML += "		<\/tr>";
    		
    		}
    		
    		textoHTML += "	<\/tbody>";
    		textoHTML += "<\/table>";
    			
			document.getElementById("variedadesContent").innerHTML=textoHTML;
			
			$('#mitabla').DataTable( {
		        "language": {
		           
		        	"url": "bootstrap/js/spanish.json"
		        	// "url": "//cdn.datatables.net/plug-ins/1.10.12/i18n/Spanish.json"
		        }
		    } );
			
				
		}
    	else{
    		if (data.errorConexion){
    			
    			alertaConexion()
    		}
    		else{
    			var mensaje = "Actualmente no hay variedades registradas";
    			listaVacia(mensaje)
    		}
    		
    	}
    	
    			
    	
    });		
}

//Funcion que muestra ventana de informatcion de variedades no registradas.
function listaVacia(mensaje){
	
	sinDatos(mensaje);
	
	var info="<h2 style='color: #e70c06 ; text-align:center'><p>ERROR</p> </h2>";
	info+="<p style='color: #e70c06; text-align:center; font-size:20px'>"+mensaje+"</p>"
	
	var textoHTML = '<div class="modal fade" id="mostrarmodal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">'
	textoHTML+='<div class="modal-dialog"><div class="modal-content">'
	textoHTML+='<div class="modal-body">'+info+'</div><div class="modal-footer">'  
	textoHTML+='<a href="#" id="closeModal" data-dismiss="modal" class="btn btn-danger">Salir</a>' 
	textoHTML+='</div></div></div></div>'    
    
	document.getElementById("modalDatos").innerHTML=textoHTML;
	$("#mostrarmodal").modal("show");
		
}

//funcion que informa de la no existencia de variedades.
function sinDatos(mensaje){
	
	var textoHTML="<h1 style='text-align:center'>"+mensaje+"</h1>";
	
	document.getElementById("variedadesContent").innerHTML=textoHTML;
	
}

//Funcion que muestra ventana de error en caso de servidor mysql detenido y rosales no registrados.
function alertaConexion(){
	
	var mensaje="<h2 style='color: #e70c06 ; text-align:center'><p>ERROR</p> </h2>";
	mensaje+="<p style='color: #e70c06; text-align:center; font-size:20px'>Posible error de conexion a base de datos. "
	mensaje+='Si el error persiste contacte inmediatamente con el administrador.</p>'	
	
	var textoHTML = '<div class="modal fade" id="mostrarmodal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">'
	textoHTML+='<div class="modal-dialog"><div class="modal-content"><div class="modal-header" style="text-align:center; background-color:#222; color:#FFF"><h2>INFORMACION</h2></div>'
	textoHTML+='<div class="modal-body">'+mensaje+'</div><div class="modal-footer">'  
	textoHTML+='<a href="#" id="closeModal" data-dismiss="modal" class="btn btn-danger">Salir</a>' 
	textoHTML+='</div></div></div></div>'    
    
	document.getElementById("modalDatos").innerHTML=textoHTML;
	$("#mostrarmodal").modal("show");
	
	
}

// Funcion que modifica una variedad.
function modificaVariedad(idVariedad, nombreVariedad, distPlantacion, descripcion){
	
	  		
    		var textoHTML ='<div class="modal fade" id="mostrarmodal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">'
    			textoHTML+='<div class="modal-dialog" id="modalVariedad"><div class="modal-content"><div class="modal-header" '	
    			textoHTML+='style="text-align:center; background-color:#222; color:#FFF"><h2>Edicion variedades</h2></div><div class="modal-body">'
    				
    			textoHTML+='<form id="formUpdateVariedad" class="form-horizontal" style="margin:10px">'
    			textoHTML+='<input type="hidden" id="idVariedad" name="idVariedad" value="'+idVariedad+'">'
    			
    			textoHTML+='<div class="form-group"><label for="nombreVariedad" class="col-sm-2 control-label">Nombre</label>'
    			textoHTML+='<div class="col-sm-10"><input type="text" class="form-control" id="nombreVariedad" name="nombreVariedad" value="'+nombreVariedad+'" maxlength="40"></div></div>'        
    			
    			textoHTML+=''
    			textoHTML+='<div class="form-group"><label for="distPlantacion" class="col-sm-2 control-label">Dist-plantacion</label>'
    			textoHTML+='<div class="col-sm-10"><input type="text" class="form-control" id="distPlantacion" name="distPlantacion" value="'+distPlantacion+'"></div></div>'
    			textoHTML+=''	
    			textoHTML+='<div class="form-group"><label for="descripcion" class="col-sm-2 control-label">Descripcion</label>'
    			textoHTML+='<div class="col-sm-10"><textarea rows="6" cols="50" class="form-control" id="descripcion" name="descripcion" maxlength="500">'+descripcion+'</textarea></div></div>'
    			textoHTML+=''
    				
    			textoHTML+='</form>'
    			textoHTML+=''
    			textoHTML+='<div class="alert alert-danger" role="alert" id="modifyMessage" style="text-align:center; font-size:20px; visibility: hidden"></div>'
    			textoHTML+='</div>'
    			textoHTML+='<div class="modal-footer">'
    			textoHTML+='<a href="#" id="closeModal" data-dismiss="modal" class="btn btn-danger">Cancelar</a>'     
    			textoHTML+=	'<button type="button" onclick="validaModificaVariedad()" class="btn btn-success">Aceptar</button>'
    			textoHTML+='</div></div></div></div>'    
    		    
    		document.getElementById("modalDatos").innerHTML=textoHTML;
    		$("#mostrarmodal").modal("show");
}

function validaModificaVariedad(){
	
	var nombre = $("#nombreVariedad").val();
	var distancia = $("#distPlantacion").val();
	var descripcion = $("#descripcion").val();
	
	if (nombre.length == 0 || /^\s+$/.test(nombre)) {
		document.getElementById("modifyMessage").style.visibility="visible";
		$("#modifyMessage").text("Nombre de variedad debe ser cumplimentado.");
		$("#nombreVariedad").focus();
		$("#nobreVariedad").select();
		$("#nombreVariedad").focus();
	}
	else{
		if (distancia.length == 0 || /^\s+$/.test(distancia)) {
			document.getElementById("modifyMessage").style.visibility="visible";
			$("#modifyMessage").text("La distancia de plantacion debe ser cumplimentada.");
			$("#distPlantacion").focus();
			$("#distPlantacion").select();
		}
		else{
			
			procesaModificaVariedad();
		}
	}
	
}

// Funcion que procesa la modificacion de una variedad
function procesaModificaVariedad(){
	
	var formData = new FormData(document.getElementById("formUpdateVariedad"));

	$.ajax({
        url: "modificaVariedad",
        type: "POST",
        data: formData,
        processData: false,
        contentType: false,
        success:function(data){
        	
        	if (data.variedadExist){
        		document.getElementById("modifyMessage").style.visibility="hidden"
        		MessageVariedad(data.message);
        		listaVariedades();
        	}
        	else{
        		document.getElementById("modifyMessage").style.visibility="visible";
        		$("#modifyMessage").text(data.message);
        	}
        }
    })
	
	
}

// Funcion que muestra el mensaje de variedad modificada correctamente.
function MessageVariedad(message){
	
$("#closeModal").click();
	
	var textoHTML ='<div class="modal fade" id="mostrarmodal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">'
		textoHTML+='<div class="modal-dialog"><div class="modal-content"><div class="modal-header" '	
		textoHTML+='style="text-align:center; background-color:#222; color:#FFF"><h2>Gestion de variedades</h2></div><div class="modal-body">'
			
		textoHTML+='<div class="progress">'
		textoHTML+='<div id="bar" class="progress-bar progress-bar-success progress-bar-striped active" role="progressbar"'   
		textoHTML+='aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%">'
		textoHTML+='<span id="porcBar">0% Completado</span></div></div>'
	
		textoHTML+='<div class="alert alert-success" role="alert" id="alertMessage" '
		textoHTML+='style="text-align:center; font-size:20px; visibility: hidden"></div></div>'
		textoHTML+='<div class="modal-footer">'
		textoHTML+=	'<button id="enviar" type="button" onclick="" class="btn btn-success" data-dismiss="modal">Aceptar</button>'    
		textoHTML+='</div></div></div></div>'  
	
		document.getElementById("modalDatos").innerHTML=textoHTML;
		$("#mostrarmodal").modal("show");
	
	var progreso = 0;
    var idIterval = setInterval(function(){
      // Aumento en 10 el progeso
      progreso +=10;
      $('#bar').css('width', progreso + '%');
      $('#porcBar').text(progreso + "% Completado");
     
    //Si llegó a 100 elimino el interval
      if(progreso == 100){
     clearInterval(idIterval);
     document.getElementById("alertMessage").innerHTML=message;
     document.getElementById("alertMessage").style.visibility="visible";
    }
    },100);	
	
}

// Funcion que elimina una variedad.
function eliminaVariedad(nombreVariedad, idVariedad){
	
	var mensaje="<h2 style='color: #e70c06 ; text-align:center'><p>AVISO</p> </h2>";
	mensaje+="<p style='color: #e70c06; text-align:center; font-size:20px'>Esta acción eliminará los rosales de esta variedad.</p>"
		
	var textoHTML = '<div class="modal fade" id="mostrarmodal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">'
	textoHTML+='<div class="modal-dialog"><div class="modal-content"><div class="modal-header" style="text-align:center; background-color:#222; color:#FFF"><h2>Eliminar rosal</h2></div>'
	
	textoHTML+='<div class="modal-body">'+mensaje+'</div><div class="modal-footer">'  
	textoHTML+='<a href="#" id="closeModal" data-dismiss="modal" class="btn btn-danger">Cancelar</a>' 
	textoHTML+=	'<button type="button" onclick="procesaEliminaVariedad(\''+nombreVariedad+'\', '+idVariedad+')" class="btn btn-success">Aceptar</button>'
	textoHTML+='</div></div></div></div>'    
    
	document.getElementById("modalDatos").innerHTML=textoHTML;
	$("#mostrarmodal").modal("show");
	
}



//Funcion que elimina una variedad.
function procesaEliminaVariedad(nombreVariedad, idVariedad){
	
	$.ajax({
        url: "eliminaVariedad?idVariedad="+idVariedad+"&nombreVariedad="+nombreVariedad
    }).then(function(data) {
    		
    	if (data.variedadExist){
    		listaVariedades();
    		processMessage(data.message);
    	}
    	else{
    		
    		processMessageError(data.message);  		
    	}
    });	
	
}



//Funcion que muestra una ventana informando del error al eliminar una variedad.
function processMessageError(message){
	
	$("#closeModal").click();
	
	var textoHTML ='<div class="modal fade" id="mostrarmodal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">'
		textoHTML+='<div class="modal-dialog"><div class="modal-content"><div class="modal-header" '	
		textoHTML+='style="text-align:center; background-color:#222; color:#FFF"><h2>Eliminar variedad</h2></div><div class="modal-body">'
			
		textoHTML+='<div class="progress">'
		textoHTML+='<div id="bar" class="progress-bar progress-bar-danger progress-bar-striped active" role="progressbar"'   
		textoHTML+='aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%">'
		textoHTML+='<span id="porcBar">0% Completado</span></div></div>'
	
		textoHTML+='<div class="alert alert-danger" role="alert" id="alertMessage" '
		textoHTML+='style="text-align:center; font-size:20px; visibility: hidden"></div></div>'
		textoHTML+='<div class="modal-footer">'
		textoHTML+=	'<button id="enviar" type="button" onclick="listaRosales()" class="btn btn-success" data-dismiss="modal">Aceptar</button>'    
		textoHTML+='</div></div></div></div>'  
	
		document.getElementById("modalDatos").innerHTML=textoHTML;
		$("#mostrarmodal").modal("show");
	
	var progreso = 0;
    var idIterval = setInterval(function(){
      // Aumento en 10 el progeso
      progreso +=10;
      $('#bar').css('width', progreso + '%');
      $('#porcBar').text(progreso + "% Completado");
     
    //Si llegó a 100 elimino el interval
	  if(progreso == 100){
		 clearInterval(idIterval);
		 document.getElementById("alertMessage").innerHTML=message;
		 document.getElementById("alertMessage").style.visibility="visible";
	  }
    },100);
}

//Funcion que muestra una ventana informando de la eliminacion de una variedad.
function processMessage(message){
	
	$("#closeModal").click();
	
	var textoHTML ='<div class="modal fade" id="mostrarmodal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">'
		textoHTML+='<div class="modal-dialog"><div class="modal-content"><div class="modal-header" '	
		textoHTML+='style="text-align:center; background-color:#222; color:#FFF"><h2>Eliminar variedad</h2></div><div class="modal-body">'
			
		textoHTML+='<div class="progress">'
		textoHTML+='<div id="bar" class="progress-bar progress-bar-success progress-bar-striped active" role="progressbar"'   
		textoHTML+='aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%">'
		textoHTML+='<span id="porcBar">0% Completado</span></div></div>'
	
		textoHTML+='<div class="alert alert-success" role="alert" id="alertMessage" '
		textoHTML+='style="text-align:center; font-size:20px; visibility: hidden"></div></div>'
		textoHTML+='<div class="modal-footer">'
		textoHTML+=	'<button id="enviar" type="button" onclick="listaRosales()" class="btn btn-success" data-dismiss="modal">Aceptar</button>'    
		textoHTML+='</div></div></div></div>'  
	
		document.getElementById("modalDatos").innerHTML=textoHTML;
		$("#mostrarmodal").modal("show");
	
	var progreso = 0;
    var idIterval = setInterval(function(){
      // Aumento en 10 el progeso
      progreso +=10;
      $('#bar').css('width', progreso + '%');
      $('#porcBar').text(progreso + "% Completado");
     
    //Si llegó a 100 elimino el interval
	  if(progreso == 100){
		 clearInterval(idIterval);
		 document.getElementById("alertMessage").innerHTML=message;
		 document.getElementById("alertMessage").style.visibility="visible";
	  }
    },100);
}


//Funcion que muestra el formulario de registro de variedades
function registraVariedad(){
	
	var textoHTML='<div class="panel panel-danger"  style="margin-left:auto; margin-right:auto; margin-bottom:; padding:10">'
		textoHTML+= '<div class="panel-heading" style="text-align:center; background-color:#222; color:#FFF"><h3>Registro de variedades</h3></div>'
			
		textoHTML+='<form id="formCreateVariedad" class="form-horizontal" style="margin:10px">'
		
		textoHTML+='<div class="form-group"><label for="nombreVariedad" class="col-sm-2 control-label">Nombre</label>'
		textoHTML+='<div class="col-sm-10"><input type="text" class="form-control" id="nombreVariedad" name="nombreVariedad"  maxlength="40"></div></div>'        
		
		textoHTML+=''
		textoHTML+='<div class="form-group"><label for="distPlantacion" class="col-sm-2 control-label">Dist-plantacion</label>'
		textoHTML+='<div class="col-sm-10"><input type="text" class="form-control" id="distPlantacion" name="distPlantacion" ></div></div>'
		textoHTML+=''	
		textoHTML+='<div class="form-group"><label for="descripcion" class="col-sm-2 control-label">Descripcion</label>'
		textoHTML+='<div class="col-sm-10"><textarea rows="6" cols="50" class="form-control" id="descripcion" name="descripcion" maxlength="500"></textarea></div></div>'
		textoHTML+=''
			
		textoHTML+='</form>'
		textoHTML+='<div class="alert alert-danger" role="alert" id="createMessage" style="text-align:center; font-size:20px; visibility: hidden"></div>'
		textoHTML+='<div style="text-align:right"><button onclick="validaRegistraVariedad()" '
		textoHTML+='class="btn btn-success" style="width:150px; margin:10px">Registrar</button></div></div>'   
	    
	document.getElementById("variedadesContent").innerHTML=textoHTML;
	
}

//Funcion que valida los datos del formulario de registro de variedades.
function validaRegistraVariedad(){
	
	var nombre = $("#nombreVariedad").val();
	var distancia = $("#distPlantacion").val();
	
	if (nombre.length == 0 || /^\s+$/.test(nombre)) {
		document.getElementById("createMessage").style.visibility="visible";
		$("#createMessage").text("Nombre de variedad debe ser cumplimentado.");
		$("#nombreVariedad").focus();
		$("#nobreVariedad").select();
		$("#nombreVariedad").focus();
	}
	else{
		if (distancia.length == 0 || /^\s+$/.test(distancia)) {
			document.getElementById("createMessage").style.visibility="visible";
			$("#createMessage").text("La distancia de plantacion debe ser cumplimentada.");
			$("#distPlantacion").focus();
			$("#distPlantacion").select();
		}
		else{
			
			procesaRegistraVariedad();
		}
	}		
}


function procesaRegistraVariedad(){
	
	var formData = new FormData(document.getElementById("formCreateVariedad"));

	$.ajax({
        url: "registraVariedad",
        type: "POST",
        data: formData,
        processData: false,
        contentType: false,
        success:function(data){
        	
        	if (data.variedadExist){
        		document.getElementById("createMessage").style.visibility="hidden"
        		MessageVariedad(data.message);
        		listaVariedades();
        	}
        	else{
        		document.getElementById("createMessage").style.visibility="visible";
        		$("#createMessage").text(data.message);
        	}
        }
    })
}

	