package com.unu.beans;

public class Prestamo {

	private int idprestamo;
	private String fechaPrestamo;
	private double monto;
	private int idcliente;
	private String cliente;
	private int interes;
	private int nroCuotas;

	public Prestamo() {
		this(0, "", 0, 0, 0, 0);
	}

	public Prestamo(int idprestamo, String fechaPrestamo, double monto, int idcliente, int interes, int nroCuotas) {
		this.idprestamo = idprestamo;
		this.fechaPrestamo = fechaPrestamo;
		this.monto = monto;
		this.idcliente = idcliente;
		this.interes = interes;
		this.nroCuotas = nroCuotas;
	}

	public int getIdprestamo() {
		return idprestamo;
	}

	public void setIdprestamo(int idprestamo) {
		this.idprestamo = idprestamo;
	}

	public String getFechaPrestamo() {
		return fechaPrestamo;
	}

	public void setFechaPrestamo(String fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public int getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}

	public int getInteres() {
		return interes;
	}

	public void setInteres(int interes) {
		this.interes = interes;
	}

	public int getNroCuotas() {
		return nroCuotas;
	}

	public void setNroCuotas(int nroCuotas) {
		this.nroCuotas = nroCuotas;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

}
