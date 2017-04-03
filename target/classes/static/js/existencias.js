
function listaExistencias(){
	$.ajax({
        url: "dameExistencias"
    }).then(function(data) {
    
    	if (data.existRosal){
			
    		var textoHTML="";
    		

    		textoHTML += "<div id='listaRosales'>";
    		textoHTML += "";
    		textoHTML += "<table id=\"mitabla\" class=\"table  table-hover table-striped table-condensed ";
    		textoHTML += "table-responsive\" cellspacing=\"0\" width=\"100%\">"
    		
    		textoHTML += "	<thead>";
    		textoHTML += "		<tr style='text-align:center; background-color:#222; color:#FFF;'>";
    		textoHTML += "			<th class='col-sm-1'>Codigo<\/th>";
    		textoHTML += "			<th class='col-sm-5'>Nombre<\/th>";
    		textoHTML += "			<th class='col-sm-2'>Variedad<\/th>";
    		textoHTML += "			<th class='col-sm-1'>Cantidad<\/th>";
    		textoHTML += "			<th class='col-sm-1'>Entradas<\/th>";
    		textoHTML += "			<th class='col-sm-1'>Salidas<\/th>";
    		textoHTML += "			<th class='col-sm-1'>Actualizar<\/th>";
    		textoHTML += "		<\/tr>";
    		textoHTML += "	<\/thead>";
    		 		
    		textoHTML += "	<tbody>";
    		
    		for(var elm = 0;elm < data.listaRosales.length;elm++){
    		
    		textoHTML += "		<tr>";
    		textoHTML += "			<td style='text-align:left; padding-left:30px'>"+data.listaRosales[elm].idRosal+"<\/td>";
    		textoHTML += "			<td>"+data.listaRosales[elm].nombreRosal+"<\/td>";
    		textoHTML += "			<td>"+data.listaRosales[elm].variedad.nombreVariedad+"<\/td>";
    		textoHTML += "			<td style='text-align:right; padding-right:40px'>"+data.listaRosales[elm].cantidad+"<\/td>";
    					
    				
    		textoHTML += "			<td style='text-align:center'><button type='button' class='btn btn-success btn-xs' style='width:40px; height:30px' ";				
    		textoHTML += "			onclick='incrementaExistencias(\""+data.listaRosales[elm].nombreRosal+"\", "+data.listaRosales[elm].idRosal+")' >"	
    		textoHTML += "			<span class='glyphicon glyphicon-plus-sign'></span></button><\/td>"
    			
    			
    		textoHTML += "			<td style='text-align:center'><button type='button' onclick='decrementaExistencias(\""+data.listaRosales[elm].nombreRosal+"\", "+data.listaRosales[elm].idRosal+")' ";
    		textoHTML += "			class='btn btn-danger btn-xs' style='width:40px; height:30px'>"
    		textoHTML += "			<span class='glyphicon glyphicon-minus-sign'></span></button><\/td>"
    			
    		textoHTML += "			<td style='text-align:center'><button type='button' onclick='actualizaExistencias(\""+data.listaRosales[elm].nombreRosal+"\", "+data.listaRosales[elm].idRosal+")' ";
    		textoHTML += "			class='btn btn-primary btn-xs' style='width:40px; height:30px'>"
    		textoHTML += "			<span class='glyphicon glyphicon-refresh'></span></button><\/td>"
    			
    			
    		textoHTML += "		<\/tr>";
    		
    		}
    		
    		textoHTML += "	<\/tbody>";
    		textoHTML += "<\/table>";
    			
			document.getElementById("existenciasContent").innerHTML=textoHTML;
			
			$('#mitabla').DataTable( {
		        "language": {
		           
		        	"url": "bootstrap/js/spanish.json"
		        	// "url": "//cdn.datatables.net/plug-ins/1.10.12/i18n/Spanish.json"
		        },
		        "order": [[ 1, "asc" ]]
		    } );	
		}
    	else{
    		
    		if(data.errorConexion){
    			
    			alertaConexion()	
    		}
    		else{
    			
    			listaVacia();
    		}	
    	}
    });		
}

