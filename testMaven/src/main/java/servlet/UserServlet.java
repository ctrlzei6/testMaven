package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javabean.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;
import dao.UserDao;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type = request.getParameter("type"); 
		User u = new User();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String userType = request.getParameter("userType");
		
		if(username != null && password != null ){
			u.setUsername(username);
			u.setPassword(password);
			u.setUserType(userType);
		}
		
		
		if("showUsers".equals(type))
		{
			Page page = new Page(1,3);
			int newsCount = UserDao.getUsersCount();
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
			
//				System.out.println(page.getStart()+"-----start");
//				System.out.println(page.getTotal()+"-----total");
			ArrayList<User> userList = new ArrayList<User>();
			userList = UserDao.getAllUsers(page);//从数据库上获得所有图书信息
			//System.out.println("length=="+userList.size());
			if(userList != null)
			{
				request.setAttribute("page", page);
				request.setAttribute("userList", userList);
				request.getRequestDispatcher("../user/userShow.jsp").forward(request, response);
			}
			
			
		}
		else if("register".equals(type))
		{
			User user = UserService.addUser(request);
			if("newsAuthor".equals(user.getUserType()) || "manager".equals(user.getUserType())){
				user.setUserEnable("stop");//注册为管理员和新闻发布员必须经过其他任意一个管理员的审核
			}
			if(user != null){
				if(UserDao.userRegister(user)){//检查用户是否已经存在
					System.out.println("注册成功");
					request.getRequestDispatcher("../index.jsp").forward(request, response);
				}else{
					response.sendRedirect(request.getContextPath()+"/user/registerFailure.jsp");
				}
			}
		}
		else if("login".equals(type))
		{
			
			System.out.println("user.toString()=="+u.toString());
			if(UserDao.loginCheck(u)){//检查用户名和密码是否匹配
				User user = UserDao.getUserByName(u.getUsername());
				if("stop".equals(user.getUserEnable())){
					PrintWriter out = response.getWriter();
					out.print("<h1>用户被禁用</h1><h3>请联系管理员</h3>");
				}else{
					String originalUrl = (String)request.getSession().getAttribute("originalUrl");
					request.getSession().setAttribute("user", user);
					System.out.println(user.getUsername()+"登录成功"+"到这里000"+user.getUserType());
					if(originalUrl!=null){
						//String originalUrl = (String)request.getSession().getAttribute("originalUrl");
						request.getRequestDispatcher("../index.jsp").forward(request, response);
					}else{
						request.getRequestDispatcher("../index.jsp").forward(request, response);
					}
				}
			}else{
				response.sendRedirect(request.getContextPath()+"/user/loginFailure.jsp");
			
			}
		}
		else if("logOut".equals(type))
		{
			System.out.println("用户"+username+"从Session中移除");
			request.getSession().removeAttribute("user");
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}
		else if("showPrivate".equals(type))
		{
			User user = (User)request.getSession().getAttribute("user");
			if(user!=null)
				request.getRequestDispatcher("../user/showPrivate.jsp").forward(request, response);
			else{
				PrintWriter out = response.getWriter();
				out.print("<h1>出错了，检查用户是否登录</h1><h3>请联系管理员</h3>");
			}
				
		}
		else if("changePassword".equals(type))
		{
			String newPassword = request.getParameter("newPassword");
			if(UserDao.userChangePassword(u,newPassword)){
				System.out.println(u.getUsername()+"修改密码成功");
				PrintWriter out = response.getWriter();
				out.print("<h1>修改密码成功</h1>");
			}else{
				PrintWriter out = response.getWriter();
				out.print("<h1>修改密码失败</h1><h3>原密码错误</h3>");
			}	
		}
		else if("checkUsers".equals(type))
		{
			User user = (User)request.getSession().getAttribute("user");
			if("manager".equals(user.getUserType())){
				Page page = new Page(1,3);
				int newsCount = UserDao.getUsersCount();
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
				
				ArrayList<User> userList = new ArrayList<User>();
				userList = UserDao.getAllUsers(page);//从数据库上获得所有图书信息
				//System.out.println("length=="+userList.size());
				if(userList != null)
				{
					request.setAttribute("page", page);
					request.setAttribute("userList", userList);
					request.getRequestDispatcher("../user/userCheck.jsp").forward(request, response);
				}
			}else{
				PrintWriter out = response.getWriter();
				out.print("<h1>用户权限不足或操作失败</h1><h3>请联系管理员</h3>");
			}
			
		}
		else if("checkUser".equals(type))
		{
			System.out.println("要审查的用户为"+username);
			User user = UserDao.getUserByName(username);
			UserService.checkUser(user);
			response.sendRedirect("/jspNews/servlet/UserServlet?type=checkUsers");
			//request.getRequestDispatcher("../user/userCheck.jsp").forward(request, response);
		}
	}

}
