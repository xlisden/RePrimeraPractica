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

<div class="container">
	<br>
	<h3> Nuevo cliente </h3>
	<div class="form-group">
	<form role="form" action="<%=url%>ClientesController" method="POST">
		<input type="hidden" name="operacion" value="insertar"> <p></p>
		<input type="text" name="nombres" placeholder="Nombres" class="form-control"> <p></p>
		<input type="text" name="apellidos" placeholder="Apellidos" class="form-control"> <p></p>
		<input type="number" name="dni" placeholder="DNI" class="form-control"> <p></p>
		<input type="text" name="fechaNacimiento" placeholder="yyyy-mm-dd" class="form-control"> <p></p>
		<input type="text" name="direccion" placeholder="Direccion" class="form-control"> <p></p>
		<br>
		<input type="submit" value="Guardar" class="btn btn-primary">
		<a href="<%=url%>ClientesController?operacion=listar" class="btn btn-outline-primary"> Volver </a>
	</form>
	</div>
</div>

</body>
</html>