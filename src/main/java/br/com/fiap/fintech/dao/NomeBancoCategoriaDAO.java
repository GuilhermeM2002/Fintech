package br.com.fiap.fintech.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.fintech.bean.NomeBancoCategoria;
import br.com.fiap.fintech.singleton.FintechDBManager;

public class NomeBancoCategoriaDAO {
	private Connection conexao;
	public List<NomeBancoCategoria> getAll(){
			
		List<NomeBancoCategoria> lista = new ArrayList<NomeBancoCategoria>();
			
			
		try {
				
			conexao = FintechDBManager.getInstance().getConnection();
			
			PreparedStatement stmt = conexao.prepareStatement("SELECT"
					+ " CD_BANCO,"
					+ " BC.NM_BANCO"
					+ " FROM"
					+ " T_BANCO BC");
			ResultSet rs = stmt.executeQuery();
			
				
			while( rs.next() ) {
				
				NomeBancoCategoria categoria = new NomeBancoCategoria();
				categoria.setCodigo( rs.getInt("CD_BANCO") );
				categoria.setNome( rs.getString("NM_BANCO") );
				
					
				lista.add(categoria);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexao = null;
		}
			
		return lista;
		
	}
}
