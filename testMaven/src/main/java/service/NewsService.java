package service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javabean.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.NewsDao;
import dao.UserDao;

public class NewsService {
	public static News addNews(HttpServletRequest request)
	{
		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Configure a repository (to ensure a secure temp location is used)
		String fullPath=request.getServletContext().getRealPath("/temp");//获取相对路径的绝对路径
		System.out.println("fullPath==="+fullPath);
		File temp = new File(fullPath);
		factory.setRepository(temp);//设置临时文件存放的文件夹
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 解析request，将其中各表单元素和上传文件提取出来
		try {
			List<FileItem> items = upload.parseRequest(request);//items存放各表单元素);
			News news = new News();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			news.setPublishTime(sdf.format(new Date()));
			news.setNewsEnable("stop");//发表新闻需要经过管理员的同意
			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {//遍历表单元素
			    FileItem item = iter.next();

			    if (item.isFormField()) {//非上传文件表单元素
			        String name = item.getFieldName();//获取表单元素的name属性
			        if("author".equals(name)){
			        	news.setAuthor(item.getString("UTF-8"));
			        }else if("newsType".equals(name)){
			        	news.setNewsType(item.getString("UTF-8"));
			        }else if("newsTime".equals(name)){
			        	news.setNewsTime(item.getString("UTF-8"));
			        }else if("caption".equals(name)){
			        	news.setNewsTime(item.getString("UTF-8"));
			        }else if("editorValue".equals(name)){//uedit上传的内容的数据名称是：editorValue
			        	news.setContent(item.getString("UTF-8"));
			        }
			        
			    } 		    
			}
			return news;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean checkNews(News news)
	{
		if("use".equals(news.getNewsEnable()))
		{
			if(NewsDao.changeNewsEnable(news,"stop"))
				return true;
			else
				return false;
		}
		else{
			if(NewsDao.changeNewsEnable(news,"use"))
				return true;
			else
				return false;
		}
	}
	
	public static ArrayList<News> searchNews(String search)
	{
		ArrayList<News> newsList = NewsDao.getAllNews();
		ArrayList<News> captionList = new ArrayList<News>();
		News news = new News();
		for(int i=0;i<newsList.size();i++)
		{
			System.out.println(newsList.get(i).getCaption());
			if(newsList.get(i).getCaption().contains(search)){
				news= newsList.get(i);
				System.out.println("news=="+news);
				captionList.add(news);
			}
		}
		return captionList;
	}
}
