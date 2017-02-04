// JavaScript Document

function loadAdministracion(){
	
	var url = "loadAdmin";
	var method ="post";
	var datType = "json";
	peticionAjax(url, method, datType);

	alert("peticion enviada");
}

function procesLoadAdmin(data){
	alert("ejecutando funcion de retorno");
	var datos = data;
	$("#contenido1").innerHTML=datos;
}


function peticionAjax(urlPeticion, method){
	
	$.ajax({
        async:true,
        type: method,   
        url: urlPeticion,
        success: function(data){document.getElementById("contenido").innerHTML= <div th:replace="administracion :: administracion"></div>;}
	})
	
}