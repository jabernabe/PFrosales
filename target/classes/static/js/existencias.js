
function listaExistencias(){
	$.ajax({
        url: "dameRosales"
    }).then(function(data) {
    
    	if (data.length>0){
				
    		var textoHTML="";
    		

    		textoHTML += "<div id='listaRosales'>";
    		textoHTML += "";
    		textoHTML += "<table id=\"mitabla\" class=\"table  table-hover table-striped table-condensed ";
    		textoHTML += "table-responsive\" cellspacing=\"0\" width=\"100%\">"
    		
    		textoHTML += "	<thead>";
    		textoHTML += "		<tr style='text-align:center; background-color:#222; color:#FFF;'>";
    		textoHTML += "			<th>Codigo<\/th>";
    		textoHTML += "			<th>Nombre<\/th>";
    		textoHTML += "			<th>Variedad<\/th>";
    		textoHTML += "			<th>Cantidad<\/th>";
    		textoHTML += "			<th>Entradas<\/th>";
    		textoHTML += "			<th>Salidas<\/th>";
    		textoHTML += "			<th>Actualizar<\/th>";
    		textoHTML += "		<\/tr>";
    		textoHTML += "	<\/thead>";
    		 		
    		textoHTML += "	<tbody>";
    		
    		for(var elm = 0;elm < data.length;elm++){
    		
    		textoHTML += "		<tr>";
    		textoHTML += "			<td>"+data[elm].idRosal+"<\/td>";
    		textoHTML += "			<td>"+data[elm].nombreRosal+"<\/td>";
    		textoHTML += "			<td>"+data[elm].variedad.nombreVariedad+"<\/td>";
    		textoHTML += "			<td style='text-align:right; padding-right:100px'>"+data[elm].cantidad+"<\/td>";
    					
    				
    		textoHTML += "			<td><button type='button' class='btn btn-success btn-xs' style='width:40px; height:30px' ";				
    		textoHTML += "			onclick='incrementaExistencias(\""+data[elm].nombreRosal+"\", "+data[elm].idRosal+")' >"	
    		textoHTML += "			<span class='glyphicon glyphicon-plus-sign'></span></button><\/td>"
    			
    			
    		textoHTML += "			<td><button type='button' onclick='decrementaExistencias(\""+data[elm].nombreRosal+"\", "+data[elm].idRosal+")' ";
    		textoHTML += "			class='btn btn-danger btn-xs' style='width:40px; height:30px'>"
    		textoHTML += "			<span class='glyphicon glyphicon-minus-sign'></span></button><\/td>"
    			
    		textoHTML += "			<td><button type='button' onclick='actualizaExistencias(\""+data[elm].nombreRosal+"\", "+data[elm].idRosal+")' ";
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
    });		
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

