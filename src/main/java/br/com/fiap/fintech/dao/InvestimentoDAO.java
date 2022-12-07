package br.com.fiap.fintech.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fiap.fintech.bean.InvestimentoUsuario;
import br.com.fiap.fintech.bean.NomeBancoCategoria;
import br.com.fiap.fintech.bean.Usuario;
import br.com.fiap.fintech.singleton.FintechDBManager;

public class InvestimentoDAO {
	
	private Connection conexao = null;
	
	public void insert(InvestimentoUsuario investimento) throws SQLException{
		
		PreparedStatement stmt = null;
		
		try {
			
			conexao = FintechDBManager.getInstance().getConnection();
			
			String sql = "INSERT INTO T_INVESTIMENTO (CD_INVESTIMENTO, T_USUARIO_NR_CPF, DS_TIPO, T_BANCO_CD_BANCO, NM_INVESTIMENTO, VL_APLICACAO, DT_INVESTIMENTO, DT_VENCIMENTO )" 
			+ "VALUES (SQ_INVESTIMENTO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
			
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, investimento.getCpf().getCpfUsuario());
			stmt.setString(2, investimento.getTipoInvestimento());
			
			stmt.setInt(3, investimento.getNomeBancoOuCorretora().getCodigo());
			stmt.setString(4, investimento.getNomeInvestimento());
			stmt.setDouble(5, investimento.getValorAplicacao());
			
			
			java.sql.Date dataI = new java.sql.Date(investimento.getDataInvestimento().getTimeInMillis());
			
			stmt.setDate(6, dataI);
			
			java.sql.Date dataV = new java.sql.Date(investimento.getDataVencimento().getTimeInMillis());
			
			stmt.setDate(7, dataV);
			
			
			
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
	
	public void update(InvestimentoUsuario investimento) throws SQLException{
		
		PreparedStatement stmt = null;

		try {
			
			conexao = FintechDBManager.getInstance().getConnection();
			
			String sql = "UPDATE T_INVESTIMENTO SET DS_TIPO = ?, T_BANCO_CD_BANCO = ?, NM_INVESTIMENTO = ?, VL_APLICACAO = ?, DT_INVESTIMENTO = ?, DT_VENCIMENTO = ? WHERE CD_INVESTIMENTO = ?";
			
			stmt = conexao.prepareStatement(sql);
			
			stmt.setString(1, investimento.getTipoInvestimento());
			stmt.setInt(2, investimento.getNomeBancoOuCorretora().getCodigo());
			stmt.setString(3, investimento.getNomeInvestimento());
			stmt.setDouble(4, investimento.getValorAplicacao());
			
			java.sql.Date dataI = new java.sql.Date(investimento.getDataInvestimento().getTimeInMillis());
			stmt.setDate(5, dataI);
			
			java.sql.Date dataV = new java.sql.Date(investimento.getDataVencimento().getTimeInMillis());
			stmt.setDate(6, dataV);
			
			stmt.setInt(7, investimento.getCodigo());
			
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
			String sql = "DELETE FROM T_INVESTIMENTO WHERE CD_INVESTIMENTO = ?";
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
	
	public InvestimentoUsuario select(int codigo){
		PreparedStatement stmt = null;
		ResultSet rs = null;
		InvestimentoUsuario investimento = null;
		
		try {

			conexao = FintechDBManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT"
		    		+ " CD_INVESTIMENTO,"
					+ " T_USUARIO_NR_CPF,"
		      		+ " T_BANCO_CD_BANCO,"
		    		+ " BC.NM_BANCO,"
		      		+ " IV.DS_TIPO," 
		      		+ " IV.NM_INVESTIMENTO,"
		      		+ " IV.VL_APLICACAO,"
		      		+ " IV.DT_INVESTIMENTO,"
		      		+ " IV.DT_VENCIMENTO"
		      		+ " FROM"
		      		+ " T_INVESTIMENTO IV"
		      		+ " INNER JOIN T_BANCO BC ON IV.T_BANCO_CD_BANCO = BC.CD_BANCO"
		      		+ " WHERE"
		      		+ " CD_INVESTIMENTO = ?");
		      		
			stmt.setInt(1, codigo);
			rs = stmt.executeQuery();
			if(rs.next()) {
				
				int cod = rs.getInt("CD_INVESTIMENTO");
				String cpf = rs.getString("T_USUARIO_NR_CPF");
				String nome = rs.getString("NM_INVESTIMENTO");
				double valor = rs.getDouble("VL_APLICACAO");
				java.sql.Date dataInvestimento = rs.getDate("DT_INVESTIMENTO");
		        java.sql.Date dataVencimento = rs.getDate("DT_VENCIMENTO");
		        String tipo = rs.getString("DS_TIPO");
		        Calendar dataI = Calendar.getInstance();
		        dataI.setTimeInMillis(dataInvestimento.getTime());
		        Calendar dataV = Calendar.getInstance();
		        dataV.setTimeInMillis(dataVencimento.getTime());
		        int codigoCategoria = rs.getInt("T_BANCO_CD_BANCO"); 
				String nomeCategoria = rs.getString("NM_BANCO");
				
		        investimento = new InvestimentoUsuario(tipo, nome, valor, dataI, dataV);
		        investimento.setCodigo(cod);
		        
		        Usuario u = new Usuario();
		        u.setCpfUsuario(cpf);
		        investimento.setCpf(u);
				NomeBancoCategoria categoria = new NomeBancoCategoria(codigoCategoria,nomeCategoria); 

				investimento.setNomeBancoOuCorretora(categoria); 
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
		return investimento;
	}
	
	public List<InvestimentoUsuario> getAll(String cpf){
	    
	    List<InvestimentoUsuario> lista = new ArrayList<InvestimentoUsuario>();
	    
	    PreparedStatement stmt = null;
	    
	    ResultSet rs = null;
	    
	    InvestimentoUsuario investimento = null;
	    
	    try {
	    	
	      conexao = FintechDBManager.getInstance().getConnection();
	      
	      stmt = conexao.prepareStatement("SELECT"
		    	+ " CD_INVESTIMENTO,"
	    		+ " T_USUARIO_NR_CPF,"
		      	+ " T_BANCO_CD_BANCO,"
		    	+ " BC.NM_BANCO,"
		      	+ " IV.DS_TIPO,"
		      	+ " IV.NM_INVESTIMENTO,"
		   		+ " IV.VL_APLICACAO,"
		   		+ " IV.DT_INVESTIMENTO,"
	      		+ " IV.DT_VENCIMENTO"
	      		+ " FROM"
		      	+ " T_INVESTIMENTO IV"
		      	+ " INNER JOIN T_BANCO BC ON IV.T_BANCO_CD_BANCO = BC.CD_BANCO"
	      		+ " WHERE"
	      		+ " IV.T_USUARIO_NR_CPF = ?"
	      		+ " ORDER BY DT_INVESTIMENTO DESC");
	      stmt.setString(1, cpf);
	      rs = stmt.executeQuery();
	    
	      while (rs.next()) {
	    	  int cod = rs.getInt("CD_INVESTIMENTO");
	    	  String nome = rs.getString("NM_INVESTIMENTO");
	    	  double valor = rs.getDouble("VL_APLICACAO");
	    	  java.sql.Date dataInvestimento = rs.getDate("DT_INVESTIMENTO");
		      java.sql.Date dataVencimento = rs.getDate("DT_VENCIMENTO");
		      String tipo = rs.getString("DS_TIPO");
		      Calendar dataI = Calendar.getInstance();
		      dataI.setTimeInMillis(dataInvestimento.getTime());
		      Calendar dataV = Calendar.getInstance();
		      dataV.setTimeInMillis(dataVencimento.getTime());
		      int codigoCategoria = rs.getInt("T_BANCO_CD_BANCO"); 
		      String nomeCategoria = rs.getString("NM_BANCO");
			
		      investimento = new InvestimentoUsuario(tipo, nome, valor, dataI, dataV);
		      investimento.setCodigo(cod);
		      
		       Usuario u = new Usuario();
		       investimento.setCpf(u);
		      
		      NomeBancoCategoria categoria = new NomeBancoCategoria(codigoCategoria, nomeCategoria); 

		      investimento.setNomeBancoOuCorretora(categoria);
	   
	        
		      lista.add(investimento);
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
	    return lista;
	  }
	
}
