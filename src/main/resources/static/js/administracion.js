/**
 * Procesos menu administracion
 */

//Proceso que muestra los usuarios registrados

function listaUsuarios(){
	
	$.ajax({
        url: "dameUsuarios"
    }).then(function(data) {
    
    	if (data.length>0){
				
    		var textoHTML="";
    		textoHTML += "<div>";
    		textoHTML += "";
    		textoHTML += "<table id=\"mitabla\" class=\"table  table-hover table-striped table-condensed table-responsive\" cellspacing=\"0\" width=\"100%\">";
    		textoHTML += "	<thead>";
    		textoHTML += "		<tr style='text-align:center; background-color:#222; color:#FFF;'>";
    		textoHTML += "			<th>Nombre<\/th>";
    		textoHTML += "			<th>Apellidos<\/th>";
    		textoHTML += "			<th>Email<\/th>";
    		textoHTML += "			<th>Login<\/th>";
    		textoHTML += "			<th>Clave<\/th>";
    		textoHTML += "			<th>Rol<\/th>";
    		textoHTML += "			<th>Eliminar<\/th>";
    		textoHTML += "			<th>Modificar<\/th>";
    		textoHTML += "		<\/tr>";
    		textoHTML += "	<\/thead>";
    		textoHTML += "	<tbody>";
    		
    		for(var elm = 0;elm < data.length;elm++){
    		
    		textoHTML += "		<tr>";
    		textoHTML += "			<td>"+data[elm].name+"<\/td>";
    		textoHTML += "			<td>"+data[elm].surname+"<\/td>";
    		textoHTML += "			<td>"+data[elm].email+"<\/td>";
    		textoHTML += "			<td>"+data[elm].login+"<\/td>";
    		textoHTML += "			<td>"+data[elm].password+"<\/td>";
    		textoHTML += "			<td>"+data[elm].rol.rolName+"<\/td>";
    				
    		textoHTML += "			<td><button type='button' onclick='eliminaUsuario(\""+data[elm].login+"\", "+data[elm].idUsuario+")' ";
    		textoHTML += "			class='btn btn-danger btn-xs' style='width:40px; height:30px'><span class='glyphicon glyphicon-trash'></span></button><\/td>"
    		
    		textoHTML += "			<td><button type='button' onclick='modificaUsuario(\""+data[elm].idUsuario+"\", "
			textoHTML += "			\""+data[elm].name+"\", \""+data[elm].surname+"\", \""+data[elm].email+"\", \""+data[elm].login+"\", \""+data[elm].password+"\", \""+data[elm].rol.idRol+"\")'"
			textoHTML += "			class='btn btn-primary btn-xs' style='width:40px; height:30px'><span class='glyphicon glyphicon-refresh'></span></button></td>"
    		
    		textoHTML += "		<\/tr>";
    		
    		}
    		
    		textoHTML += "	<\/tbody>";
    		textoHTML += "<\/table>";
			
			document.getElementById("usuarioContent").innerHTML=textoHTML;
			
			$('#mitabla').DataTable( {
		        "language": {
		           
		        	"url": "bootstrap/js/spanish.json"
		        	// "url": "//cdn.datatables.net/plug-ins/1.10.12/i18n/Spanish.json"
		        }   
		    } );		
		}
    });		
}


// Proceso que muestra el modal con el formulario para modificar un usuario 

