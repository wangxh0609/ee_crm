package TestCode;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hust.crm.staff.domain.CrmStaff;
import com.hust.crm.staff.service.StaffService;
import com.hust.crm.staff.service.Impl.StaffServiceImpl;



public class TestCrm {
		@Test
		public void test(){
			String xmlpath="classpath:spring/applicationContext.xml";
			ApplicationContext applicationContext=new ClassPathXmlApplicationContext(xmlpath);
			
			StaffService staffService=(StaffService)applicationContext.getBean("staffService");
			CrmStaff user=new CrmStaff();
			user.setStaffId("2c9091c14c78e58b014c78e5c32a0000");
			user.setLoginName("jack");
			user.setLoginPwd("1");
			staffService.updatePassword(user);
		}

}
