package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javabean.News;
import javabean.Page;
import javabean.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.NewsService;
import service.UserService;
import dao.NewsDao;
import dao.UserDao;


public class NewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public NewsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type = request.getParameter("type");
		if("showNews".equals(type))
		{
			//String start = request.getParameter("start");
			Page page = new Page(1,3);
			int newsCount = NewsDao.getNewsCount();
			if(newsCount != 0)
			{
				page.setTotal(newsCount);
			}
			if(request.getParameter("start") != null)
			{
				int start = Integer.parseInt(request.getParameter("start"));
				if(start==0)
					start=1;
				page.setStart(start);
			}
			
//			System.out.println(page.getStart()+"-----start");
//			System.out.println(page.getTotal()+"-----total");
			ArrayList<News> newsList = new ArrayList<News>();
			newsList = NewsDao.getAllNews(page);//从数据库上获得所有图书信息
			
			if(newsList != null)
			{
				request.setAttribute("page", page);
				request.setAttribute("newsList", newsList);
				request.getRequestDispatcher("../showNews.jsp").forward(request, response);
			}
			
		}
		else if("showDetailContent".equals(type))
		{
			String newsId = request.getParameter("newsId");
			News news = NewsDao.getNewsById(newsId);
			if(news != null)
			{
				request.setAttribute("news", news);
				request.getRequestDispatcher("../news/showDetailContent.jsp").forward(request, response);;
			}
		}
		else if("sortNewsByType".equals(type))
		{
			String newsType = request.getParameter("newsType");
			ArrayList<News> newsList = NewsDao.SortNewsByType(newsType);
			if(newsList != null)
			{
				request.setAttribute("newsList", newsList);
				request.getRequestDispatcher("../news/sortNewsResult.jsp").forward(request, response);
			}
		}
		else if("addNews".equals(type))
		{
			News news = NewsService.addNews(request);
			System.out.println("1111");
			if(news != null){
				if(NewsDao.addNews(news)){
					System.out.println("2222");
					request.getRequestDispatcher("../index.jsp").forward(request, response);
				}else{
					response.sendRedirect(request.getContextPath()+"/user/registerFailure.jsp");
				}
			}
		}
		else if("checkNews".equals(type))
		{
			User user = (User)request.getSession().getAttribute("user");
			if("manager".equals(user.getUserType())){
				Page page = new Page(1,3);
				int newsCount = NewsDao.getNewsCount();
				if(newsCount != 0)
				{
					page.setTotal(newsCount);
				}
				if(request.getParameter("start") != null)
				{
					int start = Integer.parseInt(request.getParameter("start"));
					if(start==0)
						start=1;
					page.setStart(start);
				}
				ArrayList<News> newsList = new ArrayList<News>();
				newsList = NewsDao.getAllNews(page);//从数据库上获得所有图书信息
				
				if(newsList != null)
				{
					request.setAttribute("page", page);
					request.setAttribute("newsList", newsList);
					request.getRequestDispatcher("../news/newsCheck.jsp").forward(request, response);
				}
			}
			else if("newsAuthor".equals(user.getUserType()))
			{
				Page page = new Page(1,3);
				int newsCount = NewsDao.getNewsCount(user.getUserId());
				if(newsCount != 0)
				{
					page.setTotal(newsCount);
				}
				if(request.getParameter("start") != null)
				{
					int start = Integer.parseInt(request.getParameter("start"));
					if(start==0)
						start=1;
					page.setStart(start);
				}
				ArrayList<News> newsList = new ArrayList<News>();
				newsList = NewsDao.getAllNews(page);//从数据库上获得所有图书信息
				
				if(newsList != null)
				{
					request.setAttribute("page", page);
					request.setAttribute("newsList", newsList);
					request.getRequestDispatcher("../news/newsCheck.jsp").forward(request, response);
				}
			}else{
				PrintWriter out = response.getWriter();
				out.print("<h1>用户权限不足或操作失败</h1><h3>请联系管理员</h3>");
			}
			
		}
		else if("manageNews".equals(type))
		{
			User user = (User)request.getSession().getAttribute("user");
			String newsId = request.getParameter("newsId");
			System.out.println("newsId=="+newsId);
			if("newsAuthor".equals(user.getUserType()))
			{
				News news = NewsDao.getNewsById(newsId);
				NewsService.checkNews(news);
				response.sendRedirect("/jspNews/servlet/NewsServlet?type=checkNews");
			}
			else if("manager".equals(user.getUserType()))
			{
				System.out.println("要审查的用户为"+newsId);
				News news = NewsDao.getNewsById(newsId);
				NewsService.checkNews(news);
				response.sendRedirect("/jspNews/servlet/NewsServlet?type=checkNews");
			}
		}
		else if("searchNews".equals(type))
		{
			System.out.println("search==-------------");
			String search = request.getParameter("search");
			System.out.println("search=="+search);
			ArrayList<News> captionList = NewsService.searchNews(search);

			System.out.println("captionList==="+captionList);
			if(captionList.size()>0){
				request.setAttribute("captionList", captionList);
				request.getRequestDispatcher("../news/searchNewsResult.jsp").forward(request, response);
			}else{
				PrintWriter out = response.getWriter();
				out.print("<h1>没有找到匹配的新闻</h1><h3>请重新搜索</h3>");
				out.print("<a href='/jspNews/index.jsp'>返回首页</a>");
			}
		}
	}

}
