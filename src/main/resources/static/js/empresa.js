//Funcion que recupera los datos de la empresa de la base de datos
function datosEmpresa(){
	
	$.ajax({
        url: "dameDatosEmpresa"   
    }).then(function(data) {
    		
    	if (data.existEmpresa){
    		
    		muestraDatosEmpresa(data);
    	}
    	else{
    		//muestraDatosEmpresa(data);
    	}
    });		
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
		textoHTML+= '<div class="col-sm-10"><input type="text" class="form-control" value="'+data.empresa.identificador+'" id="identificador" name="identificador" maxlength="50"></div></div>'
	
		textoHTML+= '<div class="form-group"><label for="ruta" class="col-sm-2 control-label">Imagen</label>'
		textoHTML+= '<div class="col-sm-10"><input type="file" class="file" id="img" name="img" ></div></div></form>'
		   
		textoHTML+='<div class="alert alert-danger" role="alert" id="createRosalMessage" style="text-align:center; font-size:20px; visibility: hidden"></div>'
		textoHTML+='<div style="text-align:right"><button onclick="actualizaEmpresa()" '
		textoHTML+='class="btn btn-success" style="width:150px; margin:10px">Actualizar</button></div></div>'
		
		document.getElementById("empresaContent").innerHTML=textoHTML;
	
}

//Funcion que valida los datos del formulario de datos de empresa.
function validaDatosEmpresa(){
	
	
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
        		
        		muestraMensaje(data.message)
        		muestraDatosEmpresa(data);
        	}
        	else{
        		muestraMensaje(data.message)
        	}
        }
    })
}