function modificaUsuario(idUsuario, name, surname, email, login, password, rol){
	
	var textoHTML ='<div class="modal fade" id="mostrarmodal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">'
		textoHTML+='<div class="modal-dialog"><div class="modal-content"><div class="modal-header" '	
		textoHTML+='style="text-align:center; background-color:#09F; color:#FFF"><h2>Modificar usuario</h2></div><div class="modal-body">'
			
		textoHTML+='<form action="insertUser" id="formUpdateUser" class="form-horizontal" style="margin:20px">'
		textoHTML+='<input type="hidden" id="idUsuario" name="idUsuario" value="'+idUsuario+'">'
				
		textoHTML+='<div class="form-group"><label for="name" class="col-sm-2 control-label">Nombre</label>'
		textoHTML+='<div class="col-sm-10"><input type="text" class="form-control" id="name" name="name" value="'+name+'" maxlength="40"></div></div>'        
				
		textoHTML+='<div class="form-group"><label for="surname" class="col-sm-2 control-label">Apellidos</label>'
		textoHTML+='<div class="col-sm-10"><input type="text" class="form-control" id="surname" name="surname" value="'+surname+'"></div></div>'
				
		textoHTML+='<div class="form-group"><label for="email" class="col-sm-2 control-label">Email</label>'
		textoHTML+='<div class="col-sm-10"><input type="email" class="form-control" id="email" name="email" value="'+email+'"></div></div>'
				
		textoHTML+='<div class="form-group"><label for="login" class="col-sm-2 control-label">Login</label>'
		textoHTML+='<div class="col-sm-10"><input type="text" class="form-control" id="login" name="login" value="'+login+'"></div></div>'
				
		textoHTML+='<div class="form-group"><label for="password" class="col-sm-2 control-label">Clave</label>'
		textoHTML+='<div class="col-sm-10"><input type="password" class="form-control" id="password" name="password" value="'+password+'"></div></div>'
		
		if (rol==1){
		
			textoHTML+='<div class="form-group"><label for="idRol" class="col-sm-2 control-label">Rol</label>'
			textoHTML+='<div class="col-sm-10"><select class="form-control" id="idRol" name="idRol">'      
			textoHTML+='<option value="1">Administrador</option><option value="2">Usuario</option>'   
			textoHTML+='</select></div></div>'	
		}
		else{		
			textoHTML+='<div class="form-group"><label for="idRol" class="col-sm-2 control-label">Rol</label>'
			textoHTML+='<div class="col-sm-10"><select class="form-control" id="idRol" name="idRol">'      
			textoHTML+='<option value="2">Usuario</option><option value="1">Administrador</option>'   
			textoHTML+='</select></div></div>'	
		}
	
		textoHTML+='</form>' 
		textoHTML+='<div class="alert alert-danger" role="alert" id="updateMessage" style="text-align:center; font-size:20px; visibility: hidden"></div>'
		textoHTML+='</div>'
		textoHTML+='<div class="modal-footer">'
		textoHTML+='<a href="#" id="closeModal" data-dismiss="modal" class="btn btn-danger">Cancelar</a>'     
		textoHTML+=	'<button type="button" onclick="validaModificaUsuario()" class="btn btn-success">Aceptar</button>'
		textoHTML+='</div></div></div></div>'    
	    
	document.getElementById("modalDatos").innerHTML=textoHTML;
	$("#mostrarmodal").modal("show");
}


// Funcion que valida los datos del formulario de modificacion de un usuario.

function validaModificaUsuario(){
	
	var name = $("#name").val();
	var surname = $("#surname").val();
	var email = $("#email").val();
	var login = $("#login").val();
	var password = $("#password").val();
	
	if (name==""){
		document.getElementById("updateMessage").style.visibility="visible";
		$("#updateMessage").text("Nombre de usuario obligatorio");
		$("#name").focus();
		$("#name").select();		
	}
	else{
		if (surname==""){
			document.getElementById("updateMessage").style.visibility="visible";
			$("#updateMessage").text("Apellidos de usuario obligatorios");
			$("#surname").focus();
			$("#surname").select();		
		}
		else{
			if (email==""){
				document.getElementById("updateMessage").style.visibility="visible";
				$("#updateMessage").text("Email de usuario obligatorio");
				$("#email").focus();
				$("#email").select();
			}
			else{
				
				var expReg = /^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,3})$/;
				
				if (expReg.test(email)){
					
					if (login==""){
						document.getElementById("updateMessage").style.visibility="visible";
						$("#updateMessage").text("Login de usuario obligatorio");
						$("#login").focus();
						$("#login").select();
					}
					else{
						if (password==""){
							document.getElementById("updateMessage").style.visibility="visible";
							$("#updateMessage").text("Clave de usuario obligatoria");
							$("#password").focus();
							$("#password").select();
						}
						else{					
							procesaModificaUsuario();			
						}
					}
				}
				else{
					document.getElementById("updateMessage").style.visibility="visible";
					$("#updateMessage").text("Formato de email incorrecto.");
					$("#email").focus();
					$("#email").select();	
				}				
			}
		}
	}
}

