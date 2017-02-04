/**
 * 
 */
function valida(){
	
	var message = document.getElementById("m").value;
	if (message=="null"){	
		document.getElementById("mensaje").style.visibility="hidden";
	}
	else{
		document.getElementById("mensaje").style.visibility="visible";
	}
}

function validaUsuario(){	
	
	var user = document.getElementById("usuario").value;
	var rol = document.getElementById("rol").value;
	if (user==""){					
			$('#logout').submit();	
	}
	else{		
				
		if (rol=="2"){		
			document.getElementById("admin").style.display="none";
		}
	}
	
}
function invalidaUser(){
		
	$('a').css("display", "none");	
}

function loadAccessDenied(){
	
	$('#accessdenied').submit();
	
	

}

function muestraUsuario(){
	
	$.ajax({	
		url:"dameUsuarios",	
	}).then( function(data){
			procesaUsuario2(data);
	})
}






