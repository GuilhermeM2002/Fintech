package br.com.fiap.fintech.bean;

import java.util.Calendar;

public class ObjetivoUsuario {
	
	int codigo;
	private String descricaoObjetivo;
	private Calendar dataCumprirObjetivo;
	private double valorObjetivo;
	
	public ObjetivoUsuario(int codigo, String descricaoObjetivo, Calendar dataCumprirObjetivo, double valorObjetivo) {
		super();
		
		this.codigo = codigo;
		this.descricaoObjetivo = descricaoObjetivo;
		this.dataCumprirObjetivo = dataCumprirObjetivo;
		this.valorObjetivo = valorObjetivo;
	}
	
	public ObjetivoUsuario(){
		
		super();
		
	}
	
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDescricaoObjetivo() {
		return descricaoObjetivo;
	}
	public void setDescricaoObjetivo(String descricaoObjetivo) {
		this.descricaoObjetivo = descricaoObjetivo;
	}
	public Calendar getDataCumprirObjetivo() {
		return dataCumprirObjetivo;
	}
	public void setDataCumprirObjetivo(Calendar dataCumprirObjetivo) {
		this.dataCumprirObjetivo = dataCumprirObjetivo;
	}
	public double getValorObjetivo() {
		return valorObjetivo;
	}
	public void setValorObjetivo(double valorObjetivo) {
		this.valorObjetivo = valorObjetivo;
	}

	@Override
	public String toString() {
		return "ObjetivoUsuario [descricaoObjetivo=" + descricaoObjetivo + ", dataCumprirObjetivo="
				+ dataCumprirObjetivo + ", valorObjetivo=" + valorObjetivo + "]";
	}
	
}