//Funcion que muestra ventana que informa de la falta de rosales registrados.
function listaVacia(){
	
	sinDatos();
	
	var mensaje="<h2 style='color: #e70c06 ; text-align:center'><p>ERROR</p> </h2>";
	mensaje+="<p style='color: #e70c06; text-align:center; font-size:20px'>"
	mensaje+='Actualmente no hay rosales registrados.</p>'	
	
	var textoHTML = '<div class="modal fade" id="mostrarmodal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">'
	textoHTML+='<div class="modal-dialog"><div class="modal-content"><div class="modal-header" style="text-align:center; background-color:#222; color:#FFF"><h2>INFORMACION</h2></div>'
	textoHTML+='<div class="modal-body">'+mensaje+'</div><div class="modal-footer">'  
	textoHTML+='<a href="#" id="closeModal" data-dismiss="modal" class="btn btn-danger">Salir</a>' 
	textoHTML+='</div></div></div></div>'    
    
	document.getElementById("modalDatos").innerHTML=textoHTML;
	$("#mostrarmodal").modal("show");	
}

//funcion que informa de la no existencia de rosales.
function sinDatos(){
	
	var textoHTML="<h1 style='text-align:center'>Actualmente no hay rosales registrados</h1>";
	
	document.getElementById("existenciasContent").innerHTML=textoHTML;
	
}

//Funcion que muestra ventana de error en caso de servidor mysql detenido o no haber rosales registrados.
function alertaConexion(){
	
	var mensaje="<h2 style='color: #e70c06 ; text-align:center'><p>ERROR</p> </h2>";
	mensaje+="<p style='color: #e70c06; text-align:center; font-size:20px'>Posible error de conexion a base de datos. "
	mensaje+='Si el error persiste contacte inmediatamente con el administrador.</p>'	
	
	var textoHTML = '<div class="modal fade" id="mostrarmodal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">'
	textoHTML+='<div class="modal-dialog"><div class="modal-content"><div class="modal-header" style="text-align:center; background-color:#222; color:#FFF"><h2>INFORMACION</h2></div>'
	textoHTML+='<div class="modal-body">'+mensaje+'</div><div class="modal-footer">'  
	textoHTML+='<a href="errorSistema" id="closeModal" class="btn btn-danger">Salir</a>' 
	textoHTML+='</div></div></div></div>'    
    
	document.getElementById("modalDatos").innerHTML=textoHTML;
	$("#mostrarmodal").modal("show");
	
	
}

//Funcion que muestra la ventana modal de incremento de existencias de un rosal.
function incrementaExistencias(nombreRosal, idRosal){
	
	
	var textoHTML ='<div class="modal fade" id="mostrarmodal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">'
		textoHTML+=' <div class="modal-dialog" role="document">'
		textoHTML+='<div class="modal-content">'
		textoHTML+=''
		textoHTML+='<div class="modal-header" '	
		textoHTML+='style="text-align:center; background-color:#222; color:#FFF"><h3>Existencias '+nombreRosal+'</h3></div>'
		textoHTML+=''
		textoHTML+='<div class="modal-body">'
		textoHTML+='<div style="text-align:center; font-size:25px">'   
		textoHTML+='Inserte el numero de unidades.'
		textoHTML+='<form id="formAumentaExistencia" class="form-horizontal" style="margin:20px">'
		textoHTML+='<input type="hidden" id="idRosal" name="idRosal" value="'+idRosal+'">'
		textoHTML+='<input type="text" id="cantidad" name="cantidad" style="text-align:center" required autofocus>'
		textoHTML+='<div  id="alertExistencias" class="alert alert-danger" role="alert"style="text-align:center; margin-top:20px; font-size:20px; visibility: hidden"></div>'
		textoHTML+=	'</form>'	
		textoHTML+=	'</div>'
		textoHTML+='<div class="modal-footer">'
		textoHTML+='<a href="#" id="closeModal" data-dismiss="modal" class="btn btn-danger">Cancelar</a>' 
		textoHTML+=	'<button type="button" onclick="validaIncrementaExistencias()" class="btn btn-success">Aceptar</button>'
		textoHTML+='</div></div></div>'
	
	
		document.getElementById("modalDatos").innerHTML=textoHTML;
		$("#mostrarmodal").modal("show");
		
}


