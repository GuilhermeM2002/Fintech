package br.com.fiap.fintech.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import br.com.fiap.fintech.bean.GeneroCategoria;
import br.com.fiap.fintech.bean.Usuario;
import br.com.fiap.fintech.singleton.FintechDBManager;

public class UsuarioDAO {
	
private Connection conexao = null;
	
	public void insert(Usuario usuario) throws SQLException{
		
		PreparedStatement stmt = null;
		
		try {
			
			conexao = FintechDBManager.getInstance().getConnection();
			
			String sql = "INSERT INTO T_USUARIO (NR_CPF, T_GENERO_CD_GENERO, NM_COMPLETO, DT_DATA_NASC, EMAIL, SENHA)" 
			+ " VALUES (?, ?, ?, TO_DATE(?, 'DD/MM/YYYY'), ?, ?)";
			
			stmt = conexao.prepareStatement(sql);
			
			stmt.setString(1, usuario.getCpfUsuario());
			stmt.setInt(2, usuario.getGeneroUsuario().getCodigo());
			stmt.setString(3, usuario.getNomeUsuario());
			
			java.sql.Date data = new java.sql.Date(usuario.getDataNascimento().getTimeInMillis());
			stmt.setDate(4, data);
			
			stmt.setString(5, usuario.getEmail());
			stmt.setString(6, usuario.getSenha());
			stmt.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
			
		}finally {
			try {
				stmt.close();
				conexao.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void update(Usuario usuario) throws SQLException{
		
		PreparedStatement stmt = null;

		try {
			
			conexao = FintechDBManager.getInstance().getConnection();
			
			String sql = "UPDATE T_USUARIO SET NM_COMPLETO = ?, DT_DATA_NASC = ?, SENHA = ? WHERE NR_CPF = ?";
			
			stmt = conexao.prepareStatement(sql);
			
			stmt.setString(1, usuario.getNomeUsuario());
			
			java.sql.Date data = new java.sql.Date(usuario.getDataNascimento().getTimeInMillis());
			stmt.setDate(2, data);
			
			stmt.setString(3, usuario.getSenha());
			
			stmt.setString(4, usuario.getCpfUsuario());
			
			stmt.executeUpdate();
			
		}catch(SQLException e){
			
			e.printStackTrace();
			
		}finally {
			try {
				stmt.close();
				conexao.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void delete(String cpfUsuario) throws SQLException{
		PreparedStatement stmt = null;
		
		try {
			conexao = FintechDBManager.getInstance().getConnection();
			String sql = "DELETE FROM T_USUARIO WHERE NR_CPF = ?";
			stmt = conexao.prepareStatement(sql);
			
			stmt.setString(1, cpfUsuario);
			
			stmt.executeQuery();
			
		} catch (SQLException e){
			
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e){
				
				e.printStackTrace();
			}
		}
	}
	
	public Usuario select(String cpfUsuario){
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Usuario usuario = null;
		
		try {

			conexao = FintechDBManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT"
		    		+ " NR_CPF,"
					+ " T_GENERO_CD_GENERO,"
		    		+ " TG.DS_GENERO,"
		      		+ " US.NM_COMPLETO,"
		      		+ " US.DT_DATA_NASC," 
		      		+ " US.EMAIL"
		      		+ " FROM"
		      		+ " T_USUARIO US"
		      		+ " INNER JOIN T_GENERO TG ON US.T_GENERO_CD_GENERO = TG.CD_GENERO"
		      		+ " WHERE"
		      		+ " NR_CPF = ?");
			stmt.setString(1, cpfUsuario);
			rs = stmt.executeQuery();
			if(rs.next()) {
				
				usuario = new Usuario();
				usuario.setCpfUsuario( rs.getString("NR_CPF") );
				usuario.setNomeUsuario( rs.getString("NM_COMPLETO") );
				java.sql.Date dataNascimento = rs.getDate("DT_DATA_NASC");
				Calendar data = Calendar.getInstance();
		        data.setTimeInMillis(dataNascimento.getTime());
		        usuario.setDataNascimento(data);
				usuario.setEmail( rs.getString("EMAIL") );
				int codigoCategoria = rs.getInt("T_GENERO_CD_GENERO");
				String nomeCategoria = rs.getString("DS_GENERO");
				GeneroCategoria categoria = new GeneroCategoria(codigoCategoria,nomeCategoria); 

				usuario.setGeneroUsuario(categoria); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
	    }finally{
	    	
	    	try {
		        stmt.close();
		        rs.close();
		        conexao.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
		return usuario;
	}
	
	public boolean validarUsuario(Usuario usuario) { 

		PreparedStatement stmt = null; 

		ResultSet rs = null; 

		try { 

			conexao = FintechDBManager.getInstance().getConnection();

			stmt = conexao.prepareStatement("SELECT NR_CPF, SENHA FROM T_USUARIO WHERE NR_CPF = ? AND SENHA = ?"); 

			stmt.setString(1, usuario.getCpfUsuario()); 

			stmt.setString(2, usuario.getSenha()); 

			rs = stmt.executeQuery(); 
  

			if (rs.next()){ 

				return true; 

			} 

			 

		} catch (SQLException e) { 

			e.printStackTrace(); 

		}finally { 

			try { 

				stmt.close(); 

				rs.close(); 

				conexao.close(); 

			} catch (SQLException e) { 

				e.printStackTrace(); 

			} 

		} 

		return false; 

	} 
}
