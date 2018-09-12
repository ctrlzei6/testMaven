package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommentDao;
import javabean.*;


public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type = request.getParameter("type");
		
		if(type.equals("addComment")){//添加评论
			Comment comment=new Comment();
			User user=(User)request.getSession().getAttribute("user");
			comment.setUserId(user.getUserId());
			comment.setContent(request.getParameter("content"));
			String newsId = request.getParameter("newsId");
			comment.setNewsId(newsId);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			comment.setCommentTime(sdf.format(new Date()));
			
			if(CommentDao.addComment(comment)){
				response.sendRedirect("../servlet/NewsServlet?type=showDetailContent&newsId="+newsId);
				//request.getRequestDispatcher("../servlet/NewsServlet?type=showDetailContent&newsId="+comment.getCommentId()).forward(request, response);
			}else{
				response.sendRedirect("../comment/addCommentFailure.jsp");
			}
		}
			
	}

}
