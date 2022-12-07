package br.com.fiap.fintech.bean;

import java.util.Calendar;

public class InvestimentoUsuario {
	
	private int codigo;
	private Usuario cpf;
	private String nomeInvestimento;
	private double valorAplicacao;
	private Calendar dataInvestimento;
	private Calendar dataVencimento;
	private NomeBancoCategoria nomeBancoOuCorretora;
	private String tipoInvestimento;
	
	public InvestimentoUsuario(String tipoInvestimento, String nomeInvestimento, double valorAplicacao, Calendar dataInvestimento, Calendar dataVencimento) {
		super();
		
		this.nomeInvestimento = nomeInvestimento;
		this.valorAplicacao = valorAplicacao;
		this.dataInvestimento = dataInvestimento;
		this.dataVencimento = dataVencimento;
		this.tipoInvestimento = tipoInvestimento;
	}
	
	public InvestimentoUsuario() {
		super();
	}
	
	
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Usuario getCpf() {
		return cpf;
	}
	public void setCpf(Usuario cpf) {
		this.cpf = cpf;
	}
	public String getNomeInvestimento() {
		return nomeInvestimento;
	}
	public void setNomeInvestimento(String nomeInvestimento) {
		this.nomeInvestimento = nomeInvestimento;
	}	
	public double getValorAplicacao() {
		return valorAplicacao;
	}
	public void setValorAplicacao(double valorAplicacao) {
		this.valorAplicacao = valorAplicacao;
	}
	public Calendar getDataInvestimento() {
		return dataInvestimento;
	}
	public void setDataInvestimento(Calendar dataInvestimento) {
		this.dataInvestimento = dataInvestimento;
	}
	public Calendar getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Calendar dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public NomeBancoCategoria getNomeBancoOuCorretora() {
		return nomeBancoOuCorretora;
	}
	public void setNomeBancoOuCorretora(NomeBancoCategoria nomeBancoOuCorretora) {
		this.nomeBancoOuCorretora = nomeBancoOuCorretora;
	}
	public String getTipoInvestimento() {
		return tipoInvestimento;
	}
	public void setTipoInvestimento(String tipoInvestimento) {
		this.tipoInvestimento = tipoInvestimento;
	}
	
	@Override
	public String toString() {
		return "InvestimentoUsuario [nomeInvestimento=" + nomeInvestimento + ", valorAplicacao=" + valorAplicacao
				+ ", dataInvestimento=" + dataInvestimento + ", dataVencimento=" + dataVencimento
				+ ", nomeBancoOuCorretora=" + nomeBancoOuCorretora + ", tipoInvestimento=" + tipoInvestimento + "]";
	}
	
	
}
