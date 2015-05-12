package Entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "Propuesta")
public class PropuestaEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	protected long id;
	@Version
	protected long version;
	
	protected String titulo;
	protected int numEstudiantes;
	protected String objetivos;
	protected String aportes;
	protected int tiempoEstimado;
	protected String condicionesEntrega;
	protected String recursosRequeridos;
	protected String fuentesFinanciacion;
	protected String observaciones;
	protected boolean aprobada;
	
	public long getId() {
		return id;
	}
	public long getVersion() {
		return version;
	}
	public String getTitulo() {
		return titulo;
	}
	public int getNumEstudiantes() {
		return numEstudiantes;
	}
	public String getObjetivos() {
		return objetivos;
	}
	public String getAportes() {
		return aportes;
	}
	public int getTiempoEstimado() {
		return tiempoEstimado;
	}
	public String getCondicionesEntrega() {
		return condicionesEntrega;
	}
	public String getRecursosRequeridos() {
		return recursosRequeridos;
	}
	public String getFuentesFinanciacion() {
		return fuentesFinanciacion;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public void setNumEstudiantes(int numEstudiantes) {
		this.numEstudiantes = numEstudiantes;
	}
	public void setObjetivos(String objetivos) {
		this.objetivos = objetivos;
	}
	public void setAportes(String aportes) {
		this.aportes = aportes;
	}
	public void setTiempoEstimado(int tiempoEstimado) {
		this.tiempoEstimado = tiempoEstimado;
	}
	public void setCondicionesEntrega(String condicionesEntrega) {
		this.condicionesEntrega = condicionesEntrega;
	}
	public void setRecursosRequeridos(String recursosRequeridos) {
		this.recursosRequeridos = recursosRequeridos;
	}
	public void setFuentesFinanciacion(String fuentesFinanciacion) {
		this.fuentesFinanciacion = fuentesFinanciacion;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public boolean isAprobada() {
		return aprobada;
	}
	public void setAprobada(boolean aprobada) {
		this.aprobada = aprobada;
	}
}
