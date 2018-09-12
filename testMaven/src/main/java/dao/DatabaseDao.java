package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//数据库连接类
public class DatabaseDao {

	private static final String driver = "com.mysql.jdbc.Driver"; //数据库驱动
	//连接数据库的URL地址,连接到newsManageSystem数据库
	private static final String url="jdbc:mysql://localhost:3306/ss?useUnicode=true&characterEncoding=UTF-8"; 
	private static final String username="root";//数据库的用户名
	private static final String password="";//数据库的密码
	
	private static Connection connect = null;
	
	//静态代码块负责加载驱动
	static
	{
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//单例模式表示只有唯一的一个对象
	//单例模式返回数据库连接对象
	public static Connection getConnextion() throws SQLException
	{
		if(connect==null)
		{
			connect=DriverManager.getConnection(url, username, password);
			return connect;
		}
		return connect;
	}
	
	//测试数据库连接
	public static void main(String[] args) {
		try {
			Connection conn=DatabaseDao.getConnextion();
			if(conn!=null)
			{
				System.out.println("数据库连接正常！");
			}
			else
			{
				System.out.println("数据库连接异常！");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
