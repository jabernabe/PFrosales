/**
 * Archivo que gestiona los procesos relacionados con los rosales.
 */

// Metodo que muestra tabla con los rosales registrados.
function listaRosales(){
	
	$.ajax({
        url: "dameRosales"
    }).then(function(data) {
    
    	if (data.existRosal){
				
    		var textoHTML="";
    		textoHTML += "<div>";
    		textoHTML += "";
    		textoHTML += "<table id=\"mitabla\" class=\"table  table-hover table-striped table-condensed ";
    		textoHTML += "table-responsive\" cellspacing=\"0\" width=\"100%\">"
    		
    		textoHTML += "	<thead>";
    		textoHTML += "		<tr style='text-align:center; background-color:#222; color:#FFF;'>";
    		textoHTML += "			<th class='col-sm-3'>Nombre<\/th>";
    		textoHTML += "			<th class='col-sm-2'>Variedad<\/th>";
    		textoHTML += "			<th class='col-sm-2'>color<\/th>";
    		textoHTML += "			<th class='col-sm-1'>Altura<\/th>";
    		textoHTML += "			<th class='col-sm-1'>Perfumada<\/th>";
    		textoHTML += "			<th class='col-sm-1'>Estado<\/th>";
    		textoHTML += "			<th class='col-sm-1'>Imagen<\/th>";
    		//textoHTML += "			<th class='col-sm-1'>Eliminar<\/th>";
    		textoHTML += "			<th class='col-sm-1'>Editar<\/th>";
    		textoHTML += "		<\/tr>";
    		textoHTML += "	<\/thead>";
    		 		
    		textoHTML += "	<tbody>";
    		
    		for(var elm = 0;elm < data.listaRosales.length;elm++){
    		
    		textoHTML += "		<tr>";
    		textoHTML += "			<td>"+data.listaRosales[elm].nombreRosal+"<\/td>";
    		textoHTML += "			<td>"+data.listaRosales[elm].variedad.nombreVariedad+"<\/td>";
    		textoHTML += "			<td>"+data.listaRosales[elm].color+"<\/td>";
    		textoHTML += "			<td>"+data.listaRosales[elm].altura+"<\/td>";
    		textoHTML += "			<td style='text-align:center'>"+data.listaRosales[elm].perfumada+"<\/td>";
    		
    		if (data.listaRosales[elm].estado=="activo"){
    			textoHTML += "		<td style='color:green'>Activo<\/td>";
    		}
    		else{
    			textoHTML += "		<td style='color:red'>Inactivo<\/td>";
    			
    		}
    		
    			
    			if (data.listaRosales[elm].nombreImagen=="null" || data.listaRosales[elm].nombreImagen=="" || data.listaRosales[elm].nombreImagen==null){
    		
    		textoHTML += "			<td style='text-align:center'><button type='button' class='btn btn-danger btn-xs' style='width:40px; height:30px' >";		
    		textoHTML += "			<span class='glyphicon glyphicon-remove'></span></button><\/td>"	
    			}
    			else{
    				
    		textoHTML += "			<td style='text-align:center'><button type='button' class='btn btn-success btn-xs' style='width:40px; height:30px' ";				
    		textoHTML += "			onclick='muestraImagen(\""+data.listaRosales[elm].nombreImagen+"\")' >"	
    		textoHTML += "			<span class='glyphicon glyphicon-picture'></span></button><\/td>"
    			}
    			
    		//textoHTML += "			<td><button type='button' onclick='eliminaRosal(\""+data.listaRosales[elm].nombreRosal+"\", "+data.listaRosales[elm].idRosal+")' ";
    		//textoHTML += "			class='btn btn-danger btn-xs' style='width:40px; height:30px'>"
    		//textoHTML += "			<span class='glyphicon glyphicon-trash'></span></button><\/td>"
    			
    		textoHTML += "			<td style='text-align:center'><button type='button' onclick='modificaRosal(\""+data.listaRosales[elm].idRosal+"\", "
    		textoHTML += "          \""+data.listaRosales[elm].nombreRosal+"\", \""+data.listaRosales[elm].variedad.nombreVariedad+"\", "
    		textoHTML += "			\""+data.listaRosales[elm].color+"\", \""+data.listaRosales[elm].altura+"\", \""+data.listaRosales[elm].perfumada+"\", "
    		textoHTML += "			\""+data.listaRosales[elm].nombreImagen+"\", \""+data.listaRosales[elm].estado+"\", \""+data.listaRosales[elm].cantidad+"\")' class='btn btn-primary btn-xs' style='width:40px; height:30px' >";
    		textoHTML += "			<span class='glyphicon glyphicon-refresh'></span></button><\/td>"
    			
    		textoHTML += "		<\/tr>";
    		
    		}
    		
    		textoHTML += "	<\/tbody>";
    		textoHTML += "<\/table>";
    			
			document.getElementById("rosalesContent").innerHTML=textoHTML;
			
			$('#mitabla').DataTable( {
		        "language": {
		           
		        	"url": "bootstrap/js/spanish.json"
		        	// "url": "//cdn.datatables.net/plug-ins/1.10.12/i18n/Spanish.json"
		        }
		    } );	
		}
    	else{
    		if(data.errorConexion){
    			
    			alertaConexion()	
    		}
    		else{
    			var mensaje = "Actualmente no hay rosales registrados";
    			listaVacia(mensaje)
    		}
    		
    	}
    });		
}

