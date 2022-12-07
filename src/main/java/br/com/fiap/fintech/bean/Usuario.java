package br.com.fiap.fintech.bean;

import java.util.Calendar;

import br.com.fiap.fintech.util.CriptografiaUtils;

/**
 * 
 * @author Guilherme Martins
 *
 */
public class Usuario{
	
	private String cpfUsuario;
	private String nomeUsuario;
	private Calendar dataNascimento;
	private GeneroCategoria generoUsuario;
	private String email;
	private String senha;
	
	public Usuario(String cpfUsuario, String nomeUsuario, Calendar dataNascimento, GeneroCategoria generoUsuario, String email, String senha){
		
		super();
		
		this.cpfUsuario = cpfUsuario;
		this.nomeUsuario = nomeUsuario;
		this.dataNascimento = dataNascimento;
		this.generoUsuario = generoUsuario;
		this.email = email;
		setSenha(senha);
	}
	
	public Usuario(){
		super();
		
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		try {
			this.senha = CriptografiaUtils.criptografar(senha);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public String getCpfUsuario() {
		return cpfUsuario;
	}
	public void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public Calendar getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public GeneroCategoria getGeneroUsuario() {
		return generoUsuario;
	}
	public void setGeneroUsuario(GeneroCategoria generoUsuario) {
		this.generoUsuario = generoUsuario;
	}

	@Override
	public String toString() {
		return "Usuario [cpfUsuario=" + cpfUsuario + ", nomeUsuario=" + nomeUsuario + ", dataNascimento="
				+ dataNascimento + ", generoUsuario=" + generoUsuario + ", email=" + email + "]";
	}
	
	
}
