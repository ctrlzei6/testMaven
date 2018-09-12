package service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import javabean.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.UserDao;


public class UserService {

	public static User addUser(HttpServletRequest request)
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
			List<FileItem> items = upload.parseRequest(request);//items存放各表单元素
			//Good good=new Good();
			User u = new User();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			u.setRegisterDate(sdf.format(new Date()));
			u.setUserEnable("use");
			
			Iterator<FileItem> iter = items.iterator();
			String s = "";
			System.out.println("到这里了111");
			while (iter.hasNext()) {//遍历表单元素
			    FileItem item = iter.next();

			    if (item.isFormField()) {//非上传文件表单元素
			        String name = item.getFieldName();//获取表单元素的name属性
			        
			        if("username".equals(name)){
			        	u.setUsername(item.getString("UTF-8"));
			        }else if("password".equals(name)){
			        	u.setPassword(item.getString("UTF-8"));
			        }else if("userGender".equals(name)){//uedit上传的内容的数据名称是：editorValue
			        	u.setUserGender(item.getString("UTF-8"));
			        }else if("userType".equals(name)){
			        	u.setUserType(item.getString("UTF-8"));
			        }else if("userHobby".equals(name)){
			        	s += "," + item.getString("UTF-8");
			        	System.out.println("1--"+s);
			        	
			        }
			        
			    } else {//上传文件		
			    	File uploadedFile = new File(request.getServletContext().getRealPath("/upload/images/"+item.getName()));
			        item.write(uploadedFile);//将临时文件转存为新文件保存（有同名文件，将被覆盖）
			        item.delete();//删除临时文件
			        u.setUserImage(request.getContextPath()+"/upload/images/"+item.getName());
			    }
			    System.out.println("到这里了222");
			  
			}
			s = s.substring(1);
		    System.out.println("S=="+s);
        	u.setUserHobby(s);
			return u;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean checkUser(User user)
	{
		if("use".equals(user.getUserEnable()))
		{
			if(UserDao.changeUserEnable(user,"stop"))
				return true;
			else
				return false;
		}
		else{
			if(UserDao.changeUserEnable(user,"use"))
				return true;
			else
				return false;
		}
	}
}