//Funcion que muestra ventana de error en caso de servidor mysql detenido y usuarios no registrados.
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

//funcion que informa de la no existencia de rosales.
function sinDatos(){
	
	var textoHTML="<h1 style='text-align:center'>Actualmente no hay rosales registrados</h1>";
	
	document.getElementById("rosalesContent").innerHTML=textoHTML;
	
}

//Funcion que muestra ventana de error en caso de servidor mysql detenido y rosales no registrados.
function alertaConexion(){
	
	var mensaje="<h2 style='color: #e70c06 ; text-align:center'><p>ERROR</p> </h2>";
	mensaje+="<p style='color: #e70c06; text-align:center; font-size:20px'>Posible error de conexion a base de datos. "
	mensaje+='Si el error persiste contacte inmediatamente con el administrador.</p>'	
	
	var textoHTML = '<div class="modal fade" id="mostrarmodal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">'
	textoHTML+='<div class="modal-dialog"><div class="modal-content"><div class="modal-header" style="text-align:center; background-color:#222; color:#FFF"><h2>INFORMACION</h2></div>'
	textoHTML+='<div class="modal-body">'+mensaje+'</div><div class="modal-footer">'  
	textoHTML+='<a href="errorSistema" id="closeModal"  class="btn btn-danger">Salir</a>' 
	textoHTML+='</div></div></div></div>'    
    
	document.getElementById("modalDatos").innerHTML=textoHTML;
	$("#mostrarmodal").modal("show");
	
	
}

// Funcion que realiza la peticion de una imagen.
function muestraImagen(nombreImagen){
	
	$.ajax({
        url: "saveImg?nombreImagen="+nombreImagen
        
    }).then(function(data) {
    		
    	muestraModalImagen(data)
    });		
}

// Funcion que muestra un modal con la imagen de un rosal.
function muestraModalImagen(data){
	
	var textoHTML ='<div class="modal fade" id="mostrarmodal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">'
		textoHTML+='<div class="modal-dialog"><div class="modal-content">'	
		textoHTML+='<div class="modal-body">'
		
		textoHTML+='<img src="data:image/jpg;base64,' + data.image + '" width="100%" alt="Imagen no disponible"/>'	

		textoHTML+='</div>'
		textoHTML+='<div class="modal-footer">'
		textoHTML+='<a href="#" id="closeModal" data-dismiss="modal" class="btn btn-success">Cerrar</a>'     
		textoHTML+='</div></div></div></div>'    
	    
	document.getElementById("modalDatos").innerHTML=textoHTML;
	$("#mostrarmodal").modal("show");
}