//Funcion que valida el valor introducico para el incremento de existencias de un rosal.
function validaIncrementaExistencias(){
	
	var cantidad =$('#cantidad').val();
	var expReg = /^\d*$/; 
	
	if (cantidad==""){
		
		document.getElementById("alertExistencias").style.visibility="visible";
		$("#alertExistencias").text("Error. Inserte un valor.");
		$("#cantidad").focus();
		$("#cantidad").select();
	}
	else{
		
		if ( !expReg.test(cantidad)) {    
			
			document.getElementById("alertExistencias").style.visibility="visible";
			$("#alertExistencias").text("Por favor. Inserte un valor correcto.");
			$("#cantidad").focus();
			$("#cantidad").select();
		}
		else {
				
				procesaIncrementaExistencias();
		}	
	}	
}


//Funcion que incrementa las existencias de un rosal.
function procesaIncrementaExistencias(){
	
	var formData = new FormData(document.getElementById("formAumentaExistencia"));

	$.ajax({
        url: "aumentaExistencia",
        type: "POST",
        data: formData,
        processData: false,
        contentType: false,
        success:function(data){
        	
        	if (data.existRosal){
        		document.getElementById("alertExistencias").style.visibility="hidden"
        		$("#closeModal").click();
        		actualizaExistenciasMessage(data.message);
        	}
        	else{
        		document.getElementById("alertExistencias").style.visibility="visible";
        		$("#alertExistencias").text(data.message);
        	}
        }
    })	
}

//Funcion que muestra la ventana modal con el mensaje de la operacion realizada.
function actualizaExistenciasMessage(message){
	
	listaExistencias()
	var textoHTML ='<div class="modal fade" id="mostrarmodal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">'
		textoHTML+=' <div class="modal-dialog" role="document">'
		textoHTML+='<div class="modal-content">'
		textoHTML+='<div class="modal-body">'		
		textoHTML+='<div  id="alertExistencias" class="alert alert-success" role="alert"style="text-align:center;'			
		textoHTML+=	'margin-top:20px; font-size:20px">'+message+'</div>'
		textoHTML+='<div class="modal-footer">'
		textoHTML+='<a href="#" id="closeModal" data-dismiss="modal" class="btn btn-success">Aceptar</a>' 
		textoHTML+='</div></div></div>'
	
	
		document.getElementById("modalDatos").innerHTML=textoHTML;
		$("#mostrarmodal").modal("show");
	
}


//Funcion que muestra la ventana modal de decremento de existencias de un rosal.
function decrementaExistencias(nombreRosal, idRosal){
	
	
	var textoHTML ='<div class="modal fade" id="mostrarmodal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">'
		textoHTML+=' <div class="modal-dialog" role="document">'
		textoHTML+='<div class="modal-content">'
		textoHTML+=''
		textoHTML+='<div class="modal-header" '	
		textoHTML+='style="text-align:center; background-color:#222; color:#FFF"><h3>Existencias '+nombreRosal+'</h3></div>'
		textoHTML+=''
		textoHTML+='<div class="modal-body">'
		textoHTML+='<div style="text-align:center; font-size:25px">'   
		textoHTML+='Inserte el numero de unidades.'
		textoHTML+='<form id="formAumentaExistencia" class="form-horizontal" style="margin:20px">'
		textoHTML+='<input type="hidden" id="idRosal" name="idRosal" value="'+idRosal+'">'
		textoHTML+='<input type="text" id="cantidad" name="cantidad" style="text-align:center; autofocus:autofocus">'
		textoHTML+='<div  id="alertExistencias" class="alert alert-danger" role="alert"style="text-align:center; margin-top:20px; font-size:20px; visibility: hidden"></div>'
		textoHTML+=	'</form>'	
		textoHTML+=	'</div>'
		textoHTML+='<div class="modal-footer">'
		textoHTML+='<a href="#" id="closeModal" data-dismiss="modal" class="btn btn-danger">Cancelar</a>' 
		textoHTML+=	'<button type="button" onclick="validaDecrementaExistencias()" class="btn btn-success">Aceptar</button>'
		textoHTML+='</div></div></div>'
	
	
		document.getElementById("modalDatos").innerHTML=textoHTML;
		$("#mostrarmodal").modal("show");
		
}

//Funcion que valida el valor introducico para el decremento de existencias de un rosal.
function validaDecrementaExistencias(){
	
	var cantidad =$('#cantidad').val();
	var expReg = /^\d*$/; 
	
	if (cantidad==""){
		
		document.getElementById("alertExistencias").style.visibility="visible";
		$("#alertExistencias").text("Error. Inserte un valor.");
		$("#cantidad").focus();
		$("#cantidad").select();
	}
	else{
		
		if ( !expReg.test(cantidad)) {    
			
			document.getElementById("alertExistencias").style.visibility="visible";
			$("#alertExistencias").text("Por favor. Inserte un valor correcto.");
			$("#cantidad").focus();
			$("#cantidad").select();
		}
		else {
				
				procesaDecrementaExistencias();
		}	
	}	
}

