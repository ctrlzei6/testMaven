package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javabean.*;

public class UserDao {
	public static ArrayList<User> getAllUsers()
	{
		Connection connect = null;
		Statement stmt=null;
		ResultSet rs = null;
		ArrayList<User> userList = new ArrayList<User>();
		String sql = "SELECT * FROM users ";//SQL语句,取出图书库中所有数据
		
		try {
			connect = DatabaseDao.getConnextion();
			stmt = connect.createStatement();//创建SQL语句对象
			rs = stmt.executeQuery(sql);
			
			if(rs!=null)
			{
				//读取结果集的数据
				while(rs.next())
				{
					User user = new User();
					user.setUserId(rs.getString("userId"));
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setUserImage(rs.getString("userImage"));
					user.setRegisterDate(rs.getString("registerDate"));
					user.setUserGender(rs.getString("userGender"));
					user.setUserHobby(rs.getString("userHobby"));
					user.setUserType(rs.getString("userType"));
					user.setUserEnable(rs.getString("userEnable"));
					userList.add(user);
				}
				return userList;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			// 释放数据集对象
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		//如果没有读取成功
		return null;
	}
	public static ArrayList<User> getAllUsers(Page page)
	{
		Connection connect = null;
		Statement stmt=null;
		ResultSet rs = null;
		ArrayList<User> userList = new ArrayList<User>();
		int start = page.getStart()-1;
		String sql = "SELECT * FROM users limit "+start+","+page.getCount();//SQL语句,取出图书库中所有数据
		
		try {
			//连接MySQL的shopping数据库
			connect = DatabaseDao.getConnextion();
			stmt = connect.createStatement();//创建SQL语句对象
			rs = stmt.executeQuery(sql);
			
			if(rs!=null)
			{
				//读取结果集的数据
				while(rs.next())
				{
					User user = new User();
					user.setUserId(rs.getString("userId"));
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setUserImage(rs.getString("userImage"));
					user.setRegisterDate(rs.getString("registerDate"));
					user.setUserGender(rs.getString("userGender"));
					user.setUserType(rs.getString("userType"));
					user.setUserHobby(rs.getString("userHobby"));
					user.setUserEnable(rs.getString("userEnable"));
					userList.add(user);
				}
				return userList;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			// 释放数据集对象
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		//如果没有读取成功
		return null;
	}

	public static int getUsersCount()
	{
		Connection connect = null;
		Statement stmt=null;
		ResultSet rs = null;
		
		String sql = "SELECT count(*) FROM users ";//SQL语句,
		
		try {
			connect = DatabaseDao.getConnextion();
			stmt = connect.createStatement();//创建SQL语句对象
			rs = stmt.executeQuery(sql);
			if(rs!=null)
			{
				//读取结果集的数据
				while(rs.next())
				{
					return rs.getInt(1);
					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			// 释放数据集对象
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		//如果没有读取成功
		return 0;
	}
	//用户注册
	public static boolean userRegister(User user)
	{
		ArrayList<User> userList = getAllUsers();//获得所有用户
		System.out.println(userList.size()+user.getUsername());
		for(User u : userList){
			if(user.getUsername().equals(u.getUsername())){
				System.out.println("用户"+user.getUsername()+"已存在，无法注册！");
				return false;
			}
		}
		
		Connection connect = null;
		Statement stmt=null;
		
		String sql = "INSERT INTO users(username,password,userType,userImage,userGender,userHobby,registerDate,userEnable) VALUES ('"+user.getUsername()+"', '"+user.getPassword()+"','"+user.getUserType()+"','"+user.getUserImage()+"','"+user.getUserGender()+"','"+user.getUserHobby()+"','"+user.getRegisterDate()+"','"+user.getUserEnable()+"')";
		System.out.println(sql);
		try {
			connect = DatabaseDao.getConnextion();
			stmt = connect.createStatement();//创建SQL语句对象
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("用户"+user.getUsername()+"注册成功！");
		return true;
	}
	
	//检查用户是否存在
	public static boolean userCheck(User user)
	{
		System.out.println("----"+user.toString());
		ArrayList<User> userList = getAllUsers();//获得所有用户
		System.out.println("userCheck(User user)===userList   length=="+userList.size());
		for(User u : userList){
			if(user.getUsername().equals(u.getUsername()) && user.getPassword().equals(u.getPassword()) ){
				System.out.println("userCheck(User user)======true");
				return true;
			}
		}
		System.out.println("userCheck(User user)======false");
		return false;
	}
	
	
	public static User getUserByName(String username)
	{
		User user = new User();
		Connection connect = null;
		Statement stmt=null;
		ResultSet rs = null;

		String sql = "SELECT * FROM users WHERE username="+"'"+username+"'";//SQL语句
		System.out.println("sql="+sql);
		try {
			connect = DatabaseDao.getConnextion();
			stmt = connect.createStatement();//创建SQL语句对象
			rs = stmt.executeQuery(sql);
			if(rs!=null){
				//读取结果集的数据
				if(rs.next())
				{	
					user.setUserId(rs.getString("userId"));
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setUserImage(rs.getString("userImage"));
					user.setRegisterDate(rs.getString("registerDate"));
					user.setUserGender(rs.getString("userGender"));
					user.setUserHobby(rs.getString("userHobby"));
					user.setUserType(rs.getString("userType"));
					user.setUserEnable(rs.getString("userEnable"));
					return user;
				}else{
					return null;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			// 释放数据集对象
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			// 释放语句对象
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return user;
	}

	public static User getUserById(String userId)
	{
		User user = new User();
		Connection connect = null;
		Statement stmt=null;
		ResultSet rs = null;

		String sql = "SELECT * FROM users WHERE userId="+"'"+userId+"'";//SQL语句
		System.out.println("sql="+sql);
		try {
			connect = DatabaseDao.getConnextion();
			stmt = connect.createStatement();//创建SQL语句对象
			rs = stmt.executeQuery(sql);
			if(rs!=null){
				//读取结果集的数据
				if(rs.next())
				{	
					user.setUserId(rs.getString("userId"));
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setUserImage(rs.getString("userImage"));
					user.setRegisterDate(rs.getString("registerDate"));
					user.setUserGender(rs.getString("userGender"));
					user.setUserHobby(rs.getString("userHobby"));
					user.setUserType(rs.getString("userType"));
					user.setUserEnable(rs.getString("userEnable"));
					return user;
				}else{
					return null;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			// 释放数据集对象
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			// 释放语句对象
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return user;
	}	
	
	//用户登录检验
	public static boolean loginCheck(User user)
	{
		if(userCheck(user))  
			return true;
		else
			return false;
	}
	//用户改密码
	public static boolean userChangePassword(User user,String newPassword)
	{
		if(userCheck(user)){//如果用户存在
			System.out.println("用户"+user.getUsername()+"存在，可以进行修改密码！");
			Connection connect = null;
			Statement stmt=null;
			String sql = "UPDATE users SET password = '"+newPassword+"'WHERE username = '"+user.getUsername()+"' and userType='"+user.getUserType()+"'";
			System.out.println(sql);
			try {
				connect = DatabaseDao.getConnextion();
				stmt = connect.createStatement();//创建SQL语句对象
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("用户"+user.getUsername()+"修改密码成功！");
			return true;
		}
		
		//用户不存在
		System.out.println("用户"+user.getUsername()+"修改密码失败！");
		return false;
	}
	//用户审查
	public static boolean changeUserEnable(User user,String userEnable)
	{
		if(userCheck(user)){//如果用户存在
			System.out.println("用户"+user.getUsername()+"存在，可以进行修改密码！");
			Connection connect = null;
			Statement stmt=null;
			String sql = "UPDATE users SET userEnable = '"+userEnable+"'WHERE username = '"+user.getUsername()+"'";
			System.out.println(sql);
			try {
				connect = DatabaseDao.getConnextion();
				stmt = connect.createStatement();//创建SQL语句对象
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("用户"+user.getUsername()+"审查成功！");
			return true;
		}
		
		//用户不存在
		System.out.println("用户"+user.getUsername()+"审查失败！");
		return false;
	}
}
