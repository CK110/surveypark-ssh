package surveypark;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.njfu.surveypark.model.User;
import com.njfu.surveypark.service.UserService;


public class TestUserService {
	private static UserService us;
	
	@SuppressWarnings("resource")
	@BeforeClass
	public static void iniUserService(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		us = (UserService) ac.getBean("userService");
	}
	@Test
	public void test() {
		User u = new User();
		u.setEmail("ck@hotmail.com");
		u.setPassword("123456");
		u.setNickName("stone");
		us.saveEntity(u);
	}

}
