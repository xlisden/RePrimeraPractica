<%@page import="com.unu.beans.Cliente"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insertar cliente</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>

<% String url = "http://localhost:8080/RePrimeraPractica/"; %>
<%
   Cliente cliente = new Cliente();
   HttpSession sesion = request.getSession();
   if(request.getAttribute("cliente") != null){
	   cliente = (Cliente)request.getAttribute("cliente");
   }
%>
<% int idcliente = cliente.getIdcliente(); %>

<div class="container">
	<br>
	<h3> Editar cliente </h3>
	<div class="form-group">
	<form role="form" action="<%=url%>ClientesController" method="POST">
		<input type="hidden" name="operacion" value="modificar"> <p></p>
		<input type="number" name="idcliente" value="<%=idcliente%>" readonly="readonly" class="form-control"> <p></p>
		<input type="text" name="nombres" value="<%=cliente.getNombres()%>" class="form-control"> <p></p>
		<input type="text" name="apellidos" value="<%=cliente.getApellidos()%>" class="form-control"> <p></p>
		<input type="number" name="dni" value="<%=cliente.getDni()%>" class="form-control"> <p></p>
		<input type="text" name="fechaNacimiento" value="<%=cliente.getFechaNacimiento()%>" class="form-control"> <p></p>
		<input type="text" name="direccion" value="<%=cliente.getDireccion()%>" class="form-control"> <p></p>
		<br>
		<input type="submit" value="Guardar" class="btn btn-primary">
		<a href="<%=url%>ClientesController?operacion=listar" class="btn btn-outline-primary"> Volver </a>
	</form>
	</div>
</div>

</body>
</html>