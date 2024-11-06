<%@page import="com.unu.beans.Prestamo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listar prestamos por cliente</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>

<% String url = "http://localhost:8080/RePrimeraPractica/"; %>
<% List<Prestamo> prestamos = (List<Prestamo>) request.getAttribute("prestamos"); %>
<% %>

<div class="container">
  <br>
  <a href="<%=url%>PrestamosController?operacion=nuevo&nomCliente=<%=prestamos.getFirst().getCliente()%>" class="btn btn-primary"> Nuevo prestamo </a>
  <br> <p></p>
  <table id="tabla" class="table table-bordered">
  	<thead>
  		<tr>
  			<th> Id prestamo </th>
  			<th> F. Prestamo </th>
  			<th> Monto </th>
  			<th> Cliente </th>
  			<th> Interes </th>
  			<th> Nro cuotas </th>
  			<th> Operaciones </th>
  		</tr>
  	</thead>
  	<tbody>
  		<%
  		if(prestamos != null){
  			for(Prestamo prestamo: prestamos){
		%>
  		<tr>
  			<td><%= prestamo.getIdprestamo() %></td>
  			<td><%= prestamo.getFechaPrestamo() %></td>
  			<td><%= prestamo.getMonto() %></td>
  			<td><%= prestamo.getCliente() %></td>
  			<td><%= prestamo.getInteres() %></td>
  			<td><%= prestamo.getNroCuotas() %></td>
  			<td>
  				<a class="btn btn-outline-warning" > Editar </a>
  				<a class="btn btn-outline-danger" > Eliminar </a>
			</td>
  		</tr>
  		<%	
			}
		}
		%>
  	</tbody>
  </table>
</div>

</body>
</html>