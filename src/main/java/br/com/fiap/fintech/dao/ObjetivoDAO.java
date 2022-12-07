package br.com.fiap.fintech.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fiap.fintech.bean.ObjetivoUsuario;
import br.com.fiap.fintech.singleton.FintechDBManager;

public class ObjetivoDAO {
	private Connection conexao;
	

	public void insert(ObjetivoUsuario objetivo, String cpf) throws SQLException{
		PreparedStatement stmt = null;
		
		try {
			
			conexao = FintechDBManager.getInstance().getConnection();
			
			String sql = "INSERT INTO T_OBJETIVO (CD_OBJETIVO, DS_OBJETIVO, DT_CUMPRIR_OBJETIVO, VL_OBJETIVO, T_USUARIO_NR_CPF)" + "VALUES (SQ_OBJETIVO.NEXTVAL, ?, ?, ?, ?)";
			
			stmt = conexao.prepareStatement(sql);
			
			
			stmt.setString(1, objetivo.getDescricaoObjetivo());
			java.sql.Date data = new java.sql.Date(objetivo.getDataCumprirObjetivo().getTimeInMillis());
			stmt.setDate(2, data);
			stmt.setDouble(3, objetivo.getValorObjetivo());
			
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
	
	public void update(ObjetivoUsuario objetivo) throws SQLException{
		PreparedStatement stmt = null;
		
		try {
			
			conexao = FintechDBManager.getInstance().getConnection();
			
			String sql = "UPDATE T_OBJETIVO SET DS_OBJETIVO = ?, DT_CUMPRIR_OBJETIVO = ?, VL_OBJETIVO = ? WHERE CD_OBJETIVO = ?";
			
			stmt = conexao.prepareStatement(sql);
			
			
			stmt.setString(1, objetivo.getDescricaoObjetivo());
			java.sql.Date data = new java.sql.Date(objetivo.getDataCumprirObjetivo().getTimeInMillis());
			stmt.setDate(2, data);
			stmt.setDouble(3, objetivo.getValorObjetivo());
			stmt.setInt(4, objetivo.getCodigo());
			
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
			String sql = "DELETE FROM T_OBJETIVO WHERE CD_OBJETIVO = ?";
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
	
	public ObjetivoUsuario select(int codigo){
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ObjetivoUsuario objetivo = null;
		
		try {

			conexao = FintechDBManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT"
					+ " CD_OBJETIVO,"
					+ " OB.DS_OBJETIVO,"
					+ " OB.DT_CUMPRIR_OBJETIVO,"
					+ " OB.VL_OBJETIVO"
					+ " FROM"
					+ " T_OBJETIVO OB"
					+ " WHERE"
					+ " CD_OBJETIVO = ?");
			stmt.setInt(1, codigo);
			rs = stmt.executeQuery();
			if(rs.next()) {
				
				objetivo = new ObjetivoUsuario();
				objetivo.setCodigo( rs.getInt("CD_OBJETIVO") );
				objetivo.setDescricaoObjetivo( rs.getString("DS_OBJETIVO") );
				objetivo.setValorObjetivo( rs.getDouble("VL_OBJETIVO") );
				java.sql.Date dataCumprir = rs.getDate("DT_CUMPRIR_OBJETIVO");
				Calendar data = Calendar.getInstance();
		        data.setTimeInMillis(dataCumprir.getTime());
		        objetivo.setDataCumprirObjetivo(data);
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
		return objetivo;
	}
	
	public List<ObjetivoUsuario> getAll(String cpf){
		
		List<ObjetivoUsuario> lista = new ArrayList<ObjetivoUsuario>();
		
		
		try {
			
			conexao = FintechDBManager.getInstance().getConnection();
			
			PreparedStatement stmt = conexao.prepareStatement("SELECT"
					+ " CD_OBJETIVO,"
					+ " OB.DS_OBJETIVO,"
					+ " OB.DT_CUMPRIR_OBJETIVO,"
					+ " OB.VL_OBJETIVO"
					+ " FROM"
					+ " T_OBJETIVO OB"
					+ " WHERE T_USUARIO_NR_CPF = ?");
			stmt.setString(1, cpf);
			ResultSet rs = stmt.executeQuery();
			
			
			while( rs.next() ) {
				
				ObjetivoUsuario objetivo = new ObjetivoUsuario();
				objetivo.setCodigo( rs.getInt("CD_OBJETIVO") );
				objetivo.setDescricaoObjetivo( rs.getString("DS_OBJETIVO") );
				java.sql.Date dataCumprir = rs.getDate("DT_CUMPRIR_OBJETIVO");
				Calendar data = Calendar.getInstance();
		        data.setTimeInMillis(dataCumprir.getTime());
		        objetivo.setDataCumprirObjetivo(data);
				objetivo.setValorObjetivo( rs.getDouble("VL_OBJETIVO") );
				
				
				lista.add(objetivo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexao = null;
		}
		
		return lista;
		
	}	

}
