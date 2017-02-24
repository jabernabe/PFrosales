//Funcion que recupera los datos de la empresa de la base de datos
function datosEmpresa(){
	
	$.ajax({
        url: "dameDatosEmpresa"   
    }).then(function(data) {
    		
    	if (data.existEmpresa){
    		
    		muestraDatosEmpresa(data);
    	}
    	else{
    		alertaConexion();
    	}
    });		
}

//Funcion que muestra ventana de error en caso de servidor mysql detenido y usuarios no registrados.
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

//Funcion que muestra los datos de la empresa.
function muestraDatosEmpresa(data){
	
	var textoHTML='<div class="panel panel-danger" style="margin-left:auto; margin-right:auto; margin-top:15px; margin-bottom:15px; max-width:800px">'
		textoHTML+= '<div class="panel-heading" style="text-align:center; height:45px; background-color:#222; color:#FFF"><h4>Datos de empresa</h4></div>'
		textoHTML+= '<form method="post" id="formulario" class="form-horizontal" style="margin:20px" enctype="multipart/form-data">'
		
		textoHTML+= '<div class="form-group">'
		textoHTML+= '<div class="col-sm-10"><input type="hidden" class="form-control" value="'+data.empresa.idEmpresa+'" id="idEmpresa" name="idEmpresa"></div></div>' 
			
		textoHTML+= '<div class="form-group"><label for="nombreEmpresa" class="col-sm-2 control-label">Nombre</label>'
		textoHTML+= '<div class="col-sm-10"><input type="text" class="form-control" value="'+data.empresa.nombreEmpresa+'"'        
		textoHTML+=' id="nombreEmpresa" name="nombreEmpresa" maxlength="50"></div></div>'
			
			
		textoHTML+= '<div class="form-group"><label for="direccion" class="col-sm-2 control-label">Dirección</label>'
		textoHTML+= '<div class="col-sm-10"><input type="text" class="form-control" value="'+data.empresa.direccion+'" id="direccion" name="direccion" maxlength="50"></div></div>'
			
		textoHTML+= '<div class="form-group"><label for="municipio" class="col-sm-2 control-label">Municipio</label>'
		textoHTML+= '<div class="col-sm-10"><input type="text" class="form-control" value="'+data.empresa.municipio+'" id="municipio" name="municipio" maxlength="50"></div></div>'
			
		textoHTML+= '<div class="form-group"><label for="provincia" class="col-sm-2 control-label">Provincia</label>'
		textoHTML+= '<div class="col-sm-10"><input type="text" class="form-control" value="'+data.empresa.provincia+'" id="provincia" name="provincia" maxlength="50"></div></div>'
					
		textoHTML+= '<div class="form-group"><label for="pais" class="col-sm-2 control-label">Pais</label>'
		textoHTML+= '<div class="col-sm-10"><input type="text" class="form-control" value="'+data.empresa.pais+'" id="pais" name="pais" maxlength="50"></div></div>'
			
		textoHTML+= '<div class="form-group"><label for="cp" class="col-sm-2 control-label">C.P.</label>'
		textoHTML+= '<div class="col-sm-10"><input type="text" class="form-control" value="'+data.empresa.cp+'" id="cp" name="cp" maxlength="50"></div></div>'
					
		textoHTML+= '<div class="form-group"><label for="email" class="col-sm-2 control-label">Email</label>'
		textoHTML+= '<div class="col-sm-10"><input type="text" class="form-control" value="'+data.empresa.email+'" id="email" name="email" maxlength="50"></div></div>'
			
		textoHTML+= '<div class="form-group"><label for="telefonos" class="col-sm-2 control-label">Contacto</label>'
		textoHTML+= '<div class="col-sm-10"><input type="text" class="form-control" value="'+data.empresa.telefonos+'" id="telefonos" name="telefonos" maxlength="50"></div></div>'
			
		textoHTML+= '<div class="form-group"><label for="tipoIden" class="col-sm-2 control-label">Tipo Iden.</label>'
		textoHTML+=	'<div class="col-sm-10"><select class="form-control" id="tipoIden" name="tipoIden">'
			
		if (data.empresa.tipoIden == "NIF")	{
			
			textoHTML+=	'<option value="NIF">NIF</option><option value="CIF">CIF</option>'   
			textoHTML+=	'</select></div></div>'	
		}
		else{
			textoHTML+=	'<option value="CIF">CIF</option><option value="NIF">NIF</option>'   
			textoHTML+=	'</select></div></div>'
		}
			
		textoHTML+= '<div class="form-group"><label for="identificador" class="col-sm-2 control-label">Identificador</label>'
		textoHTML+= '<div class="col-sm-10"><input type="text" title="Tamaño imagen no superior a 1 Mb." class="form-control" '
		textoHTML+= 'value="'+data.empresa.identificador+'" id="identificador" name="identificador" maxlength="50"></div></div>'
		textoHTML+= '<div class="form-group"><label for="ruta" class="col-sm-2 control-label">Imagen</label>'
		textoHTML+= '<div class="col-sm-10"><input type="file" class="file" id="img" name="img" ></div></div></form>'
		   
		textoHTML+='<div class="alert alert-danger" role="alert" id="createEmpresaMessage" style="text-align:center; font-size:20px; visibility: hidden"></div>'
		textoHTML+='<div style="text-align:right"><button onclick="validaDatosEmpresa()" '
		textoHTML+='class="btn btn-success" style="width:150px; margin:10px">Actualizar</button></div></div>'
		
		document.getElementById("empresaContent").innerHTML=textoHTML;
	
}