// Funcion que modifica un usuario

function procesaModificaUsuario(){
	
	var formulario = $('#formUpdateUser');
	
	$.ajax({
        url: "actualizaUsuario",
        data:$(formulario).serialize()
    }).then(function(data) {
    		
    	if (data.userExist){
    		var message = data.message;
    		processMessage(message);
    	}
    	else{
    		document.getElementById("updateMessage").style.visibility="visible";
    		$("#updateMessage").text(data.message);
    		$("#login").focus();
    		$("#login").select();
    	}
    });		
}


// Mensaje que muestra informacion sobre un proceso.

function processMessage(message){
	
	$("#closeModal").click();
	
	var textoHTML ='<div class="modal fade" id="mostrarmodal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">'
		textoHTML+='<div class="modal-dialog"><div class="modal-content"><div class="modal-header" '	
		textoHTML+='style="text-align:center; background-color:#09F; color:#FFF"><h2>Gestion de usuarios</h2></div><div class="modal-body">'
			
		textoHTML+='<div class="progress">'
		textoHTML+='<div id="bar" class="progress-bar progress-bar-success progress-bar-striped active" role="progressbar"'   
		textoHTML+='aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%">'
		textoHTML+='<span id="porcBar">0% Completado</span></div></div>'
	
		textoHTML+='<div class="alert alert-success" role="alert" id="alertMessage" '
		textoHTML+='style="text-align:center; font-size:20px; visibility: hidden"></div></div>'
		textoHTML+='<div class="modal-footer">'
		textoHTML+=	'<button id="enviar" type="button" onclick="listaUsuarios()" class="btn btn-success" data-dismiss="modal">Aceptar</button>'    
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

// Funcion que muestra el modal con el formulario de registro de usuarios.

function registraUsuarios(){
	
	var textoHTML='<div class="panel panel-primary" style="margin-left:auto; margin-right:auto; margin-bottom:; padding:10; max-width:500px">'
	textoHTML+= '<div class="panel-heading" style="text-align:center"><h3>Registro de usuarios</h3></div>'
	textoHTML+='<form action="insertUser" id="formInsertUser" class="form-horizontal" style="margin:20px">'
	
	textoHTML+=		'<div class="form-group"><label for="name" class="col-sm-2 control-label">Nombre</label>'
	textoHTML+=       	'<div class="col-sm-10"><input type="text" class="form-control" id="name" name="name" maxlength="40"></div></div>'        
	
	textoHTML+=		'<div class="form-group"><label for="surname" class="col-sm-2 control-label">Apellidos</label>'
	textoHTML+=      	'<div class="col-sm-10"><input type="text" class="form-control" id="surname" name="surname" ></div></div>'
	
	textoHTML+=		'<div class="form-group"><label for="email" class="col-sm-2 control-label">Email</label>'
	textoHTML+=  		'<div class="col-sm-10"><input type="email" class="form-control" id="email" name="email" ></div></div>'
	
	textoHTML+=		'<div class="form-group"><label for="login" class="col-sm-2 control-label">Login</label>'
	textoHTML+=      	'<div class="col-sm-10"><input type="text" class="form-control" id="login" name="login" ></div></div>'
	
	textoHTML+= 	'<div class="form-group"><label for="password" class="col-sm-2 control-label">Clave</label>'
	textoHTML+=        	'<div class="col-sm-10"><input type="password" class="form-control" id="password" name="password" ></div></div>'
	
	textoHTML+= 	'<div class="form-group"><label for="idRol" class="col-sm-2 control-label">Rol</label>'
	textoHTML+=			'<div class="col-sm-10"><select class="form-control" id="idRol" name="idRol">'      
	textoHTML+=			'<option value="2">Usuario</option><option value="1">Administrador</option>'   
	textoHTML+=			'</select></div></div></form>'
	      
	textoHTML+='<div class="alert alert-danger" role="alert" id="createMessage" style="text-align:center; font-size:20px; visibility: hidden"></div>'
	textoHTML+='<div style="text-align:right"><button onclick="validaCreaUsuario()" class="btn btn-success" style="width:150px; margin:10px">Registrar</button></div></div>'
		
	document.getElementById("usuarioContent").innerHTML=textoHTML;
}


// Funcion que valida los datos del formulario de creacion de usuarios.

function validaCreaUsuario(){
	
	var name = $("#name").val();
	var surname = $("#surname").val();
	var email = $("#email").val();
	var login = $("#login").val();
	var password = $("#password").val();
	
	if (name==""){
		document.getElementById("createMessage").style.visibility="visible";
		$("#createMessage").text("Nombre de usuario obligatorio");
		$("#name").focus();
		$("#name").select();		
	}
	else{
		if (surname==""){
			document.getElementById("createMessage").style.visibility="visible";
			$("#createMessage").text("Apellidos de usuario obligatorios");
			$("#surname").focus();
			$("#surname").select();		
		}
		else{
			if (email==""){
				document.getElementById("createMessage").style.visibility="visible";
				$("#createMessage").text("Email de usuario obligatorio");
				$("#email").focus();
				$("#email").select();
			}
			else{
				
				var expReg = /^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,3})$/;
				
				if (expReg.test(email)){
					
					if (login==""){
						document.getElementById("createMessage").style.visibility="visible";
						$("#createMessage").text("Login de usuario obligatorio");
						$("#login").focus();
						$("#login").select();
					}
					else{
						if (password==""){
							document.getElementById("createMessage").style.visibility="visible";
							$("#createMessage").text("Clave de usuario obligatoria");
							$("#password").focus();
							$("#password").select();
						}
						else{					
							procesaCreaUsuario();			
						}
					}
				}
				else{
					document.getElementById("createMessage").style.visibility="visible";
					$("#createMessage").text("Formato de email incorrecto.");
					$("#email").focus();
					$("#email").select();
					
				}						
			}
		}
	}
}

