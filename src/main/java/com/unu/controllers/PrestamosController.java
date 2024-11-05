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
}
