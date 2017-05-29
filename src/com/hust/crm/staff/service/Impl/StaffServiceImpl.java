package com.hust.crm.staff.service.Impl;

import java.util.List;

import org.junit.Test;

import com.hust.crm.staff.dao.StaffDao;
import com.hust.crm.staff.domain.CrmStaff;
import com.hust.crm.staff.service.StaffService;
import com.hust.crm.utils.MyStringUtils;

public class StaffServiceImpl implements StaffService {
	private StaffDao staffDao;
	public StaffDao getStaffDao() {
		return staffDao;
	}
	public void setStaffDao(StaffDao staffDao) {
		this.staffDao = staffDao;
	}
	@Override
	public CrmStaff login(CrmStaff staff) {
		// TODO Auto-generated method stub
		String loginPwd=MyStringUtils.getMD5Value(staff.getLoginPwd());
		return staffDao.find(staff.getLoginName(), loginPwd);
	}
	@Override
	public List<CrmStaff> findAllStaff() {
		// TODO Auto-generated method stub
		return staffDao.findAll();
	}
	
	@Override
	public CrmStaff findById(String staffId) {
		// TODO Auto-generated method stub
		return staffDao.findById(staffId);
	}	
	
	@Test
	@Override
	public boolean updatePassword(CrmStaff crmStaff) {
		String loginPwd=MyStringUtils.getMD5Value(crmStaff.getLoginPwd());
		crmStaff.setLoginPwd(loginPwd);
		return staffDao.updatePassword(crmStaff);
	}

}
