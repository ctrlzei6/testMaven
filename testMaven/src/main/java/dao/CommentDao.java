package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javabean.Comment;

public class CommentDao {
	//得到数据库上所有的新闻信息
	public static ArrayList<Comment> getAllComments()
	{
		Connection connect = null;
		Statement stmt=null;
		ResultSet rs = null;
		ArrayList<Comment> commentList = new ArrayList<Comment>();
		String sql = "SELECT * FROM comment ";//SQL语句,取出图书库中所有数据
		
		try {
			connect = DatabaseDao.getConnextion();
			stmt = connect.createStatement();//创建SQL语句对象
			rs = stmt.executeQuery(sql);
			if(rs!=null)
			{
				//读取结果集的数据
				while(rs.next())
				{
					Comment comment = new Comment();
					comment.setCommentId(rs.getString("commentId"));
					comment.setNewsId(rs.getString("newsId"));
					comment.setUserId(rs.getString("userId"));
					comment.setContent(rs.getString("content"));
					comment.setCommentTime(rs.getString("commentTime"));
					commentList.add(comment);
				}
				return commentList;
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
	
	public static boolean addComment(Comment comment)
	{
		Connection connect = null;
		Statement stmt=null;
		
		String sql = "INSERT INTO comment(newsId,userId,content,commentTime) VALUES ('"+comment.getNewsId()+"', '"+comment.getUserId()+"','"+comment.getContent()+"','"+comment.getCommentTime()+"')";
		System.out.println(sql);
		try {
			connect = DatabaseDao.getConnextion();
			stmt = connect.createStatement();//创建SQL语句对象
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("用户"+comment.getUserId()+"评论成功！");
		return true;
	}
}
