/**
 * 
 */

function muestraUsuario(){
	
	$.ajax({
		async:true,
		type: "POST",
		dataType: "json",
		contentType: "application/json",
		url:"getUsuario,
		data:"null",
		success: procesaUsuario,
		timeout: 4000
	});	
}

function procesaUsuario(data){
	
		var usuario = eval ("("+data+")");
		alert(usuario.login);
		alert(data);
		var textoHTML="<table class='table'>"
			textoHTML+="<tr><th>usuario</th><th>clave</th><th>rol</th><th> </th></tr>"	
			textoHTML+="<tr><td>usuario.login</td><td>usuario.password</td><td>usuario.rol</td></tr>"				
			textoHTML+='</table>'

			document.getElementById("usuarioContent").innerHTML=textoHTML;	
}
