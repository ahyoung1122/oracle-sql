package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import oracle.jdbc.proxy.annotation.Pre;
import vo.Emp;

public class EmpDAO {
	//q004WhereIn.jsp
	public static ArrayList<Emp> selectEmpListByGrade(
			ArrayList<Integer> ckList) throws Exception{
		ArrayList<Emp> list = new ArrayList<>();
		
		Connection conn = DBHelper.getConnection();
		//In연산자를 활용해서 sql작성하기
		String sql = "SELECT ename, grade"
				+ " FROM emp"
				+ " WHERE grade IN ";
		PreparedStatement stmt = null;
		//ckList는 ArrayList<Integer>에서 작성된것
		//여기에는 등급을 나타내는 정수값이 들어가있음.
		//ckList.size로 size를 이용하는 이유 = 가변적인 정수길이이기때문
		if(ckList.size() == 1) {
			sql = sql + "(?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ckList.get(0));
		}else if(ckList.size() == 2) {
			sql = sql + "(?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ckList.get(0));
			stmt.setInt(2, ckList.get(1));
		}else if(ckList.size() == 3) {
			sql = sql + "(?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ckList.get(0));
			stmt.setInt(2, ckList.get(1));
			stmt.setInt(3, ckList.get(2));
		}else if(ckList.size() == 4){
			sql = sql + "(?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ckList.get(0));
			stmt.setInt(2, ckList.get(1));
			stmt.setInt(3, ckList.get(2));
			stmt.setInt(4, ckList.get(3));
		}else if(ckList.size() == 5) {
			sql = sql + "(?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ckList.get(0));
			stmt.setInt(2, ckList.get(1));
			stmt.setInt(3, ckList.get(2));
			stmt.setInt(4, ckList.get(3));
			stmt.setInt(5, ckList.get(4));
		}
		
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) 
			{
				Emp e = new Emp();
				e.setEname(rs.getString("ename"));
				e.setGrade(rs.getInt("grade"));
				list.add(e);
			}
		/*
		 where grade in(1) //매개값에 따라서 달라지는것임.
		 */
		return list;
	}
	
	
	
	//q003Case.jsp
	public static ArrayList<HashMap<String,String>> selectJobCaseList() 
			throws Exception {
		ArrayList<HashMap<String,String>> color = 
				new ArrayList<HashMap<String,String>>();
		
		Connection conn = DBHelper.getConnection();
		
		String sql = 
				"SELECT ename,job, CASE\r\n"
				+ "					        WHEN job = 'PRESIDENT' Then '빨강'\r\n"
				+ "					        WHEN job = 'MANAGER' THEN '주황'\r\n"
				+ "					        WHEN job = 'ANALYST' THEN '노랑'\r\n"
				+ "					        WHEN job = 'CLERK' THEN '초록'\r\n"
				+ "					        ELSE '파랑' END color\r\n"
				+ "		FROM emp\r\n"
				+ "		ORDER BY (CASE \r\n"
				+ "			        WHEN color = '빨강' THEN 1\r\n"
				+ "			        WHEN color = '주황' THEN 2\r\n"
				+ "			        WHEN color = '노랑' THEN 3\r\n"
				+ "			        WHEN color = '초록' THEN 4\r\n"
				+ "			        ELSE 5 END) ASC\r\n";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String,String> c = 
				new HashMap<String,String>();
					c.put("job", rs.getString("job"));
					c.put("color", rs.getString("color"));
			color.add(c);
		}	
		return color;
	}
	
	//deptNo뒤에 부서별 인원 같이 조회하는 메서드
	//부서넘버랑 cnt둘다 int타입
	public static ArrayList<HashMap<String,Integer>> selectDeptNoCntList() 
			throws Exception{
		ArrayList<HashMap<String,Integer>> list = 
				new ArrayList<>();
		
		Connection conn = DBHelper.getConnection();
		String sql = "SELECT deptno deptNo, COUNT(*) cnt "
				+ "FROM emp "
				+ "WHERE deptno IS NOT NULL GROUP BY deptno "
				+ "ORDER BY deptno ASC";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String,Integer> m = 
				new HashMap<>();
					m.put("cnt", rs.getInt("cnt"));
					m.put("deptNo", rs.getInt("deptNo"));
			list.add(m);
		}
		
		conn.close();
		return list; 
	}
	
	
	
	//deptno목록을 출력하는 메서드
	public static ArrayList<Integer> selectDeptNoList() throws Exception{
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		Connection conn = DBHelper.getConnection();
		String sql ="SELECT DISTINCT deptno deptNo "
				+ "FROM emp "
				+ "WHERE deptno "
				+ "IS NOT NULL "
				+ "ORDER BY deptno ASC"; //null이 아닌것을 뽑아낸다
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Integer i = rs.getInt("deptNo"); //랩퍼 타입과 기본타입간에 Auto Boxing일어남.
			list.add(i);
		}
		
		conn.close();
		
		return list;
	}
	
	
	
	// 조인으로 Map을 사용하는 겨우
	public static ArrayList<HashMap<String, Object>> selectEmpAndDeptList()
													throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<>();
	
		Connection conn = DBHelper.getConnection();
		String sql = "SELECT"
				+ " emp.empno empNo, emp.ename ename, emp.deptno deptNo,"
				+ " dept.dname dname"
				+ " FROM emp INNER JOIN dept"
				+ " ON emp.deptno = dept.deptno";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<>();
			m.put("empNo", rs.getInt("empNo"));
			m.put("ename", rs.getString("ename"));
			m.put("deptNo", rs.getInt("deptNo"));
			m.put("dname", rs.getString("dname"));
			list.add(m);
		}
		return list;
	}
	
	// VO 사용
	public static ArrayList<Emp> selectEmpList() throws Exception {
		ArrayList<Emp> list = new ArrayList<>();
		
		Connection conn = DBHelper.getConnection();
		String sql = "SELECT"
				+ " empno empNo, ename, sal"
				+ " FROM emp";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Emp e = new Emp();
			/* e.empNo = rs.getInt("empNo"); */
			e.setEmpNo(rs.getInt("empNo"));
			/* e.ename = rs.getString("ename"); */
			e.setEname(rs.getString("ename"));
			/* e.sal = rs.getDouble("sal"); */
			e.setSal( rs.getDouble("sal"));
			list.add(e);
		}
		
		return list;
	}
}
