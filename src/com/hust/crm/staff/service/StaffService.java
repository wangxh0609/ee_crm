package com.hust.crm.staff.service;

import java.util.List;

import com.hust.crm.staff.domain.CrmStaff;

public interface StaffService {
	public CrmStaff login(CrmStaff staff);
	public List<CrmStaff> findAllStaff();
	public CrmStaff findById(String staffId);
	public boolean updatePassword(CrmStaff crmStaff);
}
