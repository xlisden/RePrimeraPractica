package com.unu.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.unu.beans.Cliente;
import com.unu.beans.Prestamo;

public class PrestamosModel {
	
	private CallableStatement cs;
	private Connection conexion;
	private ResultSet rs;
	
	public List<Prestamo> listarPrestamosPorCliente(int idcliente) {
		List<Prestamo> prestamos = null;
		try {
			String sql = "CALL spReadPrestamosCliente(?);";
			prestamos = new ArrayList<Prestamo>();
			conexion = Conexion.openConnection();
			cs = conexion.prepareCall(sql);
			cs.setInt(1, idcliente);
			rs = cs.executeQuery();
			
			while(rs.next()) {
				Prestamo prestamo = new Prestamo();
				prestamo.setIdprestamo(rs.getInt("idprestamo"));
				prestamo.setFechaPrestamo(rs.getString("fechaPrestamo"));
				prestamo.setMonto(rs.getDouble("monto"));
				prestamo.setIdcliente(idcliente);
				prestamo.setCliente(rs.getString("cliente"));
				prestamo.setInteres(rs.getInt("interes"));
				prestamo.setNroCuotas(rs.getInt("nroCuotas"));
				prestamos.add(prestamo);
			}
			
			conexion = Conexion.closeConnection();
		} catch (Exception e) {
			System.out.println("listarPrestamosPorCliente() " + e.getMessage());
		}
		return prestamos;
	}
	
	public int insertarPrestamo(Prestamo prestamo) {
		int filasAfectadas = 0;
		try {
			String sql = "CALL spCreatePrestamoId(?, ?, ?, ?, ?);";
			conexion = Conexion.openConnection();
			cs = conexion.prepareCall(sql);
			cs.setString(1, prestamo.getFechaPrestamo());
			cs.setDouble(2, prestamo.getMonto());
			cs.setInt(3, prestamo.getIdcliente());
			cs.setInt(4, prestamo.getInteres());
			cs.setInt(5, prestamo.getNroCuotas());
			filasAfectadas = cs.executeUpdate();
			if(filasAfectadas == 0) {
				System.out.println("Insercion en prestamo fallido.");
			}
			
			conexion = Conexion.closeConnection();
		} catch (Exception e) {
			System.out.println("insertarPrestamo() " + e.getMessage());
		}
		return filasAfectadas;
	}	
	
	public Prestamo listarPrestamoPorId(int idprestamo) {
		Prestamo prestamo = null;
		try {
			String sql = "CALL spReadPrestamoPorId(?);";
			conexion = Conexion.openConnection();
			cs = conexion.prepareCall(sql);
			cs.setInt(1, idprestamo);
			rs = cs.executeQuery();
			
			while(rs.next()) {
				prestamo = new Prestamo();
				prestamo.setIdprestamo(rs.getInt("idprestamo"));
				prestamo.setFechaPrestamo(rs.getString("fechaPrestamo"));
				prestamo.setMonto(rs.getDouble("monto"));
				prestamo.setIdcliente(rs.getInt("idcliente"));
				prestamo.setInteres(rs.getInt("interes"));
				prestamo.setNroCuotas(rs.getInt("nroCuotas"));
			}
			
			conexion = Conexion.closeConnection();
		} catch (Exception e) {
			System.out.println("listarPrestamoPorId() " + e.getMessage());
		}
		return prestamo;
	}
	
	public int modificarPrestamo(Prestamo prestamo) {
		int filasAfectadas = 0;
		try {
			String sql = "CALL spUpdatePrestamo(?, ?, ?, ?, ?, ?);";
			conexion = Conexion.openConnection();
			cs = conexion.prepareCall(sql);
			cs.setInt(1, prestamo.getIdprestamo());
			cs.setString(2, prestamo.getFechaPrestamo());
			cs.setDouble(3, prestamo.getMonto());
			cs.setInt(4, prestamo.getIdcliente());
			cs.setInt(5, prestamo.getInteres());
			cs.setInt(6, prestamo.getNroCuotas());
			filasAfectadas = cs.executeUpdate();
			if(filasAfectadas == 0) {
				System.out.println("Modificar en prestamo fallido.");
			}
			
			conexion = Conexion.closeConnection();
		} catch (Exception e) {
			System.out.println("modificarPrestamo() " + e.getMessage());
		}
		return filasAfectadas;
	}
	
	public int eliminarPrestamo(int idprestamo) {
		int filasAfectadas = 0;
		try {
			String sql = "CALL spDeletePrestamo(?);";
			conexion = Conexion.openConnection();
			cs = conexion.prepareCall(sql);
			cs.setInt(1, idprestamo);
			filasAfectadas = cs.executeUpdate();
			
			if(filasAfectadas == 0) {
				System.out.println("Eliminacion en prestamo fallido.");
			}
			
			conexion = Conexion.closeConnection();
		} catch (Exception e) {
			System.out.println("eliminarPrestamo() " + e.getMessage());
		}
		return filasAfectadas;
	}	
	
}
