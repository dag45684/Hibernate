package main;
// Generated 17 ene 2024 20:26:04 by Hibernate Tools 6.3.1.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Clientes generated by hbm2java
 */
public class Clientes implements java.io.Serializable {

	private byte id;
	private String nombre;
	private String direccion;
	private String poblacion;
	private String telef;
	private String nif;
	private Set ventases = new HashSet(0);

	public Clientes() {
	}

	public Clientes(byte id, String nombre, String direccion, String poblacion, String telef, String nif,
			Set ventases) {
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.poblacion = poblacion;
		this.telef = telef;
		this.nif = nif;
		this.ventases = ventases;
	}

	public byte getId() {
		return this.id;
	}

	public void setId(byte id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getPoblacion() {
		return this.poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getTelef() {
		return this.telef;
	}

	public void setTelef(String telef) {
		this.telef = telef;
	}

	public String getNif() {
		return this.nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public Set getVentases() {
		return this.ventases;
	}

	public void setVentases(Set ventases) {
		this.ventases = ventases;
	}

}
