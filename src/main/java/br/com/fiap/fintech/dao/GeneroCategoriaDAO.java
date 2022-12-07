package br.com.fiap.fintech.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.fintech.bean.GeneroCategoria;
import br.com.fiap.fintech.singleton.FintechDBManager;

public class GeneroCategoriaDAO {
	private Connection conexao;
	public List<GeneroCategoria> getAll(){
		
		List<GeneroCategoria> lista = new ArrayList<GeneroCategoria>();
		
		
		try {
			
			conexao = FintechDBManager.getInstance().getConnection();
			
			PreparedStatement stmt = conexao.prepareStatement("SELECT"
					+ " CD_GENERO,"
					+ " GR.DS_GENERO"
					+ " FROM"
					+ " T_GENERO GR");
			ResultSet rs = stmt.executeQuery();
			
			
			while( rs.next() ) {
				
				GeneroCategoria categoria = new GeneroCategoria();
				categoria.setCodigo( rs.getInt("CD_GENERO") );
				categoria.setNome( rs.getString("DS_GENERO") );
				
				
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