// Funcion que crea un usuario.

function procesaCreaUsuario(){
	
	var formulario = $('#formInsertUser');
	
	$.ajax({
        url: "creaUsuario",
        data:$(formulario).serialize()
    }).then(function(data) {
    		
    	if (data.userExist){
    		document.getElementById("createMessage").style.visibility="hidden"
    		document.getElementById("formInsertUser").reset();
    		processMessageCreate(data.message);
    	}
    	else{
    		document.getElementById("createMessage").style.visibility="visible";
    		$("#createMessage").text(data.message);
    		$("#login").focus();
    		$("#login").select();
    	}	
    });		
}

// Funcion que muestra el mensaje de usuario creado con exito.
function processMessageCreate(message){
	
	$("#closeModal").click();
	
	var textoHTML ='<div class="modal fade" id="mostrarmodal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">'
		textoHTML+='<div class="modal-dialog"><div class="modal-content"><div class="modal-header" '	
		textoHTML+='style="text-align:center; background-color:#09F; color:#FFF"><h2>Gestion de usuarios</h2></div><div class="modal-body">'
			
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

// Funcion que muestra el modal informando del usuario a eliminar.

function eliminaUsuario(login, id){
	
	var mensaje="<h2 style='color:#006; text-align:center'>El usuario "+login+" será eliminado.</h2>";
	var textoHTML = '<div class="modal fade" id="mostrarmodal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">'
		textoHTML+='<div class="modal-dialog"><div class="modal-content"><div class="modal-header" style="text-align:center; background-color:#09F; color:#FFF"><h2>Gestión de usuarios</h2></div>'
		
		textoHTML+='<div class="modal-body">'+mensaje+'</div><div class="modal-footer">'  
		textoHTML+='<a href="#" id="closeModal" data-dismiss="modal" class="btn btn-danger">Cancelar</a>' 
		textoHTML+=	'<button type="button" onclick="procesaEliminaUsuario(\''+login+'\', '+id+')" class="btn btn-success">Aceptar</button>'
		textoHTML+='</div></div></div></div>'    
	    
	document.getElementById("modalDatos").innerHTML=textoHTML;
	$("#mostrarmodal").modal("show");
}

// Funcion que elimina un usuario.

function procesaEliminaUsuario(login, id){
		
	$.ajax({
        url: "eliminaUsuario?idUsuario="+id+"&login="+login
    }).then(function(data) {
    		
    	if (data.userExist){
    		
    		processMessage(data.message);
    	}
    	else{
    		
    		processMessageError(data.message);  		
    	}
    });		
}

// Funcion que muestra una ventana informando del error al eliminar un usuario.
function processMessageError(message){
	
	$("#closeModal").click();
	
	var textoHTML ='<div class="modal fade" id="mostrarmodal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">'
		textoHTML+='<div class="modal-dialog"><div class="modal-content"><div class="modal-header" '	
		textoHTML+='style="text-align:center; background-color:#09F; color:#FFF"><h2>Gestion de usuarios</h2></div><div class="modal-body">'
			
		textoHTML+='<div class="progress">'
		textoHTML+='<div id="bar" class="progress-bar progress-bar-success progress-bar-striped active" role="progressbar"'   
		textoHTML+='aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%">'
		textoHTML+='<span id="porcBar">0% Completado</span></div></div>'
	
		textoHTML+='<div class="alert alert-danger" role="alert" id="alertMessage" '
		textoHTML+='style="text-align:center; font-size:20px; visibility: hidden"></div></div>'
		textoHTML+='<div class="modal-footer">'
		textoHTML+=	'<button id="enviar" type="button" onclick="listaUsuarios()" class="btn btn-success" data-dismiss="modal">Aceptar</button>'    
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



//Funcion que muestra la lista de usuarios para enviar email.
function listaNotificaciones(){
	
	$.ajax({
        url: "dameUsuarios"
    }).then(function(data) {
    
    	if (data.length>0){
				
    		var textoHTML="";
    		textoHTML += "<div>";
    		textoHTML += "";
    		textoHTML += "<table id=\"mitabla\" class=\"table  table-hover table-striped table-condensed table-responsive\" cellspacing=\"0\" width=\"100%\">";
    		textoHTML += "	<thead>";
    		textoHTML += "		<tr style='text-align:center; background-color:#222; color:#FFF;'>";
    		textoHTML += "			<th>Nombre<\/th>";
    		textoHTML += "			<th>Apellidos<\/th>";
    		textoHTML += "			<th>Email<\/th>";
    		textoHTML += "			<th>Login<\/th>";
    		textoHTML += "			<th>Clave<\/th>";
    		textoHTML += "			<th>Rol<\/th>";
    		textoHTML += "			<th>Enviar<\/th>";
    		textoHTML += "		<\/tr>";
    		textoHTML += "	<\/thead>";
    		textoHTML += "	<tfoot>";
    		textoHTML += "		<tr style='text-align:center; background-color:#222; color:#FFF;'>";
    		textoHTML += "			<th>Nombre<\/th>";
    		textoHTML += "			<th>Apellidos<\/th>";
    		textoHTML += "			<th>Email<\/th>";
    		textoHTML += "			<th>Login<\/th>";
    		textoHTML += "			<th>Clave<\/th>";
    		textoHTML += "			<th>Rol<\/th>";
    		textoHTML += "			<th>Enviar<\/th>";
    		textoHTML += "		<\/tr>";
    		textoHTML += "	<\/tfoot>";
    		 		
    		textoHTML += "	<tbody>";
    		
    		for(var elm = 0;elm < data.length;elm++){
    		
    		textoHTML += "		<tr>";
    		textoHTML += "			<td>"+data[elm].name+"<\/td>";
    		textoHTML += "			<td>"+data[elm].surname+"<\/td>";
    		textoHTML += "			<td>"+data[elm].email+"<\/td>";
    		textoHTML += "			<td>"+data[elm].login+"<\/td>";
    		textoHTML += "			<td>"+data[elm].password+"<\/td>";
    		textoHTML += "			<td>"+data[elm].rol.rolName+"<\/td>";
    		
    		textoHTML+="			<td style='text-align:center'><button type='button' onclick='notificaUsuario(\""+data[elm].email+"\")'"
			textoHTML+="			class='btn btn-info btn-xs' style='width:50px; height:30px'><span class='glyphicon glyphicon-envelope'></span></button></td>"
    		
    		textoHTML += "		<\/tr>";
    		
    		}
    		
    		textoHTML += "	<\/tbody>";
    		textoHTML += "<\/table>";
    	
			document.getElementById("usuarioContent").innerHTML=textoHTML;
			
			$('#mitabla').DataTable( {
		        "language": {
		           
		        	"url": "bootstrap/js/spanish.json"
		        	// "url": "//cdn.datatables.net/plug-ins/1.10.12/i18n/Spanish.json"
		        }  
		    } );	
		}
    });		
}



// Funcion que muestra el formulario para envio de email.
function notificaUsuario(mail){
	
	var textoHTML = '<div class="modal fade" id="mostrarmodal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">'
		textoHTML+='<div class="modal-dialog"><div class="modal-content"><div class="modal-header" style="text-align:center; background-color:#09F; color:#FFF"><h2>Notificacion de usuario</h2></div>'		
		textoHTML+='<div class="modal-body">' 
			
		textoHTML += "<form class=\"form-horizontal\" id=\"formNotificacion\">";
		textoHTML += "	<input type=\"hidden\" value=\"jabernabed.daw2016@gmail.com\" name=\"from\">    ";
		textoHTML += "    <div class=\"form-group\"><label for=\"to\" class=\"col-sm-2 control-label\">Para:<\/label>";
		textoHTML += "	<div class=\"col-sm-10\"><input type=\"text\" class=\"form-control\" value=\""+mail+"\" name=\"to\" id=\"to\" readonly=\"readonly\"><\/div><\/div>";
		textoHTML += "    ";
		textoHTML += "    <div class=\"form-group\"><label for=\"subject\" class=\"col-sm-2 control-label\">Asunto:<\/label>";
		textoHTML += "    <div class=\"col-sm-10\"><input type=\"text\" class=\"form-control\" value=\"\" name=\"subject\" id=\"subject\"><\/div><\/div>";
		textoHTML += "    ";
		textoHTML += "    <div class=\"form-group\"><label for=\"body\" class=\"col-sm-2 control-label\"><\/label>";
		textoHTML += "    <div class=\"col-sm-10\"><textarea name=\"body\" id=\"body\" class=\"form-control\"><\/textarea><\/div><\/div>";
		textoHTML += "<\/form>";
		textoHTML += "";
		
		textoHTML+='<div class="alert alert-danger" role="alert" id="NotificacionMessage" style="text-align:center; font-size:20px; visibility: hidden"></div> '
		textoHTML+='</div><div class="modal-footer">'
		textoHTML+='<a href="#" id="closeModal" data-dismiss="modal" class="btn btn-danger">Cancelar</a>' 
		textoHTML+=	'<button type="button" onclick="validaNotificaUsuario()" class="btn btn-success">Enviar</button>'
		textoHTML+='</div></div></div></div>'    
	    
	document.getElementById("modalDatos").innerHTML=textoHTML;
	$("#mostrarmodal").modal("show");	
}

// Funcion que valida el formulario de envio de email.
function validaNotificaUsuario(){
		
		var to = $("#to").val();
		var subject = $("#subject").val();
		var body = $("#body").val();
		
		if (to==""){
			document.getElementById("NotificacionMessage").style.visibility="visible";
			$("#NotificacionMessage").text("Direccion email de destino obligatoria");
			$("#to").focus();
			$("#to").select();		
		}
		else{
			
			var expReg = /^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,3})$/;
				
			if (expReg.test(to)){
				
				if (subject==""){
					document.getElementById("NotificacionMessage").style.visibility="visible";
					$("#NotificacionMessage").text("Asunto de email obligatorio.");
					$("#subject").focus();
					$("#subject").select();
				}
				else{					
					if (body==""){
						document.getElementById("NotificacionMessage").style.visibility="visible";
						$("#NotificacionMessage").text("Mensaje de email obligatorio.");
						$("#body").focus();
						$("#body").select();				
					}
					else{
						procesaNotificaUsuario();
					}
				}			
			}
			else{
				document.getElementById("NotificacionMessage").style.visibility="visible";
				$("#NotificacionMessage").text("Formato de email incorrecto.");
				$("#to").focus();
				$("#to").select();
			}
		}	
}


