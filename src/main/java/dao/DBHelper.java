package dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBHelper {
	public static Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");		
		System.out.println("드라이브로딩성공");	
		Connection conn = null;
		String dbUrl =  "jdbc:oracle:thin:@oracleahyoung_high?TNS_ADMIN=/oracle_wallet/Wallet_oracleAhyoung";
		String dbUser="admin";
		String dbPw="Gbay08081122!";
		//보안 이슈로 로컬에서 설정파일 로드 
		FileReader fr = new FileReader("c:\\auth\\oracle.properties");
		conn 
		= DriverManager.getConnection(dbUrl, dbUser, dbPw);
		
		return conn;
		
	}
	public static void main(String[] args) throws Exception{
		Connection conn = new DBHelper().getConnection();
		System.out.println(conn+"<==conn");
	}
	
}	
