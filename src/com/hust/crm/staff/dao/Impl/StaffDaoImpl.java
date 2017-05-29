package com.hust.crm.staff.dao.Impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hust.crm.staff.dao.StaffDao;
import com.hust.crm.staff.domain.CrmStaff;

public class StaffDaoImpl extends HibernateDaoSupport implements StaffDao {

	@Override
	public CrmStaff find(String loginName, String loginPwd) {
		List<CrmStaff> allStaff=this.getHibernateTemplate().find("from CrmStaff where loginName=? and loginPwd=?", loginName,loginPwd);
		if(allStaff.size()==1){
			return allStaff.get(0);
		}
		return null;
	}

	@Override
	public List<CrmStaff> findAll() {
		// TODO Auto-generated method stub
		List<CrmStaff> allStaff=this.getHibernateTemplate().find("from CrmStaff where 1=1");
		return allStaff;		
	}

	@Override
	public CrmStaff findById(String staffId) {
		// TODO Auto-generated method stub
		
		return this.getHibernateTemplate().get(CrmStaff.class, staffId);	
	}

	@Override
	public boolean updatePassword(CrmStaff crmStaff) {
			try{
			this.getHibernateTemplate().update(crmStaff);//update(crmStaff);
			return true;
			}
			catch(Exception e){
				return false;
			}
		
	}

	

}
