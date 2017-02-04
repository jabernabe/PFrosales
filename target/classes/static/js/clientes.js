
//Funcion que muestra mediante tabla los clientes registrados.
function listaClientes(){
	
	$.ajax({
        url: "dameListaClientes"
    }).then(function(data) {
    
    	if (data.existClientes){
				
    		var textoHTML="";
    		textoHTML += "<div>";
    		textoHTML += "";
    		textoHTML += "<table id=\"mitabla\" class=\"table  table-hover table-striped table-condensed ";
    		textoHTML += "table-responsive\" cellspacing=\"0\" width=\"100%\">"
    		
    		textoHTML += "	<thead>";
    		textoHTML += "		<tr style='text-align:center; background-color:#222; color:#FFF;'>";
    		textoHTML += "			<th>Identificador<\/th>";
    		textoHTML += "			<th>Cliente<\/th>";
    		textoHTML += "			<th>Direccion<\/th>";
    		textoHTML += "			<th>Municipio<\/th>";
    		textoHTML += "			<th>Provincia<\/th>";
    		textoHTML += "			<th>Pais<\/th>";
    		textoHTML += "			<th>C.P<\/th>";
    		textoHTML += "			<th>Tlfno.<\/th>";
    		textoHTML += "			<th>Email<\/th>";
    		textoHTML += "			<th>Eliminar<\/th>";
    		textoHTML += "			<th>Editar<\/th>";
    		textoHTML += "		<\/tr>";
    		textoHTML += "	<\/thead>";
    						
    		textoHTML += "	<tbody>";
    		 
    		for(var elm = 0;elm < data.listaClientes.length;elm++){
    		
    		textoHTML += "		<tr>";
    		textoHTML += "			<td nowrap>"+data.listaClientes[elm].tipoIden+": "+data.listaClientes[elm].identificador+"<\/td>";
    		textoHTML += "			<td nowrap>"+data.listaClientes[elm].nombreCliente+"<\/td>";
    		textoHTML += "			<td nowrap>"+data.listaClientes[elm].direccion+"<\/td>";
    		textoHTML += "			<td nowrap>"+data.listaClientes[elm].municipio+"<\/td>";
    		textoHTML += "			<td nowrap>"+data.listaClientes[elm].provincia+"<\/td>";
    		textoHTML += "			<td nowrap>"+data.listaClientes[elm].pais+"<\/td>";
    		textoHTML += "			<td nowrap>"+data.listaClientes[elm].cp+"<\/td>";
    		textoHTML += "			<td nowrap>"+data.listaClientes[elm].telefonos+"<\/td>";
    		textoHTML += "			<td nowrap>"+data.listaClientes[elm].email+"<\/td>";
    		     			
    		textoHTML += "			<td><button type='button' onclick='eliminaCliente(\""+data.listaClientes[elm].nombreCliente+"\", "+data.listaClientes[elm].idCliente+")' ";
    		textoHTML += "			class='btn btn-danger btn-xs' style='width:40px; height:30px'>"
    		textoHTML += "			<span class='glyphicon glyphicon-trash'></span></button><\/td>"
    			
    		textoHTML += "			<td><button type='button' onclick='editaCliente(\""+data.listaClientes[elm].idCliente+"\", "
    		textoHTML += "          \""+data.listaClientes[elm].nombreCliente+"\", \""+data.listaClientes[elm].direccion+"\", "
    		textoHTML += "			\""+data.listaClientes[elm].municipio+"\", \""+data.listaClientes[elm].provincia+"\", "
    		textoHTML += "			\""+data.listaClientes[elm].pais+"\", \""+data.listaClientes[elm].cp+"\", "
    		textoHTML += "			\""+data.listaClientes[elm].telefonos+"\", \""+data.listaClientes[elm].email+"\", "
    		textoHTML += "			\""+data.listaClientes[elm].observaciones+"\", \""+data.listaClientes[elm].tipoIden+"\", "
    		textoHTML += "			\""+data.listaClientes[elm].identificador+"\")' class='btn btn-primary btn-xs' style='width:40px; height:30px' >";
    		textoHTML += "			<span class='glyphicon glyphicon-refresh'></span></button><\/td>"
    		textoHTML += "		<\/tr>";
    		
    		}
    		
    		textoHTML += "	<\/tbody>";
    		textoHTML += "<\/table>";
    			
			document.getElementById("clientesContent").innerHTML=textoHTML;
			
			$('#mitabla').DataTable( {
		        "language": {
		           
		        	"url": "bootstrap/js/spanish.json"
		        	// "url": "//cdn.datatables.net/plug-ins/1.10.12/i18n/Spanish.json"
		        }
		    } );	
		}
    	else{
    		alert(data.message);
    	}
    });	
	
}

