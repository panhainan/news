package com.phn.dao.impl;

import com.phn.dao.IAdminDao;
import com.phn.po.Admin;

/**
 * @author phn
 * @TODO This class is used to implement the interface IAdminDao
 * @date 2015-4-25
 */
public class AdminDaoImpl extends JDBCDaoSupport<Admin> implements IAdminDao {

	public Admin getByName(String adminName) {
		String getByNameSql = "select * from t_admin where adminName = ?";
		return super.executeGet(getByNameSql, Admin.class, adminName);
	}
}
