package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javabean.News;
import javabean.Page;

public class NewsDao {
	//得到数据库上所有的商品
	public static ArrayList<News> getAllNews(Page page)
	{
		Connection connect = null;
		Statement stmt=null;
		ResultSet rs = null;
		ArrayList<News> newsList = new ArrayList<News>();
		int start = page.getStart()-1;
		String sql = "SELECT * FROM news limit "+start+","+page.getCount();//SQL语句,取出图书库中所有数据
		
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
					News news = new News();
					news.setNewsId(rs.getString("newsId"));
					news.setAuthor(rs.getString("author"));
					news.setNewsType(rs.getString("newsType"));
					news.setCaption(rs.getString("caption"));
					news.setContent(rs.getString("content"));
					news.setNewsTime(rs.getString("newsTime"));
					news.setPublishTime(rs.getString("publishTime"));
					news.setNewsEnable(rs.getString("newsEnable"));
					newsList.add(news);
				}
				return newsList;
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
	//得到数据库上所有的新闻信息
	public static ArrayList<News> getAllNews()
	{
		Connection connect = null;
		Statement stmt=null;
		ResultSet rs = null;
		ArrayList<News> newsList = new ArrayList<News>();
		String sql = "SELECT * FROM news ";//SQL语句,取出图书库中所有数据
		
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
					
					News news = new News();
					news.setNewsId(rs.getString("newsId"));
					news.setAuthor(rs.getString("author"));
					news.setNewsType(rs.getString("newsType"));
					news.setCaption(rs.getString("caption"));
					news.setContent(rs.getString("content"));
					news.setNewsTime(rs.getString("newsTime"));
					news.setPublishTime(rs.getString("publishTime"));
					news.setNewsEnable(rs.getString("newsEnable"));
					newsList.add(news);
				}
				return newsList;
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
	
	// 根据newsId获得news
	public static News getNewsById(String newsId) 
	{
		News news = new News();
		Connection connect = null;
		Statement stmt=null;
		ResultSet rs = null;

		String sql = "SELECT * FROM news WHERE newsId="+newsId;//SQL语句
		System.out.println("sql="+sql);
		try {
			connect = DatabaseDao.getConnextion();
			stmt = connect.createStatement();//创建SQL语句对象
			rs = stmt.executeQuery(sql);
			if(rs!=null){
				//读取结果集的数据
				if(rs.next())
				{	
					news.setNewsId(rs.getString("newsId"));
					news.setAuthor(rs.getString("author"));
					news.setNewsType(rs.getString("newsType"));
					news.setCaption(rs.getString("caption"));
					news.setContent(rs.getString("content"));
					news.setNewsTime(rs.getString("newsTime"));
					news.setPublishTime(rs.getString("publishTime"));
					news.setNewsEnable(rs.getString("newsEnable"));
					return news;
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
		return news;
	}
	
	//得到数据库上所有的商品的数量
	public static int getNewsCount()
	{
		Connection connect = null;
		Statement stmt=null;
		ResultSet rs = null;
		
		String sql = "SELECT count(*) FROM news ";//SQL语句,
		
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
	//得到数据库上所有的商品的数量
	public static int getNewsCount(String userId)
	{
		Connection connect = null;
		Statement stmt=null;
		ResultSet rs = null;
		
		String sql = "SELECT count(*) FROM news WHERE userId='"+userId+"'";//SQL语句,
		System.out.println("sql="+sql);
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
	
	//得到数据库上所有的新闻信息
	public static ArrayList<News> SortNewsByType(String newsType)
	{
		Connection connect = null;
		Statement stmt=null;
		ResultSet rs = null;
		ArrayList<News> newsList = new ArrayList<News>();
		String sql = "SELECT * FROM news where newstype='"+newsType+"'";//SQL语句
		System.out.println("sql=="+sql);
		try {
			connect = DatabaseDao.getConnextion();
			stmt = connect.createStatement();//创建SQL语句对象
			rs = stmt.executeQuery(sql);
			if(rs!=null)
			{
				//读取结果集的数据
				while(rs.next())
				{
					
					News news = new News();
					news.setNewsId(rs.getString("newsId"));
					news.setAuthor(rs.getString("author"));
					news.setNewsType(rs.getString("newsType"));
					news.setCaption(rs.getString("caption"));
					news.setContent(rs.getString("content"));
					news.setNewsTime(rs.getString("newsTime"));
					news.setPublishTime(rs.getString("publishTime"));
					news.setNewsEnable(rs.getString("newsEnable"));
					newsList.add(news);
				}
				return newsList;
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
	
	public static boolean addNews(News news)
	{
		Connection connect = null;
		Statement stmt=null;
		String sql = "INSERT INTO news(author,newsType,caption,content,newsTime,publishTime,newsEnable) " + "VALUES ('"+news.getAuthor()+"', '"+news.getNewsType()+"','"+news.getCaption()+"','"+news.getContent()+"','"+news.getNewsTime()+"','"+news.getPublishTime()+"','"+news.getNewsEnable()+"')";
		System.out.println(sql);
		try {
			connect = DatabaseDao.getConnextion();
			stmt = connect.createStatement();//创建SQL语句对象
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("news"+news.getCaption()+"添加成功！");
		return true;
	}
	
	public static boolean newsCheck(News news)
	{
		ArrayList<News> newsList = getAllNews();//获得所有用户
		System.out.println("newsCheck==newsList   length=="+newsList.size());
		for(News n : newsList){
			if(news.getNewsId().equals(n.getNewsId()) && news.getAuthor().equals(n.getAuthor()) && news.getCaption().equals(n.getCaption()) ){
				System.out.println("userCheck(User user)======true");
				return true;
			}
		}
		System.out.println("newsList(News news)======false");
		return false;
	}

	public static boolean changeNewsEnable(News news,String newsEnable)
	{
		if(newsCheck(news)){//如果用户存在
			System.out.println(news.getNewsId()+"存在");
			Connection connect = null;
			Statement stmt=null;
			String sql = "UPDATE news SET newsEnable = '"+newsEnable+"'WHERE newsId = '"+news.getNewsId()+"'";
			System.out.println(sql);
			try {
				connect = DatabaseDao.getConnextion();
				stmt = connect.createStatement();//创建SQL语句对象
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(news.getNewsId()+"新闻"+news.getCaption()+"审查成功！");
			return true;
		}
		
		System.out.println("审查失败！");
		return false;
	}
	

}