//Funcion que envia el email al usuario.
function procesaNotificaUsuario(){
	
	$.ajax({
        url: "notificaUsuario",
        data:$(formNotificacion).serialize()
    }).then(function(data) {
    		
    	if (data.send){ 
    		processMessageNotificacion(data.message)
    	}
    	else{
    		
    		processMessageError(data.message);  		
    	}
    });	
}

//Funcion que muestra el mensaje de email enviado con exito.
function processMessageNotificacion(message){
	
	$("#closeModal").click();
	
	var textoHTML ='<div class="modal fade" id="mostrarmodal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">'
		textoHTML+='<div class="modal-dialog"><div class="modal-content"><div class="modal-header" '	
		textoHTML+='style="text-align:center; background-color:#09F; color:#FFF"><h2>Notificacion de usuarios</h2></div><div class="modal-body">'
			
		textoHTML+='<div class="progress">'
		textoHTML+='<div id="bar" class="progress-bar progress-bar-success progress-bar-striped active" role="progressbar"'   
		textoHTML+='aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%">'
		textoHTML+='<span id="porcBar">0% Completado</span></div></div>'
	
		textoHTML+='<div class="alert alert-success" role="alert" id="NotificacionMessage" '
		textoHTML+='style="text-align:center; font-size:20px; visibility: hidden"></div></div>'
		textoHTML+='<div class="modal-footer">'
		textoHTML+=	'<button id="enviar" type="button" onclick="listaNotificaciones()" class="btn btn-success" data-dismiss="modal">Aceptar</button>'    
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
     document.getElementById("NotificacionMessage").innerHTML=message;
     document.getElementById("NotificacionMessage").style.visibility="visible";
    }
    },100);	
}
	
