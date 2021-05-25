package nt.makery.address.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class EmpDAO {
	
	
	/*** VARIABLES GLOBALS ***/
	private  Connection connection;
	private  PreparedStatement ps;
	private ResultSet rs;
	
	

	private Connection getConnection() throws SQLException {
		Connection conn;
		ConnectionDB connDB = new ConnectionDB();
		conn = connDB.getConnection();
		return conn;
	}
	

	
	
	/**
	 * Obté tots els empleats de la base de dades
	 * @throws SQLException 
	 */
	public ArrayList<EmpVO> obtenirTotsEmpleats() throws SQLException{
		
		EmpVO empVOAux;
		ArrayList<EmpVO> empleatsList ;
		
		try {
			connection = getConnection();
			System.out.println("Dins");
			ps = connection.prepareStatement("SELECT * FROM EMP");
			rs = ps.executeQuery();
			empleatsList = new ArrayList<EmpVO>();
			while (rs.next()) {
				empVOAux = new EmpVO();
				empVOAux.setEmpno(Integer.parseInt(rs.getString(1)));
				empVOAux.setEname(rs.getString(2));
				empVOAux.setJob(rs.getString(3));
				empVOAux.setMgr(rs.getString(4));
				empVOAux.setHiredate(rs.getString(5));
				empVOAux.setSalary(rs.getString(6));
				empVOAux.setComm(rs.getString(7));
				empVOAux.setDeptno(rs.getString(8));
				
				empleatsList.add(empVOAux);
			}
			return empleatsList;
		}
		catch(SQLException e) {
			System.err.println(e.getMessage());
			throw e;
		}
		finally {
			try {
				if(rs!= null) rs.close();
				if(ps!=null) ps.close();
				if(connection!=null) connection.close();
			}catch(SQLException e) {
				System.err.println(e.getMessage());
			}catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		
	}
	
	public  void deleteEMP(int nEmpno) throws SQLException {
		try {
			connection = getConnection();
			System.out.println("Dins deleteEMP");
			ps = connection.prepareStatement("DELETE FROM EMP WHERE EMPNO = ?");
			ps.setInt(1, nEmpno);
			
			ps.executeUpdate();
		}
		catch(SQLException e) {
			System.err.println("deleteEMP" + e.getMessage());
			throw e;
		}
		finally {
			try {
				if(ps!=null) ps.close();
				if(connection!=null) connection.close();
			}catch(SQLException e) {
				System.err.println("deleteEMP" + e.getMessage());
			}catch (Exception e) {
				System.err.println("deleteEMP" + e.getMessage());
			}
		}
	}
}
	
	