// Funcion que muestra el formulario de modificacion de un rosal.
function modificaRosal(idRosal, nombreRosal, variedad, color, altura, perfumada, nombreImagen, estado, cantidad){
	
	$.ajax({
        url: "dameVariedades"   
    }).then(function(data) {
    		
    	if (data.variedadExist){
    		
    		var textoHTML ='<div class="modal fade" id="mostrarmodal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">'
    			textoHTML+='<div class="modal-dialog"><div class="modal-content"><div class="modal-header" '	
    			textoHTML+='style="text-align:center; background-color:#222; color:#FFF"><h2>Editar rosal</h2></div><div class="modal-body">'
    				
    			textoHTML+='<form id="formUpdateRosal" class="form-horizontal" style="margin:20px" enctype="multipart/form-data">'
    			textoHTML+='<input type="hidden" id="idRosal" name="idRosal" value="'+idRosal+'">'
    			textoHTML+='<input type="hidden" id="nombreImagen" name="nombreImagen" value="'+nombreImagen+'">'
    			textoHTML+='<input type="hidden" id="cantidad" name="cantidad" value="'+cantidad+'">'
    			
    			
    			textoHTML+='<div class="form-group"><label for="nombreRosal" class="col-sm-2 control-label">Nombre</label>'
    			textoHTML+='<div class="col-sm-10"><input type="text" class="form-control" id="nombreRosal" name="nombreRosal" value="'+nombreRosal+'" maxlength="40"></div></div>'        
    			
    			textoHTML+='<div class="form-group"><label for="idVariedad" class="col-sm-2 control-label">Variedad</label>'
    			textoHTML+='<div class="col-sm-10"><select class="form-control" id="idVariedad" name="idVariedad">'
    		
    		for (i=0;i<data.listaVariedades.length;i++){
    			
    			if (variedad == data.listaVariedades[i].nombreVariedad){
    				textoHTML+='<option value="'+data.listaVariedades[i].idVariedad+'" selected>'+data.listaVariedades[i].nombreVariedad+'</option>'	
    			}
    			else{
    				textoHTML+='<option value="'+data.listaVariedades[i].idVariedad+'">'+data.listaVariedades[i].nombreVariedad+'</option>'	
    			}
    			
    		}
    			textoHTML+='</select></div></div>'	
    			textoHTML+=''
    			textoHTML+='<div class="form-group"><label for="color" class="col-sm-2 control-label">Color</label>'
    			textoHTML+='<div class="col-sm-10"><input type="text" class="form-control" id="color" name="color" value="'+color+'"></div></div>'
    			textoHTML+=''	
    			textoHTML+='<div class="form-group"><label for="altura" class="col-sm-2 control-label">Altura</label>'
    			textoHTML+='<div class="col-sm-10"><input type="email" class="form-control" id="altura" name="altura" value="'+altura+'"></div></div>'
    			textoHTML+=''
    				
    			textoHTML+= '<div class="form-group"><label for="perfumada" class="col-sm-2 control-label">Perfumada</label>'
    			textoHTML+=	'<div class="col-sm-10"><select class="form-control" id="perfumada" name="perfumada">' 
    				
    			if (perfumada=="Si"){
    				textoHTML+=	'<option value="Si">Si</option><option value="No">No</option>'
    			}
    			else{
    				textoHTML+=	'<option value="No">No</option><option value="Si">Si</option>' 
    			}
    			textoHTML+=	'</select></div></div>'	
    				
    			textoHTML+= '<div class="form-group"><label for="estado" class="col-sm-2 control-label">Estado</label>'
    	    	textoHTML+=	'<div class="col-sm-10"><select class="form-control" id="estado" name="estado">' 
    	    				
    	    	if (estado=="activo"){
    	    		textoHTML+=	'<option value="activo">Activo</option><option value="inactivo">Inactivo</option>'
    	    	}
    	    	else{
    	    		textoHTML+=	'<option value="inactivo">Inactivo</option><option value="activo">Activo</option>' 
    	    	}
    	    	textoHTML+=	'</select></div></div>'
    					
    			textoHTML+=''	
    			textoHTML+= '<div class="form-group"><label for="img" class="col-sm-2 control-label">Imagen</label>'
    			textoHTML+= '<div class="col-sm-10"><input type="file" class="file" id="img" name="img"></div></div>'
    				
    			textoHTML+='</form>'
    			textoHTML+=''
    			textoHTML+='<div class="alert alert-danger" role="alert" id="modifyMessage" style="text-align:center; font-size:20px; visibility: hidden"></div>'
    			textoHTML+='</div>'
    			textoHTML+='<div class="modal-footer">'
    			textoHTML+='<a href="#" id="closeModal" data-dismiss="modal" class="btn btn-danger">Cancelar</a>'     
    			textoHTML+=	'<button type="button" onclick="validaModificaRosal()" class="btn btn-success">Aceptar</button>'
    			textoHTML+='</div></div></div></div>'    
    		    
    		document.getElementById("modalDatos").innerHTML=textoHTML;
    		$("#mostrarmodal").modal("show");
    	}
    	else{
    		mensajeErrorModificaRosal();
    	}
    });	
	
	
}