//Funcion que incrementa las existencias de un rosal.
function procesaDecrementaExistencias(){
	
	var formData = new FormData(document.getElementById("formAumentaExistencia"));

	$.ajax({
        url: "reduceExistencia",
        type: "POST",
        data: formData,
        processData: false,
        contentType: false,
        success:function(data){
        	
        	if (data.existRosal){
        		document.getElementById("alertExistencias").style.visibility="hidden"
        		$("#closeModal").click();
        		actualizaExistenciasMessage(data.message);
        	}
        	else{
        		document.getElementById("alertExistencias").style.visibility="visible";
        		$("#alertExistencias").text(data.message);
        	}
        }
    })	
}

//Funcion que muestra la ventana modal con el formulario de actualizacion de existencias de un rosal.
function actualizaExistencias(nombreRosal, idRosal){
	
	
	var textoHTML ='<div class="modal fade" id="mostrarmodal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">'
		textoHTML+=' <div class="modal-dialog" role="document">'
		textoHTML+='<div class="modal-content">'
		textoHTML+=''
		textoHTML+='<div class="modal-header" '	
		textoHTML+='style="text-align:center; background-color:#222; color:#FFF"><h3>Existencias '+nombreRosal+'</h3></div>'
		textoHTML+=''
		textoHTML+='<div class="modal-body">'
		textoHTML+='<div style="text-align:center; font-size:25px">'   
		textoHTML+='Inserte el numero de unidades.'
		textoHTML+='<form id="formAumentaExistencia" class="form-horizontal" style="margin:20px">'
		textoHTML+='<input type="hidden" id="idRosal" name="idRosal" value="'+idRosal+'">'
		textoHTML+='<input type="text" id="cantidad" name="cantidad" style="text-align:center; autofocus:autofocus">'
		textoHTML+='<div  id="alertExistencias" class="alert alert-danger" role="alert"style="text-align:center; margin-top:20px; font-size:20px; visibility: hidden"></div>'
		textoHTML+=	'</form>'	
		textoHTML+=	'</div>'
		textoHTML+='<div class="modal-footer">'
		textoHTML+='<a href="#" id="closeModal" data-dismiss="modal" class="btn btn-danger">Cancelar</a>' 
		textoHTML+=	'<button type="button" onclick="validaActualizaExistencias()" class="btn btn-success">Aceptar</button>'
		textoHTML+='</div></div></div>'
	
	
		document.getElementById("modalDatos").innerHTML=textoHTML;
		$("#mostrarmodal").modal("show");
		
}

//Funcion que valida el valor introducido para la actualizacion de existencias de un rosal.
function validaActualizaExistencias(){
	
	var cantidad =$('#cantidad').val();
	var expReg = /^\d*$/; 
	
	if (cantidad==""){
		
		document.getElementById("alertExistencias").style.visibility="visible";
		$("#alertExistencias").text("Error. Inserte un valor.");
		$("#cantidad").focus();
		$("#cantidad").select();
	}
	else{
		
		if ( !expReg.test(cantidad)) {    
			
			document.getElementById("alertExistencias").style.visibility="visible";
			$("#alertExistencias").text("Por favor. Inserte un valor correcto.");
			$("#cantidad").focus();
			$("#cantidad").select();
		}
		else {
				
				procesaActualizaExistencias();
		}	
	}	
}

//Funcion que incrementa las existencias de un rosal.
function procesaActualizaExistencias(){
	
	var formData = new FormData(document.getElementById("formAumentaExistencia"));

	$.ajax({
        url: "actualizaExistencia",
        type: "POST",
        data: formData,
        processData: false,
        contentType: false,
        success:function(data){
        	
        	if (data.existRosal){
        		document.getElementById("alertExistencias").style.visibility="hidden"
        		$("#closeModal").click();
        		actualizaExistenciasMessage(data.message);
        	}
        	else{
        		document.getElementById("alertExistencias").style.visibility="visible";
        		$("#alertExistencias").text(data.message);
        	}
        }
    })	
}