//Funcion que valida los datos del formulario de datos de empresa.
function validaDatosEmpresa(){
	
	var nombreEmpresa = $("#nombreEmpresa").val();
	var direccion = $("#direccion").val();
	var municipio = $("#municipio").val();
	var provincia = $("#provincia").val();
	var pais = $("#pais").val();
	var cp = $("#cp").val();
	var email = $("#email").val();
	var telefonos = $("#telefonos").val();
	var identificador = $("#identificador").val();
	
	if (nombreEmpresa.length == 0 || /^\s+$/.test(nombreEmpresa)) {
		document.getElementById("createEmpresaMessage").style.visibility="visible";
		$("#createEmpresaMessage").text("Nombre de empresa debe ser cumplimentado.");
		$("#nobreEmpresa").select();
		$("#nombreEmpresa").focus();
	}
	else{
		
		if (direccion.length == 0 || /^\s+$/.test(direccion)) {
			document.getElementById("createEmpresaMessage").style.visibility="visible";
			$("#createEmpresaMessage").text("La direccion de la empresa debe ser cumplimentada.");
			$("#direccion").select();
			$("#direccion").focus();
		}
		else{
			
		
			if (municipio.length == 0 || /^\s+$/.test(municipio)) {
				document.getElementById("createEmpresaMessage").style.visibility="visible";
				$("#createEmpresaMessage").text("El municipio de la empresa debe ser cumplimentado.");
				$("#municipio").select();
				$("#municipio").focus();
			}
			else{
				if (provincia.length == 0 || /^\s+$/.test(provincia)) {
					document.getElementById("createEmpresaMessage").style.visibility="visible";
					$("#createEmpresaMessage").text("La provincia de la empresa debe ser cumplimentada.");
					$("#provincia").select();
					$("#provincia").focus();
				}
				else{
					var expReg = /^\d*$/;
					if (pais.length == 0 || /^\s+$/.test(pais)) {
						document.getElementById("createEmpresaMessage").style.visibility="visible";
						$("#createEmpresaMessage").text("Campo pais debe ser cumplimentado.");
						$("#pais").select();
						$("#pais").focus();
					}
					else{
						if (cp.length == 0 || !expReg.test(cp)) {
							document.getElementById("createEmpresaMessage").style.visibility="visible";
							$("#createEmpresaMessage").text("Campo codigo postal debe ser cumplimentado con valor numerico.");
							$("#cp").select();
							$("#cp").focus();
						}
						else{
							var expReg = /^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,3})$/;
							
							if (expReg.test(email)){
								if (telefonos.length == 0 || /^\s+$/.test(telefonos)) {
									document.getElementById("createEmpresaMessage").style.visibility="visible";
									$("#createEmpresaMessage").text("Campo contacto debe ser cumplimentado.");
									$("#telefonos").select();
									$("#telefonos").focus();
								}
								else{
									if (identificador.length == 0 || /^\s+$/.test(identificador)) {
										document.getElementById("createEmpresaMessage").style.visibility="visible";
										$("#createEmpresaMessage").text("Campo identificador debe ser cumplimentado.");
										$("#identificador").select();
										$("#identificador").focus();
									}
									else{
										var input = document.getElementById('img');
									    var file = input.files[0];
									    
									    if (file == null){
									    	
									    	actualizaEmpresa();
									    }
									    else{
									    
											var sizeByte = file.size;
											var siezekiloByte = parseInt(sizeByte / 1024);
							
											 if(siezekiloByte > 1024){
												document.getElementById("createEmpresaMessage").style.visibility="visible";
												$("#createEmpresaMessage").text("El tamaño supera el limite permitido de 1Mb.");
											 }
											 else{
												 actualizaEmpresa(); 
											 }
									    }
									}
								}
							}
							else{
								document.getElementById("createEmpresaMessage").style.visibility="visible";
								$("#createEmpresaMessage").text("Formato de email incorrecto");
								$("#email").select();
								$("#email").focus();	
							}
						}
					}	
				}			
			}
		}
	}
}

//Funcion que realiza la actualizacion de los datos de la empresa.
function actualizaEmpresa(){
	
	var formData = new FormData(document.getElementById("formulario"));

	$.ajax({
        url: "actualizaDatos",
        type: "POST",
        data: formData,
        processData: false,
        contentType: false,
        success:function(data){
        	
        	if (data.existEmpresa){
        		muestraDatosEmpresa(data);
        		muestraMensaje(data.message)
        		
        	}
        	else{
        		muestraMensaje(data.message)
        	}
        }
    })
}

//Funcion que muestra el mensaje tras la actualizacion de los datos de la empresa.
function muestraMensaje(message){
	
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