// Funcion que valida los campos del formulario de modificacion de un rosal.
function validaModificaRosal(){
	
	var nombre = $("#nombreRosal").val();
	var altura = $("#altura").val();
	var color = $("#color").val();
	var imagen = $("#img");
	
	
	if (nombre.length == 0 || /^\s+$/.test(nombre)) {
		document.getElementById("modifyMessage").style.visibility="visible";
		$("#modifyMessage").text("Nombre de rosal debe ser cumplimentado.");
		$("#nobreRosal").select();
		$("#nombreRosal").focus();
	}
	else{
		if (altura.length == 0 || /^\s+$/.test(altura)) {
			document.getElementById("modifyMessage").style.visibility="visible";
			$("#modifyMessage").text("La altura del rosal debe ser cumplimentada.");
			$("#altura").focus();
			$("#altura").select();
		}
		else{
			if (color.length == 0 || /^\s+$/.test(color)) {
				document.getElementById("modifyMessage").style.visibility="visible";
				$("#modifyMessage").text("El color del rosal debe ser cumplimentado.");
				$("#color").focus();
				$("#color").select();
			}
			else{
				
			    var input = document.getElementById('img');
			    var file = input.files[0];
			    
			    if (file == null){
			    	
			    	procesaModificaRosal();
			    }
			    else{
			    
					var sizeByte = file.size;
					var siezekiloByte = parseInt(sizeByte / 1024);
	
					 if(siezekiloByte > 1024){
						document.getElementById("modifyMessage").style.visibility="visible";
						$("#modifyMessage").text("El tamaño supera el limite permitido de 1Mb.");
					 }
					 else{
					  procesaModificaRosal(); 
					 }
			    }
				 
			}
			
		}
	}
			
}


