package br.com.fiap.fintech.factory;

import br.com.fiap.fintech.dao.GastoDAO;
import br.com.fiap.fintech.dao.GeneroCategoriaDAO;
import br.com.fiap.fintech.dao.InvestimentoDAO;
import br.com.fiap.fintech.dao.NomeBancoCategoriaDAO;
import br.com.fiap.fintech.dao.ObjetivoDAO;
import br.com.fiap.fintech.dao.RendaDAO;
import br.com.fiap.fintech.dao.UsuarioDAO;

public class DAOFactory {
	
	public static UsuarioDAO getUsuarioDAO() {
		return new UsuarioDAO();
	}
	
	public static InvestimentoDAO getInvestimentoDAO() {
		return new InvestimentoDAO();
	}
	
	public static ObjetivoDAO getObjetivoDAO() {
		return new ObjetivoDAO();
	}
	
	public static RendaDAO getRendaDAO() {
		return new RendaDAO();
	}
	
	public static GastoDAO getGastoDAO() {
		return new GastoDAO();
	}
	
	public static GeneroCategoriaDAO getGeneroCategoriaDAO(){
		return new GeneroCategoriaDAO();
	}
	
	public static NomeBancoCategoriaDAO getNomeBancoCategoriaDAO() {
		return new NomeBancoCategoriaDAO();
	}
}
