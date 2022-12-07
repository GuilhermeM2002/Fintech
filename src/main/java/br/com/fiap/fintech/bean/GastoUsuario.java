package br.com.fiap.fintech.bean;

import java.util.Calendar;


public class GastoUsuario extends FinancaUsuario{
	

	public GastoUsuario(double valor, String descricao, Calendar dataOcorrencia) {
		super(valor, descricao, dataOcorrencia);
	}
	
	public GastoUsuario() {
		super();
	}

}
