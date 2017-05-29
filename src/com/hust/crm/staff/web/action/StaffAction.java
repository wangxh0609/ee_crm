package com.hust.crm.staff.web.action;

import java.util.List;

import javax.servlet.ServletRequest;

import org.apache.catalina.core.ApplicationContext;
import org.apache.struts2.ServletActionContext;

import com.hust.crm.department.domain.CrmDepartment;
import com.hust.crm.department.service.DepartmentService;
import com.hust.crm.staff.domain.CrmStaff;
import com.hust.crm.staff.service.StaffService;
import com.hust.crm.utils.MyStringUtils;
import com.opensymphony.xwork2.ActionChainResult;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class StaffAction extends ActionSupport implements ModelDriven<CrmStaff> {
	//封装数据
	private CrmStaff staff = new CrmStaff();
	@Override
	public CrmStaff getModel() {
		return staff ;
	}
	//默认按照名称注入
	private StaffService staffService;
	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}
	//部门按名称注入
	private DepartmentService departmentService;
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	private String oldPassword;
	private String newPassword;
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getReNewPassword() {
		return reNewPassword;
	}
	public void setReNewPassword(String reNewPassword) {
		this.reNewPassword = reNewPassword;
	}
	private String reNewPassword;
	///////////////////////////////////////////////
	
	
	/**
	 * 员工登录
	 * @return
	 */
	public String login(){
		//1 查询员工
		CrmStaff findStaff = staffService.login(staff);
		//2 是否成功
		if(findStaff != null){
			//成功
			// 3.1 session作用域保存数据
			ActionContext.getContext().getSession().put("loginStaff", findStaff);
			// 3.2 重定向首页 -- xml配置			
			return "success";
		} 
		
		//4 不成功
		this.addFieldError("", "用户名与密码不匹配");
		// * 请求转发显示
		return "login";
	}
	public String logout(){
		Object obj=ActionContext.getContext().getSession().get("loginStaff");
		if(obj!=null){		
			ActionContext.getContext().getSession().clear();
		}		
		return "login";
	}
	
	public String editPassword(){
		CrmStaff m_crmStaff=null;
		Object obj=ActionContext.getContext().getSession().get("loginStaff");				
		if(obj!=null){			
			if(obj instanceof CrmStaff){
				m_crmStaff=(CrmStaff)obj;			
			}			
		}		
		if(MyStringUtils.getMD5Value(getOldPassword()).equals(m_crmStaff.getLoginPwd())&&getNewPassword().equals(getReNewPassword())){			
			m_crmStaff.setLoginPwd(getNewPassword());
			staffService.updatePassword(m_crmStaff);
			return "repwd";	
		}
		else{
			return "pwd";
		}
			
	}
	
	/**
	 * 显示首页 
	 * @return
	 */
	public String home(){
		return "home";
	}
	
	
	public String findAll(){
		List<CrmStaff> allStaff=staffService.findAllStaff();
		//将查询结果传入值栈，方便JSP获取数据
		/*方式1：context(map)存放put(key,value),jsp页面获得“#key” 
		 * ActionContext.getContext().put(key, value);
		 * 
		 * 方式2：root(值栈), push(obj)  一般数据为JavaBean或Map jsp页面获得“属性”或“key” 		
		 * ActionContext.getContext().getValueStack().push(o);
		 * 
		 * 方式3：root(值栈)  set(key,value) 一般数据为集合时（如List）  jsp页面获得“key”
		 */
		//使用context存放数据
		ActionContext.getContext().put("allStaff", allStaff);
		return "findAll";
	}
	
	public String editUI(){
		
		CrmStaff findStaff=this.staffService.findById(staff.getStaffId());		
		//	ActionContext.getContext().put("abc", value); 这种方式界面上得到用#abc.莫个值
		ActionContext.getContext().getValueStack().push(findStaff);
		
		//查询所有部门
		List<CrmDepartment> allDepartment=departmentService.findAll();
		//jsp 页面通过key获得
		ActionContext.getContext().getValueStack().set("allDepartment", allDepartment);
		
		return "editUI";
	}

}