//Funcion que muestra una ventana modal para editar un cliente.
function editaCliente(idCliente, nombre, direccion, municipio, provincia, pais, cp, tel, email, observ, tipoIden, ident){
	
	alert(idCliente+"  "+nombre+" "+ident)
	
	
    var textoHTML ='<div class="modal fade" id="mostrarmodal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">'
    	textoHTML+='<div class="modal-dialog modalClientes"><div class="modal-content"><div class="modal-header" '	
    	textoHTML+='style="text-align:center; background-color:#222; color:#FFF"><h3>Editar Cliente</h3></div><div class="modal-body">'
    				
    	textoHTML+='<form id="formEditaCliente" class="form-horizontal" style="margin:10px">'
    	textoHTML+='<input type="hidden" id="idCliente" name="idCliente" value="'+idCliente+'">'
    	
    	textoHTML+='<div class="form-group"><label for="tipoIden" class="col-sm-2 control-label">Tipo</label>'
        textoHTML+='<div class="col-sm-10"><select class="form-control" id="tipoIden" name="tipoIden">' 		
        if (tipoIden == "DNI"){
        	textoHTML+=	'<option value="DNI" selected>DNI</option><option value="NIF">NIF</option>'	
        }
        else{
        	textoHTML+=	'<option value="CIF" selected>CIF</option><option value="DNI">DNI</option>'	
        }
        textoHTML+='</select></div></div>'	
    			
    	textoHTML+='<div class="form-group"><label for="identificador" class="col-sm-2 control-label">Numero</label>'
    	textoHTML+='<div class="col-sm-10"><input type="text" class="form-control" id="identificador" name="identificador" value="'+ident+'" maxlength="40"></div></div>'        
    	textoHTML+=''
    	textoHTML+='<div class="form-group"><label for="nombreCliente" class="col-sm-2 control-label">Nombre</label>'
    	textoHTML+='<div class="col-sm-10"><input type="text" class="form-control" id="nombreCliente" name="nombreCliente" value="'+nombre+'" maxlength="40"></div></div>'   
    	textoHTML+=''
    	textoHTML+='<div class="form-group"><label for="direccion" class="col-sm-2 control-label">Direccion</label>'
        textoHTML+='<div class="col-sm-10"><input type="text" class="form-control" id="direccion" name="direccion" value="'+direccion+'" maxlength="40"></div></div>'   
        textoHTML+=''
        textoHTML+='<div class="form-group"><label for="municipio" class="col-sm-2 control-label">Municipio</label>'
        textoHTML+='<div class="col-sm-10"><input type="text" class="form-control" id="municipio" name="municipio" value="'+municipio+'" maxlength="40"></div></div>'   
    	textoHTML+=''
    	textoHTML+='<div class="form-group"><label for="provincia" class="col-sm-2 control-label">Provincia</label>'
    	textoHTML+='<div class="col-sm-10"><input type="text" class="form-control" id="provincia" name="provincia" value="'+provincia+'"></div></div>'
    	textoHTML+=''	
    	textoHTML+='<div class="form-group"><label for="pais" class="col-sm-2 control-label">Pais</label>'
    	textoHTML+='<div class="col-sm-10"><input type="text" class="form-control" id="pais" name="pais" value="'+pais+'"></div></div>'
    	textoHTML+=''
    	textoHTML+='<div class="form-group"><label for="cp" class="col-sm-2 control-label">Codigo_postal</label>'
    	textoHTML+='<div class="col-sm-10"><input type="text" class="form-control" id="cp" name="cp" value="'+cp+'"></div></div>'
    	textoHTML+=''
    	textoHTML+='<div class="form-group"><label for="telefonos" class="col-sm-2 control-label">Contacto</label>'
    	textoHTML+='<div class="col-sm-10"><input type="text" class="form-control" id="telefonos" name="telefonos" value="'+tel+'"></div></div>'
    	textoHTML+=''
    	textoHTML+='<div class="form-group"><label for="email" class="col-sm-2 control-label">Email</label>'
    	textoHTML+='<div class="col-sm-10"><input type="text" class="form-control" id="email" name="email" value="'+email+'"></div></div>'
    	textoHTML+=''
    	textoHTML+='<div class="form-group"><label for="observaciones" class="col-sm-2 control-label">Observaciones</label>'
    	textoHTML+='<div class="col-sm-10"><textarea rows="6" cols="50" class="form-control" id="observ" name="observaciones" maxlength="500">'+observ+'</textarea></div></div>'
    	textoHTML+=''
    				   				
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

//Funcion que muestra el formulario de registro de clientes
function registraCliente(){
	
	
var textoHTML='<div class="panel panel-danger" style="margin-left:auto; margin-right:auto; max-width:800px">'
	textoHTML+= '<div class="panel-heading " style="text-align:center; background-color:#222; color:#FFF; height:50px;"><h4>Registro de clientes</h4></div>'
		
	textoHTML+='<form id="formRegistraCliente" class="form-horizontal" style="margin:10px">'
	textoHTML+='<input type="hidden" id="idCliente" name="idCliente" >'
				    	
	textoHTML+='<div class="form-group"><label for="tipoIden" class="col-sm-2 control-label">Tipo</label>'
	textoHTML+='<div class="col-sm-10"><select class="form-control" id="tipoIden" name="tipoIden">' 		
	textoHTML+=	'<option value="CIF" selected>CIF</option><option value="DNI">DNI</option>'	
	textoHTML+='</select></div></div>'	
				    			
	textoHTML+='<div class="form-group"><label for="identificador" class="col-sm-2 control-label">Numero</label>'
	textoHTML+='<div class="col-sm-10"><input type="text" class="form-control" id="identificador" name="identificador"  maxlength="40"></div></div>'        
	textoHTML+=''
	textoHTML+='<div class="form-group"><label for="nombreCliente" class="col-sm-2 control-label">Nombre</label>'
	textoHTML+='<div class="col-sm-10"><input type="text" class="form-control" id="nombreCliente" name="nombreCliente"  maxlength="40"></div></div>'   
	textoHTML+=''
	textoHTML+='<div class="form-group"><label for="direccion" class="col-sm-2 control-label">Direccion</label>'
	textoHTML+='<div class="col-sm-10"><input type="text" class="form-control" id="direccion" name="direccion"  maxlength="40"></div></div>'   
	textoHTML+=''
	textoHTML+='<div class="form-group"><label for="municipio" class="col-sm-2 control-label">Municipio</label>'
	textoHTML+='<div class="col-sm-10"><input type="text" class="form-control" id="municipio" name="municipio"  maxlength="40"></div></div>'   
	textoHTML+=''
	textoHTML+='<div class="form-group"><label for="provincia" class="col-sm-2 control-label">Provincia</label>'
	textoHTML+='<div class="col-sm-10"><input type="text" class="form-control" id="provincia" name="provincia" ></div></div>'
	textoHTML+=''	
	textoHTML+='<div class="form-group"><label for="pais" class="col-sm-2 control-label">Pais</label>'
	textoHTML+='<div class="col-sm-10"><input type="text" class="form-control" id="pais" name="pais" ></div></div>'
	textoHTML+=''
	textoHTML+='<div class="form-group"><label for="cp" class="col-sm-2 control-label">Codigo_postal</label>'
	textoHTML+='<div class="col-sm-10"><input type="text" class="form-control" id="cp" name="cp" ></div></div>'
	textoHTML+=''
	textoHTML+='<div class="form-group"><label for="telefonos" class="col-sm-2 control-label">Contacto</label>'
	textoHTML+='<div class="col-sm-10"><input type="text" class="form-control" id="telefonos" name="telefonos" ></div></div>'
	textoHTML+=''
	textoHTML+='<div class="form-group"><label for="email" class="col-sm-2 control-label">Email</label>'
	textoHTML+='<div class="col-sm-10"><input type="text" class="form-control" id="email" name="email" ></div></div>'
	textoHTML+=''
	textoHTML+='<div class="form-group"><label for="observaciones" class="col-sm-2 control-label">Observaciones</label>'
	textoHTML+='<div class="col-sm-10"><textarea rows="6" cols="50" class="form-control" id="observ" name="observaciones" maxlength="500"></textarea></div></div>'
	textoHTML+=''		    				   				
	textoHTML+='</form>'
		   
	textoHTML+='<div class="alert alert-danger" role="alert" id="mensajeFormCliente" style="text-align:center; font-size:20px; visibility: hidden"></div>'
	textoHTML+='<div style="text-align:right"><button onclick="validaRegistraCliente()" '
	textoHTML+='class="btn btn-success" style="width:150px; margin:10px">Registrar</button></div></div>'
	
	document.getElementById("clientesContent").innerHTML=textoHTML;
		
}

//Funcion que valida los campos del formulario de registro de clientes.
function validaRegistraCliente(){
	
	
	var nombre = $("#nombreCliente").val();
	var direccion = $("#direccion").val();
	var municipio = $("#municipio").val();
	var provincia = $("#provincia").val();
	var pais = $("#pais").val();
	var cp = $("#cp").val();
	var telefonos = $("#telefonos").val();
	var email = $("#email").val();
	var identificador= $("#identificador").val();
	
	if (identificador!==""){
		
		if (nombre!==""){
			
			if (direccion!==""){
				
				if (municipio!==""){
					
					if (provincia!==""){
						
						if (pais!==""){
							
							if (cp!==""){
								
								if (telefonos!==""){
									
									if (email==""){
										
										document.getElementById("mensajeFormCliente").style.visibility="visible";
										$("#mensajeFormCliente").text("Email de cliente obligatorio");
										$("#email").focus();
										$("#email").select();
										
									}
									else{
										var expReg = /^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,3})$/;
										
										if (expReg.test(email)){
											
											procesaRegistroCliente()
										}
										else{
											document.getElementById("mensajeFormCliente").style.visibility="visible";
											$("#mensajeFormCliente").text("Formato de email incorrecto.");
											$("#email").focus();
											$("#email").select();	
										}	
									}
								}
								else{
									document.getElementById("mensajeFormCliente").style.visibility="visible";
									$("#mensajeFormCliente").text("Telefonos de contacto obligatorio");
									$("#telefonos").focus();
									$("#telefonos").select();
								}
							}
							else{
								document.getElementById("mensajeFormCliente").style.visibility="visible";
								$("#mensajeFormCliente").text("Codigo postal obligatorio");
								$("#cp").focus();
								$("#cp").select();
							}
						}
						else{
							document.getElementById("mensajeFormCliente").style.visibility="visible";
							$("#mensajeFormCliente").text("Pais de cliente obligatorio");
							$("#pais").focus();
							$("#pais").select();
						}
					}
					else{
						document.getElementById("mensajeFormCliente").style.visibility="visible";
						$("#mensajeFormCliente").text("Provincia de cliente obligatoria");
						$("#provincia").focus();
						$("#provincia").select();
					}	
				}
				else{
					document.getElementById("mensajeFormCliente").style.visibility="visible";
					$("#mensajeFormCliente").text("Municipio de cliente obligatorio");
					$("#municipio").focus();
					$("#municipio").select();
				}
			}
			else{
				document.getElementById("mensajeFormCliente").style.visibility="visible";
				$("#mensajeFormCliente").text("Direccion de cliente obligatoria");
				$("#direccion").focus();
				$("#direccion").select();
			}		
		}
		else{
			document.getElementById("mensajeFormCliente").style.visibility="visible";
			$("#mensajeFormCliente").text("Nombre de cliente obligatorio");
			$("#nombre").focus();
			$("#nombre").select();
		}	
	}
	else{
		document.getElementById("mensajeFormCliente").style.visibility="visible";
		$("#mensajeFormCliente").text("Número de CIF / NIF obligatorio.");
		$("#identificador").focus();
		$("#identificador").select();
	}
	
}

//Metodo que procesa el registro del nuevo cliente tras la validacion de los campos del formulario.
function procesaRegistroCliente(){
	
	alert("se procede al registro del cliente");
}


