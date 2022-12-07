package br.com.fiap.fintech.bean;

import java.util.Calendar;

public class RendaUsuario extends FinancaUsuario{
	
	
	public RendaUsuario(double valor, String descricao, Calendar dataOcorrencia) {
		
		super(valor, descricao, dataOcorrencia);
	
	}

	public RendaUsuario() {
		
		super();
	}

}
