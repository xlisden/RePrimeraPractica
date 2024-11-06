package com.unu.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.unu.beans.Cliente;

public class ClientesModel {

	private CallableStatement cs;
	private Connection conexion;
	private ResultSet rs;

	public List<Cliente> listarClientes() {
		List<Cliente> clientes = null;
		try {
			String sql = "CALL spReadClientes();";
			clientes = new ArrayList<Cliente>();
			conexion = Conexion.openConnection();
			cs = conexion.prepareCall(sql);
			rs = cs.executeQuery();
			
			while(rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setIdcliente(rs.getInt("idcliente"));
				cliente.setNombres(rs.getString("nombres"));
				cliente.setApellidos(rs.getString("apellidos"));
				cliente.setDni(rs.getString("dni"));
				cliente.setFechaNacimiento(rs.getString("fechaNacimiento"));
				cliente.setDireccion(rs.getString("direccion"));
				clientes.add(cliente);
			}
			
			conexion = Conexion.closeConnection();
		} catch (Exception e) {
			System.out.println("listarClientes() " + e.getMessage());
		}
		return clientes;
	}
	
	public int insertarCliente(Cliente cliente) {
		int filasAfectadas = 0;
		try {
			String sql = "CALL spCreateCliente(?, ?, ?, ?, ?);";
			conexion = Conexion.openConnection();
			cs = conexion.prepareCall(sql);
			cs.setString(1, cliente.getNombres());
			cs.setString(2, cliente.getApellidos());
			cs.setString(3, cliente.getDni());
			cs.setString(4, cliente.getFechaNacimiento());
			cs.setString(5, cliente.getDireccion());
			filasAfectadas = cs.executeUpdate();
			
			if(filasAfectadas == 0) {
				System.out.println("Insercion en cliente fallido.");
			}
			
			conexion = Conexion.closeConnection();
		} catch (Exception e) {
			System.out.println("insertarCliente() " + e.getMessage());
		}
		return filasAfectadas;
	}

	public List<String> listarClientesPorNombres() {
		List<String> clientes = null;
		try {
			String sql = "CALL spReadClientesPorNombres();";
			clientes = new ArrayList<String>();
			conexion = Conexion.openConnection();
			cs = conexion.prepareCall(sql);
			rs = cs.executeQuery();
			
			while(rs.next()) {
				String cliente = rs.getString("cliente");
			}
			
			conexion = Conexion.closeConnection();
		} catch (Exception e) {
			System.out.println("listarClientesPorNombres() " + e.getMessage());
		}
		return clientes;
	}

	public Cliente listarClientePorId(int idcliente) {
		Cliente cliente = null;
		try {
			String sql = "CALL spReadClientePorId(?);";
			conexion = Conexion.openConnection();
			cs = conexion.prepareCall(sql);
			cs.setInt(1, idcliente);
			rs = cs.executeQuery();
			
			while(rs.next()) {
				cliente = new Cliente();
				cliente.setIdcliente(rs.getInt("idcliente"));
				cliente.setNombres(rs.getString("nombres"));
				cliente.setApellidos(rs.getString("apellidos"));
				cliente.setDni(rs.getString("dni"));
				cliente.setFechaNacimiento(rs.getString("fechaNacimiento"));
				cliente.setDireccion(rs.getString("direccion"));
			}
			
			conexion = Conexion.closeConnection();
		} catch (Exception e) {
			System.out.println("listarClientePorId() " + e.getMessage());
		}
		return cliente;
	}

	public Cliente listarClientePorNombre(String nomCliente) {
		Cliente cliente = null;
		try {
			String sql = "CALL spReadClientesPorNombre(?);";
			conexion = Conexion.openConnection();
			cs = conexion.prepareCall(sql);
			cs.setString(1, nomCliente);
			rs = cs.executeQuery();
			
			if(rs.next()) {
				cliente = new Cliente();
				cliente.setIdcliente(rs.getInt("idcliente"));
				cliente.setNombres(rs.getString("nombres"));
				cliente.setApellidos(rs.getString("apellidos"));
				cliente.setDni(rs.getString("dni"));
				cliente.setFechaNacimiento(rs.getString("fechaNacimiento"));
				cliente.setDireccion(rs.getString("direccion"));
			}
			
			conexion = Conexion.closeConnection();
		} catch (Exception e) {
			System.out.println("listarClientePorNombre() " + e.getMessage());
		}
		return cliente;
	}
}
