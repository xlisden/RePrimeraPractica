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
			case "editar": 
				obtenerPrestamo(request, response);
				break;
			case "modificar": 
				modificar(request, response);
				break;
			case "eliminar": 
				eliminar(request, response);
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
			request.setAttribute("idcliente", idcliente);
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
	
	protected void obtenerCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			int idcliente = Integer.parseInt(request.getParameter("idcliente"));
			Cliente cliente = clientesModel.listarClientePorId(idcliente);
			
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
			int idcliente = Integer.parseInt(request.getParameter("idcliente"));
			request.setAttribute("idcliente", idcliente);
			Prestamo prestamo = new Prestamo();
			prestamo.setFechaPrestamo(request.getParameter("fechaPrestamo"));
			prestamo.setMonto(Double.parseDouble(request.getParameter("monto")));
			prestamo.setIdcliente(idcliente);
			prestamo.setInteres(Integer.parseInt(request.getParameter("interes")));
			prestamo.setNroCuotas(Integer.parseInt(request.getParameter("nroCuotas")));
			
			if(prestamosModel.insertarPrestamo(prestamo) > 0) {
				request.getSession().setAttribute("exito", "prestamo insertado");
			}else {
				request.getSession().setAttribute("fracaso", "prestamo NO insertado");
			}
			response.sendRedirect(request.getContextPath() + "/PrestamosController?operacion=listar&idcliente=" + idcliente);
		} catch (Exception e) {
			System.out.println("insertar() " + e.getMessage());
		}
	}	

	protected void obtenerPrestamo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			int idprestamo = Integer.parseInt(request.getParameter("idprestamo"));
			Prestamo prestamo = prestamosModel.listarPrestamoPorId(idprestamo);
			
			if(prestamo != null) {
				request.setAttribute("prestamo", prestamo);
				request.getRequestDispatcher("/prestamos/editarPrestamo.jsp").forward(request, response);
			}
		} catch (Exception e) {
			System.out.println("obtenerPrestamo() " + e.getMessage());
		}
	}
	
	protected void modificar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int idcliente = Integer.parseInt(request.getParameter("idcliente"));
			int idprestamo = Integer.parseInt(request.getParameter("idprestamo"));
			request.setAttribute("idcliente", idcliente);
			Prestamo prestamo = new Prestamo();
			prestamo.setIdprestamo(idprestamo);
			prestamo.setFechaPrestamo(request.getParameter("fechaPrestamo"));
			prestamo.setMonto(Double.parseDouble(request.getParameter("monto")));
			prestamo.setIdcliente(idcliente);
			prestamo.setInteres(Integer.parseInt(request.getParameter("interes")));
			prestamo.setNroCuotas(Integer.parseInt(request.getParameter("nroCuotas")));
			
			if(prestamosModel.modificarPrestamo(prestamo) > 0) {
				request.getSession().setAttribute("exito", "prestamo modificado");
			}else {
				request.getSession().setAttribute("fracaso", "prestamo NO modificado");
			}
			response.sendRedirect(request.getContextPath() + "/PrestamosController?operacion=listar&idcliente=" + idcliente);
		} catch (Exception e) {
			System.out.println("modificar() " + e.getMessage());
		}
	}	
	
	protected void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int idcliente = Integer.parseInt(request.getParameter("idcliente"));
			int idprestamo = Integer.parseInt(request.getParameter("idprestamo"));
			request.setAttribute("idcliente", idcliente);
			
			if(prestamosModel.eliminarPrestamo(idprestamo) > 0) {
				request.getSession().setAttribute("exito", "prestamo eliminado");
			}else {
				request.getSession().setAttribute("fracaso", "prestamo NO eliminado");
			}
			
			response.sendRedirect(request.getContextPath() + "/PrestamosController?operacion=listar&idcliente=" + idcliente);
		} catch (Exception e) {
			System.out.println("eliminar() " + e.getMessage());
		}
	}
	
}
