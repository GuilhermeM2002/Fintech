package br.com.fiap.fintech.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fiap.fintech.bean.GastoUsuario;
import br.com.fiap.fintech.singleton.FintechDBManager;

public class GastoDAO {
	
private Connection conexao;
	
	public void insert(GastoUsuario gasto, String cpf) throws SQLException{
		PreparedStatement stmt = null;
		
		try {
			conexao = FintechDBManager.getInstance().getConnection();
			String sql = "INSERT INTO T_GASTO (CD_GASTO, T_USUARIO_NR_CPF, DS_GASTO, DT_GASTO, VL_GASTO)" + "VALUES (SQ_GASTO.NEXTVAL, ?, ?, ?, ?)";
			
			stmt = conexao.prepareStatement(sql);
			
			stmt.setString(1, cpf);
			
			stmt.setString(2, gasto.getDescricao());
			
			java.sql.Date data = new java.sql.Date(gasto.getDataOcorrencia().getTimeInMillis());
			stmt.setDate(3, data);
			
			stmt.setDouble(4, gasto.getValor());
			
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
	
	public void update(GastoUsuario gasto) throws SQLException{
		PreparedStatement stmt = null;
		
		try {
			conexao = FintechDBManager.getInstance().getConnection();
			String sql = "UPDATE T_GASTO SET DS_GASTO = ?, VL_GASTO = ?, DT_GASTO = ? WHERE CD_GASTO = ?";
			
			stmt = conexao.prepareStatement(sql);
			
			stmt.setString(1, gasto.getDescricao());
			
			stmt.setDouble(2, gasto.getValor());
			
			java.sql.Date data = new java.sql.Date(gasto.getDataOcorrencia().getTimeInMillis());
			stmt.setDate(3, data);
			
			stmt.setInt(4, gasto.getCodigo());
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
			String sql = "DELETE FROM T_GASTO WHERE CD_GASTO = ?";
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
	
	public GastoUsuario select(int codigo){
		PreparedStatement stmt = null;
		ResultSet rs = null;
		GastoUsuario gasto = null;
		
		try {

			conexao = FintechDBManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT"
					+ " CD_GASTO,"
					+ " TG.DS_GASTO,"
					+ " TG.VL_GASTO,"
					+ " TG.DT_GASTO"
					+ " FROM"
					+ " T_GASTO TG"
					+ " WHERE"
					+ " CD_GASTO = ?");
			stmt.setInt(1, codigo);
			rs = stmt.executeQuery();
			if(rs.next()) {
				
				gasto = new GastoUsuario();
				gasto.setCodigo( rs.getInt("CD_GASTO") );
				gasto.setDescricao( rs.getString("DS_GASTO") );
				gasto.setValor( rs.getDouble("VL_GASTO") );
				java.sql.Date dataGasto = rs.getDate("DT_GASTO");
				Calendar data = Calendar.getInstance();
		        data.setTimeInMillis(dataGasto.getTime());
		        gasto.setDataOcorrencia(data);
				
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
		return gasto;
	}
	
	public List<GastoUsuario> getAll(String cpf){
		
		List<GastoUsuario> lista = new ArrayList<GastoUsuario>();
		
		
		try {
			conexao = FintechDBManager.getInstance().getConnection();
			PreparedStatement stmt = conexao.prepareStatement("SELECT"
					+ " CD_GASTO,"
					+ " TG.DS_GASTO,"
					+ " TG.VL_GASTO,"
					+ " TG.DT_GASTO"
					+ " FROM"
					+ " T_GASTO TG"
					+ " WHERE"
					+ " T_USUARIO_NR_CPF = ?"
					+ " ORDER BY DT_GASTO DESC");
			stmt.setString(1, cpf);
			ResultSet rs = stmt.executeQuery();
			
			
			while( rs.next() ) {
				
				GastoUsuario gasto = new GastoUsuario();
				gasto.setCodigo( rs.getInt("CD_GASTO") );
				gasto.setDescricao( rs.getString("DS_GASTO") );
				gasto.setValor( rs.getDouble("VL_GASTO") );
				java.sql.Date dataGasto = rs.getDate("DT_GASTO");
				Calendar data = Calendar.getInstance();
		        data.setTimeInMillis(dataGasto.getTime());
		        gasto.setDataOcorrencia(data);
				
				
				lista.add(gasto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexao = null;
		}
		
		return lista;
		
	}
}
