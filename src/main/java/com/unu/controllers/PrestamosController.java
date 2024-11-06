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
import com.unu.beans.Prestamo;
import com.unu.model.ClientesModel;
import com.unu.model.PrestamosModel;

@WebServlet(name = "PrestamosController", urlPatterns = { "/PrestamosController" })
public class PrestamosController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private PrestamosModel prestamosModel  = new PrestamosModel();
	private ClientesModel clientesModel = new ClientesModel();
       
    public PrestamosController() {
        super();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		try (PrintWriter out = response.getWriter()){
			if(request.getParameter("operacion") == null) {
				return;
			}
			
			String operacion = request.getParameter("operacion");
			switch (operacion) {
			case "listar": 
				listar(request, response);
				break;
			case "nuevo": 
				obtenerCliente(request, response);
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
			int idcliente = Integer.parseInt(request.getParameter("idcliente"));
			request.setAttribute("prestamos", prestamosModel.listarPrestamosPorCliente(idcliente));
			
			Iterator<Prestamo> it = prestamosModel.listarPrestamosPorCliente(idcliente).iterator();
			if(it.hasNext()) {
				Prestamo prestamo = it.next();
			}
			
			request.getRequestDispatcher("/prestamos/listarPrestamos.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println("listar() " + e.getMessage());
		}
	}
	
	protected void listarClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("clientes", clientesModel.listarClientesPorNombres());
			
			Iterator<String> it = clientesModel.listarClientesPorNombres().iterator();
			if(it.hasNext()) {
				String cliente = it.next();
			}
			
			request.getRequestDispatcher("/prestamos/nuevoPrestamo.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println("listarClientes() " + e.getMessage());
		}
	}
	
	protected void obtenerCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String nomCliente = request.getParameter("nomCliente");
			Cliente cliente = clientesModel.listarClientePorNombre(nomCliente);
			if(cliente != null) {
				request.setAttribute("cliente", cliente);
				request.getRequestDispatcher("/prestamos/nuevoPrestamo.jsp").forward(request, response);
			}
		} catch (Exception e) {
			System.out.println("obtenerCliente() " + e.getMessage());System.out.println("abrirConexion() " + e.getMessage());
		}
	}
	
	protected void insertar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Prestamo prestamo = new Prestamo();
			prestamo.setFechaPrestamo(request.getParameter("fechaPrestamo"));
			prestamo.setMonto(Double.parseDouble(request.getParameter("monto")));
			prestamo.setIdcliente(Integer.parseInt(request.getParameter("idcliente")));
			prestamo.setInteres(Integer.parseInt(request.getParameter("interes")));
			prestamo.setNroCuotas(Integer.parseInt(request.getParameter("nroCuotas")));
			int idcliente = Integer.parseInt(request.getParameter("idcliente"));
			
			if(prestamosModel.insertarPrestamo(prestamo) > 0) {
				System.out.println("en insertar prestamo controller");
				request.getSession().setAttribute("exito", "prestamo insertado");
			}else {
				request.getSession().setAttribute("fracaso", "prestamo NO insertado");
			}
			response.sendRedirect(request.getContextPath() + "/PrestamosController?operacion=listar&idcliente=" + idcliente);
		} catch (Exception e) {
			System.out.println("insertar() " + e.getMessage());
		}
	}	
	
}
