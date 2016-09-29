package ghc.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import ghc.files.PropertiesOpt;

public class MySqlOpt {	
	private static final String JDBC_PROPERTIES = "src/database.properties";
	private Connection conn;
	public Statement statement;
	
	/**
	 * 动态加载mysql驱动
	 * 形式：or:com.mysql.jdbc.Driver driver = new com.mysql.jdbc.Driver();
	 * or：new com.mysql.jdbc.Driver();
	 */
	public MySqlOpt(){  
        try {
        	final String driver = PropertiesOpt.GetValueByKey(JDBC_PROPERTIES, "jdbc.driver");
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        System.out.println("成功加载MySQL驱动程序");
	}
	
	/**
	 * 连接数据库
	 * MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
	 * 避免中文乱码要指定useUnicode和characterEncoding
	 */
	public void connectDB(){
        try { 	
        	String url = PropertiesOpt.GetValueByKey(JDBC_PROPERTIES, "jdbc.url");
			conn = DriverManager.getConnection(url);
        	//conn = DriverManager.getConnection(HOST+DATABASE,USER,PASSWORD);
			statement = (Statement) conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}      
	}
	
	/**
	 * 数据库查询
	 */
	public void queryDB(){
		String sql = "select * from user_footdata where UserFootId > 322 and UserFootId < 416 and BrandSizeRealSNOOPY is not null";
		try {
			//executeQuery会返回结果的集合，否则返回空值
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {    
				String str1 = rs.getString(2);
				int id = rs.getInt(4);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateDB(){
		String usernick = "dog";
		float sizeSNOOPY = 33.0f;
		float sizeANTA = 35.0f;
		String sql = "UPDATE user_footdata SET "+
				"BrandSizeRealSNOOPY="+sizeSNOOPY+
				",BrandSizeRealANTA="+sizeANTA+
				" WHERE DATE(CreateTime)='2016-09-27' AND UserNick='"+usernick+"'";

		int nSuccessDB = 0;
		int ret = 0;
		try {
			ret = statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}					
		if (ret!=0) {
			nSuccessDB++;
		}
	}

	public void createTable(){
		String sql = "create table student1(NO char(20),name varchar(20),primary key(NO))";
		int result;
		try {
			result = statement.executeUpdate(sql);
			if (result != -1) {
	            System.out.println("创建数据表成功");
	            
	            sql = "insert into student1(NO,name) values('2012001','陶伟基')";
	            result = statement.executeUpdate(sql);

	            sql = "select * from student1";
	            ResultSet rs = statement.executeQuery(sql);//executeQuery会返回结果的集合，否则返回空值
	            System.out.println("学号\t姓名");
	            while (rs.next()) {
	                System.out.println(rs.getString(1) + "\t" + rs.getString(2));
	            }
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close(){
		try {
			conn.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}