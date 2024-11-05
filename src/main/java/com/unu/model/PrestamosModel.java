package com.unu.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
	
}
