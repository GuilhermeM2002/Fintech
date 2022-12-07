package br.com.fiap.fintech.bean;

import java.util.Calendar;

/**
 * 
 * @author Guilherme Martins
 *
 */
public class FinancaUsuario {
	
	private double valor;
	private String descricao;
	private Calendar dataOcorrencia;
	private int codigo;
	
	public FinancaUsuario(double valor, String descricao, Calendar dataOcorrencia) {
		
		super();
		
		this.valor = valor;
		this.descricao = descricao;
		this.dataOcorrencia = dataOcorrencia;
	}
	
	public FinancaUsuario() {
		
		super();
		
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Calendar getDataOcorrencia() {
		return dataOcorrencia;
	}

	public void setDataOcorrencia(Calendar dataOcorrencia) {
		this.dataOcorrencia = dataOcorrencia;
	}

	@Override
	public String toString() {
		return "FinancaUsuario [valor=" + valor + ", descricao=" + descricao + ", dataOcorrencia=" + dataOcorrencia
				+ "]";
	}
	
	
}
