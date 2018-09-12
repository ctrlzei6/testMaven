package junit;

import static org.junit.Assert.*;
import javabean.User;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import service.UserService;

public class TestUserService {
	static protected User user;
	static protected UserService userService;
	@BeforeClass
	static public void beforeClass()
	{
		System.out.println("before class --- TestUserService");
		user = new User();
		user.setUsername("admin");
		user.setPassword("123456");
		user.setUserType("use");
		userService = new UserService();
	}
	@Test
	public void testCheckUser() {
		assertNotNull(userService.checkUser(user));
	}
	
	@AfterClass
	static public void afterClass()
	{
		System.out.println("after class --- TestUserService");
	}

}
