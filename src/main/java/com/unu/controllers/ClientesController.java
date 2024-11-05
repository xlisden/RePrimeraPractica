package com.unu.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import com.unu.beans.Cliente;
import com.unu.model.ClientesModel;

@WebServlet(name = "ClientesController", urlPatterns = { "/ClientesController" })
public class ClientesController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ClientesModel clientesModel = new ClientesModel();
       
    public ClientesController() {
        super();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		try (PrintWriter out = response.getWriter()){
			if(request.getParameter("operacion") == null) {
				listar(request, response);
				return;
			}
			
			String operacion = request.getParameter("operacion");
			switch (operacion) {
			case "listar": 
				listar(request, response);
				break;
			case "nuevo": 
				request.getRequestDispatcher("/clientes/nuevoCliente.jsp").forward(request, response);
				break;
			case "insertar": 
				insertar(request, response);
				break;
			}
			
		} catch (Exception e) {
			System.out.println("processRequest() " + e.getMessage());
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("clientes", clientesModel.listarClientes());
			
			Iterator<Cliente> it = clientesModel.listarClientes().iterator();
			if(it.hasNext()) {
				Cliente cliente = it.next();
			}
			
			request.getRequestDispatcher("/clientes/listarClientes.jsp").forward(request, response);;
		} catch (Exception e) {
			System.out.println("listar() " + e.getMessage());
		}
	}
	
	protected void insertar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Cliente cliente = new Cliente();
			cliente.setNombres(request.getParameter("nombres"));
			cliente.setApellidos(request.getParameter("apellidos"));
			cliente.setDni(request.getParameter("dni"));
			cliente.setFechaNacimiento(request.getParameter("fechaNacimiento"));
			cliente.setDireccion(request.getParameter("direccion"));
			
			if(clientesModel.insertarCliente(cliente) > 0) {
				request.getSession().setAttribute("exito", "cliente insertado");
			}else {
				request.getSession().setAttribute("fracaso", "cliente NO insertado");
			}
			response.sendRedirect(request.getContextPath() + "/ClientesController?operacion=listar");
		} catch (Exception e) {
			System.out.println("insertar() " + e.getMessage());
		}
	}	

}