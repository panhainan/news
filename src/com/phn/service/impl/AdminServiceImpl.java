package com.phn.service.impl;

import com.phn.dao.IAdminDao;
import com.phn.dao.impl.AdminDaoImpl;
import com.phn.po.Admin;
import com.phn.service.IAdminService;

/**
 * @author phn
 * @TODO 
 * @date 2015-4-29
 */
public class AdminServiceImpl implements IAdminService {
	IAdminDao adminDao = new AdminDaoImpl();
	public boolean getByName(Admin admin) {
		Admin adminTemp = null;
		adminTemp = adminDao.getByName(admin.getAdminName());
		System.out.println(adminTemp);
		if(adminTemp!=null && admin.getAdminPass().equals(adminTemp.getAdminPass())){
			return true;
		}
		return false;
	}

}