// Funcion que muestra el mensaje en el caso de no existir variedades.
function mensajeErrorModificaRosal(){
	var textoHTML ='<div class="modal fade" id="mostrarmodal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">'
		textoHTML+='	<div class="modal-dialog"> '
		textoHTML+='		<div class="modal-content">'
		textoHTML+='			<div class="modal-body">'	
		textoHTML+='				<div class="alert alert-danger" role="alert" id="alertMessage" '
		textoHTML+='				style="text-align:center; font-size:20px">No hay variedades registradas.</div>'
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

//Funcion que procesa lo modificacion de un rosal
function procesaModificaRosal(){
	
	var formData = new FormData(document.getElementById("formUpdateRosal"));

	$.ajax({
        url: "modificaRosal",
        type: "POST",
        data: formData,
        processData: false,
        contentType: false,
        success:function(data){
        	
        	if (data.existRosal){
        		document.getElementById("modifyMessage").style.visibility="hidden"
        		MessageModificaRosal(data.message);
        		listaRosales();
        	}
        	else{
        		document.getElementById("modifyMessage").style.visibility="visible";
        		$("#modifyMessage").text(data.message);
        	}
        }
    })
}

//Funcion que muestra el mensaje de rosal modificado correctamente.
function MessageModificaRosal(message){
	
	$("#closeModal").click();
	
	var textoHTML ='<div class="modal fade" id="mostrarmodal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">'
		textoHTML+='<div class="modal-dialog"><div class="modal-content">'	
		textoHTML+='<div class="modal-body">'
			
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


// Funcion que obtiene las diferentes variedades que hay en base de datos.
function registraRosal(){
	
	$.ajax({
        url: "dameVariedades"   
    }).then(function(data) {
    		
    	if (data.variedadExist){
    		
    		muestraRegistraRosal(data);
    	}
    	else{
    		var mensaje = "Actualmente no hay variedades registradas"
    		listaVacia(mensaje);
    	}
    });	
}

// Funcion que muestra el formulario de registro de rosales
function muestraRegistraRosal(data){
	var textoHTML='<div class="panel panel-danger" style="margin-left:auto; margin-right:auto; margin-bottom:; padding:10; max-width:500px">'
		textoHTML+= '<div class="panel-heading" style="text-align:center; background-color:#222; color:#FFF"><h3>Registro de rosales</h3></div>'
		textoHTML+= '<form  method="post" id="formInsertRosal" class="form-horizontal" style="margin:20px" enctype="multipart/form-data">'
		
		textoHTML+= '<div class="form-group"><label for="nombreRosal" class="col-sm-2 control-label">Nombre</label>'
		textoHTML+= '<div class="col-sm-10"><input type="text" class="form-control" id="nombreRosal" name="nombreRosal" maxlength="50"></div></div>'        
		
		textoHTML+='<div class="form-group"><label for="variedad" class="col-sm-2 control-label">Variedad</label>'
		textoHTML+='<div class="col-sm-10"><select class="form-control" id="idVariedad" name="idVariedad">' 
				
		for (i=0;i<data.listaVariedades.length;i++){
			
			textoHTML+='<option value="'+data.listaVariedades[i].idVariedad+'">'+data.listaVariedades[i].nombreVariedad+'</option>'	
		}	  
		textoHTML+='</select></div></div>'		
			
		textoHTML+=	'<div class="form-group"><label for="color" class="col-sm-2 control-label">Color</label>'
		textoHTML+= '<div class="col-sm-10"><input type="text" class="form-control" id="color" name="color" maxlength="30" ></div></div>'
		
		textoHTML+=	'<div class="form-group"><label for="altura" class="col-sm-2 control-label">Altura</label>'
		textoHTML+= '<div class="col-sm-10"><input type="email" class="form-control" id="altura" name="altura" maxlength="30" ></div></div>'
			    	
	    textoHTML+= '<div class="form-group"><label for="perfumada" class="col-sm-2 control-label">Perfumada</label>'
	    textoHTML+=	'<div class="col-sm-10"><select class="form-control" id="perfumada" name="perfumada">'      
	    textoHTML+=	'<option value="Si">Si</option><option value="No">No</option>'   
	    textoHTML+=	'</select></div></div>'
	    	
	    textoHTML+= '<div class="form-group"><label for="estado" class="col-sm-2 control-label">Estado</label>'
	    textoHTML+=	'<div class="col-sm-10"><select class="form-control" id="estado" name="estado">'      
	    textoHTML+=	'<option value="activo">Activo</option><option value="inactivo">Inactivo</option>'   
	    textoHTML+=	'</select></div></div>'
	    	
	    textoHTML+= '<div class="form-group"><label for="ruta" class="col-sm-2 control-label">Imagen</label>'
	    textoHTML+= '<div class="col-sm-10"><input type="file" class="file" id="img" name="img" ></div></div></form>'
		   
		textoHTML+='<div class="alert alert-danger" role="alert" id="createRosalMessage" style="text-align:center; font-size:20px; visibility: hidden"></div>'
		textoHTML+='<div style="text-align:right"><button onclick="validaRegistraRosal()" '
		textoHTML+='class="btn btn-success" style="width:150px; margin:10px">Registrar</button></div></div>'
		document.getElementById("rosalesContent").innerHTML=textoHTML;
	
}

// Funcion que valida los datos del formulario de registro de rosales.
function validaRegistraRosal(){
	
	var name = $("#nombreRosal").val();
	var color = $("#color").val();
	var altura = $("#altura").val();
	
	if (name.length == 0 || /^\s+$/.test(name)) {
		document.getElementById("createRosalMessage").style.visibility="visible";
		$("#createRosalMessage").text("Nombre de rosal obligatorio");
		$("#name").focus();
		$("#name").select();
		
	}
	else{
		if (color.length == 0 || /^\s+$/.test(color)) {
			
			document.getElementById("createRosalMessage").style.visibility="visible";
			$("#createRosalMessage").text("Color de rosal obligatorio");
			$("#color").focus();
			$("#color").select();
			
		}
		else{
			if (altura.length == 0 || /^\s+$/.test(altura)) {
				
				document.getElementById("createRosalMessage").style.visibility="visible";
				$("#createRosalMessage").text("Altura de rosal obligatoria");
				$("#altura").focus();
				$("#altura").select();			
			}
			else{
				var input = document.getElementById('img');
			    var file = input.files[0];	
			    
			    if (file == null){
			    	procesaRegistraRosal();
			    }
			    else{
					var sizeByte = file.size;
					var siezekiloByte = parseInt(sizeByte / 1024);
					
					 if(siezekiloByte > 1024){
						document.getElementById("createRosalMessage").style.visibility="visible";
						$("#createRosalMessage").text("El tamaño supera el limite permitido de 1Mb.");
					 }
					 else{
						 procesaRegistraRosal(); 
					 }
			    }
			}		
		}
	}
	
}

// Funcion que realiza el registro del rosal.
function procesaRegistraRosal(){
	
	var formData = new FormData(document.getElementById("formInsertRosal"));

	$.ajax({
        url: "insertaRosal",
        type: "POST",
        data: formData,
        processData: false,
        contentType: false,
        success:function(data){
        	
        	if (data.existRosal){
        		document.getElementById("createRosalMessage").style.visibility="hidden"
        		document.getElementById("formInsertRosal").reset();
        		MessageCreaRosal(data.message);
        	}
        	else{
        		document.getElementById("createRosalMessage").style.visibility="visible";
        		$("#createRosalMessage").text(data.message);
        	}
        }
    })
}

// Funcion que muestra el mensaje de rosal creado correctamente.
function MessageCreaRosal(message){
	
	var textoHTML ='<div class="modal fade" id="mostrarmodal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">'
		textoHTML+='<div class="modal-dialog"><div class="modal-content">'	
		textoHTML+='<div class="modal-body">'
			
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

// Metodo que muestra la ventana modal informando del rosal a eliminar.
function eliminaRosal(nombreRosal, idRosal){
	
	var mensaje="<h2 style='color: #e70c06 ; text-align:center'><p>AVISO</p> </h2>";
		mensaje+="<p style='color: #e70c06; text-align:center; font-size:20px'>Esta acción eliminará el rosal de todos los pedidos.</p>"
			
	var textoHTML = '<div class="modal fade" id="mostrarmodal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">'
		textoHTML+='<div class="modal-dialog"><div class="modal-content">'
		
		textoHTML+='<div class="modal-body">'+mensaje+'</div><div class="modal-footer">'  
		textoHTML+='<a href="#" id="closeModal" data-dismiss="modal" class="btn btn-danger">Cancelar</a>' 
		textoHTML+=	'<button type="button" onclick="procesaEliminaRosal(\''+nombreRosal+'\', '+idRosal+')" class="btn btn-success">Aceptar</button>'
		textoHTML+='</div></div></div></div>'    
	    
	document.getElementById("modalDatos").innerHTML=textoHTML;
	$("#mostrarmodal").modal("show");
	
}

// Metodo que procesa la eliminacion de un rosal.
function procesaEliminaRosal(nombreRosal, idRosal){
	
	$.ajax({
        url: "eliminaRosal?idRosal="+idRosal+"&nombreRosal="+nombreRosal
    }).then(function(data) {
    		
    	if (data.existRosal){
    		
    		processMessage(data.message);
    	}
    	else{
    		
    		processMessageError(data.message);  		
    	}
    });	
	
	
}

//Funcion que muestra una ventana informando del error al eliminar un rosal.
function processMessageError(message){
	
	$("#closeModal").click();
	
	var textoHTML ='<div class="modal fade" id="mostrarmodal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">'
		textoHTML+='<div class="modal-dialog"><div class="modal-content">'	
		textoHTML+='<div class="modal-body">'
			
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

//Funcion que muestra una ventana informando del registro correcto de un rosal.
function processMessage(message){
	
	$("#closeModal").click();
	
	var textoHTML ='<div class="modal fade" id="mostrarmodal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">'
		textoHTML+='<div class="modal-dialog"><div class="modal-content">'	
		textoHTML+='<div class="modal-body">'
			
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

