package br.com.fiap.fintech.bean;

public class NomeBancoCategoria {
	private int codigo;
	private String nome;
	
	public NomeBancoCategoria() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NomeBancoCategoria(int codigo, String nome) {
		super();
		this.codigo = codigo;
		this.nome = nome;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public String toString() {
		return nome;
	}
	
	
}
