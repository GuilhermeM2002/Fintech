package br.com.fiap.fintech.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fiap.fintech.bean.RendaUsuario;
import br.com.fiap.fintech.singleton.FintechDBManager;

public class RendaDAO {
	
private Connection conexao;
	
	public void insert(RendaUsuario renda, String cpf) throws SQLException{
		PreparedStatement stmt = null;
		
		try {
			conexao = FintechDBManager.getInstance().getConnection();
			String sql = "INSERT INTO T_RENDA (CD_RENDA, DS_RENDA, VL_RENDA, DT_RENDA, T_USUARIO_NR_CPF)" + "VALUES (SQ_RENDA.NEXTVAL, ?, ?, ?, ?)";
			
			stmt = conexao.prepareStatement(sql);
			
			
			stmt.setString(1, renda.getDescricao());
			
			stmt.setDouble(2, renda.getValor());
			
			java.sql.Date data = new java.sql.Date(renda.getDataOcorrencia().getTimeInMillis());
			stmt.setDate(3, data);
			
			stmt.setString(4, cpf);
			
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
	
	public void update(RendaUsuario renda) throws SQLException{
		PreparedStatement stmt = null;
		
		try {
			conexao = FintechDBManager.getInstance().getConnection();
			String sql = "UPDATE T_RENDA SET DS_RENDA = ?, VL_RENDA = ?, DT_RENDA = ? WHERE CD_RENDA = ?";
			
			stmt = conexao.prepareStatement(sql);
			
			stmt.setString(1, renda.getDescricao());
			
			stmt.setDouble(2, renda.getValor());
			
			java.sql.Date data = new java.sql.Date(renda.getDataOcorrencia().getTimeInMillis());
			stmt.setDate(3, data);
			
			stmt.setInt(4, renda.getCodigo());
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
	
	public void delete(int codigo) throws SQLException{
		PreparedStatement stmt = null;
		
		try {
			conexao = FintechDBManager.getInstance().getConnection();
			String sql = "DELETE FROM T_RENDA WHERE CD_RENDA = ?";
			stmt = conexao.prepareStatement(sql);
			
			stmt.setInt(1, codigo);
			
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
	
	public RendaUsuario select(int codigo){
		PreparedStatement stmt = null;
		ResultSet rs = null;
		RendaUsuario renda = null;
		
		try {

			conexao = FintechDBManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT"
					+ " CD_RENDA,"
					+ " TR.DS_RENDA,"
					+ " TR.VL_RENDA,"
					+ " TR.DT_RENDA"
					+ " FROM"
					+ " T_RENDA TR"
					+ " WHERE"
					+ " CD_RENDA = ?");
			stmt.setInt(1, codigo);
			rs = stmt.executeQuery();
			if(rs.next()) {
				
				renda = new RendaUsuario();
				renda.setCodigo( rs.getInt("CD_RENDA") );
				renda.setDescricao( rs.getString("DS_RENDA") );
				renda.setValor( rs.getDouble("VL_RENDA") );
				java.sql.Date dataRenda = rs.getDate("DT_RENDA");
				Calendar data = Calendar.getInstance();
		        data.setTimeInMillis(dataRenda.getTime());
		        renda.setDataOcorrencia(data);
				
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
		return renda;
	}
	
	public List<RendaUsuario> getAll(String cpf){
		
		ResultSet rs = null;
		List<RendaUsuario> lista = new ArrayList<RendaUsuario>();

		try {
			
			conexao = FintechDBManager.getInstance().getConnection();
			PreparedStatement stmt = conexao.prepareStatement("SELECT"
					+ " CD_RENDA,"
					+ " TR.DS_RENDA,"
					+ " TR.VL_RENDA,"
					+ " TR.DT_RENDA"
					+ " FROM"
					+ " T_RENDA TR"
					+ " WHERE"
					+ " TR.T_USUARIO_NR_CPF = ?");
			
			stmt.setString(1, cpf); 
			rs = stmt.executeQuery();
			
			while( rs.next() ) {
				
				RendaUsuario renda = new RendaUsuario();
				renda.setCodigo( rs.getInt("CD_RENDA") );
				renda.setDescricao( rs.getString("DS_RENDA") );
				renda.setValor( rs.getDouble("VL_RENDA") );
				java.sql.Date dataRenda = rs.getDate("DT_RENDA");
				Calendar data = Calendar.getInstance();
		        data.setTimeInMillis(dataRenda.getTime());
		        renda.setDataOcorrencia(data);
				
				lista.add(renda);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexao = null;
		}
		
		return lista;
		
	}
}
