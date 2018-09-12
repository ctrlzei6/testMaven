package junit;

import static org.junit.Assert.*;
import javabean.Page;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.UserDao;

public class TestUserDao {

	static protected UserDao userDao;
	static protected Page page;
	@BeforeClass
	static public void beforeClass()
	{
		System.out.println("before class --- TestUserDao");
		userDao = new UserDao();
		page = new Page(1,3);
	}
	@Test
	public void testGetAllUsers() throws Exception {
		assertNotNull(userDao.getAllUsers());//判断是否非空
	}
	@Test
	public void testGetAllUsersPage() throws Exception {
		assertEquals(userDao.getAllUsers(page).size(),3);//判断长度是否为3
	}
	@Test
	public void testGetUsersCount() throws Exception {
		assertEquals(userDao.getUsersCount(),15);//判断长度是否为15
	}
	
	@AfterClass
	static public void afterClass()
	{
		System.out.println("after class --- TestUserDao");
	}

}