//Funcion que muestra el mensaje de email cuando no se ha enviado con exito.
function processMessageError(message){
	
	$("#closeModal").click();
	
	var textoHTML ='<div class="modal fade" id="mostrarmodal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">'
		textoHTML+='<div class="modal-dialog"><div class="modal-content"><div class="modal-header" '	
		textoHTML+='style="text-align:center; background-color:#09F; color:#FFF"><h2>Notificacion de usuarios</h2></div><div class="modal-body">'
			
		textoHTML+='<div class="progress">'
		textoHTML+='<div id="bar" class="progress-bar progress-bar-success progress-bar-striped active" role="progressbar"'   
		textoHTML+='aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%">'
		textoHTML+='<span id="porcBar">0% Completado</span></div></div>'
	
		textoHTML+='<div class="alert alert-danger" role="alert" id="NotificacionMessage" '
		textoHTML+='style="text-align:center; font-size:20px; visibility: hidden"></div></div>'
		textoHTML+='<div class="modal-footer">'
		textoHTML+=	'<button id="enviar" type="button" onclick="listaNotificaciones()" class="btn btn-success" data-dismiss="modal">Aceptar</button>'    
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
     document.getElementById("NotificacionMessage").innerHTML=message;
     document.getElementById("NotificacionMessage").style.visibility="visible";
    }
    },100);	
}

	
